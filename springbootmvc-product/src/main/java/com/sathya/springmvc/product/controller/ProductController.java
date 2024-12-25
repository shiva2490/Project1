package com.sathya.springmvc.product.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya.springmvc.product.entity.ProductEntity;
import com.sathya.springmvc.product.model.ProductModel;
import com.sathya.springmvc.product.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/productform")
	public String getProductForm(Model model)
	{
		ProductModel productModel = new ProductModel();
		productModel.setMadeIn("India");
		productModel.setQuantity(2);
		productModel.setDiscountRate(10.5);
		model.addAttribute("productModel", productModel);
		return "add-products";
	}

//	@PostMapping("/save product")
//	
//	public String saveProduct(ProductModel productModel)
//	{
//		productService.saveProductDetails(productModel);
//		return "success";
//	}
	
	
	@PostMapping("/save product")
	
	public String saveProduct(@Valid ProductModel productModel , BindingResult bindingResult , Model model)
	{
		//used to store the fieldName : error message in the form of key : value
		HashMap<String, String> validationErrors = new HashMap<>();
		
		//to check errors are present or not
		if(bindingResult.hasErrors())
		{
			//to read all the field errors one by one 
			for(FieldError fieldError : bindingResult.getFieldErrors())
			{
				//putting the each field error into map
				validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			
			//adding the validationErrors into model object to send to view
			model.addAttribute("validationErrors", validationErrors);
			return "add-products";
		}
		//this code will execute when there is no errors
		productService.saveProductDetails(productModel);
		return "redirect:/getproducts";
	}
	
	
	@GetMapping("/getproducts")
	public String getAllProducts(Model model)
	{
		List<ProductEntity> products= productService.getAllProducts();
		
		model.addAttribute("products",products);
		return "product-list";
	}
	
	@GetMapping("/searchbyid")
	public String searchById() {
		return "search-product";
	}
	@PostMapping("/searchbyid")
	public String searchById(@RequestParam Long id, Model model)
	{
		ProductEntity product =  productService.searchById(id);
		model.addAttribute("product", product);
		return "search-product";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") Long id)
	{
		productService.deleteProductById(id);
		return "redirect:/getproducts";
	}
	
	
	@GetMapping("/edit/{id}")
	public String showEditProductPage(@PathVariable("id") Long id,Model model) {
	    ProductModel product = productService.editProductById(id); 
	    model.addAttribute("product", product);
	    model.addAttribute("id", id);
	    return "edit-product";
	}

	@PostMapping("/editbyid/{id}")
	public String editById(@PathVariable("id") Long id, ProductModel productModel)
	{
		productService.editById(id,productModel);
		return "redirect:/getproducts";
	}
	
	@GetMapping("/alllinks")
	public String searchByLIinks() {
		return "all-links";
	}
	
}
