package controller.instructor;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import controller.basics.AbstractController;
import classes.Activity;
import classes.Booking;
import classes.Instructor;
import classes.User;
import validators.UserValidator;
import service.LogicLayer;


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
		Collection<Activity> instructorActivities = service.getAllActivities(instructor);
		
		//Attach the list to the model
		model.addAttribute("instructorActivities", instructorActivities);
		return "instructor";
		
	}
	
	@RequestMapping(value="/details/{idBooking}")
	public String detailsPage(@PathVariable int idBooking, Model model){
		Booking booking = service.getBooking(idBooking);
		
		model.addAttribute("booking", booking);
		model.addAttribute("activity", service.getActivity(booking.getIdAct()));
		model.addAttribute("client", service.getClient(booking.getClientId()));
		
		return "instructor/details";
	}
	
	@RequestMapping(value="main/history")
	public String pastBookings(Model model, HttpSession session){
		Instructor instructor = service.getInstructor((User) session.getAttribute("user"));
		
		model.addAttribute("bookings", service.getPastBookings(instructor));
		return "main/history";
	}
}
