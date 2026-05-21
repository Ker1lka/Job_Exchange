package ua.com.kerilka.job_exchange.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profiles/list")
    public String redirectProfilesList() {
        return "redirect:/candidates/list";
    }

    @GetMapping("/profiles/my")
    public String redirectProfilesMy() {
        return "redirect:/candidates/my";
    }

    @GetMapping("/profiles/{id}")
    public String redirectProfilesSingle(@org.springframework.web.bind.annotation.PathVariable Long id) {
        return "redirect:/candidates/" + id;
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
