package controller.basicPages;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import validators.BookingValidator;
import validators.ClientValidator;
import classes.Activity;
import classes.Booking;
import classes.Client;
import classes.ClientBookingEnvelope;
import controller.basics.AbstractController;


@Controller
public class ActivityBookingUnregisteredController extends AbstractController {		
	
	@RequestMapping(value="/activities/createBookingUnregistered/{idAct}")
	public String newBookingPage(@PathVariable int idAct, Model model){
		
		Activity act = service.getActivity(idAct);
		
		model.addAttribute("clientBookingEnvelope", new ClientBookingEnvelope());
		model.addAttribute("activity", act);
		
		return "bookingAnon";
	}
	
	@RequestMapping(value="/activities/createBookingUnregistered/{idAct}", method=RequestMethod.POST)
	public String newBookingForm(@PathVariable int idAct, Model model, @ModelAttribute("clientBookingEnvelope") ClientBookingEnvelope envelope, BindingResult bindingResult) {
		Activity act = service.getActivity(idAct);
		Client client = envelope.getClient();
		Booking booking = envelope.getBooking();
		booking.setIdAct(idAct);
		booking.setDateCreation(new Date());
		booking.setClientId(client.getClientId());
		booking.setPrice(act.getPrice() * envelope.getGroupSize());
		
		new ClientValidator().validate(client, bindingResult);
		new BookingValidator().validate(booking, bindingResult);
		validateActivityBooking(envelope, act, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("activity", act);
			return "bookingAnon";
		}

		service.addClient(client);
		service.addBooking(booking);
		
		return "complete";
	}

	private void validateActivityBooking(ClientBookingEnvelope envelope,
			Activity act, BindingResult bindingResult) {
		if(act.getMaximumGroup() < envelope.getGroupSize()) bindingResult.rejectValue("groupSize", "grMaxH","Group size bigger than allowed by activity.");
		if(act.getMinimumGroup() > envelope.getGroupSize()) bindingResult.rejectValue("groupSize", "grMaxL","Group size lower than allowed by activity.");
		if(new Date().compareTo(envelope.getDateActivity()) >= 0) bindingResult.rejectValue("dateActivity", "date","The date has to be greater than today date.");
	}
}
