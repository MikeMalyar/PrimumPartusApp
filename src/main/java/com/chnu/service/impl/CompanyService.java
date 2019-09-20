package com.chnu.service.impl;

import com.chnu.model.Company;
import com.chnu.repository.ICompanyRepository;
import com.chnu.repository.ICourierRepository;
import com.chnu.repository.IUserRepository;
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
    private final ICourierRepository courierRepository;
    private final IUserRepository userRepository;

    @Autowired
    public CompanyService(ICompanyRepository companyRepository, ICourierRepository courierRepository, IUserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.courierRepository = courierRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Company> save(Company company) {
        if (!findById(company.getCompanyId()).isPresent()) return Optional.ofNullable(companyRepository.save(company));
        return Optional.empty();
    }

    public Optional<Company> findById(Long id){
        return companyRepository.findById(id);
    }

    public Company update(Company object){
        return companyRepository.update(object);
    }

    public boolean existsById(Long pk){
        return companyRepository.existsById(pk);
    }

    public void deleteById(Long pk){
        companyRepository.deleteById(pk);
    }

    public void delete(Company entity){
        companyRepository.delete(entity);
    }

    public List<Company> findWithPagination(int first, int count){
        return companyRepository.findWithPagination(first, count);
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }
}
