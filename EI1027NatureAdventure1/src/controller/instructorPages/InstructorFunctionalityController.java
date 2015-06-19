package controller.instructorPages;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import classes.Booking;
import classes.BookingActivity;
import classes.Instructor;
import classes.User;
import controller.basics.AbstractController;


/**
 * Controller that manages the pages from the instructor view
 */
@Controller
@RequestMapping(value="/instructor")
public class InstructorFunctionalityController extends AbstractController{
	
	
	/**
	 * Method that injects the activity list assigned to a instructor
	 * @param model Model of the page
	 * @param session Session of the user
	 * @return Main.jsp with the modified model
	 */
	@RequestMapping
	public String monitorPage(Model model, HttpSession session){

		//Get the activities of the instructor
		Instructor instructor = service.getInstructor((User) session.getAttribute("user"));

		Collection<BookingActivity> collection = service.getActiveBooking(instructor);
		//Attach the list to the model
		model.addAttribute("name", instructor.getName());
		model.addAttribute("bookingList", collection);
		model.addAttribute("numbookings", collection.size());
		return "instructor/main";
		
	}
	
	/**
	 * Returns the details of the booking {idBooking}
	 * @param idBooking The id of the booking
	 * @param model Injected model
	 * @return Details.jsp with the modified model
	 */
	@RequestMapping(value="/details/{idBooking}")
	public String detailsPage(@PathVariable int idBooking, Model model){
		Booking booking = service.getBooking(idBooking);
		
		model.addAttribute("booking", booking);
		model.addAttribute("activity", service.getActivity(booking.getIdAct()));
		model.addAttribute("client", service.getClient(booking.getClientId()));
		
		return "instructor/details";
	}
	
	/**
	 * Shows the past bookings assigned to the instructor
	 * @param model Injected model
	 * @param session Session to get the instructor from
	 * @return History.jsp with the modified model
	 */
	@RequestMapping(value="/history")
	public String pastBookings(Model model, HttpSession session){
		Instructor instructor = service.getInstructor((User) session.getAttribute("user"));
		
		model.addAttribute("name", instructor.getName());
		model.addAttribute("bookings", service.getPastBooking(instructor));
		return "instructor/history";
	}
}
