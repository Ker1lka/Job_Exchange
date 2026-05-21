package ua.com.kerilka.job_exchange.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.com.kerilka.job_exchange.entity.Roles;
import ua.com.kerilka.job_exchange.service.RoleService;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        // Перевіряємо, чи таблиця ролей порожня
        if (roleService.countRoles() == 0) {
            // Якщо порожня (після знесення таблиць) — додаємо ролі
            // Передаємо null замість ID, щоб база даних сама згенерувала їх по порядку (1, 2, 3, 4)
            roleService.saveRole(new Roles(null, "ROLE_candidate"));
            roleService.saveRole(new Roles(null, "ROLE_company"));
            roleService.saveRole(new Roles(null, "ROLE_manager"));
            roleService.saveRole(new Roles(null, "ROLE_admin"));

            System.out.println(">> Базові ролі успішно додані в базу даних!");
        }
    }
}