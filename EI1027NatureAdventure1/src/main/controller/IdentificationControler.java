package main.controller;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Validators.UserValidator;
import service.LogicLayer;


@Controller
public class IdentificationControler {
	
	private LogicLayer service;

	@Autowired
	public void setServicio(LogicLayer service){
		this.service = service;
	}
	
	@RequestMapping(value="/login")
	public String login(Model model){
		model.addAttribute("user", new User());
		return "login";
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("lastPage") String lastPage, @ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session){
		//Correct field format validator
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, bindingResult);
		
		if(bindingResult.hasErrors()) return "login";
		
		user = service.loginUser(user.getName(), user.getPassword());
		
		//Password check 
		if(user==null) {
			bindingResult.rejectValue("password", "badpw", "Incorrect password"); 
			return lastPage;
		}
		
		session.setAttribute("user", user);
		
		return "redirect:index.html";
		
	}
	
	
	@RequestMapping(value="/logout")
	public String logout(@ModelAttribute("lastPage") String lastPage, HttpSession session){
		session.invalidate();
		return lastPage;		
	}
}
