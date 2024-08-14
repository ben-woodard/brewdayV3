package com.coderscampus.SpringSecurityJWTDemo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@Table(name = "company_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String companyName;
    private Boolean orderReceived;
    private LocalDate orderReceivedDate;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "order_ingredient",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "order_ingredient_to_add", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "ingredient_id")
    @Column(name = "amount_to_add")
    private Map<Long, Double> ingredientsToAdd = new HashMap<>();
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", companyName='" + companyName + '\'' +
                ", ingredientsToAdd=" + ingredientsToAdd +
                '}';
    }
}
