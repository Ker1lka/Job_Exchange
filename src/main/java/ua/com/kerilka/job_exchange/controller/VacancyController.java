package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.JobApplication;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.service.*;
import ua.com.kerilka.job_exchange.service.CandidatesService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final CandidatesService candidateService;
    private final UserService userService;
    private final JobApplicationService jobApplicationService;

    // Перегляд списку та пошук активних вакансій кандидатом
    @GetMapping("/candidate/vacancies")
    public String listAllVacancies(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Vacancy> vacanciesList;

        if (search != null && !search.trim().isEmpty()) {vacanciesList = vacancyService.searchVacanciesByPosition(search);}
        else {vacanciesList = vacancyService.findActiveVacancies();}

        model.addAttribute("vacanciesList", vacanciesList);

        return "candidate-vacancies-list";
    }

    // Редагування вакансії компанією
    @GetMapping("/profile/company/vacancies/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Vacancy vacancy = vacancyService.findByIdVacancy(id);
        model.addAttribute("vacancy", vacancy);
        return "edit-vacancy";
    }

    @PostMapping("/profile/company/vacancies/edit/{id}")
    public String processEditVacancy(@PathVariable Long id,
                                     @RequestParam String position,
                                     @RequestParam(required = false) Double salary,
                                     @RequestParam String requirements,
                                     @RequestParam String conditions,
                                     @RequestParam String housingConditions) {
        Vacancy vacancy = vacancyService.findByIdVacancy(id);

        vacancy.setPosition(position);
        vacancy.setSalary(salary);
        vacancy.setRequirements(requirements);
        vacancy.setConditions(conditions);
        vacancy.setHousingConditions(housingConditions);

        vacancyService.save(vacancy);

        return "redirect:/profile/company/vacancies";
    }

    // Детальний перегляд вакансії кандидатом із перевіркою статусу відгуку
    @GetMapping("/candidate/vacancies/{id}")
    public String showVacancyDetails(@PathVariable Long id, Principal principal, Model model) {
        Vacancy vacancy = vacancyService.findByIdVacancy(id);

        Users user = userService.findUserByUsername(principal.getName());
        Candidates candidate = candidateService.findByUser(user);

        JobApplication application = jobApplicationService.findByCandidateAndVacancy(candidate, vacancy);

        model.addAttribute("vacancy", vacancy);
        model.addAttribute("application", application);

        return "vacancy-details";
    }

    // Надсилання відгуку на вакансію кандидатом (або автоматичне прийняття інвайту від фірми)
    @PostMapping("/candidate/vacancies/apply/{vacancyId}")
    public String applyForVacancy(@PathVariable Long vacancyId, Principal principal) {

        Users user = userService.findUserByUsername(principal.getName());
        Candidates candidate = candidateService.findByUser(user);
        Vacancy vacancy = vacancyService.findByIdVacancy(vacancyId);

        JobApplication existingApp = jobApplicationService.findByCandidateAndVacancy(candidate, vacancy);

        if (existingApp != null) {
            if ("COMPANY".equals(existingApp.getInitiatedBy()) && "PENDING".equals(existingApp.getStatus())) {
                existingApp.setStatus("ACCEPTED");
                jobApplicationService.save(existingApp);
            }
        } else {
            JobApplication application = new JobApplication();
            application.setCandidate(candidate);
            application.setVacancy(vacancy);
            application.setInitiatedBy("CANDIDATE");
            application.setStatus("PENDING");

            jobApplicationService.save(application);
        }
        return "redirect:/profile/candidate/applications";
    }
}
