package controller.identification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.LogicLayer;
import validators.ClientRegisterValidator;
import validators.UserValidator;
import classes.Client;
import classes.ClientRegister;
import classes.User;


@Controller
public class MainIdentification {
	
	private LogicLayer service;


	/**
	 * Method that allows automatic independence injection
	 * @param service Service to inject
	 */
	@Autowired
	public void setService(LogicLayer service){
		this.service = service;
	}
	
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
	public String processLogin(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session){
		if(user.getUser() == null){
			user.setUser(request.getParameter("user"));
			user.setPassword(request.getParameter("password"));
			user.setRememberMe(request.getParameter("remem")==null?false:true);
		}
		boolean remember = user.isRememberMe();
		
		//Correct field format validator
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, bindingResult);
		
		if(bindingResult.hasErrors()) return "login";
		
		user = service.loginUser(user.getUser(), user.getPassword());
		
		//Password check 
		if(user==null) {
			bindingResult.rejectValue("password", "badpw", "Incorrect password"); 
			return "login";
		}
		
		//Maintain the user data in the session
		session.setAttribute("user", user);
		System.out.println(remember);
		
		
		//Get the page that called the login
		StringBuffer nextPage = (StringBuffer) session.getAttribute("nextPage");
		
		
		//Return the page that called the login or go to the main page
		if(nextPage != null){
			session.removeAttribute("nextPage");
			return "redirect:"+nextPage;
		}
		return "redirect:/index.jsp";
		
	}
	
	/**
	 * Method used to logout the user
	 * @param session Http session that contains the user data
	 * @return Bye page
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "logout";		
	}
	
	/**
	 * Method used to redirect from a restricted area
	 * @return
	 */
	@RequestMapping(value="/restricted")
	public String restricted(){
		return "restricted";		
	}

	@RequestMapping(value="/register")
	public String register(Model model) {
		model.addAttribute("register", new ClientRegister());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerForm(@ModelAttribute("register") ClientRegister clientRegister, BindingResult bindingResult, HttpSession session) {
		//Correct field format validator
		ClientRegisterValidator validator = new ClientRegisterValidator();
		validator.validate(clientRegister, bindingResult);
		
		if (bindingResult.hasErrors()) return "register";
		
		service.addClient(createClientFrom(clientRegister));
		User user = createUserFrom(clientRegister);
		service.addUser(user);
		
		user.clearPassword();
		session.setAttribute("user", user);
		
		return "redirect:/index.jsp";
	}
	
	private Client createClientFrom(ClientRegister cl) {
		Client client = new Client();
		client.setClientId(cl.getId());
		client.setClientName(cl.getName());
		client.setClientLastName(cl.getLastName());
		client.setClientEmail(cl.getEmail());
		return client;
	}
	
	private User createUserFrom(ClientRegister cl) {
		User newUser = new User();
		newUser.setUser(cl.getId());
		newUser.setPassword(cl.getPassword());
		newUser.setName(cl.getName());
		newUser.setLanguage(cl.getLanguage());
		newUser.setType(2);
		return newUser;
	}
}
