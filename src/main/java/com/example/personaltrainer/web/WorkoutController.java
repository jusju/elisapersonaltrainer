package com.example.personaltrainer.web;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.personaltrainer.domain.Focus;
import com.example.personaltrainer.domain.FocusRepository;
import com.example.personaltrainer.domain.Workout;
import com.example.personaltrainer.domain.WorkoutRepository;
import com.example.personaltrainer.domain.User;
import com.example.personaltrainer.domain.UserRepository;


@Controller
public class WorkoutController {
	
	@Autowired
	private WorkoutRepository wrepository;
	
	@Autowired 
	private FocusRepository frepository;
	
	@Autowired
	private UserRepository urepository;
	
		
		@RequestMapping("/")
		public String hello(Model model) {
			return "index";
		}
	
		// LOGIN
		@RequestMapping(value="/login")
		public String login() {
			return "login";
		}    
	
		// SHOW ALL WORKOUTS IN THYMELEAF TEMPLATE
		@RequestMapping(value = "/workoutlist")
		public String workoutList(Model model) {
			
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User userNow = urepository.findByUsername(username);
			System.out.println("ELISA START");
			System.out.println(user);
			System.out.println("ELISA END");
		model.addAttribute("workouts", wrepository.findByUser(userNow));
		return "workoutlist";
		}
		
		// RESTFUL SERVICE TO GET ALL WORKOUTS
		@RequestMapping(value="/workouts", method = RequestMethod.GET)
		public @ResponseBody List<Workout> workoutListRest() {
		return (List<Workout>) wrepository.findAll();
		}
		
		// RESTFUL SERVICE TO GET WORKOUT BY ID
	    @RequestMapping(value="/workout/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Workout> findBookRest(@PathVariable("id") Long workoutId) {	
	    	return wrepository.findById(workoutId);
	    }       
		
		// ADD WORKOUT
		@RequestMapping(value = "/add")
		public String addWorkout(Model model) {
		model.addAttribute("workout", new Workout("username", "title", LocalDate.now(), 0, new Focus("focus")));


		model.addAttribute("focuses", frepository.findAll());
		return "addworkout";
		}
		
		// SAVE WORKOUT
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Workout workout) {
			System.out.println("SAVE ENDPOINT");
			System.out.println(workout);
			wrepository.save(workout);
			return "redirect:workoutlist";
		}
		
		// DELETE WORKOUT
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('ADMIN')")
		public String deleteBook(@PathVariable("id") Long workoutId, Model model) {
		 wrepository.deleteById(workoutId);
		 return "redirect:../workoutlist";
		}
		
		// EDIT WORKOUT
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public String editWorkout(@PathVariable("id") Long workoutId, Model model){
		model.addAttribute("workout", wrepository.findById(workoutId));
		model.addAttribute("focuses", frepository.findAll());
		return "editworkout";
		}
		
}
