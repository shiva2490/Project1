package com.sathya.springmvc.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "Product")    //Giving custom table name to database table
public class ProductEntity {
	
   @Id     //used to indicate primary key
   @GeneratedValue(strategy = GenerationType.AUTO)   //used to set a default values to id column
	private long id;
    private String name;
    private String brand;
    private String madeIn;
    private double price;
    private int quantity;
    @Column(name = "discRt")       //Giving custom name to database table column
    private double discountRate;
    private double taxPrice;
    private double discountPrice;
    private double offerPrice;
    private double finalPrice;
    private double stockValue;
}
