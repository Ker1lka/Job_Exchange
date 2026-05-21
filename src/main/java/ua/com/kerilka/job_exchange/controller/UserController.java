package ua.com.kerilka.job_exchange.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Roles;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.service.CandidatesService;
import ua.com.kerilka.job_exchange.service.UserService;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CandidatesService candidatesService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model,
                               @RequestParam(name = "message", defaultValue = " ") String message) {
        model.addAttribute("users", new Users());
        model.addAttribute("candidates", new Candidates());
        model.addAttribute("info", message);
        return "registration";
    }

    @PostMapping("/registration")
    public String saveNewUserToDB(@Valid Users user,
                                  BindingResult bindingResult,
                                  @Valid Candidates profile,
                                  BindingResult bindingResult2,
                                  RedirectAttributes redirectAttributes) {
        if (userService.getUserFromDB(user.getUsername())) {
            redirectAttributes.addAttribute("message", "Username is already exists");
            return "redirect:/registration";
        }
        if (bindingResult.hasErrors() || bindingResult2.hasErrors()) return "registration";

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Users userById = userService.saveNewUser(user);

        userById.setRoles(Collections.singleton(new Roles(1L, "ROLE_user")));
        profile.setUser(userById);

        candidatesService.save(profile);

        return "redirect:/login";
    }
}