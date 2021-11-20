package com.example.personaltrainer.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Workout {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String title;
	private int duration;

	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "focusid")
    private Focus focus;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	public Workout() {}
	
	public Workout(User user, String title, LocalDate date, int duration, Focus focus) {
		super();
		this.title = title;
		this.date = date;
		this.duration = duration;
		this.focus = focus;
		this.user = user;
	}
	



	public Focus getFocus() {
		return focus;
	}

	public void setFocus(Focus focus) {
		this.focus = focus;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return "Workout [id=" + id + ", title=" + title + ", date=" + date + ", duration=" + duration + ", user=" + user + "]";
	}
}
