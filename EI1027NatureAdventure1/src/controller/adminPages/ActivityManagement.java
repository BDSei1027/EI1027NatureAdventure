package controller.adminPages;

import java.util.LinkedList;
import java.util.Locale;

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

//TODO Parm: "error":integer =  0 add | 1 update
//TODO Parm: "id"   :integer = [0,*] activity id
	
	
	@RequestMapping
	public String activityManagementPage(Model model){
		LinkedList<Activity> activityList = new LinkedList<Activity>(service.getAllActivities());
		model.addAttribute("activityList", activityList);
		
		return "admin/activityManagement";
	}
	
	@RequestMapping(value="/{rezCode}&{idCode}")
	public String activityManagementPage(@PathVariable int rezCode, @PathVariable int idCode, Model model){
		LinkedList<Activity> activityList = new LinkedList<Activity>(service.getAllActivities());
		
		model.addAttribute("error",rezCode);
		model.addAttribute("id", idCode);
		model.addAttribute("activityList", activityList);
		
		return "admin/activityManagement";
	}
	
	@RequestMapping(value="/add")
	public String activityManagementAdd(Model model){
		model.addAttribute("activity", new Activity());
		
		return "admin/activityManagement/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String activityManagementAdd(@ModelAttribute("activity") Activity activity, BindingResult bindingResult, Locale locale){		
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "admin/activityManagement/add";
		
		try{
			service.addActivity(activity);
		} catch(Exception e){
			if(locale.getLanguage().equals("en")) bindingResult.rejectValue("idAct", "repAct", "Activity already existing");
			if(locale.getLanguage().equals("es")) bindingResult.rejectValue("idAct", "repAct", "Actividad ya existente");
			return "admin/activityManagement/add";
		}
		
		
		return "redirect:/admin/activityManagement/"+0+"&"+activity.getIdAct()+".html";
	}
	
	@RequestMapping(value="/disable/{actId}")
	public String activityManagementDisable(@PathVariable int actId){
		service.inactiveActivity(actId);
		
		return "forward:/admin/activityManagement/"+1+"&"+actId+".html";
	}
	
	@RequestMapping(value="/enable/{actId}")
	public String activityManagementEnable(@PathVariable int actId){
		service.activateActivity(actId);
		
		
		return "forward:/admin/activityManagement/"+1+"&"+actId+".html";
	}
	
	@RequestMapping(value="/modify/{actId}")
	public String activityManagementModify(@PathVariable int actId, Model model){		
		model.addAttribute("activity", service.getActivity(actId));
		
		return "admin/activityManagement/modify";
	}
	
	@RequestMapping(value="/modify/{actId}", method=RequestMethod.POST)
	public String activityManagementModify(@PathVariable int actId, @ModelAttribute("activity") Activity activity, BindingResult bindingResult){	
		
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "admin/activityManagement/modify";
		
		service.updateActivity(activity);
		
		return "forward:/admin/activityManagement/"+1+"&"+activity.getIdAct()+".html";
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
