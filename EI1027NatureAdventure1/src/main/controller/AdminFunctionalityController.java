package main.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import comparators.sortActivityActive;
import comparators.sortActivityDate;
import comparators.sortActivityId;
import comparators.sortActivityName;
import comparators.sortInstructorActive;
import comparators.sortInstructorName;
import comparators.sortInstructorSurname;
import service.LogicLayer;
import validators.ActivityValidator;
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
	
	
	
	
	
	
	
	
	
	
	
	
//INSTRUCTOR MANAGEMENT PAGE ---------------------------------------------------------------------------------------
	@RequestMapping(value="/instructorManagement")
	public String instructorsPage(@ModelAttribute("sort") String sort, Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		LinkedList<Instructor> instructorList = new LinkedList<Instructor>(service.getAllInstructors());
		sortInstructors(sort, instructorList);
		
		model.addAttribute("instructorList", instructorList);
		
		return "admin/instructorManagement";
		
	}


	private void sortInstructors(String sortMode, LinkedList<Instructor> instructorList) {
		if(sortMode != null) switch(sortMode){
			case "ASCname":
				Collections.sort(instructorList, new sortInstructorName('a'));
				break;
			case "ASCsurname":
				Collections.sort(instructorList, new sortInstructorSurname('a'));
				break;
			case "ASCactive":
				Collections.sort(instructorList, new sortInstructorActive('a'));
				break;
			case "DESCname":
				Collections.sort(instructorList, new sortInstructorName('d'));
				break;
			case "DESCsurname":
				Collections.sort(instructorList, new sortInstructorSurname('d'));
				break;
			case "DESCactive":
				Collections.sort(instructorList, new sortInstructorActive('d'));
				break;
		}
	}
	
	@RequestMapping(value="/instructorManagement/add")
	public String instructorsAddPage(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		//Instance new instructor used by the form
		model.addAttribute("instructor", new Instructor());
		
		return "admin/instructorManagement/add";
	}
	
	@RequestMapping(value="/instructorManagement/add", method=RequestMethod.POST)
	public String instructorsAddPage(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		//Check the instructor input format
		InstructorValidator validator = new InstructorValidator();
		validator.validate(instructor, bindingResult);
		
		if(bindingResult.hasErrors()) return "admin/instructorManagement/add";
		
		//Add the instructor
		service.addInstructor(instructor);
		
		// Create the user associated with the instructor using its telephone as password.
		User newUser = createUserFrom(instructor);
		service.addUser(newUser);
		
		return "redirect:/admin/instructorManagement.html";
	}


	private User createUserFrom(Instructor instructor) {
		User newUser = new User();
		newUser.setUser(instructor.getIdNumber());
		newUser.setPassword(instructor.getTelephone());
		newUser.setLanguage("EN");
		newUser.setType(1);
		return newUser;
	}
	

	@RequestMapping(value="/instructorManagement/disable/{idInstructor}")
	public String instructorsDisablePage(@PathVariable String idInstructor , HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		service.inactiveInstructor(idInstructor);
		
		return "redirect:/admin/instructorManagement.html";
	}
	
	@RequestMapping(value="/instructorManagement/enable/{idInstructor}")
	public String instructorsEnablePage(@PathVariable String idInstructor , HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		service.activeInstructor(idInstructor);
		
		return "redirect:/admin/instructorManagement.html";
	}


	@RequestMapping(value="/instructorManagement/modify/{idInstructor}", method=RequestMethod.GET)
	public String instructorsModifyPage(@PathVariable String idInstructor, Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		Instructor instructor = service.getInstructor(idInstructor);
		Collection<Activity> colActivities = service.getAllActivities(instructor); 
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("activities", colActivities);
		
		return "/admin/instructorManagement/modify";
	}
	
	@RequestMapping(value="/instructorManagement/modify/{idInstructor}", method=RequestMethod.POST)
	public String instructorsModifyPage(@PathVariable String idInstructor, @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:restricted.html";
		
		//Check errors
		new InstructorValidator().validate(instructor, bindingResult);
		if(bindingResult.hasErrors()) return "/admin/instructorManagement/modify/"+idInstructor;

		service.updateInstructor(instructor);
		
		return "redirect:/admin/instructorManagement.html";
	}

	@RequestMapping(value="/instructorManagement/modify/addActivity")
	public String instructorsAddActivity(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		model.addAttribute("idActivity", new Integer(0));
		model.addAttribute("idMonitor", new String());
		
		return "/admin/instructorManagement/modify/addActivity";
	}
	
	@RequestMapping(value="/instructorManagement/modify/addActivity", method=RequestMethod.POST)
	public String instructorsAddActivity(@ModelAttribute("idMonitor") String idMonitor, @ModelAttribute("idActivity") Integer idActivity, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";

		service.addInstructed(idMonitor, idActivity);
		
		return "redirect:/admin/instructorManagement/modify/addActivity";
	}

	@RequestMapping(value="/instructorManagement/modify/removeActivity/{idMonitor}&{idActivity}", method=RequestMethod.GET)
	public String instructorsRemoveActivity(@PathVariable String idMonitor, @PathVariable Integer idActivity, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";

		service.removeInstructed(idMonitor, idActivity);
		
		return "redirect:/admin/instructorManagement/modify/"+idMonitor+".html";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//ACTIVITIES MANAGEMENT PAGE ---------------------------------------------------------------------------------------
	@RequestMapping(value="/activitiesManagement/")
	public String activityManagementPage(@ModelAttribute("sortMode") String sort, Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";

		
		LinkedList<Activity> activityList = new LinkedList<Activity>(service.getAllActivities());
		sortActivities(sort, activityList);
		
		model.addAttribute("activityList", activityList);
		
		return "/activitiesManagement";
	}


	private void sortActivities(String sortMode, LinkedList<Activity> activityList) {
		if(sortMode != null) switch(sortMode){
			case "ASCname":
				Collections.sort(activityList, new sortActivityName('a'));
				break;
			case "ASCid":
				Collections.sort(activityList, new sortActivityId('a'));
				break;
			case "ASCactive":
				Collections.sort(activityList, new sortActivityActive('a'));
				break;
			case "ASCdate":
				Collections.sort(activityList, new sortActivityDate('a'));
				break;
			case "DESCname":
				Collections.sort(activityList, new sortActivityName('d'));
				break;
			case "DESCid":
				Collections.sort(activityList, new sortActivityId('d'));
				break;
			case "DESCactive":
				Collections.sort(activityList, new sortActivityActive('d'));
				break;
			case "DESdate":
				Collections.sort(activityList, new sortActivityDate('d'));
				break;
		}
		
	}
	
	@RequestMapping(value="/activitiesManagement/add")
	public String activityManagementAdd(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		model.addAttribute("activity", new Activity());
		
		return "/activitiesManagement/add";
	}
	
	@RequestMapping(value="/activitiesManagement/add", method=RequestMethod.POST)
	public String activityManagementAdd(@ModelAttribute("activity") Activity activity, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "/activitiesManagement/add";
		
		service.addActivity(activity);
		
		return "redirect:/activitiesManagement/add";
	}
	
	@RequestMapping(value="/activitiesManagement/disable/{actId}")
	public String activityManagementDisable(@PathVariable int actId, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		service.inactiveActivity(actId);
		
		return "redirect:/activitiesManagement/add";
	}
	
	@RequestMapping(value="/activitiesManagement/modify/{actId}")
	public String activityManagementModify(@PathVariable int actId, Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		model.addAttribute("activity", service.getActivity(actId));
		
		return "activitiesManagement/modify/"+actId;
	}
	
	@RequestMapping(value="/activitiesManagement/modify/{actId}", method=RequestMethod.POST)
	public String activityManagementModify(@PathVariable int actId, @ModelAttribute("activity") Activity activity, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted";
		
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "/actiiviteesmanagement/modify/"+actId;
		
		return "redirect:/activitiesManagement";
	}

	
	
}
