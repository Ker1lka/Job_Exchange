package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.kerilka.job_exchange.service.AboutUsService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AboutUsService aboutUsService;

    // Головна сторінка
    @GetMapping("/")
    public String index() {return "index";}

    // Сторінка авторизації
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Автоматичний редірект користувача на його сторінку залежно від ролі після входу
    @GetMapping("/success")
    public String redirectToRolePage(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        //Отримуємо роль користувача з аутентифікації
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("");

        if (role.equals("ROLE_admin")) {
            return "redirect:/admin/users";
        } else if (role.equals("ROLE_manager")) {
            return "redirect:/manager/vacancies";
        } else if (role.equals("ROLE_company")) {
            return "redirect:/company/candidates";
        } else if (role.equals("ROLE_candidate")) {
            return "redirect:/candidate/vacancies";
        }
        return "redirect:/";
    }

    // Відображення сторінки "Про нас" із завантаженням контенту з файлу
    @GetMapping("/about-us")
    public String aboutUs(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("guest");
            model.addAttribute("userRole", role);
        }
        else {model.addAttribute("userRole", "guest");}
        model.addAttribute("aboutText", aboutUsService.getAboutText());

        return "about-us";
    }
}
