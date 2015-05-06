package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

import classes.Instructor;
import service.LogicLayer;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	private LogicLayer service;
	
	@Autowired
	public void setService(LogicLayer service){
		this.service=service;
	}

	@RequestMapping(value="/add")
	public String addInstructor(Model model){
		model.addAttribute("instructor", new Instructor());
        return "instructor/add";

	}
	//TODO implementar y utilizar el validador
	@RequestMapping(value="/add", method=RequestMethod.POST)  
	public String processAddSubmit(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult) {  
	     if (bindingResult.hasErrors()) 
	            return "instructor/add";
	        service.addInstructor(instructor);
	        return "redirect:list.html"; 
	 }
	
	@RequestMapping(value="/list")
	public String listInstructors(Model model){
		model.addAttribute("instructors", service.getAllInstructors());
        return "instructor/list";

	}
	
}
