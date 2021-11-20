package com.example.personaltrainer.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Focus {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long focusid;
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="focus")
    private List<Workout> workouts;
	
	
	public Focus () {
	}

	public Focus(String name) {
		super();
		this.name = name;
	}
	

	public List<Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}
	
	public long getFocusId() {
		return focusid;
	}

	public void setFocusId(long focusId) {
		this.focusid = focusId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Focus [focusid=" + focusid + ", name=" + name + "]";
	}
}
