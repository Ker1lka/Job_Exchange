package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.ProfileHasVacancy;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.service.CompanyService;
import ua.com.kerilka.job_exchange.service.ProfileHasVacancyService;
import ua.com.kerilka.job_exchange.service.ProfilesService;
import ua.com.kerilka.job_exchange.service.VacancyService;

import java.util.List;

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
    @GetMapping("/vacancies/my")
    public String showMyVacancies(Model model) {
        // В реальному проекті ми беремо ID з Spring Security сесії
        Long currentProfileId = 1L;

        // Отримуємо вакансії тільки цієї компанії/юзера
        List<Vacancy> myVacancies = vacancyService.findVacanciesByAuthorId(currentProfileId);
        model.addAttribute("vacancies", myVacancies);

        return "my-vacancies";
    }

    // Сторінка відгуків на конкретну вакансію
    @GetMapping("/vacancies/{id}/applications")
    public String showApplications(@PathVariable Long id, Model model) {
        List<ProfileHasVacancy> apps = profileHasVacancyService.getApplicationsForVacancy(id);
        model.addAttribute("applications", apps);
        model.addAttribute("vacancy", vacancyService.findByIdVacancy(id));

        return "vacancy-applications"; // Треба буде створити такий .ftl
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
    public String applyToVacancy(@RequestParam Long vacancyId, @RequestParam Long profileId) {
        Profiles profile = profileService.findByIdProfile(profileId);
        Vacancy vacancy = vacancyService.findByIdVacancy(vacancyId);

        if (profile != null && vacancy != null) {
            profileHasVacancyService.apply(profile, vacancy);
        }

        return "redirect:/thanks";
    }

    @PostMapping("/vacancy/create")
    public String saveVacancy(@ModelAttribute Vacancy vacancy, @RequestParam("companyName") String companyName) {
        // 1. Шукаємо компанію за назвою
        Company company = companyService.findByName(companyName);

        // 2. Якщо не знайшли — створюємо нову
        if (company == null) {
            company = new Company();
            company.setName(companyName);
            // КРИТИЧНО: зберегти і переприсвоїти, щоб отримати ID з бази
            company = companyService.save(company);
        }
        vacancy.setCompany(company);

        // 4. Зберігаємо вакансію
        vacancyService.save(vacancy);

        return "redirect:/";
    }
}
