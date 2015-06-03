package controller.customer;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import classes.Client;
import classes.Instructor;
import classes.User;
import controller.basics.AbstractController;


@Controller
@RequestMapping("/customer")
public class MainCustomer extends AbstractController {	
	
	@RequestMapping
	public String adminPage(Model model, HttpSession session){
		Client client = service.getClient((User) session.getAttribute("user"));
		
		model.addAttribute("bookings", service.getActiveBookings(client));
		return "customer";
	}
}
