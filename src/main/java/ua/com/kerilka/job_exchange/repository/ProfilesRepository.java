package ua.com.kerilka.job_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kerilka.job_exchange.entity.Profiles;

@Repository
public interface ProfilesRepository extends JpaRepository<Profiles, Long> {
    Profiles findByFirstName(String firstName);
}
