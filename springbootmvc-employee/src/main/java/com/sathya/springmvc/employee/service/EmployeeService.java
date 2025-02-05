package com.sathya.springmvc.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.springmvc.employee.entity.EmployeeEntity;
import com.sathya.springmvc.employee.model.EmployeeModel;
import com.sathya.springmvc.employee.repository.EmployeeRepository;




@Service
public class EmployeeService {

@Autowired
EmployeeRepository employeeRepository;

	public void saveEmployeeDetails(EmployeeModel employeeModel) {
		
		int overTime = 10;
		
		double otPay = 2000;
		
		double otAmt ;
		otAmt = overTime * otPay;
		
		double grossSalary;
		grossSalary = employeeModel.getSalary() + otAmt;
		
		
		EmployeeEntity employeeEntity = new EmployeeEntity();
		
		employeeEntity.setName(employeeModel.getName());
		employeeEntity.setSalary(employeeModel.getSalary());
		employeeEntity.setAge(employeeModel.getAge());
		employeeEntity.setCompany(employeeModel.getCompany());
		employeeEntity.setOverTime(overTime);
		employeeEntity.setOtPay(otPay);
		employeeEntity.setOtAmt(otAmt);
		employeeEntity.setGrossSalary(grossSalary);
		
		employeeRepository.save(employeeEntity);
	}
		
		
		public List<EmployeeEntity> getAllEmployee()
		{
			List<EmployeeEntity> employee = employeeRepository.findAll();
			return employee;
		}


		public  EmployeeEntity searchById(Long id) {
			Optional<EmployeeEntity>optionalData=employeeRepository.findById(id);
			
			if(optionalData.isPresent())
			{
				EmployeeEntity employee = optionalData.get();
				return employee;
			}
			else {
				return null;
			}
			
		}
		
		public void deleteEmployeeById(Long id) {
			employeeRepository.deleteById(id);
			
			
		}
		
}

		
		
		
		
		
		
		
		
		
		
		
	