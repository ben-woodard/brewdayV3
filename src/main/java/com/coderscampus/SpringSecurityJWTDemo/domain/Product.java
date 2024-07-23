package com.coderscampus.SpringSecurityJWTDemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private Long defaultRecipeId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE}, orphanRemoval = true)
	@JsonIgnoreProperties("product")
	private List<Recipe> recipes = new ArrayList<>();
	@OneToMany(mappedBy = "product", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JsonIgnoreProperties("product")
	private List<Batch> batches = new ArrayList<>();
	private String selectedRecipe;
	@OneToMany(mappedBy = "product", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JsonIgnoreProperties("product")
	private List<Turn> turns = new ArrayList<>();


	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", user=" + user +
				'}';
	}
}
