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

    public List<Company> findAllCompanies() {return companyRepository.findAll();}
    public Company findByIdCompany(Long id) {return companyRepository.findById(id).get();}
    public void save(Company profile) {companyRepository.save(profile);}
    public void updateCompany(Company profile) {companyRepository.save(profile);}
    public void deleteCompanyById(long id) {companyRepository.deleteById(id);}
    public void deleteCompany(Company profile) {companyRepository.delete(profile);}
    public void deleteAllCompany() {companyRepository.deleteAll();}
}
