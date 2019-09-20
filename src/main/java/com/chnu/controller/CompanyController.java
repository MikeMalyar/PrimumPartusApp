package com.chnu.controller;

import com.chnu.model.Company;
import com.chnu.model.User;
import com.chnu.repository.IUserRepository;
import com.chnu.service.ICompanyService;
import com.chnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {
    private final ICompanyService companyService;
    private final IUserRepository userRepository;

    @Autowired
    public CompanyController(ICompanyService companyService, IUserRepository userRepository) {
        this.companyService = companyService;
        this.userRepository = userRepository;
    }

    @Transactional
    @PostMapping("")
    public Company save(@RequestBody Company company, @RequestParam Long ownerId) {
        User user = userRepository.findById(ownerId).orElse(null);
        if (user != null) {
            company.setOwnerId(user);
            return companyService.save(company).orElse(null);
        }
        return null;
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable(name = "id", required = true) Long pk) {
        return companyService.findById(pk).orElse(null);
    }

    @PutMapping("")
    public Company update(@RequestBody Company object) {
        return companyService.update(object);
    }

    @DeleteMapping("")
    public void delete(@RequestBody Company object) {
        companyService.delete(object);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id", required = true) Long pk) {
        companyService.deleteById(pk);
    }

    @GetMapping("")
    public List<Company> findAll() {
        return companyService.findAll();
    }
}
