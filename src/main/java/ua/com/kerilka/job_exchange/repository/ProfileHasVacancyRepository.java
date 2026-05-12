package ua.com.kerilka.job_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kerilka.job_exchange.entity.ProfileHasVacancy;

@Repository
public interface ProfileHasVacancyRepository extends JpaRepository<ProfileHasVacancy, Long> {
    boolean existsByProfileIdAndVacancyId(Long profileId, Long vacancyId);


}
