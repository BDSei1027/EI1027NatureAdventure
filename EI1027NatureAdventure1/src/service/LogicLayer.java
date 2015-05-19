package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import classes.Activity;
import classes.Booking;
import classes.Client;
import classes.Instructor;
import classes.Status;
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

//TODO getAllActivities(Instructor)
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
		super();
		
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
		if (instructor.getActivities() == null) instructor.setActivities(new ArrayList<Integer>());
		daoInstructor.addElement(instructor);
		
	}
	
	/**
	 * Set inactive an instructor from the database. The ssNumber is required
	 * @param ssnumber of the instructor
	 * 
	 */
	public void inactiveInstructor(String code){
		Instructor myInstructor= this.getInstructor(code);
		if (myInstructor==null)	return;
		
		myInstructor.setActive(false);
	    this.updateInstructor(myInstructor);
		
	}
	

	public void activeInstructor(String code) {
		Instructor myInstructor= this.getInstructor(code);
		if (myInstructor==null)	return;
		
		myInstructor.setActive(true);
	    this.updateInstructor(myInstructor);
		
	}
	
	
	/**
	 * Set inactive an instructor from the database.
	 * @param the instructor
	 * 
	 */
	public void inactiveInstructor(Instructor instructor){
		this.inactiveInstructor(instructor.getIdNumber());
		
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
	
	public Instructor getInstructor(String ssNumber){
		Instructor myInstructor = (Instructor) daoInstructor.getElement(ssNumber);
		return myInstructor;
	}
	
	
	
	
	
	public Instructor getInstructor(User user){
		Instructor myInstructor= (Instructor) daoInstructor.getElement(user.getUser());
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
	 * Instruidas subzone
	 */
	
	/**
	 * 
	 * @param instructor
	 * @return
	 */
	public Collection<Activity> getAllActivities(Instructor instructor){
		daoInstructor myDaoInstructor = (daoInstructor) daoInstructor;
		List <Integer> myData = (List<Integer>) myDaoInstructor.getActivitiesInstructor(instructor.getSsNumber());
		Collection<Activity> myActivities= new LinkedList<Activity>();
		for(int i=0;i<myData.size();i++){
			myActivities.add((Activity) daoActivity.getElement(myData.get(i)));
		}
		return myActivities;
		
	}
	
	/**
	 * Method to add an activity to the instructor for teach it
	 * @param idMonitor ssnumber of the instructor
	 * @param idActivity idact of the activity
	 */
	public void addInstructed(String idMonitor, Integer idActivity) {
		daoInstructor dao = (daoInstructor) daoInstructor;
		dao.addActivity(idMonitor, idActivity);
	}
	
	/**
	 * Method to remove an activity that the instructor can teach
	 * @param idMonitor ssnumber of the instructor
	 * @param idActivity idact of the activity
	 */
	public void removeInstructed(String idMonitor, Integer idActivity) {
		daoInstructor dao = (daoInstructor) daoInstructor;
		dao.deleteInstructorFromActivity(idMonitor, idActivity);
	}
	
	/*
	 * ACTIVITY ZONE
	 */
	
	
	
	/**
	 * Add a activity in the database.
	 * @param The activity
	 */
	public void addActivity(Activity activity){
		this.activityID ++;
		activity.setIdAct(activityID);
		daoActivity.addElement(activity);
	}
	
	/**
	 * Set inactive an activity from the database. The ssNumber is required
	 * @param idActivity of the activity
	 */
	public void inactiveActivity(String code){
		Activity myActivity = this.getActivity(code);
		if (myActivity==null) return;
		myActivity.setIsActive(false);
		this.updateActivity(myActivity);	
	}
	
	
	/**
	 * Set inactive an activity from the database.
	 * @param The activity
	 */
	public void inactiveActivity(Activity activity){
		this.inactiveActivity(String.valueOf(activity.getIdAct()));	
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
	
	
	/** Add the booking in the database
	 * @param booking
	 */
	public void addBooking(Booking booking){
		this.innerBookingID ++;
		booking.setInnerIdBooking(innerBookingID);
		daoBooking.addElement(booking);
	}
	
	
	/** Delete the booking in the database. The innerBooking is required.
	 * @param id
	 */
	public void deleteBooking(String id){
		Booking myBooking = this.getBooking(id);
		if(myBooking==null) return;
		daoBooking.deleteElement(myBooking);
	}
	
	/** Delete the booking in the database
	 * @param booking
	 */
	public void deleteBooking(Booking booking){
		this.deleteBooking(String.valueOf(booking.getInnerIdBooking()));
		
	}
	
	/** Retrieves the desired booking
	 * @param id
	 * @return The booking
	 */
	public Booking getBooking(String id) {
		Booking myBooking = (Booking) daoBooking.getElement(id);
		return myBooking;
	}
	
	/**
	 * Get all the bookings from the database
	 * @return A collection of Booking  with all bookings
	 */
	public Collection<Booking> getAllBookings(){
		Map<String,Booking> allBookings = (Map<String,Booking>) daoBooking.getElements();
		Collection<Booking> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	/*
	 * STATUS ZONE
	 */
	
	
	
	/** Add a status to the database
	 * @param status
	 */
	public void addStatus(Status status){
		daoStatus.addElement(status);
	}
	
	public void deleteStatus(Status status){
		
	}
	
	/** Retrieves a status. InnerIdBooking is required
	 * @param idBooking
	 * @return The status
	 */
	public Status getStatus(String idBooking){
		Status myStatus = (Status) daoStatus.getElement(idBooking);
		return myStatus;
		
	}
	
	/** Retrieves a status.
	 * @param booking
	 * @return The status
	 */
	public Status getStatus(Booking booking){
		return this.getStatus(String.valueOf(booking.getInnerIdBooking()));
	}
	
	public Collection<Status> getAllStatus(){
		Map<Integer,Status> allStatus = (Map<Integer,Status>) daoStatus.getElements();
		Collection<Status> allStatusClasses= allStatus.values();
		return allStatusClasses;
	}
	
	
	
	

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
		user.setPassword(encryptor.encryptPassword(user.getPassword()));
		daoUser.addElement(user);
		
	}
	
	public void deleteUser(String identifier){
		User myUser = this.getUser(identifier);
		if (myUser==null) return;
		daoUser.deleteElement(myUser);	
		
	}
	
	public void updateUser(User user){
		if (this.getUser(String.valueOf(user.getUser()))==null) return;
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
	
	
	/*
	 * CLIENT ZONE
	 */
	
	
	public void addClient(Client client){
		daoClient.addElement(client);
		
	}
	
	public void deleteClient(Client client){
		this.deleteClient(client.getClientId());
		
	}
	
	public void deleteClient(String clientID){
		Client myClient = this.getClient(clientID);
		if (myClient==null) return;
		daoClient.deleteElement(myClient);
	}
	
	
	public Client getClient(String clientID){
		Client myClient= (Client) daoClient.getElement(clientID);
		return myClient;
	}
	
	
	/** Retrieves the desired Client
	 * @param user
	 * @return The client
	 */
	public Client getClient(User user){
		return (Client) daoClient.getElement(user.getUser());
		
	}
	
	public Collection<Client> getAllClients(){
		Map<String,Client> allClients =  (Map<String, Client>) daoClient.getElements();
		Collection<Client> allClientsClasses = allClients.values();
		return allClientsClasses;
		
	}
	
	
	
	
	
	
//Setter inyectables ---------------------------------------------------------------------------------------------------------------------------
	
	public void setDaoActivity(daoActivity daoActivity) {
		this.daoActivity = daoActivity;
	}
	
	public void setDaoBooking(daoBooking daoBooking) {
		this.daoBooking = daoBooking;
	}
	
	public void setDaoClient(daoClient daoClient) {
		this.daoClient = daoClient;
	}
	
	public void setDaoInstructor(daoInstructor daoInstructor) {
		this.daoInstructor = daoInstructor;
	}
	
	public void setDaoStatus(daoStatus daoStatus) {
		this.daoStatus = daoStatus;
	}
	
	public void setDaoUser(daoUser daoUser) {
		this.daoUser = daoUser;
	}
	
	public void setDaoAvaliable(daoAvaliableBook daoAvaliable) {
		this.daoAvaliable = daoAvaliable;
	}





}
