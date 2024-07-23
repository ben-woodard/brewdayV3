package com.coderscampus.SpringSecurityJWTDemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private Integer batchNumber;
    private Integer numberOfTurns;
    private LocalDate startDate;
    private LocalDate endDate;
    private String tankName;
    private Boolean turnsComplete;
    private Boolean batchComplete;
//    private Long selectedRecipeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(mappedBy = "batch", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnoreProperties("batch")
    private List<Turn> turns = new ArrayList<>();

    @Override
    public String toString() {
        return "Batch{" +
                "batchId=" + batchId +
                ", batchNumber=" + batchNumber +
                ", numberOfTurns=" + numberOfTurns +
                ", turnsComplete=" + turnsComplete +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tankName='" + tankName + '\'' +
                ", product=" + product +
                '}';
    }

}
