package com.coderscampus.SpringSecurityJWTDemo.service;

import com.coderscampus.SpringSecurityJWTDemo.domain.Company;

public interface CompanyService {
    Company findByCompanyName(String companyName);

    void save(Company company);

    Company findById(Long companyId);
}
