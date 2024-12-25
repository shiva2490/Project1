package com.sathya.springmvc.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathya.springmvc.product.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
