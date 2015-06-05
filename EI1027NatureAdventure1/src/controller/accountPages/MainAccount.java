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
		
		if (user.getType() == 0) {
			return "redirect:/admin";
		} else if (user.getType() == 1) {
			Instructor instr = service.getInstructor(user);
			model.addAttribute("instructor", instr);
		} else {
			Client client = service.getClient(user);
			model.addAttribute("client", client);
		}

		model.addAttribute("doublepassword", new DoublePassword());
		return "account";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String updateUser(Model model, HttpSession session, @ModelAttribute("client") Client client, @ModelAttribute("doublepassword") DoublePassword doublepassword, @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult){
		
		System.out.println(service);
		if (!client.isEmpty()) {

			new ClientValidator().validate(client, bindingResult);		
			if(bindingResult.hasErrors()) {
				model.addAttribute("error", 1);
				return "account";
			}
			service.updateClient(client);
			model.addAttribute("client", client);
		}
		
		if (!instructor.isEmpty()) {
			System.out.println("instr");
			new InstructorValidator().validate(client, bindingResult);
			if (bindingResult.hasErrors()) {
				model.addAttribute("error", 2);
				return "account";
			}
			service.updateInstructor(instructor);
			model.addAttribute("instructor", instructor);
		}
		
		if (!doublepassword.isEmpty()) {
			System.out.println("pass");
			new DoublePasswordValidator().validate(doublepassword, bindingResult);
			if (bindingResult.hasErrors()) {
				model.addAttribute("error", 3);
				return "account";
			}
			User user = (User) session.getAttribute("user");
			user.setPassword(doublepassword.getPassword());
			service.updateUserWithPasswordType(user);
		}
		
		model.addAttribute("error", 0);
		return "/account";
	}

}
