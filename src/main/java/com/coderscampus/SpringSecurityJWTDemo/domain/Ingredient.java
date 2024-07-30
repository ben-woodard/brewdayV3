package com.coderscampus.SpringSecurityJWTDemo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId;
    private String ingredientName;
    @ManyToMany(mappedBy = "ingredients", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Recipe> recipes = new ArrayList<>();
    @ManyToMany(mappedBy = "ingredients", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Recipe> orders = new ArrayList<>();
    private Double amountInStock;
    private Double orderingThreshold;
    @Enumerated(EnumType.STRING)
    @Column(name = "ingredientType")
    private ingredientType ingredientType;
    @Enumerated(EnumType.STRING)
    @Column(name = "unitOfMeasurement")
    private unitOfMeasurement unitOfMeasurement;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public enum ingredientType {
        MALT,
        HOP,
        ADJUNCT,
        EXTRACT,
        SALT,
        YEAST
    }

    public enum unitOfMeasurement {
        lBS,
        OZ,
        EACH
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", ingredientName='" + ingredientName + '\'' +
                ", amountInStock=" + amountInStock +
                ", ingredientType=" + ingredientType +
                ", unitOfMeasurement=" + unitOfMeasurement +
                '}';
    }
}
