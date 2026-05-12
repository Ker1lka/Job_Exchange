package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.service.ProfileHasVacancyService;
import ua.com.kerilka.job_exchange.service.VacancyService;

@Controller
@RequiredArgsConstructor
public class VacancyManager {

    private final VacancyService vacancyService;
    private final ProfileHasVacancyService phvService;

    @GetMapping("/vacancy-manager")
    public String getVacancyPageForManager(Model model){

        model.addAttribute("vacancies", vacancyService.findAllVacancies());

        return  "vacancy-manager";
    }

    @GetMapping("/phv-manager")
    public String getPHVPageForManager(Model model){
        model.addAttribute("phv", phvService.findAllProfilesHasVacancies());

        return "phv-manager";
    }

    @PostMapping("/updateVacancy")
    public String updateCompanyFromManager(Model model,
                                           @RequestParam(name = "id") Long id,
                                           @RequestParam(name = "name") String name,
                                           @RequestParam(name = "description") String description,
                                           @RequestParam(name = "contactInfo") String contactInfo,
                                           @RequestParam(name = "address") String address){



        return "redirect:/company-manager";
    }



}
