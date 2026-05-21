package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.service.CandidatesService;

@Controller
@RequiredArgsConstructor
public class CandidateManager {

    private final CandidatesService candidatesService;

    @GetMapping("/candidate-manager")
    public String getCandidatesPageForManager(Model model){

        model.addAttribute("candidates", candidatesService.findAllCandidates());

        return "candidate-manager";
    }

    @PostMapping("/updateCandidate")
    public String updateCandidateFromManager(Model model,
                                           @RequestParam(name = "id") Long id,
                                           @RequestParam(name = "firstName") String firstName,
                                           @RequestParam(name = "lastName") String lastName,
                                           @RequestParam(name = "middleName") String middleName,
                                           @RequestParam(name = "address") String address,
                                           @RequestParam(name = "phone") String contactInfo,
                                           @RequestParam(name = "age") Integer age
                                           ){

        Candidates candidate = new Candidates();
        candidate.setId(id);
        candidate.setFirstName(firstName);
        candidate.setLastName(lastName);
        candidate.setMiddleName(middleName);
        candidate.setAddress(address);
        candidate.setContactInfo(contactInfo);
        candidate.setAge(age);

        candidatesService.updateCandidate(candidate);

        return "redirect:/candidate-manager";
    }

    @PostMapping("/deleteCandidate")
    public String deleteCandidateFromManager(Model model,
                                           @RequestParam(name = "id") Long id){
        candidatesService.deleteCandidateById(id);

        return "redirect:/candidate-manager";
    }
}

