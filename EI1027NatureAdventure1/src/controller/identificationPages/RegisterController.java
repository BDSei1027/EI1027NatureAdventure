package controller.identificationPages;

import java.util.Locale;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import controller.basics.AbstractController;
import validators.ClientRegisterValidator;
import classes.ClientRegister;
import classes.User;


/**
 * Controller that manages the identification pages
 */
@Controller
public class RegisterController extends AbstractController{

	/**
	 * Returns the register form
	 * @param model Injected model
	 * @return Register.jsp with the modified model
	 */
	@RequestMapping(value="/register")
	public String register(Model model) {
		model.addAttribute("register", new ClientRegister());
		return "register";
	}
	
	/**
	 * Processes the data from the costumer register
	 * @param clientRegister Object containing all the data from the form
	 * @param bindingResult Error handler
	 * @param session Session to save the user
	 * @param locale Language
	 * @return Redirect to index.html
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerForm(@ModelAttribute("register") ClientRegister clientRegister, BindingResult bindingResult, HttpSession session, Locale locale) {
		//Correct field format validator
		new ClientRegisterValidator().validate(clientRegister, bindingResult);
		if (bindingResult.hasErrors()) return "register";
		
		
		try{
			User user = service.createUserFrom(clientRegister);
			
			service.addClient(service.createClientFrom(clientRegister));
			service.addUser(user);
			
			session.setAttribute("user", user);
		} catch(Exception e){
			if(locale.getLanguage().equals("es")) bindingResult.rejectValue("id", "repCli", "El cliente ya existe");
			if(locale.getLanguage().equals("en")) bindingResult.rejectValue("id", "repCli", "The client already exists");
			return "register";
		}
		
		return "redirect:/index.html";
	}
}
