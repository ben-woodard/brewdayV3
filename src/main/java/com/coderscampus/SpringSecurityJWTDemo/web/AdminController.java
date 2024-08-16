package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.domain.Authority;
import com.coderscampus.SpringSecurityJWTDemo.domain.Company;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.dto.UserDto;
import com.coderscampus.SpringSecurityJWTDemo.service.CompanyService;
import com.coderscampus.SpringSecurityJWTDemo.service.impl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private CompanyService companyService;

    @PostConstruct
    public void init() {
        createAdminUser();
        createCompany1();
    }

    public void createAdminUser() {
        if(userService.findUserByEmail("admin@email.com").isEmpty()) {
            User adminUser = new User();
            adminUser.setFirstName("Admin");
            adminUser.setLastName("User");
            adminUser.setEmail("admin@email.com");
            adminUser.setPassword(passwordEncoder.encode("adminPassword"));
            Authority adminAuth = new Authority("SUPER", adminUser);
            adminUser.setAuthorities(Collections.singletonList(adminAuth));
            userService.save(adminUser);
        }
    }

    public void createCompany1() {
        if(companyService.findByCompanyName("Company1") == null){
            Company company = new Company();
            company.setCompanyName("Company1");
            companyService.save(company);
        }
    }

    @GetMapping
    public List<UserDto> getAllUsers () {
        return userService.findAll();
    }

    @PostMapping("/makeAdmin/{userId}")
    public UserDto elevateToAdmin (@PathVariable Integer userId) {
        return userService.elevateUserToAdmin(userId);
    }
}
