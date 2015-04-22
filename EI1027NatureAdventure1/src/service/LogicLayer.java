package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.DaoActivity;
import database.DaoBooking;
import database.DaoClient;
import database.DaoInstructor;
import database.DaoStatus;

//Implementación palera 
//TODO trabajar en memoria
//TODO guardar en fichero local
//TODO subir cada X tiempo
//TODO boton subir ++

@Service
public class LogicLayer {
	//DAOS
	private DaoActivity daoActivity;
	private DaoBooking daoBooking;
	private DaoClient daoClient;
	private DaoInstructor daoInstructor;
	private DaoStatus daoStatus;
	
	//ID autoincrementales
	private int innerBookingID;
	private int activeBookingID;
	private int activityID;
	
	
	/**
	 * Inicializa las IDs para que se puedan autoincrementar.
	 */
	public LogicLayer() {
		inicializarIds();
	}

	
	/**
	 * Pide uan conexion a traves de los daos de la ID max
	 */
	private void inicializarIds() {
		this.innerBookingID = daoBooking.getMaxInnerID();
		this.activeBookingID = daoBooking.getMaxActiveID();
		this.activityID = daoActivity.getMaxID();
	}

	
	
	
//Setter inyectables ---------------------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public void setDaoActivity(DaoActivity daoActivity) {
		this.daoActivity = daoActivity;
	}
	
	@Autowired
	public void setDaoBooking(DaoBooking daoBooking) {
		this.daoBooking = daoBooking;
	}
	
	@Autowired
	public void setDaoClient(DaoClient daoClient) {
		this.daoClient = daoClient;
	}
	
	@Autowired
	public void setDaoInstructor(DaoInstructor daoInstructor) {
		this.daoInstructor = daoInstructor;
	}
	
	@Autowired
	public void setDaoStatus(DaoStatus daoStatus) {
		this.daoStatus = daoStatus;
	}
	
}
