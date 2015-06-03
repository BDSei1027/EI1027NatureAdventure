package controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin/bookingManagement")
public class BookingManagement extends AbstractController {

	
	@RequestMapping
	public String bookingMainPage(Model model){
		model.addAttribute("bookingList", service.getAllBookings());
		
		return "admin/bookingManagement/";
	}	
	
}
