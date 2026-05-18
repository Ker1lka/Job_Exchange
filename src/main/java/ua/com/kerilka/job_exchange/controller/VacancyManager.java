package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.ProfileHasVacancy;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.service.CompanyService;
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

    @PostMapping("/updateVacancy")
    public String updateVacancyFromManager(Model model,
                                           @RequestParam(name = "id") Long id,
                                           @RequestParam(name = "position") String position,
                                           @RequestParam(name = "salary") Double salary,
                                           @RequestParam(name = "requirements") String requirements){
        Vacancy vacancy = new Vacancy();
        vacancy.setId(id);
        vacancy.setPosition(position);
        vacancy.setSalary(salary);
        vacancy.setRequirements(requirements);

        vacancyService.updateVacancy(vacancy);

        return "redirect:/vacancy-manager";
    }

    @PostMapping("/deleteVacancy")
    public String deleteVacancyFromManager(Model model,
                                           @RequestParam(name = "id") Long id){
        vacancyService.deleteVacancyById(id);

        return "redirect:/vacancy-manager";
    }


    @GetMapping("/phv-manager")
    public String getPHVPageForManager(Model model){
        model.addAttribute("phv", phvService.findAllProfilesHasVacancies());

        return "phv-manager";
    }


    @PostMapping("/updatePHV")
    public String updatePHVFromManager(Model model,
                                       @RequestParam(name = "id") Long id,
                                       @RequestParam(name = "status") String status,
                                       @RequestParam(name = "profileId") Profiles profileId,
                                       @RequestParam(name = "vacancyId")Vacancy vacancyId){
        ProfileHasVacancy phv = new ProfileHasVacancy();
        phv.setId(id);
        phv.setProfile(profileId);
        phv.setVacancy(vacancyId);
        phv.setStatus(status);

        phvService.updatePHV(phv);

        return "redirect:/phv-manager";
    }

    @PostMapping("/deletePHV")
    public String deletePHVFromManager(Model model,
                                           @RequestParam(name = "id") Long id){
        phvService.deletePHVById(id);

        return "redirect:/phv-manager";
    }
}
