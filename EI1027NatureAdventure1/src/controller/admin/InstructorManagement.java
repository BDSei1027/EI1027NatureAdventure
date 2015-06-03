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
import validators.InstructorValidator;
import validators.SessionValidator;
import classes.Activity;
import classes.Instructor;
import classes.User;
import comparators.sortInstructorActive;
import comparators.sortInstructorName;
import comparators.sortInstructorSurname;
import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin/instructorManagement")
public class InstructorManagement extends AbstractController {

	@RequestMapping
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

	@RequestMapping(value="/onlyActive")
	public String instructorsPageActive(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		
		
		model.addAttribute("instructorList", service.getAllInstructorsActive());
		
		return "admin/instructorManagement/onlyActive";
	}
	
	@RequestMapping(value="/onlyInactive")
	public String instructorsPageInactive(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		
		
		model.addAttribute("instructorList", service.getAllInstructorsInacctive());
		
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
	
	@RequestMapping(value="/add")
	public String instructorsAddPage(Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		//Instance new instructor used by the form
		model.addAttribute("instructor", new Instructor());
		
		return "admin/instructorManagement/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
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
		newUser.setName(instructor.getName());
		newUser.setLanguage("EN");
		newUser.setType(1);
		return newUser;
	}
	

	@RequestMapping(value="/disable/{idInstructor}")
	public String instructorsDisablePage(@PathVariable String idInstructor , HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		service.inactiveInstructor(idInstructor);
		
		return "redirect:/admin/instructorManagement.html";
	}
	
	@RequestMapping(value="/enable/{idInstructor}")
	public String instructorsEnablePage(@PathVariable String idInstructor , HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		service.activeInstructor(idInstructor);
		
		return "redirect:/admin/instructorManagement.html";
	}


	@RequestMapping(value="/modify/{idInstructor}", method=RequestMethod.GET)
	public String instructorsModifyPage(@PathVariable String idInstructor, Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		Instructor instructor = service.getInstructor(idInstructor);
		Collection<Activity> colActivities = service.getAllActivities(instructor); 
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("activities", colActivities);
		
		return "/admin/instructorManagement/modify";
	}
	
	@RequestMapping(value="/modify/{idInstructor}", method=RequestMethod.POST)
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

	@RequestMapping(value="/addActivity/{idInstructor}", method=RequestMethod.GET)
	public String instructorsAddActivity(@PathVariable String idInstructor, Model model, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
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
	public String instructorsAddActivity(@PathVariable String idInstructor, @ModelAttribute("nuevaAct") Integer myint, BindingResult bindingResult, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";
		
		service.addInstructed(idInstructor, myint);
		
		return "redirect:/admin/instructorManagement/addActivity" + idInstructor + ".html";
	}

	@RequestMapping(value="/removeActivity/{idMonitor}&{idActivity}", method=RequestMethod.GET)
	public String instructorsRemoveActivity(@PathVariable String idMonitor, @PathVariable Integer idActivity, HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";;
		if(!user.hasPermissions(0)) return "restricted";

		service.removeInstructed(idMonitor, idActivity);
		
		return "redirect:/admin/instructorManagement/modify/"+idMonitor+".html";
	}

}
