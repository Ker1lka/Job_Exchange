package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.repository.VacancyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    public List<Vacancy> findAllVacancies() {return vacancyRepository.findAll();}
    public Vacancy findByIdVacancy(Long id) {return vacancyRepository.findById(id).get();}
    public List<Vacancy> findByCompany(Company company){return vacancyRepository.findByCompany(company);}
    public List<Vacancy> findActiveVacancies(){return vacancyRepository.findByIsClosedFalse();}

    public List<Vacancy> searchVacanciesByPosition(String keyword) {return vacancyRepository.findByPositionContainingIgnoreCase(keyword);}

    public void save(Vacancy vacancy) {vacancyRepository.save(vacancy);}

    public void deleteVacancyById(long id) {vacancyRepository.deleteById(id);}
}
