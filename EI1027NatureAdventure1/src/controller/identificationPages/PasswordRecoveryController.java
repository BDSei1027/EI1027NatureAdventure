package controller.identificationPages;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import controller.basics.AbstractController;
import validators.DoublePasswordValidator;
import classes.DoublePassword;
import classes.Email;
import classes.Token;
import classes.User;


/**
 * Controller that manages the identification pages
 */
@Controller
public class PasswordRecoveryController extends AbstractController{
	
	/**
	 * (work in progress)
	 * Password recovery form, (future uses)
	 * @param model Injected model
	 * @return Recoverpassword.jsp with the modified model
	 */
	@RequestMapping(value="/passwordRecovery")
	public String recoveryPage(Model model){
		model.addAttribute("email", new Email());
		return "recoverpassword";
	}
	
	/**
	 * (work in progress)
	 * Processes the data from the password recovery form (future uses)
	 * @param email Email used in the account
	 * @param model Injected model
	 * @return Recoverpassword.jsp with the result code (old version, error code is used)
	 */
	@RequestMapping(value="/passwordRecovery", method=RequestMethod.POST)
	public String recoveryPage(@ModelAttribute("email") Email email, Model model){
		if(email.isEmpty()){
			model.addAttribute("error", RESULT_DELETE_OR_DENY);
			return "recoverpassword";
		}
		
		try{
			String id = service.getClientId(email);
			String token = UUID.randomUUID().toString();
			service.sendPasswordRecovery(email,token);
			service.setToken(id, token);
		} catch (Exception e){}

		model.addAttribute("error", RESULT_ADD_OR_ACCEPT);
		return "recoverpassword";
	}
	
	/**
	 * (work in progress)
	 * Method that verifies the recovery token to process the account recovery
	 * @param token String token obtained by the email
	 * @param model Injected model
	 * @return Recoverpassword.jsp (not implemented yet, future impl)
	 */
	@RequestMapping(value="/passwordRecoveryAuth/{token}")
	public String recoveryAuthPage(@PathVariable String token, Model model){
		model.addAttribute("doublepassword", new DoublePassword());
		return "recoverpasswordauth";
	}
	
	/**
	 * (work in progress)
	 * Method that verifies the recovery token to process the account recovery
	 * @param model Injected model
	 * @param token String token obtained by the email
	 * @param passwd New password and its confirmation
	 * @param bindingResult Error handler
	 * @return Recoverpasswordauth.jsp with the error
	 */
	@RequestMapping(value="/passwordRecoveryAuth/{token}", method=RequestMethod.POST)
	public String recoveryAuthPage(Model model, @PathVariable String token, @ModelAttribute("doublepassword") DoublePassword passwd, BindingResult bindingResult, HttpSession session){
		new DoublePasswordValidator().validate(passwd, bindingResult);
		if(bindingResult.hasErrors()) return "recoverpasswordauth";
		
		String userName = service.getUserGivenAToken(token);
		if(userName != null){
			
			User user = service.getUser(userName);
			System.out.println(user);
			user.setPassword(passwd.getPassword());
			service.updateUser(user);
			
			session.setAttribute("user", user);
			
			service.deleteToken(userName);
			return "redirect:/index.html";
		}
		
		model.addAttribute("error", RESULT_DELETE_OR_DENY);
		return "recoverpasswordauth";
	}
}
