package controller.basics;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import service.LogicLayer;

/**
 * Base abstract class for all controllers
 */
public abstract class AbstractController {
	
	public static final int RESULT_ADD_OR_ACCEPT = 0;
	public static final int RESULT_MODIFY = 1;
	public static final int RESULT_DELETE_OR_DENY = 2;
	
	protected LogicLayer service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}


	/**
	 * Method that allows automatic independence injection
	 * @param service Service to inject
	 */
	@Autowired
	public void setService(LogicLayer service){
		this.service = service;
	}
	
	

}
