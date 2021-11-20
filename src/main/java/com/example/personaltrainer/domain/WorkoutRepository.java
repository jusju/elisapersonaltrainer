package com.example.personaltrainer.domain;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface WorkoutRepository extends CrudRepository<Workout, Long>{
	
	// FETCH WORKOUT BY TITLE
	List<Workout> findByTitle(@Param("title")String title);
	
	// FETCH WORKOUT BY DATE
	List<Workout> findByDate(LocalDate date);
	
	// FETCH WORKOUT BY USER
	List<Workout> findByUser(User user);
}
