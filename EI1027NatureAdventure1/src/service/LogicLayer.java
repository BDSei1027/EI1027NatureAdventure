package service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import classes.Instructor;
import database.oldDaos.DaoActivity;
import database.oldDaos.DaoBooking;
import database.oldDaos.DaoClient;
import database.oldDaos.DaoInstructor;
import database.oldDaos.DaoStatus;

//TODO Cambiar referencias daos viejos
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
	private int instructorID;
	
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
		this.instructorID = daoInstructor.getMaxID();
	}

	
	/**
	 * ALPHA VERSION
	 */
	
	
	/**
	 * INSTRUCTOR ZONE
	 */
	
	
	/**
	 * Add a instructor in the database.
	 */
	public void addInstructor(Instructor instructor){
		DaoInstructor miDao = (DaoInstructor) daoInstructor; //Casting preparado para cuando se establezca el cambio a abstract
													   //Como en este momento no tengo la interfaz delante lo dejo asi pendiente de cambiar el método
		miDao.addInstructor(instructor);
		
	}
	
	/**
	 * Delete a instructor from the database. The ssNumber is required
	 */
	public void deleteInstructor(String code){
		DaoInstructor miDao = (DaoInstructor) daoInstructor;
		if (this.getInstructor(code)==null){
			return;
		}
		miDao.deleteInstructorFromActivities(code);
		miDao.deleteInstructor(code);
		
	}
	
	/**
	 * Update a instructor from the database. This operation is only allowed when the instructor was registered before in the database
	 */
	
	public void updateInstructor(Instructor instructor){
		DaoInstructor miDao = (DaoInstructor) daoInstructor; 
		if (this.getInstructor(instructor.getSsNumber())==null){
			return;
		}
		miDao.updateInstructor(instructor);
	}

	/**
	 * Given a ssNumber a Instructor is delivered. If the instructor doesn't exist in the database it returns null 
	 *@return a instructor or null
	 */
	
	public Instructor getInstructor(String code){
		DaoInstructor miDao = (DaoInstructor) daoInstructor; 
		Instructor myInstructor = miDao.getInstructor(code);
		return myInstructor;
	}
	
	/**
	 * Get all the instructors in the database
	 * @return A collection of Instructor with all instructors
	 */
	
	public Collection<Instructor> getAllInstructors(){ //Devuelvo solo lista de Instructores para facilitar tarea a la vista
		DaoInstructor miDao = (DaoInstructor) daoInstructor; 
		Map<String,Instructor> allInstructors =  miDao.getInstructors();
		Collection<Instructor> allInstructorsClasses= allInstructors.values();
		return allInstructorsClasses;
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
