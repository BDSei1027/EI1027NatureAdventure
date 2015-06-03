package controller.interceptors;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import validators.SessionValidator;
 

public class AdminInterceptor extends HandlerInterceptorAdapter  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         
    	//Check if the user is allowed to enter this page
		SessionValidator user = new SessionValidator(request.getSession());
		if(!user.isLogged()) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return false;
		}
		
		if(!user.hasPermissions(0)){ 
			response.sendRedirect(request.getContextPath()+"/login.html");
			return false;
		}
         
        return true;
    }
}