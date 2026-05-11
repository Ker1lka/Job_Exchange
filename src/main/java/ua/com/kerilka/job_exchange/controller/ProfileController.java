package ua.com.kerilka.job_exchange.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.kerilka.job_exchange.service.ProfilesService;
import ua.com.kerilka.job_exchange.service.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final ProfilesService profilesService;
    private final UserService userService;

    @GetMapping("/profiles/list")
    public String showAllProfiles(Model model) {
        model.addAttribute("profiles", profilesService.findAllProfiles());
        return "profiles-list";
    }

    @GetMapping("/profiles/my")
    public String showMyProfile(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            Long userId = userService.findUserByUsername(username).getId();
            return "redirect:/profiles/" + userId;
        }
        return "redirect:/login"; // Redirect to login if not authenticated
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
}
