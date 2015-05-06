package service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import classes.Activity;
import classes.Instructor;
import database.DaoInterface;
import database.daoActivity;
import database.daoBooking;
import database.daoClient;
import database.daoInstructor;
import database.daoStatus;

//TODO Cambiar referencias daos viejos
//Implementación palera 
//TODO trabajar en memoria
//TODO guardar en fichero local
//TODO subir cada X tiempo
//TODO boton subir ++

@Service
public class LogicLayer {
	//DAOS
	private DaoInterface daoActivity;
	private DaoInterface daoBooking;
	private DaoInterface daoClient;
	private DaoInterface daoInstructor;
	private DaoInterface daoStatus;
	
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
		daoBooking daoBook = (daoBooking) this.daoBooking;
		daoActivity daoAct = (daoActivity) this.daoActivity;
		
		this.innerBookingID = daoBook.getMaxInnerID();
		this.activeBookingID = daoBook.getMaxActiveID();
		this.activityID = daoAct.getMaxID();
		// El instructor no tiene idMaxima que es el dni
		//this.instructorID = daoInstructor.getMaxID();
	}

	
	/**
	 * ALPHA VERSION
	 */
	
	
	/**
	 * INSTRUCTOR ZONE
	 */
	
	
	/**
	 * Add an instructor in the database.
	 * @param The instructor
	 */
	public void addInstructor(Instructor instructor){
//		daoInstructor miDao = (daoInstructor) daoInstructor; //Casting preparado para cuando se establezca el cambio a abstract
//													   //Como en este momento no tengo la interfaz delante lo dejo asi pendiente de cambiar el método
//		miDao.addInstructor(instructor);
		daoInstructor.addElement(instructor);
		
	}
	
	/**
	 * Set inactive an instructor from the database. The ssNumber is required
	 * @param ssnumber of the instructor
	 * TODO realizar operacion de borrar en instruidas (Solucionado, lo hace el dao al borrar el instructor)
	 * 
	 */
	public void deleteInstructor(String code){
		//daoInstructor miDao = (daoInstructor) daoInstructor;
		Instructor myInstructor= this.getInstructor(code);
		if (myInstructor==null){
			return;
		}
		//miDao.deleteInstructorFromActivities(code);
		//miDao.deleteInstructor(code);
		
		myInstructor.setActive(false);
	    this.updateInstructor(myInstructor);
		
	}
	
	/**
	 * Update an instructor from the database. This operation is only allowed when the instructor was registered before in the database
	 * @param The instructor
	 */
	
	public void updateInstructor(Instructor instructor){
		//daoInstructor miDao = (daoInstructor) daoInstructor; 
		if (this.getInstructor(instructor.getSsNumber())==null){
			return;
		}
		//miDao.updateInstructor(instructor);
		daoInstructor.updateElement(instructor);
	}

	/**
	 * Given a ssNumber a Instructor is delivered. If the instructor doesn't exist in the database it returns null 
	 *@return an instructor or null
	 */
	
	public Instructor getInstructor(String code){
//		daoInstructor miDao = (daoInstructor) daoInstructor; 
//		Instructor myInstructor = miDao.getInstructor(code);
		Instructor myInstructor = (Instructor) daoInstructor.getElement(code);
		return myInstructor;
	}
	
	/**
	 * Get all the instructors in the database
	 * @return A collection of Instructor with all instructors
	 */
	
	public Collection<Instructor> getAllInstructors(){ //Devuelvo solo lista de Instructores para facilitar tarea a la vista
		//daoInstructor miDao = (daoInstructor) daoInstructor; 
		Map<String,Instructor> allInstructors = (Map<String,Instructor>) daoInstructor.getElements();
		Collection<Instructor> allInstructorsClasses= allInstructors.values();
		return allInstructorsClasses;
	}
	
	/**
	 * change the active property of the instructor in the database
	 * @param The instructor 
	 */
	
	public void setInstructorAvailable(Instructor instructor){
		instructor.setActive(true);
		daoInstructor.updateElement(instructor);	
	}
		
	
	/**
	 * ACTIVITY ZONE
	 */
	
	
	
	/**
	 * Add a activity in the database.
	 * @param The activity
	 */
	public void addActivity(Activity activity){
		daoActivity.addElement(activity);
	}
	
	
	/**
	 * Set inactive an activity from the database. The ssNumber is required
	 * @param idActivity of the activity
	 * TODO realizar operacion de borrar en instruidas (Solucionado, lo hace el dao al borrar el instructor)
	 * 
	 */
	public void deleteActivity(String code){
		Activity myActivity = this.getActivity(code);
		if (myActivity==null){
			return;
		}
		myActivity.setIsActive(false);
		this.updateActivity(myActivity);
		
		
	}
	
	
	/**
	 * Update an activity from the database. This operation is only allowed when the activity was registered before in the database
	 * @param The activity
	 */
	public void updateActivity(Activity activity){
		if (this.getActivity(""+ activity.getIdAct())==null){
			return;
		}
		daoActivity.updateElement(activity);
	}
	
	
	
	/**
	 * Given a code a activity is delivered. If the activity doesn't exist in the database it returns null 
	 *@return an activity or null
	 */
	public Activity getActivity(String code){
		Activity myActivity = (Activity) daoActivity.getElement(code);
		return myActivity;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//Setter inyectables ---------------------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public void setDaoActivity(daoActivity daoActivity) {
		this.daoActivity = daoActivity;
	}
	
	@Autowired
	public void setDaoBooking(daoBooking daoBooking) {
		this.daoBooking = daoBooking;
	}
	
	@Autowired
	public void setDaoClient(daoClient daoClient) {
		this.daoClient = daoClient;
	}
	
	@Autowired
	public void setDaoInstructor(daoInstructor daoInstructor) {
		this.daoInstructor = daoInstructor;
	}
	
	@Autowired
	public void setDaoStatus(daoStatus daoStatus) {
		this.daoStatus = daoStatus;
	}
	
}
