package controller.interceptors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.User;
import service.LogicLayer;
import validators.SessionValidator;

/**
 * Intercepter that changes the account language when the user changes the page langauge
 */
public class LanguajeInterceptor extends org.springframework.web.servlet.i18n.LocaleChangeInterceptor {
	
	private LogicLayer service;

	public void setService(LogicLayer service){
		this.service = service;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws ServletException {
		String lang = arg0.getParameter(getParamName());
		if(lang == null) return true;
		
		HttpSession session = arg0.getSession();
		SessionValidator validator = new SessionValidator(session);
		
		if(validator.isLogged()){
			User user = (User) session.getAttribute("user");
			user.setLanguage(lang);
			service.updateUser(user);
		}
		
		return super.preHandle(arg0, arg1, arg2);
	}
}
