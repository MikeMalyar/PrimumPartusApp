package com.chnu.controller;

import com.chnu.controller.base.BaseController;
import com.chnu.dto.CompanyDTO;
import com.chnu.model.Company;
import com.chnu.model.User;
import com.chnu.rest.GenericResponse;
import com.chnu.service.ICompanyService;
import com.chnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/company")
public class CompanyController extends BaseController {
    private final ICompanyService companyService;
    private final IUserService userService;

    @Autowired
    public CompanyController(ICompanyService companyService, IUserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @PostMapping("")
    public GenericResponse<CompanyDTO> save(@RequestBody CompanyDTO companyDTO) {
        Company company = fromCompanyDTO(companyDTO);
        validate(company);
        if(company != null) {
            return GenericResponse.of(CompanyDTO.fromCompany(companyService.save(company).orElse(null)));
        }
        return GenericResponse.error("Company is null or owner not found.");
    }

    @GetMapping("/{id}")
    public GenericResponse<CompanyDTO> findById(@PathVariable(name = "id", required = true) Long pk) {
        GenericResponse<CompanyDTO> response = GenericResponse
                .of(CompanyDTO.fromCompany(companyService.findById(pk).orElse(null)));
        return response.getResult() != null ? response :
                GenericResponse.error("Company not found with id " + pk);
    }

    @PutMapping("")
    public GenericResponse<CompanyDTO> update(@RequestBody CompanyDTO companyDTO) {
        Company company = fromCompanyDTO(companyDTO);
        validate(company);
        if(company != null) {
            return GenericResponse.of(CompanyDTO.fromCompany(companyService.update(company)));
        }
        return GenericResponse.error("Company is null or owner not found.");
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Void> deleteById(@PathVariable(name = "id", required = true) Long pk) {
        companyService.deleteById(pk);
        return GenericResponse.empty();
    }

    @GetMapping("")
    public GenericResponse<List<CompanyDTO>> findAll() {
        return GenericResponse.of(companyService.findAll().stream().map(CompanyDTO::fromCompany)
                .collect(Collectors.toList()));
    }

    private Company fromCompanyDTO(CompanyDTO dto) {
        User user = userService.findById(dto.getOwnerId()).orElse(null);
        if (user != null) {
            Company company = CompanyDTO.toCompany(dto);
            company.setOwner(user);
            return company;
        }
        return null;
    }
}
