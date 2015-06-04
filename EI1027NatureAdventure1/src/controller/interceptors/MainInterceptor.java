package controller.interceptors;
 
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 




import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import service.LogicLayer;
import validators.SessionValidator;
 

public class MainInterceptor extends HandlerInterceptorAdapter  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	HttpSession session = request.getSession();
    	SessionValidator sessionValidator = new SessionValidator(session);
    	
    	
    	if(!sessionValidator.isLogged()){
    		
    		String name = null;
    		String token = null;
    		
    		for(Cookie cookie:request.getCookies()){
    			if (cookie.getName()=="user"){
    				name = cookie.getValue();
    				cookie.setMaxAge(0);
    			}
    			else if (cookie.getName()=="token"){
    				token = cookie.getValue();
    				cookie.setMaxAge(0);
    			}
    		}
    		
    		if(name != null){
    			LogicLayer service = new LogicLayer();
    			
    			if(service.validateToken(name,token)){
    				session.setAttribute("user", service.getUser(name));
    				
    				String newToken = UUID.randomUUID().toString();
    				
    				response.addCookie(new Cookie("user", name));
    				response.addCookie(new Cookie("token", token));
    				
    				service.setToken(name, token);
    			}
    		}
	
    	}
         
        return true;
    }
}