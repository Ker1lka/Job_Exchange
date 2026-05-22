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

    @GetMapping("/candidate/vacancies")
    public String listAllVacancies(@RequestParam(value = "search", required = false) String search, Model model) {
        // Отримуємо всі вакансії (у сервісі можна відфільтрувати тільки активні: isClosed = false)
        List<Vacancy> vacanciesList;

        if (search != null && !search.trim().isEmpty()) {
            // Якщо шукають — викликаємо пошук (можна шукати тільки серед активних)
            vacanciesList = vacancyService.searchVacanciesByPosition(search);
        } else {
            // Якщо пошуковий рядок порожній — повертаємо твій стандартний список
            vacanciesList = vacancyService.findActiveVacancies();
        }

        model.addAttribute("vacanciesList", vacanciesList);

        return "candidate-vacancies-list"; // Назва .ftl файлу
    }


    //Edit Vacancy

    @GetMapping("/profile/company/vacancies/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Vacancy vacancy = vacancyService.findByIdVacancy(id);
        model.addAttribute("vacancy", vacancy);
        return "edit-vacancy"; // Назва нового FTL файлу
    }

    // 2. ОБРОБКА ОНОВЛЕНИХ ДАНИХ
    @PostMapping("/profile/company/vacancies/edit/{id}")
    public String processEditVacancy(@PathVariable Long id,
                                     @RequestParam String position,
                                     @RequestParam(required = false) Double salary,
                                     @RequestParam String requirements,
                                     @RequestParam String conditions) {

        // Знаходимо існуючу вакансію в базі
        Vacancy vacancy = vacancyService.findByIdVacancy(id);

        // Оновлюємо її поля
        vacancy.setPosition(position);
        vacancy.setSalary(salary);
        vacancy.setRequirements(requirements);
        vacancy.setConditions(conditions);

        // Зберігаємо змінений об'єкт
        vacancyService.save(vacancy);

        // Повертаємо компанію назад до її списку вакансій
        return "redirect:/profile/company/vacancies";
    }

    //Vacancy Details
    @GetMapping("/candidate/vacancies/{id}")
    public String showVacancyDetails(@PathVariable Long id, Principal principal, Model model) {
        // 1. Знаходимо вакансію
        Vacancy vacancy = vacancyService.findByIdVacancy(id);

        // 2. Знаходимо поточного кандидата
        Users user = userService.findUserByUsername(principal.getName());
        Candidates candidate = candidateService.findByUser(user);

        // 3. Шукаємо, чи є вже якийсь запис у таблиці job_applications для цієї пари
        JobApplication application = jobApplicationService.findByCandidateAndVacancy(candidate, vacancy);

        model.addAttribute("vacancy", vacancy);
        model.addAttribute("application", application); // Передаємо запис (може бути null)

        return "vacancy-details";
    }

    @PostMapping("/candidate/vacancies/apply/{vacancyId}")
    public String applyForVacancy(@PathVariable Long vacancyId, Principal principal) {

        Users user = userService.findUserByUsername(principal.getName());
        Candidates candidate = candidateService.findByUser(user);
        Vacancy vacancy = vacancyService.findByIdVacancy(vacancyId);

        // [ОСЬ ЦЯ ПЕРЕВІРКА]
        // Шукаємо, чи існує вже якийсь запис (інвайт чи відгук) для цієї пари кандидата і вакансії
        // Тобі знадобиться такий метод у JobApplicationService / Repository
        JobApplication existingApp = jobApplicationService.findByCandidateAndVacancy(candidate, vacancy);

        if (existingApp != null) {
            // Якщо запис вже є, і його створила КОМПАНІЯ, то відгук кандидата
            // автоматично перетворюється на ПРИЙНЯТТЯ пропозиції!
            if ("COMPANY".equals(existingApp.getInitiatedBy()) && "PENDING".equals(existingApp.getStatus())) {
                existingApp.setStatus("ACCEPTED");
                jobApplicationService.save(existingApp);
            }
            // Якщо запис вже був (наприклад, кандидат уже колись відгукувався),
            // ми просто нічого не робимо, щоб не плодити дублі
        } else {
            // Якщо жодних записів немає — створюємо новий чистий відгук
            JobApplication application = new JobApplication();
            application.setCandidate(candidate);
            application.setVacancy(vacancy);
            application.setInitiatedBy("CANDIDATE");
            application.setStatus("PENDING");

            jobApplicationService.save(application);
        }

        return "redirect:/profile/candidate/applications"; // Краще редиректнути одразу сюди, щоб юзер бачив статус
    }
}
