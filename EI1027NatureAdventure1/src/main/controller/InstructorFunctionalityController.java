package main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import classes.Activity;
import classes.Instructor;
import classes.User;
import validators.UserValidator;
import service.LogicLayer;


@Controller
public class InstructorFunctionalityController {
	
	private LogicLayer service;


	/**
	 * Method that allows automatic independence injection
	 * @param service Service to inject
	 */
	public void setService(LogicLayer service){
		this.service = service;
	}
	
	
	/**
	 * Method that injects the activity list assigned to a monitor
	 * @param model Model of the page
	 * @param session Session of the user
	 * @return Main monitors page
	 */
	@RequestMapping(value="/monitor")
	public String monitorPage(Model model, HttpSession session){
		//If the user is not logged
		User user = (User) session.getAttribute("user");
		if(user == null) return "redirect:login.jsp";
		
		//If the user is not an instructor
		if(user.getType() != 1) return "redirect:restricted.jsp";
		
		//Get the activities of the instructor
		Instructor instructor = service.getInstructor(user);
		List<Activity> instructorActivities = service.getAllActivities(instructor);
		
		//Attach the list to the model
		model.addAttribute("monitorActivities", instructorActivities);
		return "monitor";
		
	}

}
