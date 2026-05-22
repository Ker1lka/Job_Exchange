package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> findAllCompanies() {return companyRepository.findAllRealCompanies();}
    public Company findById(Long id) {return companyRepository.findById(id).get();}

    public Company findByUser(Users user) {return companyRepository.findByUser(user)
            .orElseThrow(() -> new IllegalArgumentException("Профіль компанії не знайдено"));
    }


    public void save(Company company) {companyRepository.save(company);}
    public void updateCompany(Company company) {companyRepository.save(company);}
    public void deleteCompany(Company company) {companyRepository.delete(company);}
    public void deleteCompanyById(long id) {companyRepository.deleteById(id);}
}
