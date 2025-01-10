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
	ProductService productService;   //injecting the ProductService object into ProductController class
	
	
	//getting the html form to fill the detail by client
	@GetMapping("/productform")
	public String getProductForm(Model model)
	{
		ProductModel productModel = new ProductModel();
		productModel.setMadeIn("India");      //setting the default field value to productform
		productModel.setQuantity(2);
		productModel.setDiscountRate(10.5);
		model.addAttribute("productModel", productModel);  //sending the "productModel" to view layer
		return "add-products";
	}
	
	//Form without default values and validations
//	@PostMapping("/save product")
//	
//	public String saveProduct(ProductModel productModel)
//	{
//		productService.saveProductDetails(productModel);
//		return "success";
//	}
	
	
	//Form with default values and validations
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
	
	
	
	
	//displaying all the records in the database or displaying the entire table
	@GetMapping("/getproducts")
	public String getAllProducts(Model model)
	{
		List<ProductEntity> products= productService.getAllProducts();
		
		model.addAttribute("products",products);
		return "product-list";
	}
	
	
	
	
	//searching the record from database based on id
	@GetMapping("/searchbyid")
	public String searchById() {
		return "search-product";
	}
	@PostMapping("/searchbyid")
	public String searchById(@RequestParam Long id, Model model)
	{
		ProductEntity product =  productService.searchById(id);  //sending the id to service layer and getting the id based details and storing into ProducEntity reference 
		model.addAttribute("product", product);  //sending the ProductEntity reference to view(html) page
		return "search-product";
	}
	
	
	
	
	//deleting the record in the database
	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") Long id)
	{
		productService.deleteProductById(id);   //sending the id to service layer
		return "redirect:/getproducts";
	}
	
	
	
	
	//edit the details in the database
	
	@GetMapping("/edit/{id}")
	public String showEditProductPage(@PathVariable("id") Long id,Model model) {
	    ProductModel product = productService.editProductById(id);     //sending the id to service layer and getting the id based details and storing into ProducModel reference
	    model.addAttribute("product", product);    //sending the ProductModel reference to view(html) page
	    model.addAttribute("id", id);			  //sending the id to view(html) page
	    return "edit-product";
	}

	@PostMapping("/editbyid/{id}")
	public String editById(@PathVariable("id") Long id, ProductModel productModel)
	{
		productService.editById(id,productModel);     //sending the id and ProducModel reference to service layer
		return "redirect:/getproducts";     //After editing and adding the details to database then the all records to be displayed
	}
	
	
	
	
	//getting the all links in one form
	@GetMapping("/alllinks")
	public String searchByLIinks() {
		return "all-links";
	}
	
}
