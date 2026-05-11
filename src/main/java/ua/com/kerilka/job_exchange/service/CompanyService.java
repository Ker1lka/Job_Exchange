package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Cacheable(cacheNames = "companies")
    public List<Company> findAllCompanies() {return companyRepository.findAll();}

    @Cacheable(cacheNames = "companyById", key = "#id")
    public Company findByIdCompany(Long id) {return companyRepository.findById(id).get();}

    @Cacheable(cacheNames = "companyByName", key = "#name")
    public Company findByNameCompany(String name) {return companyRepository.findByName(name);}

    @CacheEvict(cacheNames = "companies", allEntries = true)
    public void save(Company company) {companyRepository.save(company);}

    @CacheEvict(cacheNames = {"companyById", "companyByName", "companies"}, allEntries = true)
    public void updateCompany(Company company) {companyRepository.save(company);}

    @CacheEvict(cacheNames = {"companyById", "companyByName", "companies"}, allEntries = true)
    public void deleteCompanyById(long id) {companyRepository.deleteById(id);}

    @CacheEvict(cacheNames = {"companyById", "companyByName", "companies"}, allEntries = true)
    public void deleteCompany(Company company) {companyRepository.delete(company);}

    @CacheEvict(cacheNames = {"companyById", "companyByName", "companies"}, allEntries = true)
    public void deleteAllCompany() {companyRepository.deleteAll();}
}
