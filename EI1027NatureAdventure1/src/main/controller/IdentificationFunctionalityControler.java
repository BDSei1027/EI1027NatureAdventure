package main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.LogicLayer;
import validators.UserValidator;
import classes.User;


@Controller
public class IdentificationFunctionalityControler {
	
	private LogicLayer service;


	/**
	 * Method that allows automatic independence injection
	 * @param service Service to inject
	 */
	@Autowired
	public void setService(LogicLayer service){
		this.service = service;
	}
	
	/**
	 * Method that initializes a new user used by the form
	 * @param model Page model
	 * @return Login page
	 */
	@RequestMapping(value="/login")
	public String login(Model model){
		model.addAttribute("user", new User());
		return "login";
		
	}
	
	/**
	 * Method used to process the login form data
	 * @param user User data gathered by the form
	 * @param bindingResult Result of processing the form
	 * @param session Http session used to store the user information
	 * @return Page that requested the login or the main page
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session){
		//Correct field format validator
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, bindingResult);
		
		if(bindingResult.hasErrors()) return "login";
		
		user = service.loginUser(user.getUser(), user.getPassword());
		
		//Password check 
		if(user==null) {
			bindingResult.rejectValue("password", "badpw", "Incorrect password"); 
			return "login";
		}
		
		//Maintain the user data in the session
		session.setAttribute("user", user);
		
		//Get the page that called the login
		String nextPage = (String) session.getAttribute("nextPage");
		
		
		//Return the page that called the login or go to the main page
		if(nextPage != null) return "redirect:"+nextPage;
		return "redirect:/index.jsp";
		
	}
	
	
	/**
	 * Method used to logout the user
	 * @param session Http session that contains the user data
	 * @return Bye page
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "logout";		
	}
	
}
