package controller.adminPages;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import classes.Booking;
import controller.basics.AbstractController;


/**
 * Controller that manages the bookings.
 */
@Controller
@RequestMapping("/admin/bookingManagement")
public class BookingManagement extends AbstractController {

	
	/**
	 * Returns the booking management page
	 * @param model Injected model
	 * @return BookingManagement.jsp with the modified model
	 */
	@RequestMapping
	public String bookingMainPage(Model model){
		model.addAttribute("bookingList", service.getAllBookings());
		
		return "admin/bookingManagement";
	}	
	
	/**
	 * Returns the booking management page with the result of some operation
	 * @param resCode Result code
	 * @param idActivity Id of the activity affected
	 * @param model Injected model
	 * @return BookingManagement.jsp with the modified model
	 */
	@RequestMapping(value="/{resCode}&{idActivity}")
	public String bookingMainPage(@PathVariable int resCode, @PathVariable int idActivity, Model model){
		model.addAttribute("error", resCode);
		model.addAttribute("id",idActivity);
		model.addAttribute("bookingList", service.getAllBookings());
		
		return "admin/bookingManagement";
	}
	
	/**
	 * Returns the page with the details of the booking with the id {idBooking}
	 * @param idBooking The id of the booking
	 * @param model Injected model
	 * @return details.jsp with the modified model
	 */
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
	
	/**
	 * Assigns an instructor to a booking
	 * @param idBooking Id of the booking
	 * @param idInstructor Id of the instructor
	 * @return Redirect to bookingManagemet with the result code and the booking id
	 */
	@RequestMapping(value="details/assignInstructor/{idBooking}&{idInstructor}")
	public String assignInstructor(@PathVariable int idBooking, @PathVariable String idInstructor){		
		service.assignInstructorToBooking(idInstructor,idBooking);
		
		return "forward:/admin/bookingManagement/"+RESULT_ADD_OR_ACCEPT+"&"+idBooking+".html";
	}
	
	/**
	 * Declines a booking with the id {idBooking}
	 * @param idBooking The id of the booking
	 * @return Redirect to bookingManagemet with the result code and the booking id
	 */
	@RequestMapping(value="details/decline/{idBooking}")
	public String declineBooking(@PathVariable int idBooking){
		service.declineBooking(idBooking);
		
		return "forward:/admin/bookingManagement/"+RESULT_DELETE_OR_DENY+"&"+idBooking+".html";
	}
	
	/**
	 * Puts the booking with {idBooking} to pending state
	 * @param idBooking Id of the booking
	 * @return Redirect to bookingManagemet with the result code and the booking id
	 */
	@RequestMapping(value="/details/toPending/{idBooking}")
	public String bookingToPending(@PathVariable int idBooking){
		service.bookingToPending(idBooking);
		
		return "forward:/admin/bookingManagement/"+RESULT_MODIFY+"&"+idBooking+".html";
	}
	
	
	/**
	 * Returns the page with the pending bookings
	 * @param model Injected model
	 * @return Pending.jsp with the modified model
	 */
	@RequestMapping(value="/pendings")
	public String pendingActivitiesPage(Model model){
		model.addAttribute("bookingList", service.getPendingBookings());
		
		return "admin/bookingManagement/pending";
	}
	
	/**
	 * Returns the page with the declined bookings
	 * @param model Injected model
	 * @return Declined.jsp with the modified model
	 */
	@RequestMapping(value="/declined")
	public String declinedActivitiesPage(Model model){
		model.addAttribute("bookingList", service.getDeclinedBookings());
		
		return "admin/bookingManagement/declined";
	}
	
	/**
	 * Returns the page with the accepted bookings
	 * @param model Injected model
	 * @return Accepted.jsp with the modified model
	 */
	@RequestMapping(value="/accepted")
	public String acceptedActivitiesPage(Model model){
		model.addAttribute("bookingList", service.getActiveBookings());
		
		return "admin/bookingManagement/accepted";
	}
	
}
