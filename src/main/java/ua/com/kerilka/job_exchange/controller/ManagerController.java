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

    // Сторінка 1: Управління всіма вакансіями платформи
    @GetMapping("/vacancies")
    public String manageVacancies(Model model) {
        model.addAttribute("vacanciesList", vacancyService.findAllVacancies());
        return "manager-vacancies";
    }

    // Сторінка 2: База Кандидатів та Компаній
    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("candidatesList", candidatesService.findAllCandidates());
        model.addAttribute("companiesList", companyService.findAllCompanies());
        return "manager-users";
    }

    // Сторінка 3: Модерація журналів відгуків
    @GetMapping("/applications")
    public String manageApplications(Model model) {
        model.addAttribute("applicationsList", jobApplicationService.findAllApplications());
        return "manager-applications";
    }

    // --- ДІЇ З ВАКАНСІЯМИ ---
    @PostMapping("/vacancies/delete/{id}")
    public String deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteVacancyById(id);
        return "redirect:/manager/vacancies?deleted=true";
    }

    @PostMapping("/vacancies/edit/{id}")
    public String editVacancy(@PathVariable Long id, @RequestParam String position, @RequestParam Double salary) {
        Vacancy vacancy = vacancyService.findByIdVacancy(id);
        if (vacancy != null) {
            vacancy.setPosition(position);
            vacancy.setSalary(salary);
            vacancyService.save(vacancy);
        }
        return "redirect:/manager/vacancies?updated=true";
    }

    // --- ДІЇ З КАНДИДАТАМИ ---
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

    @Transactional
    @PostMapping("/candidates/delete/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        Candidates candidate = candidatesService.findById(id);

        if (candidate != null) {
            // 1. Видаляємо всі відгуки, де фігурує цей кандидат
            jobApplicationService.deleteByCandidate(candidate);

            // 2. Зберігаємо посилання на юзера в окрему змінну перед видаленням кандидата
            Users user = candidate.getUser();

            // 3. Видаляємо спочатку КАНДИДАТА. Зв'язок розірвано!
            candidatesService.deleteCandidate(candidate);

            // 4. Тепер спокійно видаляємо користувача, на якого більше ніхто не посилається
            if (user != null) {
                userService.deleteUser(user);
            }
        }
        return "redirect:/manager/users?success=candidate_deleted";
    }

    // --- ДІЇ З КОМПАНІЯМИ ---
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

    @Transactional
    @PostMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        Company company = companyService.findById(id);

        if (company != null) {
            // 1. Знаходимо та видаляємо всі вакансії та їхні відгуки
            List<Vacancy> vacancies = vacancyService.findByCompany(company);
            for (Vacancy vacancy : vacancies) {
                jobApplicationService.deleteByVacancy(vacancy);
                vacancyService.deleteVacancyById(vacancy.getId());
            }

            // 2. Запам'ятовуємо користувача
            Users user = company.getUser();

            // 3. Видаляємо КОМПАНІЮ
            companyService.deleteCompany(company);

            // 4. Видаляємо юзера
            if (user != null) {
                userService.deleteUser(user);
            }
        }
        return "redirect:/manager/users?success=company_deleted";
    }

    // --- ДІЇ З ВІДГУКАМИ ---
    @PostMapping("/applications/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        jobApplicationService.deleteById(id);
        return "redirect:/manager/applications?success=deleted";
    }
}