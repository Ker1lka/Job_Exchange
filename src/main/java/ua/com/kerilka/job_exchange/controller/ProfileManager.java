package ua.com.kerilka.job_exchange.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class ProfileManager {

    @GetMapping("/profile-manager")
    public String redirectProfileManager() {
        return "redirect:/candidate-manager";
    }

    @PostMapping("/updateProfile")
    public String redirectUpdateProfile() {
        return "redirect:/candidate-manager";
    }

    @PostMapping("/deleteProfile")
    public String redirectDeleteProfile() {
        return "redirect:/candidate-manager";
    }
}
