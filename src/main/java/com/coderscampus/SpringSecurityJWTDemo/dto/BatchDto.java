package com.coderscampus.SpringSecurityJWTDemo.dto;

import com.coderscampus.SpringSecurityJWTDemo.domain.Product;
import com.coderscampus.SpringSecurityJWTDemo.domain.Turn;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class BatchDto {

    private Long batchId;

    private Integer batchNumber;

    private Integer numberOfTurns;

    private LocalDate startDate;

    private LocalDate endDate;

    private String tankName;

    private Boolean turnsComplete;

    private Boolean batchComplete;

    private Product product;

    private List<Turn> turns;

}
