package com.chnu.dto;

import com.chnu.model.Company;

public class CompanyDTO {

    private Long companyId;
    private String name;
    private Long ownerId;
    private String url;
    private Boolean enabled;

    public static CompanyDTO fromCompany(Company company) {
        return company != null ? new CompanyDTO()
                .setCompanyId(company.getCompanyId())
                .setName(company.getName())
                .setOwnerId(company.getOwner().getUserId())
                .setUrl(company.getUrl())
                .setEnabled(company.getEnabled()) : null;
    }

    public static Company toCompany(CompanyDTO dto) {
        return dto != null ? new Company()
                .setCompanyId(dto.getCompanyId())
                .setName(dto.getName())
                .setUrl(dto.getUrl())
                .setEnabled(dto.getEnabled()) : null;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public CompanyDTO setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public CompanyDTO setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CompanyDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public CompanyDTO setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
