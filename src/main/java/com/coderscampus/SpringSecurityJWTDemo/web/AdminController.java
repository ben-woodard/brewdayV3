package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.domain.Authority;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.dto.UserDto;
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

    @PostConstruct
    public void init() {
        createAdminUser();
    }

    public void createAdminUser() {
        if(userService.findUserByEmail("admin@email.com").isEmpty()) {
            User adminUser = new User();
            adminUser.setFirstName("Admin");
            adminUser.setLastName("User");
            adminUser.setEmail("admin@email.com");
            adminUser.setPassword(passwordEncoder.encode("adminPassword"));
            Authority adminAuth = new Authority("ROLE_ADMIN", adminUser);
            adminUser.setAuthorities(Collections.singletonList(adminAuth));
            userService.save(adminUser);
        }
    }

    @GetMapping
    public List<UserDto> getAllUsers () {
        return userService.findAll();
    }
//
//    @GetMapping("/dashboard")
//    public String getDashboard (ModelMap model) {
//        List<User> users = userService.findAll();
//        model.addAttribute("userList", users);
//        return "admin/dashboard";
//    }
//
//    @PostMapping("/makeAdmin")
//    public ResponseEntity<String> elevateToAdmin (@RequestParam Integer userId) {
//        Optional<User> findUser = userService.findUserById(userId);
//
//        userService.elevateUserToAdmin(userId);
////        logger.info("Processing elevation for user: {}", findUser.get().getEmail());
////        logger.info("Role: {}", findUser.get().getAuthorities());
//        return ResponseEntity.ok("User elevated to admin");
//    }
}
