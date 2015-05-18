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
import classes.Instructor;
import classes.User;
import validators.SessionValidator;
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
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.jsp";;
		if(!user.hasPermissions(0)) return "redirect:restricted.jsp";
		
		return "admin";
		
	}
	
	
	@RequestMapping(value="/instructorManagement")
	public String instructorsPage(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.jsp";;
		if(!user.hasPermissions(0)) return "redirect:restricted.jsp";
		
		return "admin/instructorManagement";
		
	}
	
	@RequestMapping(value="/instructorManagement/add")
	public String instructorsAddPage(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.jsp";;
		if(!user.hasPermissions(0)) return "redirect:restricted.jsp";
		
		//Instance new instructor used by the form
		model.addAttribute("instructor", new Instructor());
		
		return "admin/instructorManagement/add";
	}
	
	@RequestMapping(value="/instructorManagement/add", method=RequestMethod.POST)
	public String instructorsAddPage(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.jsp";;
		if(!user.hasPermissions(0)) return "redirect:restricted.jsp";
		
		//Check the instructor input format
		ValidatorInstructor validator = new ValidatorInstructor();
		validator.validate(instructor, bindingResult);
		
		if(bindingResult.hasErrors()) return "admin/instructorManagement/add";
		
		service.addInstructor(instructor);
		
		return "admin/instructorManagement/add";
	}
	
	@RequestMapping(value="/instructorManagement/disable")
	public String instructorsDisablePage(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.jsp";;
		if(!user.hasPermissions(0)) return "redirect:restricted.jsp";
		
		//Instance new instructor used by the form
		model.addAttribute("instructor", new Instructor());
		
		return "admin/instructorManagement/disable";
	}
	
	@RequestMapping(value="/instructorManagement/disable", method=RequestMethod.POST)
	public String instructorsDisablePage(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.jsp";;
		if(!user.hasPermissions(0)) return "redirect:restricted.jsp";

		service.inactiveInstructor(instructor);
		
		return "admin/instructorManagement/disable";
	}
	
	@RequestMapping(value="/instructorManagement/modify")
	public String instructorsModifyPage(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.jsp";;
		if(!user.hasPermissions(0)) return "redirect:restricted.jsp";
		
		return "admin/instructorManagement/modify";
	}
	
	@RequestMapping(value="/instructorManagement/modify", method=RequestMethod.POST)
	public String instructorsModifyPage(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.jsp";;
		if(!user.hasPermissions(0)) return "redirect:restricted.jsp";

		service.updateInstructor(instructor);
		
		return "admin/instructorManagement/modify";
	}
	
	

}
