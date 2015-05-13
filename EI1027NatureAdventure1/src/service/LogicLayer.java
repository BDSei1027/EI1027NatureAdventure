package service;

import java.util.Collection;
import java.util.Map;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import classes.Activity;
import classes.Booking;
import classes.Instructor;
import classes.User;
import database.DaoInterface;
import database.daoActivity;
import database.daoBooking;
import database.daoClient;
import database.daoInstructor;
import database.daoStatus;
import database.daoUser;
import database.daoAvaliableBook;

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
	private DaoInterface daoUser;
	private DaoInterface daoAvaliable;
	
	//ID autoincrementales
	private int innerBookingID;
	private int activeBookingID;
	private int activityID;
	
	// Encriptacion
	private PasswordEncryptor encryptor = new BasicPasswordEncryptor(); 
	
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
	}

	
	/*
	 * ALPHA VERSION
	 */
	
	
	/*
	 * INSTRUCTOR ZONE
	 */
	
	
	/**
	 * Add an instructor in the database.
	 * @param The instructor
	 */
	public void addInstructor(Instructor instructor){
		daoInstructor.addElement(instructor);
		
	}
	
	/**
	 * Set inactive an instructor from the database. The ssNumber is required
	 * @param ssnumber of the instructor
	 * 
	 */
	public void deleteInstructor(String code){
		Instructor myInstructor= this.getInstructor(code);
		if (myInstructor==null)	return;
		
		myInstructor.setActive(false);
	    this.updateInstructor(myInstructor);
		
	}
	
	/**
	 * Update an instructor from the database. This operation is only allowed when the instructor was registered before in the database
	 * @param The instructor
	 */
	public void updateInstructor(Instructor instructor){
		if (this.getInstructor(instructor.getSsNumber()) == null) return;
		daoInstructor.updateElement(instructor);
	}

	/**
	 * Given a ssNumber a Instructor is delivered. If the instructor doesn't exist in the database it returns null 
	 *@return an instructor or null
	 */
	
	public Instructor getInstructor(String code){
		Instructor myInstructor = (Instructor) daoInstructor.getElement(code);
		return myInstructor;
	}
	
	/**
	 * Get all the instructors from the database
	 * @return A collection of Instructor with all instructors
	 */
	public Collection<Instructor> getAllInstructors(){ //Devuelvo solo lista de Instructores para facilitar tarea a la vista
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
		
	
	/*
	 * ACTIVITY ZONE
	 */
	
	
	
	/**
	 * Add a activity in the database.
	 * @param The activity
	 */
	public void addActivity(Activity activity){
		activity.setIdAct(activityID);
		this.activityID ++;
		daoActivity.addElement(activity);
	}
	
	/**
	 * Set inactive an activity from the database. The ssNumber is required
	 * @param idActivity of the activity
	 */
	public void deleteActivity(String code){
		Activity myActivity = this.getActivity(code);
		if (myActivity==null) return;
		myActivity.setIsActive(false);
		this.updateActivity(myActivity);	
	}
	
	/**
	 * Update an activity from the database. This operation is only allowed when the activity was registered before in the database
	 * @param The activity
	 */
	public void updateActivity(Activity activity){
		if (this.getActivity(""+ activity.getIdAct())==null) return;
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
	
	/**
	 * Get all the activities from the database
	 * @return A collection of Activity  with all activities
	 */
	public Collection<Activity> getAllActivities(){ //Devuelvo solo lista de actividades para facilitar tarea a la vista
		Map<String,Activity> allInstructors = (Map<String,Activity>) daoActivity.getElements();
		Collection<Activity> allInstructorsClasses= allInstructors.values();
		return allInstructorsClasses;
		
	}
	
	
	/*
	 * BOOKING ZONE
	 */
	
	
//	public void addBooking(Booking booking){
//		
//	}
//	
	
	/*
	 * USER ZONE
	 */
	
	public User loginUser(String user, String password) {
		User u = (User) daoUser.getElement(user.trim());
		if ( u == null ) return null;
		if ( encryptor.checkPassword(password, u.getPassword()) ) {
			u.clearPassword();
			return u;
		}
		else return null; // Bad Login
	}
	
	public void addUser(User user){
		daoUser.addElement(user);
		
	}
	
	public void deleteUser(String identifier){
		User myUser = this.getUser(identifier);
		if (myUser==null) return;
		daoUser.deleteElement(myUser);	
		
	}
	
	public void updateUser(User user){
		if (this.getUser(""+ user.getUser())==null) return;
		daoUser.updateElement(user);
		
	}
	
	public User getUser(String identifier){
		User myUser = (User) daoUser.getElement(identifier);
		return myUser;
		
	}
	
	public Collection<User> getAllUsers(){
		Map<String,User> allUsers =  (Map<String, User>) daoUser.getElements();
		Collection<User> allUsersClasses = allUsers.values();
		return allUsersClasses;
		
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
	
	@Autowired
	public void setDaoUser(daoUser daoUser) {
		this.daoUser = daoUser;
	}
	
	@Autowired
	public void setDaoAvaliable(daoAvaliableBook daoAvaliable) {
		this.daoAvaliable = daoAvaliable;
	}
}
