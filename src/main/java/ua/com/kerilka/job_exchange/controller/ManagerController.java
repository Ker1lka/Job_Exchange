package ua.com.kerilka.job_exchange.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.service.*;

import java.util.List;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final VacancyService vacancyService;
    private final CandidatesService candidatesService;
    private final CompanyService companyService;
    private final UserService userService;
    private final JobApplicationService jobApplicationService;

    // Сторінка керування вакансіями
    @GetMapping("/vacancies")
    public String manageVacancies(Model model) {
        model.addAttribute("vacanciesList", vacancyService.findAllVacancies());
        return "manager-vacancies";
    }

    // Сторінка керування акаунтами безробітних та фірм
    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("candidatesList", candidatesService.findAllCandidates());
        model.addAttribute("companiesList", companyService.findAllCompanies());
        return "manager-users";
    }

    // Сторінка модерації журналу відгуків і запрошень
    @GetMapping("/applications")
    public String manageApplications(Model model) {
        model.addAttribute("applicationsList", jobApplicationService.findAllApplications());
        return "manager-applications";
    }

    // Редагування вакансії
    @PostMapping("/vacancies/edit/{id}")
    public String editVacancy(@PathVariable Long id,
                              @RequestParam String position,
                              @RequestParam Double salary,
                              @RequestParam String housingConditions,
                              @RequestParam String requirements,
                              @RequestParam String conditions) {
        Vacancy vacancy = vacancyService.findByIdVacancy(id);
        if (vacancy != null) {
            vacancy.setPosition(position);
            vacancy.setSalary(salary);
            vacancy.setHousingConditions(housingConditions);
            vacancy.setRequirements(requirements);
            vacancy.setConditions(conditions);

            vacancyService.save(vacancy);
        }
        return "redirect:/manager/vacancies?updated=true";
    }
    //Видалення вакансії
    @PostMapping("/vacancies/delete/{id}")
    public String deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteVacancyById(id);
        return "redirect:/manager/vacancies?deleted=true";
    }

    // Редагування анкети кандидата
    @PostMapping("/candidates/edit/{id}")
    public String editCandidate(@PathVariable Long id,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String profession) {
        Candidates candidate = candidatesService.findById(id);
        if (candidate != null) {
            candidate.setFirstName(firstName);
            candidate.setLastName(lastName);
            candidate.setProfession(profession);
            candidatesService.save(candidate);
        }
        return "redirect:/manager/users?success=candidate_updated";
    }

    // Повне видалення профілю та акаунту кандидата (з очищенням відгуків)
    @Transactional
    @PostMapping("/candidates/delete/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        Candidates candidate = candidatesService.findById(id);

        if (candidate != null) {
            jobApplicationService.deleteByCandidate(candidate);
            Users user = candidate.getUser();
            candidatesService.deleteCandidate(candidate);
            if (user != null) {userService.deleteUser(user);}
        }
        return "redirect:/manager/users?success=candidate_deleted";
    }

    // Редагування даних компанії
    @PostMapping("/companies/edit/{id}")
    public String editCompany(@PathVariable Long id, @RequestParam String name, @RequestParam String address) {
        Company company = companyService.findById(id);
        if (company != null) {
            company.setName(name);
            company.setAddress(address);
            companyService.save(company);
        }
        return "redirect:/manager/users?success=company_updated";
    }

    // Повне видалення компанії з її вакансіями, відгуками та акаунтом
    @Transactional
    @PostMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        Company company = companyService.findById(id);

        if (company != null) {
            List<Vacancy> vacancies = vacancyService.findByCompany(company);
            for (Vacancy vacancy : vacancies) {
                jobApplicationService.deleteByVacancy(vacancy);
                vacancyService.deleteVacancyById(vacancy.getId());
            }
            Users user = company.getUser();
            companyService.deleteCompany(company);
            if (user != null) {userService.deleteUser(user);}
        }

        return "redirect:/manager/users?success=company_deleted";
    }

    // Видалення запису із журналу взаємодії (відгуку/запрошення)
    @PostMapping("/applications/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        jobApplicationService.deleteById(id);
        return "redirect:/manager/applications?success=deleted";
    }
}