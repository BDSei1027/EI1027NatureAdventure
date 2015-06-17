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
import classes.DoublePassword;
import classes.Instructor;
import classes.User;
import validators.ClientValidator;
import validators.DoublePasswordValidator;
import validators.InstructorValidator;


/**
 *Controller that manages the personal information page
 */
@Controller 
@RequestMapping(value="/account")
public class MainAccount extends AbstractController{
	
	
	public static final int ADMIN = 0;
	public static final int INSTRUCTOR1 = 1;
	public static final int CLIENT = 2;


	/**
	 * Manages the function "my account" that every type of user has
	 * @param model Page model
	 * @param session User session
	 * @return Account.jps
	 */
	@RequestMapping
	public String acoountPage(Model model, HttpSession session){
		//Logged data
		User user = (User) session.getAttribute("user");
		
		//Gives the model the required data based on the user type
		if (user.getType() == ADMIN) return "redirect:/admin.html";
		if (user.getType() == INSTRUCTOR1) model.addAttribute("instructor", service.getInstructor(user));
		if (user.getType() == CLIENT) model.addAttribute("client", service.getClient(user));

		//And gives also a field with the password and its confirmation
		model.addAttribute("doublepassword", new DoublePassword());
		
		return "account";
		
	}
	
	/**
	 * Updates the client information based on the data passed through post. 
	 * @param model Model of the page
	 * @param client Client information
	 * @param bindingResult Error handler
	 * @return Account.jsp with the modified model and bindingResult.
	 */
	@RequestMapping(value="/updateClient", method=RequestMethod.POST)
	public String updateClientPage(Model model, @ModelAttribute("client") Client client, BindingResult bindingResult){
		model.addAttribute("doublepassword", new DoublePassword());
		new ClientValidator().validate(client, bindingResult);
		if(bindingResult.hasErrors()) return "account";
		service.updateClient(client);
		
		model.addAttribute("error", 0);
		
		return "account";
	}
	
	
	/**
	 * Updates the instructor information based on the data passed through post. 
	 * @param model Injected model
	 * @param instructor Instructor data
	 * @param bindingResult Error handler
	 * @return Account.jsp with the modified model and bindingResult.
	 */
	@RequestMapping(value="/updateInstructor",  method=RequestMethod.POST)
	public String updateInstructorPage(Model model, @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult){
		model.addAttribute("doublepassword", new DoublePassword());
		new InstructorValidator().validate(instructor, bindingResult);
		if(bindingResult.hasErrors()) return "account";
		service.updateInstructor(instructor);
		
		model.addAttribute("error", 0);
		
		return "account";
	}
	
	
	/**
	 * Updates the password of any account
	 * @param model Injected model
	 * @param doublePasswd The new password and its confirmation
	 * @param bindingResult Error handler
	 * @param session Session containing the user
	 * @return Account.jsp with the modified model and bindingResult.
	 */
	@RequestMapping(value="/updateAuth", method=RequestMethod.POST)
	public String updateAuthPage(Model model, @ModelAttribute("doublepassword") DoublePassword doublePasswd, BindingResult bindingResult, HttpSession session){
		new DoublePasswordValidator().validate(doublePasswd, bindingResult);
		
		User user = (User) session.getAttribute("user");
		
		
		if (user.getType() == 0) return "redirect:/admin.html";
		if (user.getType() == 1) model.addAttribute("instructor", service.getInstructor(user));
		if (user.getType() == 2) model.addAttribute("client", service.getClient(user));
		
		if(bindingResult.hasErrors()) return "account";
		
		user.setPassword(doublePasswd.getPassword());
		service.updateUserWithPasswordType(user);
		
		model.addAttribute("error", 0);
		
		return "account";
	}
	
	


}
