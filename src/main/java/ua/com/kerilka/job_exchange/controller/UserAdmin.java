package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.kerilka.job_exchange.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserAdmin {
    private final UserService userService;

    @GetMapping("/users-admin")
    public String getUserForAdmin(Model model){
        model.addAttribute("users", userService.findAllUsers());

        return "users-admin";
    }
    @GetMapping("/roles-admin")
    public String getRolesForAdmin(Model model){
        model.addAttribute("roles", userService.findAllRoles());

        return "roles-admin";
    }
}
