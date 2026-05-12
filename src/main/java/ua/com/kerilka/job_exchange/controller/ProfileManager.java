package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.kerilka.job_exchange.service.ProfilesService;

@Controller
@RequiredArgsConstructor
public class ProfileManager {

    private final ProfilesService profilesService;

    @GetMapping("/profile-manager")
    public String getProfilesPageForManager(Model model){

        model.addAttribute("profiles", profilesService.findAllProfiles());

        return "profile-manager";
    }
}
