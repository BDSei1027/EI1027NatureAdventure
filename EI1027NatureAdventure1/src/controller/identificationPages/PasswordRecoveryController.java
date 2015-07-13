package controller.identificationPages;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import controller.basics.AbstractController;
import validators.ClientRegisterValidator;
import validators.DoublePasswordValidator;
import validators.UserValidator;
import classes.ClientRegister;
import classes.DoublePassword;
import classes.Email;
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
		return "recverpassword";
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

		model.addAttribute("error", 0);
		return "recoverpassword";
	}
	
	/**
	 * (work in progress)
	 * Method that verifies the recovery token to process the account recovery
	 * @param token String token obtained by the email
	 * @param model Injected model
	 * @return Recoverpassword.jsp (not implemented yet, future impl)
	 */
	@RequestMapping(value="/passwordRecoveryAuth?{token}")
	public String recoveryAuthPage(@PathVariable String token, Model model){
		model.addAttribute("doublepassword", new DoublePassword());
		return "recverpasswordauth";
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
	@RequestMapping(value="/passwordRecoveryAuth?{token}", method=RequestMethod.POST)
	public String recoveryAuthPage(Model model, @PathVariable String token, @ModelAttribute("doublepassword") DoublePassword passwd, BindingResult bindingResult){
		new DoublePasswordValidator().validate(passwd, bindingResult);
		if(bindingResult.hasErrors()) return "recoverpasswordauth";
		
		if(service.validateToken(passwd.getUser(), token)){
			User user = service.getUser(passwd.getUser());
			user.setPassword(passwd.getPassword());
			service.updateUser(user);
			
			model.addAttribute("error", 0);
			return "recoverpasswordauth";
		}
		
		model.addAttribute("error", 1);
		return "recverpasswordauth";
	}
}
