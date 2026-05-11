package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.kerilka.job_exchange.entity.ProfileHasVacancy;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.service.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class VacancyController {

    private final ProfileHasVacancyService profileHasVacancyService;
    private final ProfilesService profileService;
    private final VacancyService vacancyService;
    private final CompanyService companyService;
    private final UserService userService;
    private final ProfileHasVacancyService phvService;

    @GetMapping("/")
    public String showAllVacancies(Model model) {
        model.addAttribute("vacancies", vacancyService.findAllVacancies());
        return "vacancies-list";
    }

    @GetMapping("/vacancy/{id}")
    public String showVacancyDetails(@PathVariable Long id, Model model) {
        model.addAttribute("vacancy", vacancyService.findByIdVacancy(id));
        return "vacancy-details";
    }

    @GetMapping("/vacancy/create")
    public String showCreateForm(Model model) {
        model.addAttribute("companies", companyService.findAllCompanies());
        model.addAttribute("create_vacancy", vacancyService.findAllVacancies());
        return "vacancy-create";
    }
    @GetMapping("/thanks")
    public String showSuccessPage() {
        return "thanks"; // Назва файлу .ftl
    }


    @PostMapping("/vacancies/create")
    public String createVacancy(@ModelAttribute Vacancy vacancy) {
        // Spring автоматично створює об'єкт Vacancy з даних форми
        vacancyService.save(vacancy);

        // Після збереження перенаправляємо на список всіх вакансій
        return "redirect:/";
    }
    @PostMapping("/vacancies/apply")
    public String applyForVacancy(@RequestParam("vacancyId") Long vacancyId,
                                  Principal principal) {

        // 1. Отримуємо дані про поточного кандидата
        Users currentUser = userService.findUserByUsername(principal.getName());
        Profiles currentProfile = profileService.findByUser(currentUser);

        // 2. Викликаємо метод нашого профільного сервісу
        phvService.apply(currentProfile, vacancyId);

        // 3. Редірект на GetMapping "/thanks"
        return "redirect:/thanks";
    }
}
