package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import service.LogicLayer;


@Controller
public class LoginControler {
	
	private LogicLayer service;

	@Autowired
	public void setServicio(LogicLayer service){
		this.service = service;
	}
	
	@RequestMapping(value="/login")
	public String login(Model model){
		model.addAttribute("user", new User());
		return "login";
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("user") User user, BindingResult bindingResult){
		model.addAttribute("user", new User());
		return "login";
		
	}
	
}
