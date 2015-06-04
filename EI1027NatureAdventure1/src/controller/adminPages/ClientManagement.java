package controller.adminPages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
		model.addAttribute("client", service.getClient(idClient));
		return "admin/clientManagement/details";
	}
	
}
