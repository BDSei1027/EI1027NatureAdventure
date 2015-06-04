package controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import classes.Client;
import classes.Email;
import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin/clientManagement")
public class ClientManagement extends AbstractController {	
	
	
	@RequestMapping
	public String clientsPage(Model model){
		model.addAttribute("clientList", service.getAllClients());
		return "admin/clientManagement";
	}
	
	@RequestMapping(value="/details/{idClient}")
	public String clientDetailPage(@PathVariable String idClient, Model model){
		Client client = service.getClient(idClient);
		Email email = new Email();
		email.setTo(client.getClientEmail());
		
		model.addAttribute("client", client);
		model.addAttribute("email", email);
		return "admin/clientManagement/details";
	}
	
}
