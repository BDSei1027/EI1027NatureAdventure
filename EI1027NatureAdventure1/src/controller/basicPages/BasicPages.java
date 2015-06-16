package controller.basicPages;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import validators.BookingValidator;
import validators.ClientRegisterValidator;
import classes.Activity;
import classes.Booking;
import classes.Client;
import classes.ClientRegister;
import classes.User;
import controller.basics.AbstractController;


@Controller
public class BasicPages extends AbstractController {	

	@RequestMapping(value="/index")
	public String indexPage(){
		return "index";
	}
	
	
	@RequestMapping(value="/activities")
	public String activitiesPage(Model model){
		model.addAttribute("activityList", service.getAllActivitiesActive());
		return "activities";
	}
	
	@RequestMapping(value="/activities/createBooking/{idAct}")
	public String newBookingPage(@PathVariable int idAct, Model model){
		Booking booking = new Booking();
		booking.setIdAct(idAct);
		booking.setDateCreation(new Date());
		
		Activity act = service.getActivity(idAct);
		
		model.addAttribute("client", new ClientRegister());
		model.addAttribute("booking", booking);
		model.addAttribute("activity", act);
		
		return "booking";
	}
	
	@RequestMapping(value="/activities/createBooking/{idAct}", method=RequestMethod.POST)
	public String newBookingForm(@ModelAttribute("client") ClientRegister clientR, @ModelAttribute("booking") Booking booking, @PathVariable int idAct, Model model, HttpSession session, BindingResult bindingResult) {
		
		new ClientRegisterValidator().validate(clientR, bindingResult);
		new BookingValidator().validate(booking, bindingResult);
		if(service.getActivity(idAct).getMaximumGroup() < booking.getGroupSize())  bindingResult.rejectValue("groupSize", "grMax","GrSize");
		if(bindingResult.hasErrors()){
			model.addAttribute(service.getActivity(idAct));
			return "booking";
		}
		
		Client client = clientR.getClient();
		User user = (User) session.getAttribute("user");
		
		
		String clientId = client.getClientId() == null? user.getName(): client.getClientId();
		Activity act = service.getActivity(idAct);

		booking.setPrice(act.getPrice() * booking.getGroupSize());
		booking.setClientId(clientId);

			
			service.addBooking(booking);

		try{
			if(client.getClientId()!= null) service.addClient(client);
		} catch (Exception e){
			service.updateClient(client);
		}
		return "complete";
	}
	
	@RequestMapping(value="/about")
	public String aboutPage(){
		return "about";
	}
	
}
