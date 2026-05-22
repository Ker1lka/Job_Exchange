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

    private final UserService userService;
    private final CandidatesService candidatesService;
    private final CompanyService companyService;
    private final VacancyService vacancyService;
    private final JobApplicationService jobApplicationService;

    // Перегляд списку всіх кандидатів (База безробітних)
    @GetMapping("/company/candidates")
    public String listAllCandidates(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Candidates> candidatesList;

        if (search != null && !search.trim().isEmpty()) {
            candidatesList = candidatesService.searchCandidatesByProfession(search);
        } else {
            // Якщо пошук порожній — виводимо всю базу резюме як зазвичай
            candidatesList = candidatesService.findAllCandidates();
        }

        model.addAttribute("candidatesList", candidatesList);

        return "company-candidates-list";
    }

    // Перегляд конкретного профілю безробітного компанією за ID
    @GetMapping("/company/candidates/{id}")
    public String viewCandidateDetails(@PathVariable Long id, Principal principal, Model model) {
        Candidates candidate = candidatesService.findById(id);
        Company currentCompany = companyService.findByUser(userService.findUserByUsername(principal.getName()));
        List<Vacancy> myVacancies = vacancyService.findByCompany(currentCompany);

        model.addAttribute("candidate", candidate);
        model.addAttribute("myVacancies", myVacancies);

        return "candidate-details";
    }

    // Обробка надсилання пропозиції роботи кандидату
    @PostMapping("/company/candidates/{candidateId}/invite")
    public String inviteCandidate(@PathVariable Long candidateId,
                                  @RequestParam Long vacancyId) {

        jobApplicationService.createCompanyInvitation(candidateId, vacancyId);

        return "redirect:/company/candidates/" + candidateId + "?success=true";
    }


    //Edit Profile Company
    @GetMapping("/profile/company/edit")
    public String showEditForm(Principal principal, Model model) {
        // Отримуємо поточного користувача і його компанію
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);

        model.addAttribute("company", company);
        return "edit-company-profile"; // Назва FTL файлу
    }

    // 2. ОБРОБКА ЗБЕРЕЖЕННЯ ДАНИХ ФОРМИ
    @PostMapping("/profile/company/edit")
    public String saveCompanyProfile(Principal principal,
                                     @RequestParam String companyName,
                                     @RequestParam String address,
                                     @RequestParam String contacts,
                                     @RequestParam String description) {

        // Знаходимо саме ту компанію, яка зараз авторизована
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);

        // Оновлюємо плоскі String поля новими даними з форми
        company.setName(companyName);
        company.setAddress(address);
        company.setContactInfo(contacts);
        company.setDescription(description);

        // Зберігаємо оновлений об'єкт в базу даних
        companyService.save(company);

        // Перенаправляємо назад на сторінку перегляду профілю компанії
        return "redirect:/profile/company";
    }

    //Only my vacancies (created by me)

    @GetMapping("/profile/company/vacancies")
    public String showMyVacancies(Principal principal, Model model) {
        // Знаходимо поточну авторизовану компанію
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);

        // Витягуємо вакансії, що належать суто цій компанії
        List<Vacancy> myVacancies = vacancyService.findByCompany(company);

        model.addAttribute("myVacancies", myVacancies);

        return "my-vacancies-list"; // Назва .ftl файлу
    }

    // 2. Обробка кнопки "Архівувати / Закрити вакансію"
    @PostMapping("/profile/company/vacancies/close/{id}")
    public String closeVacancy(@PathVariable Long id, Principal principal) {
        // Перевірка безпеки: переконуємось, що вакансія належить саме цій компанії
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);

        Vacancy vacancy = vacancyService.findByIdVacancy(id);

        if (vacancy.getCompany().getId().equals(company.getId())) {
            // Змінюємо статус на закриту
            vacancy.setClosed(true);
            vacancyService.save(vacancy);
        }
        // Повертаємо роботодавця на оновлений список його вакансій
        return "redirect:/profile/company/vacancies";
    }


    //Create Vacancy

    @GetMapping("/profile/company/vacancies/create")
    public String showCreateForm() {
        return "create-vacancy"; // Назва вашого FTL файлу
    }

    // 2. ОБРОБКА ДАНИХ ФОРМИ
    @PostMapping("/profile/company/vacancies/create")
    public String processCreateVacancy(Principal principal,
                                       @RequestParam String position,
                                       @RequestParam(required = false) Double salary,
                                       @RequestParam String requirements,
                                       @RequestParam String conditions) {

        // Знаходимо користувача та його компанію
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);

        // Створюємо новий об'єкт вакансії
        Vacancy vacancy = new Vacancy();
        vacancy.setPosition(position);
        vacancy.setSalary(salary);
        vacancy.setRequirements(requirements);
        vacancy.setConditions(conditions);
        vacancy.setCompany(company); // Прив'язуємо до поточної фірми
        vacancy.setClosed(false);    // Нова вакансія завжди активна

        // Зберігаємо в базу
        vacancyService.save(vacancy);

        // Повертаємо на список своїх вакансій
        return "redirect:/profile/company/vacancies";
    }

    //Accept or Reject

    @PostMapping("/profile/company/applications/{id}/accept")
    public String companyAcceptApplication(@PathVariable Long id) {
        JobApplication app = jobApplicationService.findById(id);
        if (app != null) {
            app.setStatus("ACCEPTED");
            jobApplicationService.save(app);
        }
        return "redirect:/profile/company"; // Повертаємо в профіль компанії
    }

    @PostMapping("/profile/company/applications/{id}/reject")
    public String companyRejectApplication(@PathVariable Long id) {
        JobApplication app = jobApplicationService.findById(id);
        if (app != null) {
            app.setStatus("REJECTED");
            jobApplicationService.save(app);
        }
        return "redirect:/profile/company";
    }

    //Деталі кандидата з відгука на вакансію
    @GetMapping("/profile/company/applications/candidates/{id}")
    public String showCandidateProfileForCompany(@PathVariable Long id, Model model) {
        // 1. Знаходимо кандидата за його ID
        Candidates candidate = candidatesService.findById(id); // Переконайся, що такий метод є в сервісі

        if (candidate == null) {
            return "redirect:/profile/company?error=CandidateNotFound";
        }

        model.addAttribute("candidate", candidate);
        return "company-view-candidate"; // Новий шаблон
    }

    //Delete Company Profile

    @PostMapping("/profile/company/delete")
    public String deleteCompanyProfile(Principal principal, HttpServletRequest request) {
        Users user = userService.findUserByUsername(principal.getName());

        // Видаляємо користувача (каскад видалить компанію та її вакансії)
        userService.deleteUser(user);

        // Розлогінюємо
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}