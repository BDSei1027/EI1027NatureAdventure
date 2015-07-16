package controller.adminPages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import validators.NoteValidator;
import classes.DoublePassword;
import classes.Note;
import classes.User;
import controller.basics.AbstractController;


/**
 * Controller that manages the admin main page
 */
@Controller
@RequestMapping("/admin")
public class MainAdmin extends AbstractController {	
	
	/**
	 * Method that returns the admin main page
	 * @param model Injected model
	 * @return Admin.jsp with the modified model
	 */
	@RequestMapping
	public String adminPage(Model model){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");     
		String dateToday = df.format(new Date());
		Note nota = new Note();
		nota.setDateCreation(new Date());
		
		model.addAttribute("notas", service.getAllNotes());
		model.addAttribute("note", nota);
		model.addAttribute("dateToday", dateToday);
		model.addAttribute("numbookings", service.getPendingBookingsCount());
		model.addAttribute("numclients", service.getUserCount());
		model.addAttribute("doublepassword", new DoublePassword());
		
		return "admin";
	}
	
	/**
	 * Method that shows the admin page after changing its password
	 * @param model Injected model
	 * @param resCode Result code
	 * @return Admin.jsp and the resulf of trying to change the password.
	 */
	@RequestMapping(value="/{resCode}")
	public String adminPage(Model model, @PathVariable int resCode){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");     
		String dateToday = df.format(new Date());
		Note nota = new Note();
		nota.setDateCreation(new Date());
		
		model.addAttribute("notas", service.getAllNotes());
		model.addAttribute("note", nota);
		model.addAttribute("dateToday", dateToday);
		model.addAttribute("numbookings", service.getPendingBookingsCount());
		model.addAttribute("numclients", service.getUserCount());
		model.addAttribute("doublepassword", new DoublePassword());
		model.addAttribute("error", resCode);
		
		return "admin";
	}
	
	/**
	 * Processes the try to change the admin password
	 * @param passwd Object containing the password and its confirmation
	 * @param bindingResult Error handler
	 * @param session Session to get the user from
	 * @return Redirect to the main page with the result
	 */
	@RequestMapping(value="/authAdmin", method=RequestMethod.POST)
	public String adminPage(@ModelAttribute("doublepassword") DoublePassword passwd,BindingResult bindingResult, HttpSession session ){
		new DoublePasswordValidator().validate(passwd, bindingResult);
		if(bindingResult.hasErrors()) return "forward:/admin/"+RESULT_DELETE_OR_DENY+".html";
		
		User user = (User) session.getAttribute("user");
		user.setPassword(passwd.getPassword());
		service.updateUser(user);
		
		return "forward:/admin/"+RESULT_ADD_OR_ACCEPT+".html";
	}
	
	@RequestMapping(value="/newNote", method=RequestMethod.POST)
	public String newNote(@ModelAttribute("note") Note note,BindingResult bindingResult){
		new NoteValidator().validate(note, bindingResult);
		if(bindingResult.hasErrors()) return "forward:/admin/"+4+".html";
		
		service.addNote(note);
		
		return "forward:/admin/"+3+".html";
	}
	
	@RequestMapping(value="/deleteNote/{idNote}")
	public String deleteNote(@PathVariable int idNote){
		
		service.deleteNote(idNote);
		
		return "forward:/admin/"+3+".html";
	}
}
