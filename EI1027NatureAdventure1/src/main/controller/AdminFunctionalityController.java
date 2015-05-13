package main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import classes.Client;
import classes.User;
import validators.UserValidator;
import service.LogicLayer;


@Controller
@RequestMapping("/admin")
public class AdminFunctionalityController {
	
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
	 * Method that checks if the user is admin
	 * @param session User session
	 * @return The admin main page
	 */
	@RequestMapping(value="/")
	public String adminPage(HttpSession session){
		//Check if the user is logged
		User user = (User) session.getAttribute("user");
		if(user == null) return "redirect:login.jsp";
		
		//Check if the user has permissions
		if(user.getType() != 0) return "redirect:restricted.jsp";
		
		return "account";
		
	}
	
	
	@RequestMapping(value="/monitorManagment")
	
	

}
