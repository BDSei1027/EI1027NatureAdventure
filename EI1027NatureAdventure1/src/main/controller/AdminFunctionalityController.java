package main.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.LogicLayer;
import validators.InstructorValidator;
import validators.SessionValidator;
import classes.Activity;
import classes.Instructor;
import classes.User;


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
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";
		
		return "admin";
		
	}
	
	
	@RequestMapping(value="/instructorManagement")
	public String instructorsPage(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";
		
		model.addAttribute("instructorList", service.getAllInstructors());
		
		return "admin/instructorManagement";
		
	}
	
	@RequestMapping(value="/instructorManagement/add")
	public String instructorsAddPage(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";
		
		//Instance new instructor used by the form
		model.addAttribute("instructor", new Instructor());
		
		return "admin/instructorManagement/add";
	}
	
	@RequestMapping(value="/instructorManagement/add", method=RequestMethod.POST)
	public String instructorsAddPage(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";
		
		//Check the instructor input format
		InstructorValidator validator = new InstructorValidator();
		validator.validate(instructor, bindingResult);
		
		if(bindingResult.hasErrors()) return "admin/instructorManagement/add";
		
		service.addInstructor(instructor);
		
		// Crea el usuario para el instructor
		// Contraseña sera el telefono
		User newUser = new User();
		newUser.setUser(instructor.getIdNumber());
		newUser.setPassword(instructor.getTelephone());
		newUser.setLanguage("EN");
		newUser.setType(1);
		
		service.addUser(newUser);
		
		return "redirect:/admin/instructorManagement.html";
	}
	
	@RequestMapping(value="/instructorManagement/disable/{idInstructor}")
	public String instructorsDisablePage(@PathVariable String idInstructor , HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";
		
		service.inactiveInstructor(idInstructor);
		
		return "redirect:/admin/instructorManagement.html";
	}
	
	@RequestMapping(value="/instructorManagement/enable/{idInstructor}")
	public String instructorsEnablePage(@PathVariable String idInstructor , HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";
		
		service.activeInstructor(idInstructor);
		
		return "redirect:/admin/instructorManagement.html";
	}
	
	@RequestMapping(value="/instructorManagement/modify/{idInstructor}", method=RequestMethod.GET)
	public String instructorsModifyPage(@PathVariable String idInstructor, Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";
		
		Instructor instructor = service.getInstructor(idInstructor);
		Collection<Activity> colActivities = service.getAllActivities(instructor); 
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("activities", colActivities);
		
		return "/admin/instructorManagement/modify";
	}
	
	@RequestMapping(value="/instructorManagement/modify/{idInstructor}", method=RequestMethod.POST)
	public String instructorsModifyPage(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:restricted.html";
		
		//Check errors
		//TODO Falla este return siempre entra aqui
		if(bindingResult.hasErrors()) return "redirect:/admin/instructorManagement/modify/{idInstructor}.html";

		service.updateInstructor(instructor);
		
		return "redirect:/admin/instructorManagement.html";
	}
	
	@RequestMapping(value="/instructorManagement/modify/addActivity")
	public String instructorsAddActivity(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";
		
		model.addAttribute("idActivity", new Integer(0));
		model.addAttribute("idMonitor", new String());
		
		return "/admin/instructorManagement/modify/addActivity";
	}
	
	@RequestMapping(value="/instructorManagement/modify/addActivity", method=RequestMethod.POST)
	public String instructorsAddActivity(@ModelAttribute("idMonitor") String idMonitor, @ModelAttribute("idActivity") Integer idActivity, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";

		service.addInstructed(idMonitor, idActivity);
		
		return "redirect:/admin/instructorManagement/modify/addActivity";
	}

	@RequestMapping(value="/instructorManagement/modify/removeActivity/{idMonitor}&{idActivity}", method=RequestMethod.GET)
	public String instructorsRemoveActivity(@PathVariable String idMonitor, @PathVariable Integer idActivity, Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";

		service.removeInstructed(idMonitor, idActivity);
		
		return "redirect:/admin/instructorManagement/modify/{idMonitor}.html";
	}
}
