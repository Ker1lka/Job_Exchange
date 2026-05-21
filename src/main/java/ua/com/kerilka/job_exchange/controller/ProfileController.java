package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.JobApplication;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.service.CandidatesService;
import ua.com.kerilka.job_exchange.service.CompanyService;
import ua.com.kerilka.job_exchange.service.JobApplicationService;
import ua.com.kerilka.job_exchange.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final CandidatesService candidatesService;
    private final CompanyService companyService;
    private final JobApplicationService jobApplicationService;

    // Спільна точка входу для розподілу користувачів за ролями
    @GetMapping("/profile")
    public String profileGateway(Principal principal) {
        Users user = userService.findUserByUsername(principal.getName());

        boolean isCandidate = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_CANDIDATE"));

        if (isCandidate) {
            return "redirect:/profile/candidate";
        } else {
            return "redirect:/profile/company";
        }
    }

    // Сторінка власного профілю КАНДИДАТА
    @GetMapping("/profile/candidate")
    public String showMyCandidateProfile(Principal principal, Model model) {
        Users user = userService.findUserByUsername(principal.getName());
        Candidates candidate = candidatesService.findByUser(user);

        model.addAttribute("candidate", candidate);
        return "my-candidate-profile";
    }

    // Сторінка власного профілю КОМПАНІЇ
    @GetMapping("/profile/company")
    public String showMyCompanyProfile(Principal principal, Model model) {
        Users user = userService.findUserByUsername(principal.getName());
        Company company = companyService.findByUser(user);

        List<JobApplication> sentOffers = jobApplicationService.findAllByCompany(company);

        model.addAttribute("company", company);
        model.addAttribute("sentOffers", sentOffers);

        return "my-company-profile";
    }
}