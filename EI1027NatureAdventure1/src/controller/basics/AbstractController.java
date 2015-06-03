package controller.basics;

import org.springframework.beans.factory.annotation.Autowired;

import service.LogicLayer;

public abstract class AbstractController {
	
	protected LogicLayer service;


	/**
	 * Method that allows automatic independence injection
	 * @param service Service to inject
	 */
	@Autowired
	public void setService(LogicLayer service){
		this.service = service;
	}
	
	

}
