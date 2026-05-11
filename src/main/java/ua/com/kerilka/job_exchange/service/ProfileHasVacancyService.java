package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.ProfileHasVacancy;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.repository.ProfileHasVacancyRepository;
import ua.com.kerilka.job_exchange.repository.VacancyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileHasVacancyService {
    private final ProfileHasVacancyRepository phvRepository;
    private final VacancyRepository vacancyRepository; // Додаємо, щоб знайти об'єкт вакансії

    public void apply(Profiles profile, Long vacancyId) {
        // 1. Знаходимо вакансію за ID, який прийшов з форми
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new RuntimeException("Vacancy not found with id: " + vacancyId));

        // 2. Створюємо новий запис для таблиці зв'язку
        ProfileHasVacancy application = new ProfileHasVacancy();
        application.setProfile(profile);
        application.setVacancy(vacancy);

        // Можна додати дату відгуку, якщо є таке поле
        // application.setAppliedAt(LocalDateTime.now());

        // 3. Зберігаємо в базу
        phvRepository.save(application);
    }
}
