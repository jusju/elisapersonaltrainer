package com.example.personaltrainer;

import java.time.LocalDate;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.personaltrainer.domain.FocusRepository;
import com.example.personaltrainer.domain.Workout;
import com.example.personaltrainer.domain.WorkoutRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WorkoutRepositoryTests {
	@Autowired
	private WorkoutRepository wrepository;
	@Autowired
	private FocusRepository frepository;
	
	@Test
	public void findByTitleShouldReturnFocus() {
		List<Workout> workouts = wrepository.findByTitle("Abs & Core");
		assertThat(workouts).hasSize(1);
		assertThat(workouts.get(0).getFocus().getName()).isEqualTo("Strength");
	}
	
	@Test
	public void createNewWorkout() {
		
		Workout workout = new Workout("TestUser","Spinning", LocalDate.of(2021, 1, 24), 40, 
		frepository.findByName("Endurance").get(0));
		wrepository.save(workout);
		assertThat(workout.getId()).isNotNull();
	}
	
	
	}