package com.chnu.repository.impl;

import com.chnu.model.Company;
import com.chnu.repository.ICompanyRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepository extends AbstractRepository<Company, Long> implements ICompanyRepository {
}
