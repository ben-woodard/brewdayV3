package com.coderscampus.SpringSecurityJWTDemo.service;

import com.coderscampus.SpringSecurityJWTDemo.domain.Company;

import java.util.Collection;

public interface CompanyService {
    Company findByCompanyName(String companyName);

    void save(Company company);
}
