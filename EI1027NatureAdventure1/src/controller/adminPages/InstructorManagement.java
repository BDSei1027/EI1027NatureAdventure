package controller.adminPages;

import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


@Controller
@RequestMapping("/admin/instructorManagement")
public class InstructorManagement extends AbstractController {

//TODO Parm: "error":integer =  0 add | 1 update | 2 delete
//TODO Parm: "id"   :String  = instructor id
	
	@RequestMapping
	public String instructorsPage(Model model){
		LinkedList<Instructor> instructorList = new LinkedList<Instructor>(service.getAllInstructors());		
		model.addAttribute("instructorList", instructorList);
		
		return "admin/instructorManagement";
	}
	
	@RequestMapping(value="/{rezCode}&{idCode}")
	public String instructorsPage(@PathVariable int rezCode, @PathVariable String idCode, Model model){
		LinkedList<Instructor> instructorList = new LinkedList<Instructor>(service.getAllInstructors());	
		
		model.addAttribute("instructorList", instructorList);
		model.addAttribute("error", rezCode);
		model.addAttribute("id", idCode);
		
		return "admin/instructorManagement";
	}
	
	
	

	@RequestMapping(value="/onlyActive")
	public String instructorsPageActive(Model model){
		model.addAttribute("instructorList", service.getAllInstructorsActive());
		
		return "admin/instructorManagement/onlyActive";
	}
	
	@RequestMapping(value="/onlyInactive")
	public String instructorsPageInactive(Model model){
		model.addAttribute("instructorList", service.getAllInstructorsInacctive());
		
		return "admin/instructorManagement/onlyInactive";
	}
	
	@RequestMapping(value="/add")
	public String instructorsAddPage(Model model){
		model.addAttribute("instructor", new Instructor());
		
		return "admin/instructorManagement/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String instructorsAddPage(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session){
		//Check the instructor input format
		InstructorValidator validator = new InstructorValidator(session);
		validator.validate(instructor, bindingResult);
		
		if(bindingResult.hasErrors()) return "admin/instructorManagement/add";
		
		service.addInstructor(instructor);
		
		// Create the user associated with the instructor using its telephone as password.
		User newUser = service.createUserFrom(instructor);
		service.addUser(newUser);
		
		return "forward:/admin/instructorManagement/"+0+"&"+instructor.getSsNumber()+".html";
	}
	

	@RequestMapping(value="/disable/{idInstructor}")
	public String instructorsDisablePage(@PathVariable String idInstructor){
		service.inactiveInstructor(idInstructor);
		
		return "forward:/admin/instructorManagement/"+1+"&"+idInstructor+".html";
	}
	
	@RequestMapping(value="/enable/{idInstructor}")
	public String instructorsEnablePage(@PathVariable String idInstructor){
		service.activeInstructor(idInstructor);
		
		return "forward:/admin/instructorManagement/"+1+"&"+idInstructor+".html";
	}


	@RequestMapping(value="/modify/{idInstructor}")
	public String instructorsModifyPage(@PathVariable String idInstructor, Model model){
		Instructor instructor = service.getInstructor(idInstructor);
		Collection<Activity> colActivities = service.getAllActivities(instructor); 
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("activities", colActivities);
		
		return "admin/instructorManagement/modify";
	}
	
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
	
	@RequestMapping(value="/modify/{idInstructor}", method=RequestMethod.POST)
	public String instructorsModifyPage(Model model, @PathVariable String idInstructor, @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session){
		//Check errors
		new InstructorValidator(session).validate(instructor, bindingResult);
		
		if(bindingResult.hasErrors()){
			model.addAttribute("activities", service.getAllActivities(instructor));
			return "admin/instructorManagement/modify";
		}

		service.updateInstructor(instructor);
		
		return "forward:/admin/instructorManagement/"+1+"&"+idInstructor+".html";
	}

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
	
	@RequestMapping(value="/addActivity/{idInstructor}", method=RequestMethod.POST)
	public String instructorsAddActivity(@PathVariable String idInstructor, HttpServletRequest request){
		int idAct = Integer.parseInt(request.getParameter("newAct"));
		service.addInstructed(idInstructor, idAct);
		
		return "forward:/admin/instructorManagement/modify/"+idInstructor+"/"+0+"&"+idAct+".html";
	}

	@RequestMapping(value="/removeActivity/{idInstructor}&{idActivity}")
	public String instructorsRemoveActivity(@PathVariable String idInstructor, @PathVariable Integer idActivity){
		service.removeInstructed(idInstructor, idActivity);
		
		return "forward:/admin/instructorManagement/modify/"+idInstructor+"/"+2+"&"+idActivity+".html";
	}

}
