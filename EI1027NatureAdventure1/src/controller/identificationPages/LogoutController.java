package controller.identificationPages;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.basics.AbstractController;


/**
 * Controller that manages the identification pages
 */
@Controller
public class LogoutController extends AbstractController{
	
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
}
