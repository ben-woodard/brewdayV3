package com.coderscampus.SpringSecurityJWTDemo.dto;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long orderId;
    private String companyName;
    private Boolean orderReceived;
    private LocalDate orderReceivedDate;
    private List<Ingredient> ingredients;
    private Map<Long, Double> ingredientsToAdd;
    private User user;
}
