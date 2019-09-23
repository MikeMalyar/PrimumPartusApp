package com.chnu.service.impl;

import com.chnu.model.Company;
import com.chnu.repository.ICompanyRepository;
import com.chnu.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService implements ICompanyService {
    private final ICompanyRepository companyRepository;

    @Autowired
    public CompanyService(ICompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> save(Company company) {
        if (!findById(company.getCompanyId()).isPresent()) {
            return Optional.ofNullable(companyRepository.save(company));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Company> findById(Long id){
        return companyRepository.findById(id);
    }

    @Override
    public Company update(Company object){
        return companyRepository.update(object);
    }

    @Override
    public boolean existsById(Long pk){
        return companyRepository.existsById(pk);
    }

    @Override
    public void deleteById(Long pk){
        companyRepository.deleteById(pk);
    }

    @Override
    public void delete(Company entity){
        companyRepository.delete(entity);
    }

    @Override
    public List<Company> findWithPagination(int first, int count){
        return companyRepository.findWithPagination(first, count);
    }

    @Override
    public List<Company> findAll(){
        return companyRepository.findAll();
    }
}
