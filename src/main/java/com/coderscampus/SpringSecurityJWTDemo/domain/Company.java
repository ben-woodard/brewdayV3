package com.coderscampus.SpringSecurityJWTDemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private String companyName;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("company")
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("company")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "company", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnoreProperties("company")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<User> requestedUsers = new ArrayList<>();

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", users=" + users.stream().map(User::getUsername).toList() +  // Include user emails only
                ", products=" + products.stream().map(Product::getProductName).toList() +  // Include product names only
                '}';
    }
}
