package controller.interceptors;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import validators.SessionValidator;
 

/**
 * Intercepter that allows only admin user to enter the admin pages
 */
public class AdminInterceptor extends HandlerInterceptorAdapter  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(); 
    	
    	//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			session.setAttribute("nextPage", request.getRequestURL());
			return false;
		}
		
		if(!user.hasPermissions(0)){ 
			response.sendRedirect(request.getContextPath()+"/restricted.html");
			return false;
		}
         
        return true;
    }
}