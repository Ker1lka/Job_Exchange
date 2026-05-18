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
    private final VacancyRepository vacancyRepository;

    public void apply(Profiles profile, Long vacancyId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new RuntimeException("Vacancy not found with id: " + vacancyId));

        ProfileHasVacancy application = new ProfileHasVacancy();
        application.setProfile(profile);
        application.setVacancy(vacancy);
        phvRepository.save(application);
    }
    public List<ProfileHasVacancy> findAllProfilesHasVacancies() {
        return phvRepository.findAll();
    }
    public void updatePHV(ProfileHasVacancy phv) {
        phvRepository.save(phv);
    }
    public void deletePHVById(Long id){
        phvRepository.deleteById(id);
    }
}
