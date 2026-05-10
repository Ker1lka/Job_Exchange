package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.repository.VacancyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    public List<Vacancy> findAllVacancies() {return vacancyRepository.findAll();}
    public Vacancy findByIdVacancy(Long id) {return vacancyRepository.findById(id).get();}
    public void save(Vacancy vacancy) {vacancyRepository.save(vacancy);}
    public void updateVacancy(Vacancy vacancy) {vacancyRepository.save(vacancy);}
    public void deleteVacancyById(long id) {vacancyRepository.deleteById(id);}
    public void deleteVacancy(Vacancy vacancy) {vacancyRepository.delete(vacancy);}
    public void deleteAllVacancies() {vacancyRepository.deleteAll();}
}
