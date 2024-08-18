package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.domain.Authority;
import com.coderscampus.SpringSecurityJWTDemo.domain.Company;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.dto.UserDto;
import com.coderscampus.SpringSecurityJWTDemo.service.CompanyService;
import com.coderscampus.SpringSecurityJWTDemo.service.impl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
//        createCompany1();
//        createSuperUser();
    }

    public void createAdminUser() {
        if (userService.findUserByEmail("admin@email.com").isEmpty() && companyService.findByCompanyName("Company1") == null) {
            Company company = new Company();
            company.setCompanyName("Company1");
            companyService.save(company);
            User adminUser = new User();
            adminUser.setFirstName("Admin");
            adminUser.setLastName("User");
            adminUser.setEmail("admin@email.com");
            adminUser.setPassword(passwordEncoder.encode("password"));
            adminUser.setCompany(company);
            Authority adminAuth = new Authority("ROLE_ADMIN", adminUser);
            adminUser.setAuthorities(Collections.singletonList(adminAuth));
            userService.save(adminUser);
            company.getUsers().add(adminUser);
            userService.save(adminUser);
            companyService.save(company);
        }
    }

//    public void createSuperUser() {
//        if(userService.findUserByEmail("admin@email.com").isEmpty()) {
//            User superUser = new User();
//            superUser.setFirstName("SUPER");
//            superUser.setLastName("User");
//            superUser.setEmail("super@email.com");
//            superUser.setPassword(passwordEncoder.encode("super"));
//            Authority superAuth = new Authority("SUPER", superUser);
//            superUser.setAuthorities(Collections.singletonList(superAuth));
//            userService.save(superUser);
//        }
//    }
//
//    public void createCompany1() {
//        if(companyService.findByCompanyName("Company1") == null){
//            Company company = new Company();
//            company.setCompanyName("Company1");
//            companyService.save(company);
//        }
//    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{companyId}")
    public List<UserDto> getAllUsersByCompany(@PathVariable Long companyId) {
//        return userService.findAllUsersByCompany(companyId);
        return null;
    }

    @PostMapping("/makeAdmin/{userId}")
    public UserDto elevateToAdmin(@PathVariable Integer userId) {
        return userService.elevateUserToAdmin(userId);
    }

    @GetMapping("/requestedUsers/{companyId}")
    public List<UserDto> getRequestedUsers(@PathVariable Long companyId) {
        List<UserDto> users = companyService.findAllRequestedUsers(companyId);
        users.forEach(System.out::println);
        return companyService.findAllRequestedUsers(companyId);
    }
}
