package com.coderscampus.SpringSecurityJWTDemo.repository;


import java.util.List;
import java.util.Optional;

import com.coderscampus.SpringSecurityJWTDemo.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.SpringSecurityJWTDemo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Since email is unique, we'll find users by email
    Optional<User> findByEmail(String email);

    List<User> findAllUsersByCompany_companyId(Long companyId);
}