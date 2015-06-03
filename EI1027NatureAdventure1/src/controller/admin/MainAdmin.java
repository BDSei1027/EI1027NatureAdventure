package controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.basics.AbstractController;
import service.LogicLayer;
import validators.SessionValidator;


@Controller
@RequestMapping("/admin")
public class MainAdmin extends AbstractController {	
	
	/**
	 * Method that returns the admin main page
	 * @param session User session
	 * @return The admin main page
	 */
	@RequestMapping
	public String adminPage(HttpSession session){
		//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) return "redirect:/login.html";
		if(!user.hasPermissions(0)) return "redirect:/restricted.html";
		
		return "admin";
	}
}
