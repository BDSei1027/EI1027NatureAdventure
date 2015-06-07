package controller.basicPages;

import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import classes.Activity;
import classes.Booking;
import classes.Client;
import controller.basics.AbstractController;


@Controller
public class BasicPages extends AbstractController {	

	@RequestMapping(value="/index")
	public String indexPage(){
		return "index";
	}
	
	
	@RequestMapping(value="/activities")
	public String activitiesPage(Model model){
		model.addAttribute("activityList", service.getAllActivities());
		return "activities";
	}
	
	@RequestMapping(value="/activities/newBooking/{idAct}")
	public String newBookingPage(Locale locale, @PathVariable int idAct, Model model){
		Booking booking = new Booking();
		booking.setIdAct(idAct);
		booking.setDateCreation(new Date());
		
		Activity act = service.getActivity(idAct);
		
		model.addAttribute("client", new Client());
		model.addAttribute("booking", booking);
		if(locale.getLanguage().equalsIgnoreCase("en")) model.addAttribute("actName", act.getName());
		else model.addAttribute("actName", act.getNombre());
		
		return "booking";
	}
	
	@RequestMapping(value="/about")
	public String aboutPage(){
		return "about";
	}
	
}
