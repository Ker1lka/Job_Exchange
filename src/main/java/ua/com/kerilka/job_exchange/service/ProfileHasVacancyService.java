package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.ProfileHasVacancy;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.repository.ProfileHasVacancyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileHasVacancyService {
    private final ProfileHasVacancyRepository profileHasVacancyRepository;

    public void apply(Profiles profile, Vacancy vacancy) {
        // Перевірка на дублікат (щоб не відгукуватися двічі)
        if (profileHasVacancyRepository.existsByProfileIdAndVacancyId(profile.getId(), vacancy.getId())) {
            return;
        }

        ProfileHasVacancy relation = new ProfileHasVacancy();
        relation.setProfile(profile);
        relation.setVacancy(vacancy);

        profileHasVacancyRepository.save(relation);
    }

    // Отримати всі відгуки на конкретну вакансію (для роботодавця)
    public List<ProfileHasVacancy> getApplicationsForVacancy(Long vacancyId) {
        return profileHasVacancyRepository.findAll().stream()
                .filter(rel -> rel.getVacancy().getId().equals(vacancyId))
                .toList();
    }

    // Отримати всі відгуки конкретного юзера (для кабінету кандидата)
    public List<ProfileHasVacancy> getMyApplications(Long profileId) {
        return profileHasVacancyRepository.findAll().stream()
                .filter(rel -> rel.getProfile().getId().equals(profileId))
                .toList();
    }
}
