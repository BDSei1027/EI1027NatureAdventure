package controller.customerPages;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import classes.BookingActivity;
import classes.Client;
import classes.User;
import controller.basics.AbstractController;


@Controller
@RequestMapping("/customer")
public class MainCustomer extends AbstractController {	
	
	@RequestMapping
	public String costumerPage(Model model, HttpSession session){
		Client client = service.getClient((User) session.getAttribute("user"));
		
		Collection<BookingActivity> collection = service.getActiveBookings(client);
		
		model.addAttribute("name", client.getClientName());
		model.addAttribute("numbookings", collection.size());
		model.addAttribute("bookings", collection);
		return "customer/main";
	}
	
	@RequestMapping(value="/history")
	public String costumerRecords(Model model, HttpSession session){
		Client client = service.getClient((User) session.getAttribute("user"));
		
		Collection<BookingActivity> collection = service.getActiveBookings(client);
		
		model.addAttribute("name", client.getClientName());
		model.addAttribute("numbookings", collection.size());
		model.addAttribute("bookings", collection);
		return "customer/history";
	}
}
