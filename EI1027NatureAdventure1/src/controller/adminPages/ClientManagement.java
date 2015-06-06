package controller.adminPages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import validators.ClientValidator;
import validators.EmailValidator;
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
	
	@RequestMapping(value="/{rezCode}&{idClient}")
	public String clientsPage(@PathVariable int rezCode, @PathVariable String idClient, Model model){
		
		model.addAttribute("error", rezCode);
		model.addAttribute("id", idClient);
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
	
	@RequestMapping(value="/details/{idClient}", method=RequestMethod.POST)
	public String clientDetailPagePost(@PathVariable String idClient, @ModelAttribute("client") Client client, @ModelAttribute("email") Email email, BindingResult bindingResult, Model model) {
		if (!client.isEmpty()) {
			new ClientValidator().validate(client, bindingResult);
			if (bindingResult.hasErrors()) return "admin/clientManagement/details";
			service.updateClient(client);
			return "redirect:/admin/clientManagement.html";
		}
		
		
		
		if (!email.isEmpty()) {
			new EmailValidator().validate(email, bindingResult);
			if (bindingResult.hasErrors()) {
				model.addAttribute("error", 1);
				return "admin/clientManagement/details";
			}
			//service.sendMail(email);
			model.addAttribute("error", 0);
		}
		
		model.addAttribute("client", client);
		return "/admin/clientManagement/details";
	}
	
}
