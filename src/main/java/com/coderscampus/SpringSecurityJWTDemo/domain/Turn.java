package com.coderscampus.SpringSecurityJWTDemo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long turnId;
    private Integer turnNumber;
    private Long recipeId;
    private Boolean turnComplete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @Override
    public String toString() {
        return "Turn{" +
                "turnId=" + turnId +
                ", turnComplete=" + turnComplete +
                ", batch=" + batch +
                '}';
    }

}
