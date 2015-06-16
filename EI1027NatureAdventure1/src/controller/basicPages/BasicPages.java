package controller.basicPages;

import java.util.Date;
import java.util.Locale;

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
import classes.ClientBookingEnvelope;
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
		
		model.addAttribute("registerEnvelope", new ClientBookingEnvelope());
		model.addAttribute("activity", act);
		
		return "booking";
	}
	
	@RequestMapping(value="/activities/createBooking/{idAct}", method=RequestMethod.POST)
	public String newBookingForm(@ModelAttribute("registerEnvelope") ClientBookingEnvelope clientBooking, @PathVariable int idAct, Model model, HttpSession session, BindingResult bindingResult, Locale locale) {
		Client client = clientBooking.getClient();
		if(client.getClientId()!=null){
			new ClientRegisterValidator().validate(client, bindingResult);
			if(bindingResult.hasErrors()){
				model.addAttribute(service.getActivity(idAct));
				return "booking";
			}
			try{
				service.addClient(client);
			} catch (Exception e){
				model.addAttribute(service.getActivity(idAct));
				if(locale.getLanguage().equals("es")) bindingResult.rejectValue("clientId","repId","El cliente ya existe");
				if(locale.getLanguage().equals("en")) bindingResult.rejectValue("clientId","repId","The client already exists");
				return "booking";
			}
		}
		Booking booking = clientBooking.getBooking();
		
		Activity act = service.getActivity(idAct);
		new BookingValidator().validate(booking, bindingResult);
		if(act.getMaximumGroup() < clientBooking.getGroupSize())  bindingResult.rejectValue("groupSize", "grMax","GrSize");
		if(act.getMinimumGroup() > clientBooking.getGroupSize())  bindingResult.rejectValue("groupSize", "grMax","GrSize");
		
		User user = (User) session.getAttribute("user");

		String clientId = client.getClientId() == null? user.getName(): client.getClientId();

		booking.setPrice(act.getPrice() * booking.getGroupSize());
		booking.setClientId(clientId);

			
		service.addBooking(booking);
		return "complete";
	}
	
	@RequestMapping(value="/about")
	public String aboutPage(){
		return "about";
	}
	
}
