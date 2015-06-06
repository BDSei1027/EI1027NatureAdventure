package controller.adminPages;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String adminPage(Model model){
		
		model.addAttribute("dateToday",new Date());
		model.addAttribute("numbookings", service.getPendingBookingsCount());
		model.addAttribute("numclients", service.getUserCount());
		
		return "admin";
	}
}
