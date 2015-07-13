package controller.identificationPages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import controller.basics.AbstractController;
import classes.Email;


/**
 * Controller that manages the identification pages
 */
@Controller
public class RestrictedController extends AbstractController{

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


}
