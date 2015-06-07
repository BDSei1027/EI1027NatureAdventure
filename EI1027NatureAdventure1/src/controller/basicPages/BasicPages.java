package controller.basicPages;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import classes.User;
import controller.basics.AbstractController;


@Controller
public class BasicPages extends AbstractController {	
	
	@RequestMapping(value="/es")
	public String esLanguage(HttpServletResponse response, HttpSession session){
		User user = (User) session.getAttribute("user");
		
		if(user!=null){
			user.setLanguage("ES");
			service.updateUser(user);
		}
		
		session.setAttribute("lang", "ES");
		
		return "redirect:index.html";
	}
	
	@RequestMapping(value="/en")
	public String enLanguage(HttpServletResponse response, HttpSession session){
		User user = (User) session.getAttribute("user");
		
		if(user!=null){
			user.setLanguage("EN");
			service.updateUser(user);
		}
		
		session.setAttribute("lang", "EN");
		
		return "redirect:index.html";
	}

	@RequestMapping(value="/index")
	public String indexPage(){
		return "index";
	}
	
	
	@RequestMapping(value="/activities")
	public String activitiesPage(){
		return "activities";
	}
	
	@RequestMapping(value="/about")
	public String aboutPage(){
		return "about";
	}
	
}
