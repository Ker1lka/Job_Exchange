package ua.com.kerilka.job_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByUser(Users user);

    @Query("SELECT c FROM Company c JOIN c.user u JOIN u.roles r WHERE r.name = 'ROLE_company'")
    List<Company> findAllRealCompanies();
}
