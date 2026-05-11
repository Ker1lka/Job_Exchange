package ua.com.kerilka.job_exchange.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.kerilka.job_exchange.entity.ProfileHasVacancy;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.service.ProfileHasVacancyService;
import ua.com.kerilka.job_exchange.service.ProfilesService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final ProfilesService profilesService;
    private final ProfileHasVacancyService profileHasVacancyService;

    @GetMapping("/profiles/list")
    public String showAllProfiles(Model model) {
        model.addAttribute("profiles", profilesService.findAllProfiles());
        return "profiles-list";
    }

    @GetMapping("/profiles/{id}")
    public String showSingleProfile(@PathVariable Long id, Model model) {
        model.addAttribute("profile", profilesService.findByIdProfile(id));
        return "profiles-view";
    }

    @GetMapping("/manager")
    public String getPageManager() {
        return "manager";
    }

    @GetMapping("/admin")
    public String getPageAdmin(){
        return "admin";
    }

    @GetMapping("/profile/my")
    public String showMyProfile(Model model) {
        // У майбутньому ID буде братися з сесії Spring Security
        Long currentUserId = 1L;

        // 1. Отримуємо дані профілю
        Profiles profile = profilesService.findByIdProfile(currentUserId);

        // 2. Отримуємо історію відгуків (ми вже створили цей метод у сервісі раніше)
        List<ProfileHasVacancy> myApps = profileHasVacancyService.getMyApplications(currentUserId);

        model.addAttribute("profile", profile);
        model.addAttribute("applications", myApps);

        return "my-profile";
    }

}
