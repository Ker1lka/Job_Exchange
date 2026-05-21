package ua.com.kerilka.job_exchange.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.kerilka.job_exchange.service.CandidatesService;
import ua.com.kerilka.job_exchange.service.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CandidateController {
    private final CandidatesService candidatesService;
    private final UserService userService;

    @GetMapping("/candidates/list")
    public String showAllCandidates(Model model) {
        model.addAttribute("candidates", candidatesService.findAllCandidates());
        return "candidates-list";
    }

    @GetMapping("/candidates/my")
    public String showMyCandidate(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            Long userId = userService.findUserByUsername(username).getId();
            return "redirect:/candidates/" + userId;
        }
        return "redirect:/login";
    }

    @GetMapping("/candidates/{id}")
    public String showSingleCandidate(@PathVariable Long id, Model model) {
        model.addAttribute("candidate", candidatesService.findByIdCandidate(id));
        return "candidates-view";
    }


    @GetMapping("/admin")
    public String getPageAdmin(){
        return "admin";
    }

    @GetMapping("/manager")
    public String getPageManager() {
        return "manager";
    }
}

