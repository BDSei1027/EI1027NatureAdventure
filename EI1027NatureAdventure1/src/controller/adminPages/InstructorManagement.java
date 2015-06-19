package controller.adminPages;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import validators.InstructorValidator;
import classes.Activity;
import classes.Instructor;
import classes.User;
import controller.basics.AbstractController;


/**
 * Controller that manages the instructors.
 */
@Controller
@RequestMapping("/admin/instructorManagement")
public class InstructorManagement extends AbstractController {

//TODO Parm: "error":integer =  0 add | 1 update | 2 delete
//TODO Parm: "id"   :String  = instructor id
	
	/**
	 * Returns the instructor management main page
	 * @param model Injected model
	 * @return InstructorManagement.jsp with the modified model
	 */
	@RequestMapping
	public String instructorsPage(Model model){
		LinkedList<Instructor> instructorList = new LinkedList<Instructor>(service.getAllInstructors());		
		model.addAttribute("instructorList", instructorList);
		
		return "admin/instructorManagement";
	}
	
	
	/**
	 * Returns the instructor management main page after an instructor operation
	 * @param rezCode Result code
	 * @param idCode The id ofthe instructor
	 * @param model Injected model
	 * @return InstructorManagement.jsp with the modified model
	 */
	@RequestMapping(value="/{rezCode}&{idCode}")
	public String instructorsPage(@PathVariable int rezCode, @PathVariable String idCode, Model model){
		LinkedList<Instructor> instructorList = new LinkedList<Instructor>(service.getAllInstructors());	
		
		model.addAttribute("instructorList", instructorList);
		model.addAttribute("error", rezCode);
		model.addAttribute("id", idCode);
		
		return "admin/instructorManagement";
	}
	
	/**
	 * Returns the page instructor management main page with only the active instructors
	 * @param model Injected model
	 * @return OnlyActive.jsp with the modified model
	 */
	@RequestMapping(value="/onlyActive")
	public String instructorsPageActive(Model model){
		model.addAttribute("instructorList", service.getAllInstructorsActive());
		
		return "admin/instructorManagement/onlyActive";
	}
	
	/**
	 * Returns the page instructor management main page with only the inactive instructors
	 * @param model Injected model
	 * @return OnlyInactive.jsp with the modified model
	 */
	@RequestMapping(value="/onlyInactive")
	public String instructorsPageInactive(Model model){
		model.addAttribute("instructorList", service.getAllInstructorsInacctive());
		
		return "admin/instructorManagement/onlyInactive";
	}
	
	/**
	 * Returns the add a new instructor form
	 * @param model Injected model
	 * @return Add.jsp with the modified model
	 */
	@RequestMapping(value="/add")
	public String instructorsAddPage(Model model){
		model.addAttribute("instructor", new Instructor());
		
		return "admin/instructorManagement/add";
	}
	
	/**
	 * Processes the data from a new instructor
	 * @param instructor The instructor object
	 * @param bindingResult Error handler
	 * @param locale Languaje
	 * @return Redirect to main page with the result code and the instructor id.
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String instructorsAddPage(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, Locale locale){
		//Check the instructor input format
		InstructorValidator validator = new InstructorValidator();
		validator.validate(instructor, bindingResult);
		
		if(bindingResult.hasErrors()) return "admin/instructorManagement/add";
		
		try{
			service.addInstructor(instructor);
		
			// Create the user associated with the instructor using its telephone as password.
			User newUser = service.createUserFrom(instructor);
			service.addUser(newUser);
		} catch (Exception e){
			//This means the instructor is aldreay in the database
			if(locale.getLanguage().equals("en")) bindingResult.rejectValue("SsNumber", "repInstr", "Instructor already existing");
			if(locale.getLanguage().equals("es")) bindingResult.rejectValue("SsNumber", "repInstr", "El instructor ya existe en la base de datos");
			return "admin/instructorManagement/add";
		}
		
		return "forward:/admin/instructorManagement/"+RESULT_ADD_OR_ACCEPT+"&"+instructor.getSsNumber()+".html";
	}
	

	/**
	 * Disables an instructor
	 * @param idInstructor The id of the instructor
	 * @return Redirect to main page with the result code and the instructor id.
	 */
	@RequestMapping(value="/disable/{idInstructor}")
	public String instructorsDisablePage(@PathVariable String idInstructor){
		service.inactiveInstructor(idInstructor);
		
		return "forward:/admin/instructorManagement/"+RESULT_MODIFY+"&"+idInstructor+".html";
	}
	
