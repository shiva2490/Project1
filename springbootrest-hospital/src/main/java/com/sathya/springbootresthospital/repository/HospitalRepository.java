package com.sathya.springbootresthospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sathya.springbootresthospital.hospital.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{

	Optional<List<Hospital>> findByLocation(String location);

	

	public boolean existsByLocation(String location);
	
	
	
	@Transactional
	@Modifying
	public void deleteByLocation(String location);

	
	
	@Query("SELECT h FROM Hospital h WHERE h.rating > :minRating AND h.rating < :maxRating AND h.location = :location")
	Optional<List<Hospital>> findByRatingBetween(@Param("location") String location, 
	                                             @Param("maxRating") double maxRating, 
	                                             @Param("minRating") double minRating);
//	Optional<List<Hospital>> findByRatingBetween(String location, Double minRating, Double maxRating);


	@Query(value = "SELECT * FROM HOSPITAL ORDER BY RATING ASC", nativeQuery = true)
    List<Hospital> findAll();
	



}
