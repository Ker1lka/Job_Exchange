package ua.com.kerilka.job_exchange.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Roles;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.service.CompanyService;
import ua.com.kerilka.job_exchange.service.ProfilesService;
import ua.com.kerilka.job_exchange.service.UserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfilesService profilesService;
    private final CompanyService companyService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model,
                               @RequestParam(name = "message", defaultValue = " ") String message) {
        model.addAttribute("users", new Users());
        model.addAttribute("profiles", new Profiles());
        model.addAttribute("info", message);
        return "registration";
    }

    @GetMapping("/registration-company")
    public String registrationCompany(Model model,
                                      @RequestParam(name = "message", defaultValue = " ") String message) {
        // Створюємо порожні об'єкти, щоб форма могла з ними працювати
        model.addAttribute("users", new Users());
        model.addAttribute("profiles", new Profiles());

        // Додаємо інформаційне повідомлення (якщо воно є)
        model.addAttribute("info", message);

        // ВАЖЛИВО: Повертаємо саме шаблон для компанії
        return "registration-company";
    }

    @PostMapping("/registration")
    public String saveNewUserToDB(@Valid Users user,
                                  BindingResult bindingResult,
                                  @Valid Profiles profile,
                                  BindingResult bindingResult2,
                                  RedirectAttributes redirectAttributes) {
        if (userService.getUserFromDB(user.getUsername())) {
            redirectAttributes.addAttribute("message", "Username is already exists");
            return "redirect:/registration";
        }
        if (bindingResult.hasErrors() || bindingResult2.hasErrors()) return "registration";

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Users userById = userService.saveNewUser(user);

        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles(1L, "ROLE_user"));
        userById.setRoles(roles);

        userService.saveNewUser(userById);

        profilesService.save(profile);

        return "redirect:/login";
    }

    @PostMapping("/registration-company")
    public String saveNewCompanyToDB(@Valid Users user,
                                     BindingResult br1,
                                     @Valid Profiles profile,
                                     BindingResult br2,
                                     RedirectAttributes ra) {
        // 1. Перевірка чи юзер існує
        if (userService.getUserFromDB(user.getUsername())) {
            ra.addAttribute("message", "Username exists!");
            return "redirect:/registration-company";
        }

        // 2. Валідація полів
        if (br1.hasErrors() || br2.hasErrors()) return "registration-company";

        // 3. Хешування пароля (ОДИН РАЗ)
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        // 2. Встановлюємо роль (використовуємо HashSet)
        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles(2L, "ROLE_company")); // Твій новий ID для компанії
        user.setRoles(roles);

        // 3. ЗБЕРІГАЄМО ЮЗЕРА СПОЧАТКУ (щоб він отримав ID з бази)
        Users savedUser = userService.saveNewUser(user);

        // 4. ПРИВ'ЯЗУЄМО ТА ЗБЕРІГАЄМО ПРОФІЛЬ
        profile.setUser(savedUser); // Тепер savedUser точно не null і має ID
        profilesService.save(profile);

        return "redirect:/login";
    }
}
