package controller.admin;

import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import validators.ActivityValidator;
import classes.Activity;
import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin/activityManagement")
public class ActivityManagement extends AbstractController{

	
	@RequestMapping
	public String activityManagementPage(Model model){
		LinkedList<Activity> activityList = new LinkedList<Activity>(service.getAllActivities());
		
		model.addAttribute("activityList", activityList);
		
		return "/admin/activityManagement";
	}
	
	@RequestMapping(value="/add")
	public String activityManagementAdd(Model model){
		model.addAttribute("activity", new Activity());
		
		return "/admin/activityManagement/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String activityManagementAdd(@ModelAttribute("activity") Activity activity, BindingResult bindingResult){		
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "/activityManagement/add";
		
		service.addActivity(activity);
		
		return "redirect:/activityManagement/add";
	}
	
	@RequestMapping(value="/disable/{actId}")
	public String activityManagementDisable(@PathVariable int actId){
		service.inactiveActivity(actId);
		
		return "redirect:/activityManagement/add";
	}
	
	@RequestMapping(value="/modify/{actId}")
	public String activityManagementModify(@PathVariable int actId, Model model){		
		model.addAttribute("activity", service.getActivity(actId));
		
		return "activityManagement/modify/"+actId;
	}
	
	@RequestMapping(value="/modify/{actId}", method=RequestMethod.POST)
	public String activityManagementModify(@PathVariable int actId, @ModelAttribute("activity") Activity activity, BindingResult bindingResult){		
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "/actiivityManagement/modify/"+actId;
		
		return "redirect:/activityManagement";
	}
	
	@RequestMapping(value="/onlyActive")
	public String activityPageActive(Model model){
		model.addAttribute("activityList", service.getAllActivitiesActive());
		
		return "admin/activityManagement/onlyActive";
	}
	
	@RequestMapping(value="/onlyInactive")
	public String activityPageInactive(Model model){
		model.addAttribute("activityList", service.getAllActivitiesInactive());
		
		return "admin/activityManagement/onlyInactive";
	}
}
