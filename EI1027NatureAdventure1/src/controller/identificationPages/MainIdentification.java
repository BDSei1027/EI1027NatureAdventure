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
public class MainIdentification extends AbstractController{

	/**
	 * Method that initializes a new user used by the form
	 * @param model Page model
	 * @return Login page
	 */
	@RequestMapping(value="/login")
	public String login(Model model){
		model.addAttribute("user", new User());
		return "login";
		
	}
	
	/**
	 * Method used to process the login form data
	 * @param request ServlerRequest used to gather the post data if the model was not used
	 * @param user User data gathered by the form
	 * @param bindingResult Result of processing the form
	 * @param session Http session used to store the user information
	 * @return Page that requested the login or the main page
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String lang = LocaleContextHolder.getLocale().getLanguage();
		if(request.getParameter("remem")!=null) user.setRememberMe(true);
		boolean remember = user.isRememberMe();
		
		//Correct field format validator
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, bindingResult);
		
		if(bindingResult.hasErrors()) return "login";
		
		user = service.loginUser(user.getUser(), user.getPassword());
		
		//Password check 
		if(user==null) {
			if(lang.equals("es")) bindingResult.rejectValue("password", "badpw", "Contraseña incorrecta"); 
			else bindingResult.rejectValue("password", "badpw", "Incorrect password"); 
			return "login";
		}
		
		//Maintain the user data in the session
		session.setAttribute("user", user);
		lang = user.getLanguage();
		if(remember){
			String userName = user.getUser();
			String token = UUID.randomUUID().toString();
			
			Cookie userCookie = new Cookie("user", userName);
			userCookie.setPath("/");
			Cookie tokenCookie = new Cookie("token", token);
			tokenCookie.setPath("/");
			
			response.addCookie(userCookie);
			response.addCookie(tokenCookie);
			
			service.setToken(userName, token); 
		}
		
		
		//Get the page that called the login
		StringBuffer nextPage = (StringBuffer) session.getAttribute("nextPage");
		
		//Return the page that called the login or go to the main page
		if(nextPage != null){
			session.removeAttribute("nextPage");
			return "redirect:"+nextPage+"?lang="+lang;
		}
		return "redirect:/index.html"+"?lang="+lang;
		
	}
	
	/**
	 * Method used to logout the user
	 * @param session Http session that contains the user data
	 * @param request Request used to delete the rememberMe cookies
	 * @return Logout.jsp
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session, HttpServletRequest request){
		
		
		for(Cookie cookie:request.getCookies()){
			if(cookie.getName().equals("user")){
				service.deleteToken(cookie.getValue());
				cookie.setMaxAge(0);
			}
			else if(cookie.getName().equals("token")) cookie.setMaxAge(0);
		}
		
		session.setAttribute("user", null);
		return "logout";		
	}
	
	/**
	 * Method used to redirect from a restricted area
	 * @param model Injected model
	 * @return Restricted.jsp 
	 */
	@RequestMapping(value="/restricted")
	public String restricted(Model model){
		model.addAttribute("email",new Email()); //Future uses
		return "restricted";
	}

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
			if(locale.getLanguage().equals("es")) bindingResult.rejectValue("client", "repCli", "El cliente ya existe");
			if(locale.getLanguage().equals("es")) bindingResult.rejectValue("client", "repCli", "The client already exists");
			return "register";
		}
		
		
		return "redirect:/index.html";
	}
	
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
