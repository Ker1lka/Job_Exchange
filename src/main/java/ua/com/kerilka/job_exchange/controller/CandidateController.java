package ua.com.kerilka.job_exchange.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.JobApplication;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.service.CandidatesService;
import ua.com.kerilka.job_exchange.service.JobApplicationService;
import ua.com.kerilka.job_exchange.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CandidateController {
    private final CandidatesService candidatesService;
    private final UserService userService;
    private final JobApplicationService jobApplicationService;

    // Перегляд усіх заявок та запрошень кандидата
    @GetMapping("/profile/candidate/applications")
    public String showCandidateApplications(Principal principal, Model model) {
        Users user = userService.findUserByUsername(principal.getName());
        Candidates candidate = candidatesService.findByUser(user);
        List<JobApplication> applications = jobApplicationService.findByCandidate(candidate);

        model.addAttribute("applicationsList", applications);
        return "candidate-applications";
    }

    // Редагування профілю кандидата
    @GetMapping("/profile/candidate/edit")
    public String showEditPage(Principal principal, Model model) {
        Users user = userService.findUserByUsername(principal.getName());
        Candidates candidate = candidatesService.findByUser(user);

        model.addAttribute("candidate", candidate);
        return "edit-candidate-profile";
    }

    @PostMapping("/profile/candidate/edit")
    public String updateCandidateProfile(@ModelAttribute Candidates formData, Principal principal) {
        Users user = userService.findUserByUsername(principal.getName());
        Candidates existingCandidate = candidatesService.findByUser(user);

        existingCandidate.setFirstName(formData.getFirstName());
        existingCandidate.setLastName(formData.getLastName());
        existingCandidate.setMiddleName(formData.getMiddleName());
        existingCandidate.setContactInfo(formData.getContactInfo());
        existingCandidate.setFamilyStatus(formData.getFamilyStatus());
        existingCandidate.setHousingConditions(formData.getHousingConditions());
        existingCandidate.setProfession(formData.getProfession());
        existingCandidate.setLastJobPlace(formData.getLastJobPlace());
        existingCandidate.setLastJobPosition(formData.getLastJobPosition());
        existingCandidate.setLeavingReason(formData.getLeavingReason());
        existingCandidate.setInstitution(formData.getInstitution());
        existingCandidate.setSpecialization(formData.getSpecialization());
        existingCandidate.setDegree(formData.getDegree());
        existingCandidate.setEducationYears(formData.getEducationYears());
        existingCandidate.setJobRequirements(formData.getJobRequirements());

        candidatesService.save(existingCandidate);

        return "redirect:/profile/candidate";
    }

    // Прийняття або відхилення запрошення від компанії
    @PostMapping("/profile/candidate/applications/{id}/accept")
    public String acceptApplication(@PathVariable Long id) {
        JobApplication app = jobApplicationService.findById(id);
        if (app != null) {
            app.setStatus("ACCEPTED");
            jobApplicationService.save(app);
        }
        return "redirect:/profile/candidate/applications";
    }

    @PostMapping("/profile/candidate/applications/{id}/reject")
    public String rejectApplication(@PathVariable Long id) {
        JobApplication app = jobApplicationService.findById(id);
        if (app != null) {
            app.setStatus("REJECTED");
            jobApplicationService.save(app);
        }
        return "redirect:/profile/candidate/applications";
    }

    // Повне видалення профілю кандидата та його акаунту
    @PostMapping("/profile/candidate/delete")
    public String deleteCandidateProfile(Principal principal, HttpServletRequest request) {
        Users user = userService.findUserByUsername(principal.getName());
        userService.deleteUser(user);

        // Розлогінюємо (очищаємо сесію)
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}

