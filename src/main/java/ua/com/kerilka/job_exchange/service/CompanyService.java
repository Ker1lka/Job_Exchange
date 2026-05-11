package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Company;
import ua.com.kerilka.job_exchange.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }

    public List<Company> findAllCompanies() {return companyRepository.findAll();}
    public Company findByIdCompany(Long id) {return companyRepository.findById(id).get();}
    public void save(Company company) {companyRepository.save(company);}
    public void updateCompany(Company company) {companyRepository.save(company);}
    public void deleteCompanyById(long id) {companyRepository.deleteById(id);}
    public void deleteCompany(Company company) {companyRepository.delete(company);}
    public void deleteAllCompany() {companyRepository.deleteAll();}
}
