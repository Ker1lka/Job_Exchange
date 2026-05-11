package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.kerilka.job_exchange.entity.ProfileHasVacancy;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.service.CompanyService;
import ua.com.kerilka.job_exchange.service.ProfileHasVacancyService;
import ua.com.kerilka.job_exchange.service.ProfilesService;
import ua.com.kerilka.job_exchange.service.VacancyService;

@Controller
@RequiredArgsConstructor
public class VacancyController {

    private final ProfileHasVacancyService profileHasVacancyService;
    private final ProfilesService profileService;
    private final VacancyService vacancyService;
    private final CompanyService companyService;

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

    @PostMapping("/vacancies/create")
    public String createVacancy(@ModelAttribute Vacancy vacancy) {
        // Spring автоматично створює об'єкт Vacancy з даних форми
        vacancyService.save(vacancy);

        // Після збереження перенаправляємо на список всіх вакансій
        return "redirect:/";
    }
    @PostMapping("/vacancies/apply")
    public String applyToVacancy(@RequestParam Long vacancyId, @RequestParam Long profileId) {
        Profiles profile = profileService.findByIdProfile(profileId);
        Vacancy vacancy = vacancyService.findByIdVacancy(vacancyId);

        if (profile != null && vacancy != null) {
            profileHasVacancyService.apply(profile, vacancy);
        }

        return "redirect:/thanks";
    }
}
