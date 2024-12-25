package com.sathya.springmvc.product.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	
	@NotBlank(message="Product name cannot be blank")
	private String name;
	
	@NotBlank(message="Brand cannot be blank")
	private String brand;
	
	@NotBlank(message="MadeIn cannot be blank")
	private String madeIn;
	
	@Positive(message="Price must be greaterthan zero")
	private double price;
	
	@Min(value=1 , message="Quantity must be atleast 1")
	private int quantity;
	
	@DecimalMax(value="100.0" , message="Discountrate cannot exceed 100")
	private double discountRate;
	
	
}

