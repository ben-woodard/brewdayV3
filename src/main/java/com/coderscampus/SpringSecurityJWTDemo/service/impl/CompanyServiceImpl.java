package com.coderscampus.SpringSecurityJWTDemo.service.impl;

import com.coderscampus.SpringSecurityJWTDemo.domain.Company;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.dto.UserDto;
import com.coderscampus.SpringSecurityJWTDemo.mappers.CompanyMapper;
import com.coderscampus.SpringSecurityJWTDemo.mappers.UserMapper;
import com.coderscampus.SpringSecurityJWTDemo.repository.CompanyRepository;
import com.coderscampus.SpringSecurityJWTDemo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements com.coderscampus.SpringSecurityJWTDemo.service.CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public Company findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName).orElse(null);
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company findById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);

    }

    @Override
    public List<UserDto> findAllRequestedUsers(Long companyId) {
        Company company = findById(companyId);
        company.getRequestedUsers().forEach(System.out::println);
        return userMapper.entityListToDto(company.getRequestedUsers());
    }

    @Transactional
    @Override
    public List<UserDto> addUserToCompany(Integer userId, Long companyId) {
        User user = userService.findUserById(userId);
        Company company = findById(companyId);
        company.getUsers().add(user);
        user.setCompany(company);
        user.setRequestedCompany(null);
        company.getRequestedUsers().remove(user);
        save(company);
        userService.save(user);
        return findAllUsers(company.getCompanyId());
    }

    @Override
    public List<UserDto> findAllUsers(Long companyId) {
        return userMapper.entityListToDto(findById(companyId).getUsers());
    }



}
