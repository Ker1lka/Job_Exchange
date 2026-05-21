package ua.com.kerilka.job_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.JobApplication;
import ua.com.kerilka.job_exchange.entity.Vacancy;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByVacancyCompany(Company company);

    List<JobApplication> findByCandidate(Candidates candidate);

    JobApplication findByCandidateAndVacancy(Candidates candidate, Vacancy vacancy);
}
