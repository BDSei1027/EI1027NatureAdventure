package controller.basicPages;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import validators.SessionValidator;
import controller.basics.AbstractController;


@Controller
public class ActivityBookingController extends AbstractController {		
	
	@RequestMapping(value="/activities/createBooking/{idAct}")
	public String newBookingPage(@PathVariable int idAct, HttpSession session){
		SessionValidator sessionValidator = new SessionValidator(session);
		if(sessionValidator.isLogged()) return "redirect:/activities/createBookingRegistered/"+idAct+".html";
		return "redirect:/activities/createBookingUnregistered/"+idAct+".html";
	}
}
	
