package controller.admin;

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

import service.LogicLayer;
import validators.ActivityValidator;
import validators.InstructorValidator;
import validators.SessionValidator;
import classes.Activity;
import classes.Instructor;
import classes.User;
import comparators.sortActivityActive;
import comparators.sortActivityDate;
import comparators.sortActivityId;
import comparators.sortActivityName;
import comparators.sortInstructorActive;
import comparators.sortInstructorName;
import comparators.sortInstructorSurname;
import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin/activityManagement")
public class ActivityManagement extends AbstractController{

	
	@RequestMapping
	public String activityManagementPage(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";

		
		LinkedList<Activity> activityList = new LinkedList<Activity>(service.getAllActivities());
		
		model.addAttribute("activityList", activityList);
		
		return "/admin/activityManagement";
	}
	
	@RequestMapping(value="/add")
	public String activityManagementAdd(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		model.addAttribute("activity", new Activity());
		
		return "/activityManagement/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String activityManagementAdd(@ModelAttribute("activity") Activity activity, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "/activityManagement/add";
		
		service.addActivity(activity);
		
		return "redirect:/activityManagement/add";
	}
	
	@RequestMapping(value="/disable/{actId}")
	public String activityManagementDisable(@PathVariable int actId, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		service.inactiveActivity(actId);
		
		return "redirect:/activityManagement/add";
	}
	
	@RequestMapping(value="/modify/{actId}")
	public String activityManagementModify(@PathVariable int actId, Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		model.addAttribute("activity", service.getActivity(actId));
		
		return "activityManagement/modify/"+actId;
	}
	
	@RequestMapping(value="/modify/{actId}", method=RequestMethod.POST)
	public String activityManagementModify(@PathVariable int actId, @ModelAttribute("activity") Activity activity, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "redirect:/restricted";
		
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "/actiivityManagement/modify/"+actId;
		
		return "redirect:/activityManagement";
	}
	
	@RequestMapping(value="/onlyActive")
	public String activityPageActive(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		
		
		model.addAttribute("activityList", service.getAllActivitiesActive());
		
		return "admin/activityManagement/onlyActive";
	}
	
	@RequestMapping(value="/onlyInactive")
	public String activityPageInactive(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		
		
		model.addAttribute("activityList", service.getAllActivitiesActive());
		
		return "admin/activityManagement/onlyInactive";
	}
}
