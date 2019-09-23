package com.chnu.service;

import com.chnu.model.Company;
import java.util.List;
import java.util.Optional;

public interface ICompanyService {
    Optional<Company> save(Company company);

    Optional<Company> findById(Long pk);

    Company update(Company object);

    void delete(Company object);

    boolean existsById(Long pk);

    void deleteById(Long pk);

    List<Company> findAll();

    List<Company> findWithPagination(int first, int count);
}
