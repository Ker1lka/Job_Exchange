package ua.com.kerilka.job_exchange.controller;


import lombok.RequiredArgsConstructor;
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


    @GetMapping("/profile/candidate/applications")
    public String showCandidateApplications(Principal principal, Model model) {
        // 1. Знаходимо поточного кандидата
        Users user = userService.findUserByUsername(principal.getName());
        Candidates candidate = candidatesService.findByUser(user);

        // 2. Отримуємо ВСІ його заявки (і відгуки, і запрошення від компаній)
        // Тобі знадобиться метод у репозиторії/сервісі: findByCandidate(candidate)
        List<JobApplication> applications = jobApplicationService.findByCandidate(candidate);

        model.addAttribute("applicationsList", applications);
        return "candidate-applications"; // Назва нового FTL файлу
    }

    //Edit Profile

    @GetMapping("/profile/candidate/edit")
    public String showEditPage(Principal principal, Model model) {
        Users user = userService.findUserByUsername(principal.getName());
        Candidates candidate = candidatesService.findByUser(user);

        model.addAttribute("candidate", candidate);
        return "edit-candidate-profile"; // наш новий HTML/FTL шаблон
    }

    // 2. ОБРОБИТИ ЗБЕРЕЖЕННЯ ДАНИХ
    @PostMapping("/profile/candidate/edit")
    public String updateCandidateProfile(@ModelAttribute Candidates formData, Principal principal) {
        Users user = userService.findUserByUsername(principal.getName());
        Candidates existingCandidate = candidatesService.findByUser(user);

        // 1. Основні та анкетні дані
        existingCandidate.setFirstName(formData.getFirstName());
        existingCandidate.setLastName(formData.getLastName());
        existingCandidate.setMiddleName(formData.getMiddleName());
        existingCandidate.setContactInfo(formData.getContactInfo());
        existingCandidate.setFamilyStatus(formData.getFamilyStatus());
        existingCandidate.setHousingConditions(formData.getHousingConditions());

        // 2. Професія та минула робота (ДОДАНО)
        existingCandidate.setProfession(formData.getProfession());
        existingCandidate.setLastJobPlace(formData.getLastJobPlace());
        existingCandidate.setLastJobPosition(formData.getLastJobPosition());
        existingCandidate.setLeavingReason(formData.getLeavingReason());

        // 3. Освіта
        existingCandidate.setInstitution(formData.getInstitution());
        existingCandidate.setSpecialization(formData.getSpecialization());
        existingCandidate.setDegree(formData.getDegree());
        existingCandidate.setEducationYears(formData.getEducationYears());

        // 4. Вимоги до майбутньої роботи (ДОДАНО)
        existingCandidate.setJobRequirements(formData.getJobRequirements());

        // Зберігаємо оновленого кандидата в БД
        candidatesService.save(existingCandidate);

        return "redirect:/profile/candidate";
    }

    //Accept Or Reject

    @PostMapping("/profile/candidate/applications/{id}/accept")
    public String acceptApplication(@PathVariable Long id) {
        JobApplication app = jobApplicationService.findById(id); // або getById
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
}

