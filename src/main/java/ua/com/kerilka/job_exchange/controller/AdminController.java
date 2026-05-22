package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.kerilka.job_exchange.entity.Roles;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.service.AboutUsService;
import ua.com.kerilka.job_exchange.service.RoleService;
import ua.com.kerilka.job_exchange.service.UserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AboutUsService aboutUsService;
    private final UserService userService;
    private final RoleService roleService;

    //Виведення всіх користувачів
    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("allUsers", userService.findAllUsers());
        return "admin-users";
    }

    //Зміна ролі користувача
    @PostMapping("/users/{id}/role")
    public String changeRole(@PathVariable Long id, @RequestParam String roleName) {
        Users user = userService.findById(id);
        if (user != null) {
            Roles role = roleService.findByName(roleName);
            if (role != null) {
                Set<Roles> newRoles = new HashSet<>();
                newRoles.add(role);
                user.setRoles(newRoles);
                userService.save(user);
            }
        }
        return "redirect:/admin/users?success=role";
    }

    // Зміна логіна користувача
    @PostMapping("/users/{id}/username")
    public String changeUsername(@PathVariable Long id, @RequestParam String newUsername) {
        if (userService.getUserFromDB(newUsername)) {
            return "redirect:/admin/users?error=username_exists";
        }
        Users user = userService.findById(id);
        if (user != null) {
            user.setUsername(newUsername);
            userService.save(user);
        }
        return "redirect:/admin/users?success=username";
    }

    // Керування сторінкою "Про нас"
    @GetMapping("/settings")
    public String adminSettings(Model model) {
        model.addAttribute("currentAboutText", aboutUsService.getAboutText());
        return "admin-settings";
    }

    @PostMapping("/settings/about")
    public String updateAboutText(@RequestParam String newText) {
        aboutUsService.saveAboutText(newText);
        return "redirect:/admin/settings?success=true";
    }
}
