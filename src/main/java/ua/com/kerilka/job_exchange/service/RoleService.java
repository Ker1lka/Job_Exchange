package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Roles;
import ua.com.kerilka.job_exchange.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void saveRole(Roles role){roleRepository.save(role);}

    // Отримання загальної кількості ролей у системі (для ініціалізації БД)
    public long countRoles() {return roleRepository.count();}

    // Пошук ролі за її назвою з обробкою виключення у разі відсутності
    public Roles findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }
}
