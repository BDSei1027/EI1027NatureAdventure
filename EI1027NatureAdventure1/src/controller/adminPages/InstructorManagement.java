package controller.adminPages;

import java.util.Collection;
import java.util.LinkedList;

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


@Controller
@RequestMapping("/admin/instructorManagement")
public class InstructorManagement extends AbstractController {

	@RequestMapping
	public String instructorsPage(Model model){
		LinkedList<Instructor> instructorList = new LinkedList<Instructor>(service.getAllInstructors());		
		model.addAttribute("instructorList", instructorList);
		
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
	public String instructorsAddPage(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult){
		//Check the instructor input format
		InstructorValidator validator = new InstructorValidator();
		validator.validate(instructor, bindingResult);
		
		if(bindingResult.hasErrors()) return "admin/instructorManagement/add";
		
		service.addInstructor(instructor);
		
		// Create the user associated with the instructor using its telephone as password.
		User newUser = service.createUserFrom(instructor);
		service.addUser(newUser);
		
		return "redirect:/admin/instructorManagement.html";
	}
	

	@RequestMapping(value="/disable/{idInstructor}")
	public String instructorsDisablePage(@PathVariable String idInstructor){
		service.inactiveInstructor(idInstructor);
		
		return "redirect:/admin/instructorManagement.html";
	}
	
	@RequestMapping(value="/enable/{idInstructor}")
	public String instructorsEnablePage(@PathVariable String idInstructor){
		service.activeInstructor(idInstructor);
		
		return "redirect:/admin/instructorManagement.html";
	}


	@RequestMapping(value="/modify/{idInstructor}", method=RequestMethod.GET)
	public String instructorsModifyPage(@PathVariable String idInstructor, Model model){
		Instructor instructor = service.getInstructor(idInstructor);
		Collection<Activity> colActivities = service.getAllActivities(instructor); 
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("activities", colActivities);
		
		return "admin/instructorManagement/modify";
	}
	
	@RequestMapping(value="/modify/{idInstructor}", method=RequestMethod.POST)
	public String instructorsModifyPage(@PathVariable String idInstructor, @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult){
		//Check errors
		new InstructorValidator().validate(instructor, bindingResult);
		if(bindingResult.hasErrors()) return "admin/instructorManagement/modify";

		service.updateInstructor(instructor);
		
		return "redirect:/admin/instructorManagement.html";
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
		service.addInstructed(idInstructor, Integer.parseInt(request.getParameter("newAct")));
		
		return "redirect:/admin/instructorManagement/addActivity/" + idInstructor + ".html";
	}

	@RequestMapping(value="/removeActivity/{idInstructor}&{idActivity}")
	public String instructorsRemoveActivity(@PathVariable String idInstructor, @PathVariable Integer idActivity){
		service.removeInstructed(idInstructor, idActivity);
		
		return "redirect:/admin/instructorManagement/addActivity/" + idInstructor + ".html";
	}

}
