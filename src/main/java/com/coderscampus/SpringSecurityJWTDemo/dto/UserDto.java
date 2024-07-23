package com.coderscampus.SpringSecurityJWTDemo.dto;

import com.coderscampus.SpringSecurityJWTDemo.domain.Authority;
import com.coderscampus.SpringSecurityJWTDemo.domain.Order;
import com.coderscampus.SpringSecurityJWTDemo.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Authority> authorities;
    private List<Product> products;
    private List<Order> orders;
}
