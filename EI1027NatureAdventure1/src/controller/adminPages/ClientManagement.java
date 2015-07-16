package controller.adminPages;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import validators.ClientValidator;
import classes.Client;
import classes.Email;
import controller.basics.AbstractController;


/**
 * Controller that manages the clients.
 */
@Controller
@RequestMapping("/admin/clientManagement")
public class ClientManagement extends AbstractController {	
	
	
	/**
	 * Returns the client management main page
	 * @param model Injected model
	 * @return ClientManagement.jsp with the modified model
	 */
	@RequestMapping
	public String clientsPage(Model model){
		model.addAttribute("clientList", service.getAllClients());
		return "admin/clientManagement";
	}
	
	/**
	 * Shows any change made to the clients in the client management main page
	 * @param rezCode Operation code
	 * @param idClient Client id
	 * @param model Injected model
	 * @return ClientManagement.jsp with the modified model
	 */
	@RequestMapping(value="/{rezCode}&{idClient}")
	public String clientsPage(@PathVariable int rezCode, @PathVariable String idClient, Model model){
		
		model.addAttribute("error", rezCode);
		model.addAttribute("id", idClient);
		model.addAttribute("clientList", service.getAllClients());
		return "admin/clientManagement";
	}
	
	/**
	 * Shows the details of the client with id {idClient} and allow the admin to modify them.
	 * @param idClient Id of the client
	 * @param model Injected model
	 * @return Details.jsp with the modified model
	 */
	@RequestMapping(value="/details/{idClient}")
	public String clientDetailPage(@PathVariable String idClient, Model model){
		Client client = service.getClient(idClient);
		Email email = new Email();
		email.setTo(client.getClientEmail());
		
		model.addAttribute("client", client);
		model.addAttribute("email", email);
		
		return "admin/clientManagement/details";
	}
	
	@RequestMapping(value="/details/{idClient}/{rezCode}")
	public String clientDetailPageRez(@PathVariable String idClient, @PathVariable int rezCode, Model model){
		Client client = service.getClient(idClient);
		Email email = new Email();
		email.setTo(client.getClientEmail());
		
		model.addAttribute("client", client);
		model.addAttribute("email", email);
		model.addAttribute("error", rezCode);
		
		return "admin/clientManagement/details";
	}
	
	@RequestMapping(value="/details/{idClient}/sendmail", method=RequestMethod.POST)
	public String sendAdminMail(@PathVariable("idClient") String idClient, HttpServletRequest request){
		String to = request.getParameter("to");
		String message = request.getParameter("message");
		if(to.equals("") || message.equals("")) return "forward:/admin/clientManagement/details/"+idClient+"/"+RESULT_DELETE_OR_DENY+".html";
		try{
			service.enviarmailDeAdminHacia(to, message);
		} catch (Exception e){
			return "forward:/admin/clientManagement/details/"+idClient+"/"+RESULT_DELETE_OR_DENY+".html";
		}
		return "forward:/admin/clientManagement/details/"+idClient+"/"+RESULT_ADD_OR_ACCEPT+".html";
	}
	
	/**
	 * Process the data from a modified client
	 * @param client The client itself
	 * @param bindingResult 
	 * @return Redirect to client management main page with the result code and the client id
	 */
	@RequestMapping(value="/details/{idClient}", method=RequestMethod.POST)
	public String clientDetailPagePost(@ModelAttribute("client") Client client, BindingResult bindingResult) {

		new ClientValidator().validate(client, bindingResult);
		if (bindingResult.hasErrors()) return "admin/clientManagement/details";
		service.updateClient(client);
		return "forward:/admin/clientManagement/"+RESULT_MODIFY+"&"+client.getClientId()+".html";	
	}
	
	/**
	 * Deletes a client
	 * @param idClient Id of the client
	 * @return Redirect to client management main page with the result code and the client id
	 */
	@RequestMapping(value="/deleteClient/{idClient}")
	public String clientRemoval(@PathVariable String idClient){
		service.deleteClient(idClient);
		return "forward:/admin/clientManagement/"+RESULT_DELETE_OR_DENY+"&"+idClient+".html";
	}
	
}
