package controller.identificationPages;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import controller.basics.AbstractController;
import validators.UserValidator;
import classes.User;


/**
 * Controller that manages the identification pages
 */
@Controller
public class LoginController extends AbstractController{

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
}
