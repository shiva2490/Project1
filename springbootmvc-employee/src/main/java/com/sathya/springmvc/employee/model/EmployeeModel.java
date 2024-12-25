package com.sathya.springmvc.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
	
	private String name;
	private double salary;
	private int age;
	private String company;
//	private double pf;
//	private double da;
//	private double hra;
}