	/**
	 * Enables an instructor
	 * @param idInstructor The id of the instructor
	 * @return Redirect to main page with the result code and the instructor id.
	 */
	@RequestMapping(value="/enable/{idInstructor}")
	public String instructorsEnablePage(@PathVariable String idInstructor){
		service.activeInstructor(idInstructor);
		
		return "forward:/admin/instructorManagement/"+RESULT_MODIFY+"&"+idInstructor+".html";
	}


	/**
	 * Returns the form to modify an instructor
	 * @param idInstructor Id of the instructor
	 * @param model Injected model
	 * @return Modify.jsp with the modified model
	 */
	@RequestMapping(value="/modify/{idInstructor}")
	public String instructorsModifyPage(@PathVariable String idInstructor, Model model){
		Instructor instructor = service.getInstructor(idInstructor);
		Collection<Activity> colActivities = service.getAllActivities(instructor); 
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("activities", colActivities);
		
		return "admin/instructorManagement/modify";
	}
	
	/**
	 * Returns the instructor form page after adding or removing an activity from theinstructor
	 * @param idInstructor Id of the instructor
	 * @param resCode The result code
	 * @param idCode The id of the activity
	 * @param model The injected model
	 * @return Modify.jsp with the modified model
	 */
	@RequestMapping(value="/modify/{idInstructor}/{resCode}&{idCode}")
	public String instructorsModifyPage(@PathVariable String idInstructor, @PathVariable int resCode, @PathVariable String idCode, Model model){
		Instructor instructor = service.getInstructor(idInstructor);
		Collection<Activity> colActivities = service.getAllActivities(instructor); 
		
		model.addAttribute("error",resCode);
		model.addAttribute("id", idCode);
		model.addAttribute("instructor", instructor);
		model.addAttribute("activities", colActivities);
		
		return "admin/instructorManagement/modify";
	}
	
	/**
	 * Processes the data from a modified instructor
	 * @param model The injected model
	 * @param instructor The instructor object
	 * @param bindingResult Error handler
	 * @return Redirect to the instructor management main page with the result and the instructor id
	 */
	@RequestMapping(value="/modify/{idInstructor}", method=RequestMethod.POST)
	public String instructorsModifyPage(Model model, @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult){
		//Check errors
		new InstructorValidator().validate(instructor, bindingResult);
		
		if(bindingResult.hasErrors()){
			model.addAttribute("activities", service.getAllActivities(instructor));
			return "admin/instructorManagement/modify";
		}

		service.updateInstructor(instructor);
		
		return "forward:/admin/instructorManagement/"+RESULT_MODIFY+"&"+instructor.getSsNumber()+".html";
	}

	/**
	 * Returns the add or remove activity from an instructor
	 * @param idInstructor Id of the instructor
	 * @param model Injected model
	 * @return AddActivity.jsp with the modified model
	 */
	@RequestMapping(value="/addActivity/{idInstructor}")
	public String instructorsAddActivity(@PathVariable String idInstructor, Model model){
		Instructor instructor = service.getInstructor(idInstructor);
		Collection<Activity> colActivities = service.getAllActivities(instructor);
		Collection<Activity> possibleActivities = service.getNoInstruidasActivities(instructor);
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("activitiesInstructor", colActivities);
		model.addAttribute("possibleActivities", possibleActivities);
		
		Integer mint = new Integer(0);
		model.addAttribute("nuevaAct", mint);

		return "/admin/instructorManagement/addActivity";
	}
	
	/**
	 * Adds an activity to the instructor
	 * @param idInstructor Id of the instructor
	 * @param request Post parameters
	 * @return Redirect to modify an instructor with the result code and the instructor id
	 */
	@RequestMapping(value="/addActivity/{idInstructor}", method=RequestMethod.POST)
	public String instructorsAddActivity(@PathVariable String idInstructor, HttpServletRequest request){
		int idAct = Integer.parseInt(request.getParameter("newAct"));
		service.addInstructed(idInstructor, idAct);
		
		return "forward:/admin/instructorManagement/modify/"+idInstructor+"/"+RESULT_ADD_OR_ACCEPT+"&"+idAct+".html";
	}

	/**
	 * Remove an activity from the instructor
	 * @param idInstructor Id of the instructor
	 * @param idActivity Id of the activity
	 * @returnRedirect to modify an instructor with the result code and the instructor id
	 */
	@RequestMapping(value="/removeActivity/{idInstructor}&{idActivity}")
	public String instructorsRemoveActivity(@PathVariable String idInstructor, @PathVariable Integer idActivity){
		service.removeInstructed(idInstructor, idActivity);
		
		return "forward:/admin/instructorManagement/modify/"+idInstructor+"/"+RESULT_DELETE_OR_DENY+"&"+idActivity+".html";
	}

}
