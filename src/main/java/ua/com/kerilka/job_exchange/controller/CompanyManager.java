package ua.com.kerilka.job_exchange.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.service.CompanyService;

@Controller
@RequiredArgsConstructor
public class CompanyManager {

    private final CompanyService companyService;

    @GetMapping("/company-manager")
    public String getCompaniesPageFromManager(Model model){

        model.addAttribute("companies", companyService.findAllCompanies());

        return "company-manager";
    }

    @PostMapping("/updateCompany")
    public String updateCompanyFromManager(Model model,
                                           @RequestParam(name = "id") Long id,
                                           @RequestParam(name = "name") String name,
                                           @RequestParam(name = "description") String description,
                                           @RequestParam(name = "contactInfo") String contactInfo,
                                           @RequestParam(name = "address") String address){

        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setDescription(description);
        company.setContactInfo(contactInfo);
        company.setAddress(address);

        companyService.updateCompany(company);

        return "redirect:/company-manager";
     }

     @PostMapping("/deleteCompany")
     public String deleteCompanyFromManager(Model model,
                                            @RequestParam(name = "id") Long id){
        companyService.deleteCompanyById(id);

        return "redirect:/company-manager";
     }
}
