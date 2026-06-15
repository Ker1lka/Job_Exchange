package ua.com.kerilka.job_exchange.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kerilka.job_exchange.entity.*;
import ua.com.kerilka.job_exchange.service.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private static final String REDIRECT_PROFILE_COMPANY = "redirect:/profile/company";

    private final UserService userService;
    private final CandidatesService candidatesService;
    private final CompanyService companyService;
    private final VacancyService vacancyService;
    private final JobApplicationService jobApplicationService;

    // Перегляд списку всіх кандидатів
    @GetMapping("/company/candidates")
    public String listAllCandidates(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Candidates> candidatesList;

        if (search != null && !search.trim().isEmpty()) {candidatesList = candidatesService.searchCandidatesByProfession(search);}
        else {candidatesList = candidatesService.findAllCandidates();}

        model.addAttribute("candidatesList", candidatesList);

        return "company-candidates-list";
    }

    // Перегляд конкретного профілю кандидата з можливістю запрошення
    @GetMapping("/company/candidates/{id}")
    public String viewCandidateDetails(@PathVariable Long id, Principal principal, Model model) {
        Candidates candidate = candidatesService.findById(id);
        Company currentCompany = companyService.findByUser(userService.findUserByUsername(principal.getName()));
        List<Vacancy> myVacancies = vacancyService.findByCompany(currentCompany);

        model.addAttribute("candidate", candidate);
        model.addAttribute("myVacancies", myVacancies);

        return "candidate-details";
    }

    // Надсилання пропозиції роботи (запрошення) кандидату
    @PostMapping("/company/candidates/{candidateId}/invite")
    public String inviteCandidate(@PathVariable Long candidateId,
                                  @RequestParam Long vacancyId) {
        jobApplicationService.createCompanyInvitation(candidateId, vacancyId);
        return "redirect:/company/candidates/" + candidateId + "?success=true";
    }


    // Редагування профілю компанії
    @GetMapping("/profile/company/edit")
    public String showEditForm(Principal principal, Model model) {
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);

        model.addAttribute("company", company);
        return "edit-company-profile";
    }

    @PostMapping("/profile/company/edit")
    public String saveCompanyProfile(Principal principal,
                                     @RequestParam String companyName,
                                     @RequestParam String address,
                                     @RequestParam String contacts,
                                     @RequestParam String description) {
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);

        company.setName(companyName);
        company.setAddress(address);
        company.setContactInfo(contacts);
        company.setDescription(description);

        companyService.save(company);

        return REDIRECT_PROFILE_COMPANY;
    }

    // Перегляд власних вакансій компанії
    @GetMapping("/profile/company/vacancies")
    public String showMyVacancies(Principal principal, Model model) {
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);
        List<Vacancy> myVacancies = vacancyService.findByCompany(company);

        model.addAttribute("myVacancies", myVacancies);

        return "my-vacancies-list";
    }

    // Переведення вакансії в архів (закриття після працевлаштування)
    @PostMapping("/profile/company/vacancies/close/{id}")
    public String closeVacancy(@PathVariable Long id, Principal principal) {
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);
        Vacancy vacancy = vacancyService.findByIdVacancy(id);

        if (vacancy.getCompany().getId().equals(company.getId())) {
            vacancy.setClosed(true);
            vacancyService.save(vacancy);}

        return "redirect:/profile/company/vacancies";
    }

    // Створення нової вакансії фірми
    @GetMapping("/profile/company/vacancies/create")
    public String showCreateForm() {return "create-vacancy";}

    @PostMapping("/profile/company/vacancies/create")
    public String processCreateVacancy(Principal principal,
                                       @RequestParam String position,
                                       @RequestParam(required = false) Double salary,
                                       @RequestParam String requirements,
                                       @RequestParam String conditions,
                                       @RequestParam String housingConditions) {
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);

        Vacancy vacancy = new Vacancy();
        vacancy.setPosition(position);
        vacancy.setSalary(salary);
        vacancy.setRequirements(requirements);
        vacancy.setConditions(conditions);
        vacancy.setHousingConditions(housingConditions);
        vacancy.setCompany(company);
        vacancy.setClosed(false);

        vacancyService.save(vacancy);

        return "redirect:/profile/company/vacancies";
    }

    // Прийняття або відхилення відгуку кандидата на вакансію
    @PostMapping("/profile/company/applications/{id}/accept")
    public String companyAcceptApplication(@PathVariable Long id) {
        JobApplication app = jobApplicationService.findById(id);
        if (app != null) {
            app.setStatus("ACCEPTED");
            jobApplicationService.save(app);
        }
        return REDIRECT_PROFILE_COMPANY;
    }

    @PostMapping("/profile/company/applications/{id}/reject")
    public String companyRejectApplication(@PathVariable Long id) {
        JobApplication app = jobApplicationService.findById(id);
        if (app != null) {
            app.setStatus("REJECTED");
            jobApplicationService.save(app);
        }
        return REDIRECT_PROFILE_COMPANY;
    }

    // Перегляд анкети безробітного через журнал відгуків
    @GetMapping("/profile/company/applications/candidates/{id}")
    public String showCandidateProfileForCompany(@PathVariable Long id, Model model) {
        Candidates candidate = candidatesService.findById(id);
        if (candidate == null) {return "redirect:/profile/company?error=CandidateNotFound";}

        model.addAttribute("candidate", candidate);
        return "company-view-candidate";
    }

    // Повне видалення профілю компанії та її акаунту
    @PostMapping("/profile/company/delete")
    public String deleteCompanyProfile(Principal principal, HttpServletRequest request) {
        Users user = userService.findUserByUsername(principal.getName());
        userService.deleteUser(user);

        // Розлогінюємо (очищаємо сесію)
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {session.invalidate();}

        return "redirect:/";
    }
}