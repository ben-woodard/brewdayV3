package com.coderscampus.SpringSecurityJWTDemo.service.impl;

import com.coderscampus.SpringSecurityJWTDemo.domain.Authority;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;

import com.coderscampus.SpringSecurityJWTDemo.dto.UserDto;
import com.coderscampus.SpringSecurityJWTDemo.exceptions.BadRequestException;
import com.coderscampus.SpringSecurityJWTDemo.mappers.UserMapper;
import com.coderscampus.SpringSecurityJWTDemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coderscampus.SpringSecurityJWTDemo.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                User user = userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

                List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                        .collect(Collectors.toList());

                return user;
            }
        };
    }

    @Secured({"ROLE_ADMIN"})
    @CrossOrigin(origins = "http://localhost:4200")
    public List<UserDto> findAll() {
        return userMapper.entityListToDto(userRepository.findAll());
    }

    @Secured("ROLE_ADMIN")
    @Transactional // This annotation ensures that changes are committed to the database
    public UserDto elevateUserToAdmin(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getAuthorities().stream().noneMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()))) {
                Authority adminAuthority = new Authority("ROLE_ADMIN");
                adminAuthority.setUser(user);
                user.getAuthorities().add(adminAuthority);
                return userMapper.entityToDto(userRepository.save(user));
            }
        }
        throw new BadRequestException("There was An Error elevating the user to Admin");
    }


    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void save(User adminUser) {
        userRepository.save(adminUser);
    }

    @Override
    public List<UserDto> findAll(User user) {
        return null;
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }


//    public User loadUserByUsername(String username) {
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
//
//        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
//                .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
//                .collect(Collectors.toList());
//
//        return user;
//
//    }
}