package controller.instructor;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import controller.basics.AbstractController;
import classes.Booking;
import classes.Instructor;
import classes.User;


@Controller
@RequestMapping(value="/instructor")
public class InstructorFunctionalityController extends AbstractController{
	
	
	/**
	 * Method that injects the activity list assigned to a instructor
	 * @param model Model of the page
	 * @param session Session of the user
	 * @return Main monitors page
	 */
	@RequestMapping
	public String monitorPage(Model model, HttpSession session){

		//Get the activities of the instructor
		Instructor instructor = service.getInstructor((User) session.getAttribute("user"));
		
		//Attach the list to the model
		model.addAttribute("bookings", service.getBookingActivities(instructor));
		return "instructor/main";
		
	}
	
	@RequestMapping(value="/details/{idBooking}")
	public String detailsPage(@PathVariable int idBooking, Model model){
		Booking booking = service.getBooking(idBooking);
		
		model.addAttribute("booking", booking);
		model.addAttribute("activity", service.getActivity(booking.getIdAct()));
		model.addAttribute("client", service.getClient(booking.getClientId()));
		
		return "instructor/details";
	}
	
	@RequestMapping(value="/history")
	public String pastBookings(Model model, HttpSession session){
		Instructor instructor = service.getInstructor((User) session.getAttribute("user"));
		
		model.addAttribute("bookings", service.getPastBooking(instructor));
		return "instructor/history";
	}
}
