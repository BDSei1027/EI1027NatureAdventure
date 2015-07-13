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
import classes.Activity;
import classes.Booking;
import classes.Client;
import classes.User;
import controller.basics.AbstractController;


@Controller
public class ActivityBookingController extends AbstractController {		
	
	@RequestMapping(value="/activities/createBooking/{idAct}")
	public String newBookingPage(@PathVariable int idAct, Model model){
		Booking booking = new Booking();
		booking.setIdAct(idAct);
		booking.setDateCreation(new Date());
		
		Activity act = service.getActivity(idAct);
		
		model.addAttribute("booking", booking);
		model.addAttribute("activity", act);
		
		return "booking";
	}
	
	@RequestMapping(value="/activities/createBooking/{idAct}", method=RequestMethod.POST)
	public String newBookingForm(@PathVariable int idAct,  HttpSession session, Locale locale, Model model, @ModelAttribute("booking") Booking booking, BindingResult bindingResult) {
		User user = (User) session.getAttribute("user");
		Client client = service.getClient(user);
		Activity act = service.getActivity(idAct);
		new BookingValidator().validate(booking, bindingResult);
		Date date = new  Date();
		
		if(act.getMaximumGroup() < booking.getGroupSize()){
			if (locale.getLanguage().equals("en"))
				bindingResult.rejectValue("groupSize", "grMax","Group size bigger than allowed by activity.");
			if (locale.getLanguage().equals("es"))
				bindingResult.rejectValue("groupSize", "grMax","Tamaño de grupo mayor que el permitido por la actividad.");
		}
		if(act.getMinimumGroup() > booking.getGroupSize()) {
			if (locale.getLanguage().equals("en")) bindingResult.rejectValue("groupSize", "grMax","Group size lower than allowed by activity.");
			if (locale.getLanguage().equals("es")) bindingResult.rejectValue("groupSize", "grMin","Tamaño de grupo menor que el permitido por la actividad.");
		}
		
		if(date.compareTo(booking.getDateActivity()) >= 0) {
			if (locale.getLanguage().equals("en")) bindingResult.rejectValue("dateActivity", "date","The date has to be greater than today date.");
			if (locale.getLanguage().equals("es")) bindingResult.rejectValue("dateActivity", "date","La fecha tiene que ser mayor que el día de hoy.");
		}
		if (bindingResult.hasErrors()) { 	
			model.addAttribute("activity", act);
			return "booking";
		}

		booking.setPrice(act.getPrice() * booking.getGroupSize());
		booking.setClientId(client.getClientId());

		service.addBooking(booking);
		
		return "complete";
	}
}
