package ua.com.kerilka.job_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Users;

@Repository
public interface CandidatesRepository extends JpaRepository<Candidates, Long> {

    Candidates findByFirstName(String firstName);

    Candidates findByUser(Users user);
}

