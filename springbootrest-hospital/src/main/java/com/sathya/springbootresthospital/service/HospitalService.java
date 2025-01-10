package com.sathya.springbootresthospital.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.springbootresthospital.address.Address;
import com.sathya.springbootresthospital.doctor.Doctors;
import com.sathya.springbootresthospital.hospital.Hospital;
import com.sathya.springbootresthospital.repository.HospitalRepository;

@Service
public class HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;
   
	public Hospital saveHospital(Hospital hospital) {
		
		return hospitalRepository.save(hospital);
		
	}

	
	public List<Hospital> saveHospitals(List<Hospital> hospital) {
		return hospitalRepository.saveAll(hospital);
		
	}
	
	
	public List<Hospital> getAllDetails() {
		List<Hospital> hospitals = hospitalRepository.findAll();
		return hospitals;
	}


	public Optional<Hospital> getById(Long id) {
		Optional<Hospital> hospital = hospitalRepository.findById(id);
		return hospital;
	}


	public Optional<List<Hospital>> getByLocation(String location) {
		Optional<List<Hospital>> hospital = hospitalRepository.findByLocation(location);
		return hospital;
	}


	public Optional<List<Hospital>> getByRating(String location,Double minRating, Double maxRating) {
		Optional<List<Hospital>> hospital = hospitalRepository.findByRatingBetween(location,minRating, maxRating);
	    return hospital;
	}


	public boolean deleteById(Long id) {
		if(hospitalRepository.existsById(id))
		{
		hospitalRepository.deleteById(id);
		return true;
		}
		return false;
	}

	
	//deleting the record based on email
	public boolean deleteByLocation(String location) {
		if(hospitalRepository.existsByLocation(location))
		{
			hospitalRepository.deleteByLocation(location);
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//put or update the data based on id
	public Hospital updateDetails(Hospital newHospital, long id) {
		Optional<Hospital> hospital= hospitalRepository.findById(id);
		
		if(hospital.isPresent())
		{
		Hospital existingHospital = hospital.get();
		existingHospital.setName(newHospital.getName());
		existingHospital.setLocation(newHospital.getLocation());
		existingHospital.setRating(newHospital.getRating());
		
		return hospitalRepository.save(existingHospital);
		}
		else {
			return null;
		}
	}


	public List<Hospital> findAllDetails() {
		
		List<Hospital> hospitals = hospitalRepository.findAll();
		return hospitals;
	}


	@SuppressWarnings("unchecked")
	public Hospital partialUpdate(Map<String, Object> updateDetails, Long id) {
		
		Optional<Hospital> optionalData= hospitalRepository.findById(id);
		
		if(optionalData.isPresent())
		{
               Hospital existingHospital = optionalData.get();
			
			if(updateDetails.containsKey("name"))
			{
				existingHospital.setName((String)updateDetails.get("name"));
			}
			
			if(updateDetails.containsKey("location"))
			{
				existingHospital.setLocation((String)updateDetails.get("location"));
			}

			if(updateDetails.containsKey("rating"))
			{
				existingHospital.setRating((Double)updateDetails.get("rating"));
			}
			if (updateDetails.containsKey("address")) {
	            existingHospital.setAddress((Address)updateDetails.get("address"));//Address((Address) updateDetails.get("address"));
	        }
	        if (updateDetails.containsKey("doctors")) {
	            existingHospital.setDoctors((List<Doctors>)updateDetails.get("doctors")); 
	        }
	        
	        return hospitalRepository.save(existingHospital);
		}
		else {
		return null;
		}
		
	}


	//getting the details based on min and maxRating
//	public List<Hospital> getByRating(String location, double minRating, double maxRating) {
//		
//	List<Hospital> hospitals = hospitalRepository.findByRatingBetween(minRating,maxRating);
//	return hospitals;
//	}

	




	

	
	 

//	public Hospital saveHospitalAndAddress(Hospital hospital) {
//		
//	  return hospitalRepository.save(hospital);
//	}



	
}
