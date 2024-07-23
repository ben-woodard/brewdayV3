package com.coderscampus.SpringSecurityJWTDemo.dto;

import com.coderscampus.SpringSecurityJWTDemo.domain.Batch;
import com.coderscampus.SpringSecurityJWTDemo.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TurnDto {

    private Long turnId;
    private Integer turnNumber;
    private Long recipeId;
    private Boolean turnComplete;
    private Batch batch;
    private Product product;
}
