package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.service.VacancyService;

@Controller
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

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
        model.addAttribute("create_vacancy", vacancyService.findAllVacancies());
        return "vacancy-create";
    }
}
