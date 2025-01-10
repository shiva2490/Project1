package com.sathya.springbootresthospital.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.springbootresthospital.api.ApiRepository;
import com.sathya.springbootresthospital.hospital.Hospital;
import com.sathya.springbootresthospital.service.HospitalService;

@RestController
@RequestMapping("/api/v1")
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;

	
	@PostMapping("/hospital")
	public ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital)
	{
		Hospital hospital2= hospitalService.saveHospital(hospital);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				             .header("info", "created succcefully")
				             .body(hospital2);
	}
	
	
	
	@PostMapping("/hospitals")
	public ResponseEntity<List<Hospital>> saveHospitals(@RequestBody List<Hospital> hospital)
	{
		List<Hospital> hospital2= hospitalService.saveHospitals(hospital);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				             .header("info", "created succcefully")
				             .body(hospital2);
	}
	
	
	
	//getting the all details stored in database
	@GetMapping("/getalldetails")
	public ResponseEntity<List<Hospital>> getAllDetails()
	{
		List<Hospital> hospitals = hospitalService.getAllDetails();
		return ResponseEntity.status(HttpStatus.OK)
							 .header("info", "data retrievrs succefully")
							 .body(hospitals);
	}
	
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getByID(@PathVariable("id") Long id)
	{
		Optional<Hospital> optionalData = hospitalService.getById(id);
		if(optionalData.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK)
								 .header("info", "Data retrieved succefully")
								 .body(optionalData.get());
		}
		else {
			ApiRepository apiRepository = new ApiRepository();
			apiRepository.setStatusCode(HttpStatus.NOT_FOUND.value());
			apiRepository.setErrorMessage("employee not found with the id"+id);
			apiRepository.setTimestamp(LocalDateTime.now());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .header("info", "No data present")
								 .body(apiRepository);
		}
		
	}
	
	//getting the details based on location
			@GetMapping("/getbylocation/{location}")
				public ResponseEntity<?> getByEmail(@PathVariable("location") String location)
				{
					Optional<List<Hospital>> optionallocation = hospitalService.getByLocation(location);
					if(optionallocation.isPresent())
					{
						return ResponseEntity.status(HttpStatus.OK)
											 .header("info", "Data retrieved succefully")
											 .body(optionallocation.get());
					}
					else {
						ApiRepository apiRepository = new ApiRepository();
						apiRepository.setStatusCode(HttpStatus.NOT_FOUND.value());
						apiRepository.setErrorMessage("employee not found with the email"+location);
						apiRepository.setTimestamp(LocalDateTime.now());
						
						return ResponseEntity.status(HttpStatus.NOT_FOUND)
											 .header("info", "No data present")
											 .body(apiRepository);
					}
					
				}
			
			
			
			@GetMapping("/gethospital/rating/{location}/{minRating}/{maxRating}")
			public ResponseEntity<?> getBySalaryRange(@PathVariable("location")String location,@PathVariable("minRating") Double minRating, @PathVariable("maxRating") Double maxRating) {
			    Optional<List<Hospital>> optionalHospital = hospitalService.getByRating(location,minRating, maxRating);
			    
			    if (optionalHospital.isPresent()) { 
			        return ResponseEntity.status(HttpStatus.OK)
			                             .header("info", "Data retrieved successfully")
			                             .body(optionalHospital.get());
			    } else {
			        ApiRepository apiRepository = new ApiRepository();
			        apiRepository.setStatusCode(HttpStatus.NOT_FOUND.value());
			        apiRepository.setErrorMessage("No employees found in the salary range: " + minRating + " - " + maxRating);
			        apiRepository.setTimestamp(LocalDateTime.now());

			        return ResponseEntity.status(HttpStatus.NOT_FOUND)
			                             .header("info", "No data present")
			                             .body(apiRepository);
			    }
			}
			
			
			
			
			//getting the data based on rating and Asscending order
			@GetMapping("/hospitals/sort-by-rating")
			public ResponseEntity<List<Hospital>> findAllDetails(){
				List<Hospital> hospitals = hospitalService.findAllDetails();
				return ResponseEntity.status(HttpStatus.OK)
						             .header("info", "Data retrieved succefully")
						             .body(hospitals);
			}
			
			
			
			
			
			
			
			
			
			
			@DeleteMapping("/delete-by-id/{id}")
			public ResponseEntity<ApiRepository> deleteById(@PathVariable("id") Long id){
				
			boolean status = hospitalService.deleteById(id);
			 
			if(status)
			{
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
									 .header("info", "Data deleted based on id= "+id)
									 .build();
			}
			else {
				ApiRepository apiRepository = new ApiRepository();
				
				apiRepository.setStatusCode(HttpStatus.NOT_FOUND.value());
				apiRepository.setErrorMessage("Employee with "+id+" is not found");
				apiRepository.setTimestamp(LocalDateTime.now());
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
									 .header("info", "Data not deleted")
									 .body(apiRepository);
			}
				
			}
			
			
			@DeleteMapping("/delete-by-location/{location}")
			public ResponseEntity<?> deleteByLocation(@PathVariable("location") String location)
			{
				boolean status = hospitalService.deleteByLocation(location);
				
				if(status)
				{
					return ResponseEntity.status(HttpStatus.OK)
							             .header("info", "data deleted succefully")
							             .build();
				}
				else {
					ApiRepository apiRepository = new ApiRepository();
					
					apiRepository.setStatusCode(HttpStatus.NOT_FOUND.value());
					apiRepository.setErrorMessage("Employee with "+location+" is not found");
					apiRepository.setTimestamp(LocalDateTime.now());
					
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
										 .header("info", "Data not deleted")
										 .body(apiRepository);
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			@PostMapping("/savehospital") 
//			public ResponseEntity<Hospital> saveHospitalAndAddress(@RequestBody Hospital hospital)
//			{
//				Hospital hospital2= hospitalService.saveHospitalAndAddress(hospital);
//				
//				return ResponseEntity.status(HttpStatus.CREATED)
//									 .header("info", "Hospital and address is created succefully")
//									 .body(hospital2);
//			}
			
			
			
			
			//updating the data based on id
			@PutMapping("/update/{id}")
			public ResponseEntity<?> updateDetails(@RequestBody Hospital newHospital,@PathVariable("id") long id)
			{
				Hospital hospital = hospitalService.updateDetails(newHospital,id);
				if(hospital != null)
				{
					return ResponseEntity.status(HttpStatus.OK)
							             .header("info", "Data Updated succefully")
							             .body(hospital);
				}
				else {
					ApiRepository apiRepository = new ApiRepository();
					apiRepository.setStatusCode(HttpStatus.NOT_FOUND.value());
			        apiRepository.setErrorMessage("No Hospital is found: " + id);
			        apiRepository.setTimestamp(LocalDateTime.now()); 
			        return ResponseEntity.status(HttpStatus.NOT_FOUND)
			        		             .header("info","Data is not updated")
			        		             .body(apiRepository);
				}
			}
			
			
			
			@PatchMapping("/updatepartial-id/{id}")
			public ResponseEntity<?> partialUpdate(@RequestBody Map<String,Object> updateDetails, @PathVariable("id") Long id)
			{
				Hospital updateEmployee = hospitalService.partialUpdate(updateDetails, id);
				
				if(updateEmployee != null)
				{
					return ResponseEntity.status(HttpStatus.OK)
							             .header("info", "Data updated succedully")
							             .body(updateDetails);
				}
				else {
					ApiRepository apiRepository = new ApiRepository();
					apiRepository.setErrorMessage("Employee with "+id+" is not found");
	      			apiRepository.setStatusCode(HttpStatus.NOT_FOUND.value());
	      			apiRepository.setTimestamp(LocalDateTime.now());
	      			
	      			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	      					             .header("info", "Data is not updated")
	      					             .body(apiRepository);
				}
			}
			
			
//			@GetMapping("/hospitals/{location}/{minRating}/{maxRating}")
//			public ResponseEntity<List<Hospital>> getHospitalsBasedOnRating(@PathVariable("location") String location,@PathVariable("minRating") double minRating, @PathVariable("maxRating") double maxRating)
//			{
//				hospitalService.getHospitalsBasedOnRating(location,minRating,maxRating);
//			}
}
