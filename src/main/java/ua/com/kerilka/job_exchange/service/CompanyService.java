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

    public List<Company> findAllCompanies() {return companyRepository.findAll();}
    public Company findByIdCompany(Long id) {return companyRepository.findById(id).get();}
    public Company findByNameCompany(String name) {return companyRepository.findByName(name);}
    public void save(Company company) {companyRepository.save(company);}
    public void updateCompany(Company company) {companyRepository.save(company);}
    public void deleteCompanyById(long id) {companyRepository.deleteById(id);}
}
