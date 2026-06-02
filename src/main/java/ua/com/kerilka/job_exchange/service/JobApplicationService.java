package ua.com.kerilka.job_exchange.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.JobApplication;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.repository.CandidatesRepository;
import ua.com.kerilka.job_exchange.repository.JobApplicationRepository;
import ua.com.kerilka.job_exchange.repository.VacancyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final CandidatesRepository candidatesRepository;
    private final VacancyRepository vacancyRepository;


    // Створення нового запрошення на роботу від імені компанії
    @Transactional
    public void createCompanyInvitation(Long candidateId, Long vacancyId) {
        Candidates candidate = candidatesRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Кандидата не знайдено з ID: " + candidateId));

        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new IllegalArgumentException("Вакансію не знайдено з ID: " + vacancyId));

        JobApplication application = new JobApplication();
        application.setCandidate(candidate);
        application.setVacancy(vacancy);
        application.setInitiatedBy("COMPANY");
        application.setStatus("PENDING");

        jobApplicationRepository.save(application);
    }

    public List<JobApplication> findAllByCompany(Company company) {return jobApplicationRepository.findByVacancyCompany(company);}
    public List<JobApplication> findAllApplications() {return jobApplicationRepository.findAll();}
    public List<JobApplication> findByCandidate(Candidates candidate) {return jobApplicationRepository.findByCandidate(candidate);}
    public JobApplication findById(Long id) {return jobApplicationRepository.findById(id).get();}
    public JobApplication findByCandidateAndVacancy(Candidates candidate, Vacancy vacancy) {
        return jobApplicationRepository.findByCandidateAndVacancy(candidate, vacancy);
    }
    public void save(JobApplication jobApplication){jobApplicationRepository.save(jobApplication);}

    public void deleteById(Long id){jobApplicationRepository.deleteById(id);}
    public void deleteByVacancy(Vacancy vacancy){jobApplicationRepository.deleteByVacancy(vacancy);}
    public void deleteByCandidate(Candidates candidate){jobApplicationRepository.deleteByCandidate(candidate);}

}