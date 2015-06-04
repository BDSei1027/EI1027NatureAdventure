package controller.admin;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/details/{idBooking}")
	public String bookingDetailsPage(@PathVariable int idBooking, Model model){
		Booking booking  = service.getBooking(idBooking);
		Status status = service.getStatus(booking);
		
		model.addAttribute("booking", booking);
		model.addAttribute("status", service.getStatus(booking));
		model.addAttribute("dateToday", new Date());
//		model.addAttribute("instructorsAvailable", service.get);
		model.addAttribute("client", service.getClient(booking.getClientId()));
		model.addAttribute("activity", service.getActivity(booking.getIdAct()));
		
		return "admin/bookingManagement/details";
	}	
	
	@RequestMapping(value="details/assignInstructor/")
	public String assignInstructor(@PathVariable int idBooking, @PathVariable String idInstr){		
		service.assignInstructorToBooking(idInstr,idBooking);
		
		return "redirect:/admin/bookingManagement.html";
	}
	
	@RequestMapping(value="details/decline/{idBooking}")
	public String declineBooking(@PathVariable int idBooking){
		service.declineBooking(idBooking);
		
		return "redirect:/admin/bookingManagement.html";
	}
	
	@RequestMapping(value="/details/toPending/{idBooking}")
	public String bookingToPending(@PathVariable int idBooking){
		service.bookingToPending(idBooking);
		
		return "redirect:/admin/bookingManagement.html";
	}
	
}
