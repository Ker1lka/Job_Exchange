package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.service.CompanyService;

@Controller
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/companies/create")
    public String showCreateForm() {
        return "company-create";
    }

    @PostMapping("/companies/create")
    public String createCompany(@ModelAttribute Company company) {
        companyService.save(company);

        return "redirect:/vacancy/create";
    }
}
