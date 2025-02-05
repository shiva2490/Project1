package com.sathya.springmvc.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya.springmvc.employee.entity.EmployeeEntity;
import com.sathya.springmvc.employee.model.EmployeeModel;
import com.sathya.springmvc.employee.service.EmployeeService;




@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employee")
	public String getEmployeeDetails()
	{
		return "add-employee";
	}
	
	@PostMapping("/save employee")
	public String saveEmployee(EmployeeModel employeeModel)
	{
		employeeService.saveEmployeeDetails(employeeModel);
		return "success";
	}
	
	@GetMapping("/getemployee")
	public String getAllProducts(Model model)
	{
		List<EmployeeEntity> employees = employeeService.getAllEmployee();
		model.addAttribute("employees", employees);
		return "employee-list";
	}
	
	@GetMapping("/searchbyid")
	public String searchById() {
		return "search-employee";
	}
	
	@PostMapping("/searchbyid")
	public String searchById(@RequestParam Long id, Model model)
	{
		EmployeeEntity employee =  employeeService.searchById(id);
		model.addAttribute("employee", employee);
		return "search-employee";
	}
	
	
		@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") Long id)
	{
	employeeService.deleteEmployeeById(id);
	return "redirect:/getemployee";
	}
	

}
