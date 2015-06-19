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
import classes.User;
import controller.basics.AbstractController;


/**
 * Controller that manages pages that does not belong to any secction
 */
@Controller
public class BasicPages extends AbstractController {	

	/**
	 * Method that returns the main page of the web
	 * @return Index.jsp
	 */
	@RequestMapping(value="/index")
	public String indexPage(){
		return "index";
	}
	
	/**
	 * Method that returns the activity list to book
	 * @param model Inject model
	 * @return Activities.jsp with the modified model
	 */
	@RequestMapping(value="/activities")
	public String activitiesPage(Model model){
		model.addAttribute("activityList", service.getAllActivitiesActive());
		return "activities";
	}
	
	/**
	 * Returns the form to create a new booking
	 * @param idAct Id of the activity to book
	 * @param model Injected model
	 * @return Booing.jsp with the modified model.
	 */
	@RequestMapping(value="/activities/createBooking/{idAct}")
	public String newBookingPage(@PathVariable int idAct, Model model){
		Booking booking = new Booking();
		//Some of the booking attributes are already known
		booking.setIdAct(idAct);
		booking.setDateCreation(new Date());
		
		Activity act = service.getActivity(idAct);
		
		model.addAttribute("registerEnvelope", new ClientBookingEnvelope());
		model.addAttribute("activity", act);
		
		return "booking";
	}
	
	/**
	 * Processes the information to create a new booking
	 * @param clientBooking Object containing the client and the booking
	 * @param idAct Id of the activity
	 * @param model Injected model
	 * @param session Session used to get the user
	 * @param bindingResult Error handler
	 * @param locale Language
	 * @return Complete.jsp with the modified model.
	 */
	@RequestMapping(value="/activities/createBooking/{idAct}", method=RequestMethod.POST)
	public String newBookingForm(@ModelAttribute("registerEnvelope") ClientBookingEnvelope clientBooking, @PathVariable int idAct, Model model, HttpSession session, BindingResult bindingResult, Locale locale) {
		Client client = clientBooking.getClient();
		//Check if the user wants to register
		if(client.getClientId()!=null){
			new ClientRegisterValidator().validate(client, bindingResult);
			if(bindingResult.hasErrors()){
				model.addAttribute(service.getActivity(idAct));
				return "booking";
			}
			try{
				service.addClient(client);
			} catch (Exception e){
				//this that means the client already exists
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
	
	/**
	 * Returns the about us page
	 * @return About.jsp
	 */
	@RequestMapping(value="/about")
	public String aboutPage(){
		return "about";
	}
	
}
