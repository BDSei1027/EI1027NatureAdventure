package controller.adminPages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import validators.DoublePasswordValidator;
import classes.DoublePassword;
import classes.User;
import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin")
public class MainAdmin extends AbstractController {	
	
	/**
	 * Method that returns the admin main page
	 * @return The admin main page
	 */
	@RequestMapping
	public String adminPage(Model model){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");     
		String dateToday = df.format(new Date());
				
		model.addAttribute("dateToday",dateToday);
		model.addAttribute("numbookings", service.getPendingBookingsCount());
		model.addAttribute("numclients", service.getUserCount());
		model.addAttribute("doublepassword", new DoublePassword());
		
		return "admin";
	}
	
	@RequestMapping(value="/{resCode}")
	public String adminPage(Model model, @PathVariable int resCode){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");     
		String dateToday = df.format(new Date());
		
		model.addAttribute("dateToday",dateToday);
		model.addAttribute("numbookings", service.getPendingBookingsCount());
		model.addAttribute("numclients", service.getUserCount());
		model.addAttribute("doublepassword", new DoublePassword());
		model.addAttribute("error", resCode);
		
		return "admin";
	}
	
	@RequestMapping(value="/authAdmin", method=RequestMethod.POST)
	public String adminPage(@ModelAttribute("doublepassword") DoublePassword passwd,BindingResult bindingResult, HttpSession session ){
		new DoublePasswordValidator().validate(passwd, bindingResult);
		if(bindingResult.hasErrors()) return "forward:/admin/"+2+".html";
		
		User user = (User) session.getAttribute("user");
		user.setPassword(passwd.getPassword());
		service.updateUser(user);
		
		return "forward:/admin/"+0+".html";
	}
}
