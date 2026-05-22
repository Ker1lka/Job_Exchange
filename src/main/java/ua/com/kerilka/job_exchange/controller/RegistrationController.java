package ua.com.kerilka.job_exchange.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.Roles;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.service.CandidatesService;
import ua.com.kerilka.job_exchange.service.CompanyService;
import ua.com.kerilka.job_exchange.service.RoleService;
import ua.com.kerilka.job_exchange.service.UserService;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final RoleService roleService;
    private final UserService userService;
    private final CandidatesService candidatesService;
    private final CompanyService companyService;


    // Реєстрація нового кандидата (безробітного)
    @GetMapping("/registration/candidate")
    public String registrationCandidate(){
        return "registration-candidates";
    }

    @PostMapping("/registration/candidate")
    public String saveNewCandidateToDB(@Valid Users user,
                                       BindingResult bindingResult,
                                       @Valid Candidates profile,
                                       BindingResult bindingResult2,
                                       RedirectAttributes redirectAttributes) {
        if (userService.getUserFromDB(user.getUsername())) {
            redirectAttributes.addAttribute("message", "Username already exists");
            return "redirect:/registration/candidate";
        }

        if (bindingResult.hasErrors() || bindingResult2.hasErrors()) {return "registration-candidates";}

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Users userById = userService.saveNewUser(user);

        Roles candidateRole = roleService.findByName("ROLE_candidate");
        userById.setRoles(Collections.singleton(candidateRole));
        profile.setUser(userById);
        candidatesService.save(profile);

        return "redirect:/login";
    }

    // Реєстрація нової компанії (роботодавця)
    @GetMapping("/registration/company")
    public String registrationCompany(){
        return "registration-company";
    }

    @PostMapping("/registration/company")
    public String saveNewCompanyToDB(@Valid Users user,
                                     BindingResult bindingResult,
                                     @Valid Company profile,
                                     BindingResult bindingResult2,
                                     RedirectAttributes redirectAttributes) {
        if (userService.getUserFromDB(user.getUsername())) {
            redirectAttributes.addAttribute("message", "Username already exists");
            return "redirect:/registration/company";
        }

        if (bindingResult.hasErrors() || bindingResult2.hasErrors()) {return "registration-company";}

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Users userById = userService.saveNewUser(user);

        Roles candidateCompany = roleService.findByName("ROLE_company");
        userById.setRoles(Collections.singleton(candidateCompany));
        profile.setUser(userById);
        companyService.save(profile);

        return "redirect:/login";
    }
}
