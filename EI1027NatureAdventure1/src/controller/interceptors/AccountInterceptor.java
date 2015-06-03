package controller.interceptors;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import validators.SessionValidator;
 

public class AccountInterceptor extends HandlerInterceptorAdapter  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(); 
    	
		SessionValidator user = new SessionValidator(session);
		if(!user.isLogged()) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			session.setAttribute("nextPage", request.getRequestURL());
			return false;
		}
         
        return true;
    }
}