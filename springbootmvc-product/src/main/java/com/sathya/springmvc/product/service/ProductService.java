package com.sathya.springmvc.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.springmvc.product.entity.ProductEntity;
import com.sathya.springmvc.product.model.ProductModel;
import com.sathya.springmvc.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	
	//setting the extra details to model class for adding these to database
	public void saveProductDetails(ProductModel productModel)
	{
		double stockValue;
			stockValue = productModel.getPrice() * productModel.getQuantity();
		
		double discountPrice;
			discountPrice = productModel.getDiscountRate()/100 * productModel.getPrice();
			
		double offerPrice;
			offerPrice = productModel.getPrice() - discountPrice;
			
		double taxPrice = 0.18;   //default value
		
		double finalPrice;
			finalPrice = offerPrice + (taxPrice * offerPrice);
			
          //create the entity objects : read the data from model class set the data to entity class
			
			ProductEntity productEntity = new ProductEntity();
			
			productEntity.setName(productModel.getName());
			productEntity.setBrand(productModel.getBrand());
			productEntity.setMadeIn(productModel.getMadeIn());
			productEntity.setPrice(productModel.getPrice());
			productEntity.setQuantity(productModel.getQuantity());
			productEntity.setDiscountRate(productModel.getDiscountRate());
			productEntity.setTaxPrice(taxPrice);
			productEntity.setDiscountPrice(discountPrice);
			productEntity.setOfferPrice(offerPrice);
			productEntity.setFinalPrice(finalPrice);
			productEntity.setStockValue(stockValue);
			
			productRepository.save(productEntity);  //save the productEntity data into database
	}
			
	
	
	       //Getting the All products details from database
			public List<ProductEntity> getAllProducts()
			{
				List<ProductEntity> products = productRepository.findAll(); 
				return products;
			}

			public ProductEntity searchById(Long id) {
				Optional<ProductEntity> optionalData= productRepository.findById(id);
				if(optionalData.isPresent())
				{
					ProductEntity product = optionalData.get();
					return product;
				}
				else {
					return null;
				}
			}

			
			
			
			//Deleting the row By Id 
			public void deleteProductById(Long id) {
				
				productRepository.deleteById(id);	
			}

			
			
			
			//Get the details from database and set the details to ProductModel fields(variables) 
			public ProductModel  editProductById(Long id) {
				Optional<ProductEntity> optionalData= productRepository.findById(id);
				if(optionalData.isPresent())
				{
					ProductEntity product = optionalData.get();
					
					ProductModel productModel = new ProductModel();
					productModel.setName(product.getName());
					productModel.setBrand(product.getBrand());
					productModel.setMadeIn(product.getMadeIn());
					productModel.setPrice(product.getPrice());
					productModel.setQuantity(product.getQuantity());
					productModel.setDiscountRate(product.getDiscountRate());
					return productModel;
				}
				else {
					return null;
				}
			}

			
			
			//After user edit the data that comes controller to service and set the ProductEntity fields(variables)
			public void editById(Long id, ProductModel productModel) {
				
				Optional <ProductEntity> product = productRepository.findById(id);
				
				if(product.isPresent())
				{
					ProductEntity entity = product.get();
					
					double stockValue;
					stockValue = productModel.getPrice() * productModel.getQuantity();
					
					double discountPrice;
					discountPrice = productModel.getPrice() * productModel.getDiscountRate()/100;
					
					double offerPrice;
					offerPrice = productModel.getPrice() - discountPrice;
					double taxPrice = 0.18;
					
					double finalPrice;
					finalPrice = offerPrice + ( taxPrice * offerPrice);
					
					entity.setName(productModel.getName());
					entity.setBrand(productModel.getBrand());
					entity.setMadeIn(productModel.getMadeIn());
					entity.setPrice(productModel.getPrice());
					entity.setQuantity(productModel.getQuantity());
					entity.setDiscountRate(productModel.getDiscountRate());
					entity.setTaxPrice(taxPrice);
					entity.setDiscountPrice(discountPrice);
					entity.setOfferPrice(offerPrice);
					entity.setFinalPrice(finalPrice);
					entity.setStockValue(stockValue);
					
					productRepository.save(entity);
				}
				
			}
			
		
			

}
