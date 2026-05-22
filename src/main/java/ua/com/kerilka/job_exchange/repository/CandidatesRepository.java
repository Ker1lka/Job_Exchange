package ua.com.kerilka.job_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatesRepository extends JpaRepository<Candidates, Long> {
    Optional<Candidates> findByUser(Users user);

    @Query("SELECT c FROM Candidates c JOIN c.user u JOIN u.roles r WHERE r.name = 'ROLE_candidate'")
    List<Candidates> findAllRealCandidates();

    List<Candidates> findByProfessionContainingIgnoreCase(String profession);
}

