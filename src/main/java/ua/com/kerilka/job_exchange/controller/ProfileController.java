package ua.com.kerilka.job_exchange.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.kerilka.job_exchange.service.ProfilesService;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final ProfilesService profilesService;

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
}
