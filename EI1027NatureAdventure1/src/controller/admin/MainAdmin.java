package controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin")
public class MainAdmin extends AbstractController {	
	
	/**
	 * Method that returns the admin main page
	 * @return The admin main page
	 */
	@RequestMapping
	public String adminPage(){
		return "admin";
	}
}
