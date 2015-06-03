package controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin/clientManagement")
public class ClientManagement extends AbstractController {	
	
	/**
	 * Method that returns the admin main page
	 * @return The admin main page
	 */
	@RequestMapping
	public String clientsPage(Model model){
		model.addAttribute("item", service.getAllClients());
		return "admin/clientManagement";
	}
	
}
