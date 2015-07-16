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
import classes.Activity;
import classes.Booking;
import classes.Client;
import classes.User;
import controller.basics.AbstractController;


@Controller
public class ActivityBookingRegisteredController extends AbstractController {		
	
	@RequestMapping(value="/activities/createBookingRegistered/{idAct}")
	public String newBookingPage(@PathVariable int idAct, Model model){
		Booking booking = new Booking();
		
		
		Activity act = service.getActivity(idAct);
		
		model.addAttribute("booking", booking);
		model.addAttribute("activity", act);
		
		return "bookingRegistered";
	}
	
	@RequestMapping(value="/activities/createBookingRegistered/{idAct}", method=RequestMethod.POST)
	public String newBookingForm(@PathVariable int idAct,  HttpSession session, Model model, @ModelAttribute("booking") Booking booking,  BindingResult bindingResult) {
		User user = (User) session.getAttribute("user");
		
		Activity act = service.getActivity(idAct);
		validateBokingActivity(booking, bindingResult, act);
		booking.setIdAct(idAct);
		booking.setClientId(user.getUser());
		booking.setDateCreation(new Date());
		booking.setPrice(act.getPrice() * booking.getGroupSize());
		new BookingValidator().validate(booking, bindingResult);
		
		
		if (bindingResult.hasErrors()) { 	
			System.out.println(bindingResult.getFieldError());
			model.addAttribute("activity", act);
			model.addAttribute("booking", booking);
			return "bookingRegistered";
		}


		service.addBooking(booking);
		
		return "complete";
	}

	private void validateBokingActivity(Booking booking, BindingResult bindingResult, Activity act) {
		if(act.getMaximumGroup() < booking.getGroupSize()) bindingResult.rejectValue("groupSize", "grMaxH","Group size bigger than allowed by activity.");
		if(act.getMinimumGroup() > booking.getGroupSize()) bindingResult.rejectValue("groupSize", "grMaxL","Group size lower than allowed by activity.");
		if(new Date().compareTo(booking.getDateActivity()) >= 0) bindingResult.rejectValue("dateActivity", "date","The date has to be greater than today date.");
	}
}
