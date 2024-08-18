package com.coderscampus.SpringSecurityJWTDemo.service;

import com.coderscampus.SpringSecurityJWTDemo.domain.Company;
import com.coderscampus.SpringSecurityJWTDemo.dto.UserDto;

import java.util.List;

public interface CompanyService {
    Company findByCompanyName(String companyName);

    void save(Company company);

    Company findById(Long companyId);

    List<UserDto> findAllRequestedUsers(Long companyId);
}
