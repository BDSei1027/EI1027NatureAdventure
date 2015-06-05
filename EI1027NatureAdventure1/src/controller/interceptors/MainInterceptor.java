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
	
	private LogicLayer service;

	public void setService(LogicLayer service){
		this.service = service;
	}
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	HttpSession session = request.getSession();
    	SessionValidator sessionValidator = new SessionValidator(session);
    	
    	
    	if(!sessionValidator.isLogged()){
    		
    		Cookie nameCookie = null;
    		Cookie tokenCookie = null;
    		
    		if(request.getCookies()!=null) for(Cookie cookie:request.getCookies()){
    			if (cookie.getName().equals("user")){
    				nameCookie = cookie;
    			}
    			else if (cookie.getName().equals("token")){
    				tokenCookie = cookie;
    			}
    		}
    		
    		if(nameCookie != null){
    			if(service.validateToken(nameCookie.getValue(),tokenCookie.getValue())){
    				session.setAttribute("user", service.getUser(nameCookie.getValue()));
    				
    				String newToken = UUID.randomUUID().toString();
    				
    				tokenCookie.setValue(newToken);
    				tokenCookie.setPath("/");
    				
    				response.addCookie(tokenCookie);
    				    				
    				service.setToken(nameCookie.getValue(), newToken);
    			}
    		}
	
    	}
         
        return true;
    }
}