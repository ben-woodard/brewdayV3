package com.coderscampus.SpringSecurityJWTDemo.dto;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.domain.Order;
import com.coderscampus.SpringSecurityJWTDemo.domain.Product;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CompanyDto {

    private Long companyId;
    private String companyName;
    private List<User> users;
    private List<User> requestedUsers;
    private List<Product> products;
    private List<Order> orders;
    private List<Ingredient> ingredients;
}
