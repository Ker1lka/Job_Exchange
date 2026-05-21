package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.service.*;
import ua.com.kerilka.job_exchange.service.CandidatesService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class VacancyController {
    private final CandidatesService candidatesService;
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
        return "thanks";
    }


    @PostMapping("/vacancies/create")
    public String createVacancy(@ModelAttribute Vacancy vacancy) {
        vacancyService.save(vacancy);

        return "redirect:/";
    }
    @PostMapping("/vacancies/apply")
    public String applyForVacancy(@RequestParam("vacancyId") Long vacancyId,
                                  Principal principal) {

        Users currentUser = userService.findUserByUsername(principal.getName());
        Candidates currentCandidate = candidatesService.findByUser(currentUser);

        phvService.apply(currentCandidate, vacancyId);

        return "redirect:/thanks";
    }
}
