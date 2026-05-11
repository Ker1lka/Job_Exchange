package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Vacancy;
import ua.com.kerilka.job_exchange.repository.VacancyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    @Cacheable(cacheNames = "vacancies")
    public List<Vacancy> findAllVacancies() {return vacancyRepository.findAll();}

    @Cacheable(cacheNames = "vacancyId", key = "#id")
    public Vacancy findByIdVacancy(Long id) {return vacancyRepository.findById(id).get();}

    @CacheEvict(cacheNames = "vacancies", allEntries = true)
    public void save(Vacancy vacancy) {vacancyRepository.save(vacancy);}

    @CacheEvict(cacheNames = {"vacancyId", "vacancies"}, allEntries = true)
    public void updateVacancy(Vacancy vacancy) {vacancyRepository.save(vacancy);}

    @CacheEvict(cacheNames = {"vacancyId", "vacancies"}, allEntries = true)
    public void deleteVacancyById(long id) {vacancyRepository.deleteById(id);}

    @CacheEvict(cacheNames = {"vacancyId", "vacancies"}, allEntries = true)
    public void deleteVacancy(Vacancy vacancy) {vacancyRepository.delete(vacancy);}

    @CacheEvict(cacheNames = {"vacancyId", "vacancies"}, allEntries = true)
    public void deleteAllVacancies() {vacancyRepository.deleteAll();}
}
