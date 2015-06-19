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


/**
 * Controller that managed the admin activity management page.
 */
@Controller
@RequestMapping("/admin/activityManagement")
public class ActivityManagement extends AbstractController{

	
	/**
	 * Returns the base activity management page.
	 * @param model Injected model
	 * @return activityManagement.jsp with the model modified.
	 */
	@RequestMapping
	public String activityManagementPage(Model model){
		LinkedList<Activity> activityList = new LinkedList<Activity>(service.getAllActivities());
		model.addAttribute("activityList", activityList);
		
		return "admin/activityManagement";
	}
	
	/**
	 * Returns the base activity management page with the results of any operation
	 * @param rezCode Result code (see AbstractController)
	 * @param idCode Id of the activity
	 * @param model Injected model
	 * @return activityManagement.jsp with the model modified.
	 */
	@RequestMapping(value="/{rezCode}&{idCode}")
	public String activityManagementPage(@PathVariable int rezCode, @PathVariable int idCode, Model model){
		LinkedList<Activity> activityList = new LinkedList<Activity>(service.getAllActivities());
		
		model.addAttribute("error",rezCode);
		model.addAttribute("id", idCode);
		model.addAttribute("activityList", activityList);
		
		return "admin/activityManagement";
	}
	
	/**
	 * New activity form
	 * @param model Injected model
	 * @return add.jsp with the model modified.
	 */
	@RequestMapping(value="/add")
	public String activityManagementAdd(Model model){
		//New writable activity
		model.addAttribute("activity", new Activity());
		
		return "admin/activityManagement/add";
	}
	
	/**
	 * Managed the data send by post on adding a new activity
	 * @param activity The activity
	 * @param bindingResult Error handler
	 * @param locale Language
	 * @return The activityManagemeng with the added code and the activity
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String activityManagementAdd(@ModelAttribute("activity") Activity activity, BindingResult bindingResult, Locale locale){		
		//Checks if the data given is correct
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "admin/activityManagement/add";
		
		//Try to add the activity. Error means that already exists.
		try{
			service.addActivity(activity);
		} catch(Exception e){
			if(locale.getLanguage().equals("en")) bindingResult.rejectValue("idAct", "repAct", "Activity already existing");
			if(locale.getLanguage().equals("es")) bindingResult.rejectValue("idAct", "repAct", "Actividad ya existente");
			return "admin/activityManagement/add";
		}
		
		
		return "redirect:/admin/activityManagement/"+RESULT_ADD_OR_ACCEPT+"&"+activity.getIdAct()+".html";
	}
	
	/**
	 * Disables an activity
	 * @param actId Id of the activity
	 * @return Redirect to activityManagement with the modified code and the id of activity
	 */
	@RequestMapping(value="/disable/{actId}")
	public String activityManagementDisable(@PathVariable int actId){
		service.inactiveActivity(actId);
		
		return "forward:/admin/activityManagement/"+RESULT_MODIFY+"&"+actId+".html";
	}
	
	/**
	 * Enables an activity
	 * @param actId Id of the activity
	 * @return Redirect to activityManagement with the modified code and the id of activity
	 */
	@RequestMapping(value="/enable/{actId}")
	public String activityManagementEnable(@PathVariable int actId){
		service.activateActivity(actId);
		
		
		return "forward:/admin/activityManagement/"+RESULT_MODIFY+"&"+actId+".html";
	}
	
	/**
	 * Form to modify the activity
	 * @param actId Id of the activity
	 * @param model Injected model
	 * @return Modify.jsp
	 */
	@RequestMapping(value="/modify/{actId}")
	public String activityManagementModify(@PathVariable int actId, Model model){		
		model.addAttribute("activity", service.getActivity(actId));
		
		return "admin/activityManagement/modify";
	}
	
	/**
	 * Processes the data gathered by the activity modify form
	 * @param actId Id of the activity
	 * @param bindingResult Error handler
	 * @return ActivityManagement page with the modify code and the id of the activity
	 */
	@RequestMapping(value="/modify/{actId}", method=RequestMethod.POST)
	public String activityManagementModify(@ModelAttribute("activity") Activity activity, BindingResult bindingResult){	
		
		//Check if the activity data is correctly entered.
		new ActivityValidator().validate(activity, bindingResult);
		if(bindingResult.hasErrors()) return "admin/activityManagement/modify";
		
		service.updateActivity(activity);
		
		return "forward:/admin/activityManagement/"+RESULT_MODIFY+"&"+activity.getIdAct()+".html";
	}
	
	/**
	 * Returns the main page with only the active activities
	 * @param model Injected model
	 * @return OnlyActive.jsp
	 */
	@RequestMapping(value="/onlyActive")
	public String activityPageActive(Model model){ 
		model.addAttribute("activityList", service.getAllActivitiesActive());
		
		return "admin/activityManagement/onlyActive";
	}
	
	/**
	 * Returns the main page with only the inactive activities
	 * @param model Injected model
	 * @return OnlyInctive.jsp
	 */
	@RequestMapping(value="/onlyInactive")
	public String activityPageInactive(Model model){
		model.addAttribute("activityList", service.getAllActivitiesInactive());
		
		return "admin/activityManagement/onlyInactive";
	}
}
