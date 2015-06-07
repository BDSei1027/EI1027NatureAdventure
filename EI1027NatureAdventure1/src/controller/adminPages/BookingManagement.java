package controller.adminPages;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import classes.Booking;
import classes.Status;
import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin/bookingManagement")
public class BookingManagement extends AbstractController {

	
	@RequestMapping
	public String bookingMainPage(Model model){
		model.addAttribute("bookingList", service.getAllBookings());
		
		return "admin/bookingManagement";
	}	
	
	@RequestMapping(value="/{resCode}&{idActivity}")
	public String bookingMainPage(@PathVariable int resCode, @PathVariable int idActivity, Model model){
		model.addAttribute("error", resCode);
		model.addAttribute("id",idActivity);
		model.addAttribute("bookingList", service.getAllBookings());
		
		return "admin/bookingManagement";
	}
	
	@RequestMapping(value="/details/{idBooking}")
	public String bookingDetailsPage(@PathVariable int idBooking, Model model){
		Booking booking  = service.getBooking(idBooking);
		
		model.addAttribute("booking", booking);
		model.addAttribute("status", service.getStatus(booking));
		model.addAttribute("dateToday", new Date());
		model.addAttribute("instructorsAvailable", service.getAvaliableInstructorsToAssign(idBooking));
		model.addAttribute("client", service.getClient(booking.getClientId()));
		model.addAttribute("activity", service.getActivity(booking.getIdAct()));
		
		return "admin/bookingManagement/details";
	}		
	
	@RequestMapping(value="details/assignInstructor/{idBooking}&{idInstructor}")
	public String assignInstructor(@PathVariable int idBooking, @PathVariable String idInstructor){		
		service.assignInstructorToBooking(idInstructor,idBooking);
		
		return "forward:/admin/bookingManagement/"+0+"&"+idBooking+".html";
	}
	
	@RequestMapping(value="details/decline/{idBooking}")
	public String declineBooking(@PathVariable int idBooking){
		service.declineBooking(idBooking);
		
		return "forward:/admin/bookingManagement/"+2+"&"+idBooking+".html";
	}
	
	@RequestMapping(value="/details/toPending/{idBooking}")
	public String bookingToPending(@PathVariable int idBooking){
		service.bookingToPending(idBooking);
		
		return "forward:/admin/bookingManagement/"+1+"&"+idBooking+".html";
	}
	
}
