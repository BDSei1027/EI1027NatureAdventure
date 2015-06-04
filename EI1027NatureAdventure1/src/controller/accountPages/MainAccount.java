package controller.accountPages;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import controller.basics.AbstractController;
import classes.Client;
import classes.User;
import validators.ClientValidator;


@Controller
@RequestMapping(value="/account")
public class MainAccount extends AbstractController{
	
	
	/**
	 * Method that adds the user data to the model, so the page displays it
	 * @param model Page model
	 * @param session User session
	 * @return The page with the user data
	 */
	@RequestMapping
	public String userPage(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		
		//Get the client data from the user
		Client client = service.getClient(user);
		
		//Inject the client data
		model.addAttribute("client", client);

		return "account";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String updateUser(Model model, @ModelAttribute("client") Client client, BindingResult bindingResult){
		
		
		//Correct field format validator
		ClientValidator clientValidator = new ClientValidator();
		clientValidator.validate(client, bindingResult);		
		
		//Any filed error returns to the form again
		if(bindingResult.hasErrors()) return "account";
		
		//Update client data
		service.updateClient(client);
		return "redirect:/account.html";
	}

}
