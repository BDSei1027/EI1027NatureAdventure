package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.stereotype.Service;

import classes.Activity;
import classes.Booking;
import classes.BookingActivity;
import classes.Client;
import classes.Instructor;
import classes.Status;
import classes.User;
import database.daoActivity;
import database.daoAvaliableBook;
import database.daoBooking;
import database.daoClient;
import database.daoInstructor;
import database.daoStatus;
import database.daoUser;

//Implementación palera 
//TODO trabajar en memoria
//TODO guardar en fichero local
//TODO subir cada X tiempo
//TODO boton subir ++

@SuppressWarnings(value = {"unchecked", "unused"})
@Service
public class LogicLayer {
	//DAOS
	private daoActivity daoActivity;
	private daoBooking daoBooking;
	private daoClient daoClient;
	private daoInstructor daoInstructor;
	private daoStatus daoStatus;
	private daoUser daoUser;
	private daoAvaliableBook daoAvaliable;
	
	//ID autoincrementales
	private int innerBookingID;
	private int activeBookingID;
	private int activityID;
	
	// Encriptacion
	private PasswordEncryptor encryptor = new BasicPasswordEncryptor(); 
	
	// Recuperacion de passwords
	private RecoverPasswordSystem recoverPass = new RecoverPasswordSystem();
	
	/**
	 * Inicializa las IDs para que se puedan autoincrementar.
	 */
	public LogicLayer() {
		super();
		
	}

	/**
	 * Pide una conexion a traves de los daos de la ID max
	 */
	private void inicializarIds() {
		this.innerBookingID = daoBooking.getMaxInnerID();
		this.activeBookingID = daoBooking.getMaxActiveID();
		this.activityID = daoActivity.getMaxID();
	}

	
	/*
	 * ALPHA VERSION
	 */
	
	public void init() {
		inicializarIds();
		System.out.println("Se han inicializado las ID de activities y bookings");
	}
	
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
	 * Get all the instructors, which are active
	 * @return A collection of active Instructors 
	 */
	public Object getAllInstructorsActive() {
		Map<String, Instructor> map = (Map<String, Instructor>) daoInstructor.getElementsActive();
		Collection<Instructor> collection = map.values();
		return collection;
	}
	
	/**
	 * Get all the instructors, which are inactive
	 * @return A collection of inactive Instructors 
	 */
	public Object getAllInstructorsInacctive() {
		Map<String, Instructor> map = (Map<String, Instructor>) daoInstructor.getElementsInactive();
		Collection<Instructor> collection = map.values();
		return collection;
	}

	/**
	 * change the active property of the instructor in the database
	 * @param The instructor 
	 */
	public void setInstructorAvailable(Instructor instructor){
		instructor.setActive(true);
		daoInstructor.updateElement(instructor);	
	}
	
	public Collection<BookingActivity> getBookingActivities(Instructor instructor) {
		Map<Integer, BookingActivity>map = daoBooking.getBookingActivities(instructor.getSsNumber());
		return map.values();
	}
		
	/**
	 * Method to obtain the number of bookings the instructor have to teach
	 * @param ssnum Instructor's ssnumber
	 * @return Number of bookings to do
	 */
	public Integer getBookingsToDo(String ssnum) {
		return daoInstructor.getNumberBookings(ssnum);
	}
	
	/**
	 * Method to obtain the number of bookings the instructor have to teach
	 * @param ssnum Instructor to query
	 * @return Number of bookings to do
	 */
	public Integer getBookingsToDo(Instructor instructor) {
		return this.getBookingsToDo(instructor.getSsNumber());
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
//		daoInstructor myDaoInstructor = (daoInstructor) daoInstructor;
//		List <Integer> myData = (List<Integer>) myDaoInstructor.getActivitiesInstructor(instructor.getSsNumber());
//		Collection<Activity> myActivities= new LinkedList<Activity>();
//		for(int i=0;i<myData.size();i++){
//			myActivities.add((Activity) daoActivity.getElement(myData.get(i)));
//		}
//		return myActivities;
		return daoInstructor.getAllActivitiesFromInstructor(instructor.getSsNumber());
	}
	
	/**
	 * Get all the activities, that the instructor does not teach
	 * @param instructor Class Instructor
	 * @return Collection<Activity>
	 */
	public Collection<Activity> getNoInstruidasActivities(Instructor instructor) {
		return daoInstructor.getAllActivitiesFromNoInstructor(instructor.getSsNumber());
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
	public void inactiveActivity(int code){
		Activity myActivity = this.getActivity(code);
		myActivity.setIsActive(false);
		this.updateActivity(myActivity);	
	}
	
	/**
	 * Set inactive an activity from the database.
	 * @param The activity
	 */
	public void inactiveActivity(Activity activity){
		this.inactiveActivity(activity.getIdAct());	
	}
	
	
	/**
	 * Set acive an activity from the database. The ssNumber is required
	 * @param idActivity of the activity
	 */
	public void activateActivity(int code){
		Activity myActivity = this.getActivity(code);
		myActivity.setIsActive(true);
		this.updateActivity(myActivity);	
	}
	
	/**
	 * Set active an activity from the database.
	 * @param The activity
	 */
	public void activateActivity(Activity activity){
		this.inactiveActivity(activity.getIdAct());	
	}
	
	/**
	 * Update an activity from the database. This operation is only allowed when the activity was registered before in the database
	 * @param The activity
	 */
	public void updateActivity(Activity activity){
		daoActivity.updateElement(activity);
	}
	
	/**
	 * Given a code a activity is delivered. If the activity doesn't exist in the database it returns null 
	 *@return an activity or null
	 */
	public Activity getActivity(int code){
		Activity myActivity = (Activity) daoActivity.getElement(code);
		return myActivity;
	}
	
	/**Given a code a activity is delivered. If the activity doesn't exist in the database it returns null 
	 * @param name
	 * @return an activity or null
	 */
	public Activity getActivity(String name){
		Activity myActivity = (Activity) daoActivity.getElement(name);
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
	
	/** Get all the activities that are active in the database
	 * @return A collection of Activity with all active activities
	 */
	public Object getAllActivitiesActive() {
		Map<Integer, Activity> map = (Map<Integer, Activity>) daoActivity.getElementsActive();
		Collection<Activity> collection = map.values();
		return collection;
	}
	
	
	/** Get all the activities that are active in the database
	 * @return A collection of Activity with all inactive activities
	 */
	public Object getAllActivitiesInactive() {
		Map<Integer, Activity> map = (Map<Integer, Activity>) daoActivity.getElementsInactive();
		Collection<Activity> collection = map.values();
		return collection;
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
	public void deleteBooking(int id){
		Booking myBooking = this.getBooking(id);
		if(myBooking==null) return;
		daoBooking.deleteElement(myBooking);
	}
	
	/** Delete the booking in the database
	 * @param booking
	 */
	public void deleteBooking(Booking booking){
		this.deleteBooking(booking.getInnerIdBooking());
		
	}
	
	
	/** Retrieves the desired booking
	 * @param id
	 * @return The booking
	 */
	public Booking getBooking(int id) {
		Booking myBooking = (Booking) daoBooking.getElement(id);
		return myBooking;
	}
	
	/**
	 * Get all the bookings from the database
	 * @return A collection of Booking  with all bookings
	 */
	public Collection<Booking> getAllBookings(){
		Map<Integer,Booking> allBookings = (Map<Integer,Booking>) daoBooking.getElements();
		Collection<Booking> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	
	public Collection<Booking> getActiveBookings(Client client){
		String idClient = client.getClientId();
		Map<Integer,Booking> allBookings = (Map<Integer,Booking>) daoBooking.getActiveBookingsWithClient(idClient);
		Collection<Booking> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	public Collection<Booking> getPastBookings(Client client){
		String idClient = client.getClientId();
		Map<Integer,Booking> allBookings = (Map<Integer,Booking>) daoBooking.getPastBookingsWithClient(idClient);
		Collection<Booking> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	public Collection<Booking> getActiveBooking(Instructor instructor){
		String ssNumber = instructor.getSsNumber();
		Map<Integer,Booking> allBookings = (Map<Integer,Booking>) daoBooking.getActiveBookingsWithInstructor(ssNumber);
		Collection<Booking> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	public Collection<Booking> getPastBooking(Instructor instructor){
		String ssNumber = instructor.getSsNumber();
		Map<Integer,Booking> allBookings = (Map<Integer,Booking>) daoBooking.getPastBookingsWithInstructor(ssNumber);
		Collection<Booking> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	/*
	 * STATUS ZONE
	 */
	
	/** Add a status in the database
	 * @param status
	 */
	public void addStatus(Status status){
		daoStatus.addElement(status);
	}
	
	/** Delete a status from the database
	 * @param status
	 */
	public void deleteStatus(Status status){
		daoStatus.deleteElement(status.getIDbooking());
	}
	
	/**update a status in the database
	 * @param status
	 */
	public void updateStatus(Status status){
		daoStatus.updateElement(status);
	}
	
	/** Retrieves a status. InnerIdBooking is required
	 * @param idBooking
	 * @return The status
	 */
	public Status getStatus(int idBooking){
		Status myStatus = (Status) daoStatus.getElement(idBooking);
		return myStatus;
		
	}
	
	/**
	 * Change the status of the status to pending
	 * @param status
	 */
	public void changeToPending(Status status) {
		/* status.setDateRevision(null);
		status.setSsNumber(null);
		status.setStatus(null);
		daoStatus.updateElement(status); */
		daoStatus.deleteElement(status.getIDbooking());
	}
	
	public void changeToDeclined(Status status) {
		status.setDateRevision(new Date());
		status.setSsNumber(null);
		status.setStatus("declined");
		daoStatus.updateElement(status);
	}
	/** Retrieves a status.
	 * @param booking
	 * @return The status
	 */
	public Status getStatus(Booking booking){
		return this.getStatus(booking.getInnerIdBooking());
	}
	
	/** Retrieve all the status from the database
	 * @return
	 */
	public Collection<Status> getAllStatus(){
		Map<Integer,Status> allStatus = (Map<Integer,Status>) daoStatus.getElements();
		Collection<Status> allStatusClasses= allStatus.values();
		return allStatusClasses;
	}
	
	public void assignInstructorToBooking(String ssNumber, int idBooking){
		Status myStatus = this.getStatus(idBooking);
		if(myStatus==null) return;
		myStatus.setSsNumber(ssNumber);
		myStatus.setDateRevision(new Date());
		myStatus.setStatus("accepted");
		this.updateStatus(myStatus);
	}
	
	public void declineBooking(int idBooking){
		Status myStatus = this.getStatus(idBooking);
		if(myStatus==null) return;
		myStatus.setDateRevision(new Date());
		myStatus.setStatus("declined");
		this.updateStatus(myStatus);
	}
	
	public void bookingToPending(int idBooking){
		Status myStatus = this.getStatus(idBooking);
		if(myStatus==null) return;
		myStatus.setDateRevision(new Date());
		myStatus.setStatus("pending");
		this.updateStatus(myStatus);
	}
	
	/*
	 * USER ZONE
	 */
	
	/** Login a user in the system
	 * @param user
	 * @param password
	 * @return The user data
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
	
	/** Add a user in the database, this self encrypt the password
	 * @param user
	 */
	public void addUser(User user){
		user.setPassword(encryptor.encryptPassword(user.getPassword()));
		daoUser.addElement(user);
		
	}
	
	/** Delete a user from the database
	 * @param identifier
	 */
	public void deleteUser(String identifier){
		User myUser = this.getUser(identifier);
		if (myUser==null) return;
		daoUser.deleteElement(myUser);	
		
	}
	
	/** Update an user in the database, does not update the password or type, for this <b>updateUserWithPasswordType</b>
	 * @param user
	 */
	public void updateUser(User user){
		if (user.getUser() == null) return;
		daoUser.updateElementWithoutPassword(user);
	}
	
	/**
	 * Update an user in the database, updates the password and the type. <b><i>The password must be plain text with encrypt</i></b>
	 * @param user
	 */
	public void updateUserWithPasswordType(User user) {
		if (user.getUser() == null) return;
		user.setPassword(encryptor.encryptPassword(user.getPassword()));
		daoUser.updateElement(user);
	}
	
	/** Retrieve the desired user form the database. The identifier is required
	 * @param identifier
	 * @return
	 */
	public User getUser(String identifier){
		User myUser = (User) daoUser.getElement(identifier);
		return myUser;
		
	}
	
	/** Retrieves all the users from the database
	 * @return
	 */
	public Collection<User> getAllUsers(){
		Map<String,User> allUsers =  (Map<String, User>) daoUser.getElements();
		Collection<User> allUsersClasses = allUsers.values();
		return allUsersClasses;
		
	}
	
	
	/*
	 * CLIENT ZONE
	 */
	
	/** Add a client in the database
	 * @param client
	 */
	public void addClient(Client client){
		daoClient.addElement(client);
		
	}
	
	/** Delete a client from the database
	 * @param client
	 */
	public void deleteClient(Client client){
		if(this.getClient(client.getClientId())==null) return;
		this.deleteClient(client.getClientId());
		
	}
	
	/** Delete a client in the database. The clientID is required
	 * @param clientID
	 */
	public void deleteClient(String clientID){
		Client myClient = this.getClient(clientID);
		if (myClient==null) return;
		daoClient.deleteElement(myClient);
	}
	
	/** Upadate a client in the database
	 * @param client
	 */
	public void updateClient(Client client){
		if (this.getClient(client.getClientId())==null) return;
		daoClient.updateElement(client);
	}
	
	/** Retrieves the desired Client. The clientID is required
	 * @param clientID
	 * @return
	 */
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
	
	public User createUserFrom(Instructor instructor) {
		User newUser = new User();
		newUser.setUser(instructor.getIdNumber());
		newUser.setPassword(instructor.getTelephone());
		newUser.setName(instructor.getName());
		newUser.setLanguage("EN");
		newUser.setType(1);
		return newUser;
	}
	
	
	
	/*
	 * AVALIABLE ACTIVITIES FOR BOOKING ZONE
	 */
	
	
	
	
//	/**
//	 * @param myProposal
//	 */
//	public void addAvaliableForBooking(AvaliableForBooking myProposal){
//		daoAvaliable.addElement(myProposal);
//	}
//	
//	public void deleteAvaliableForBooking(AvaliableForBooking myProposal){
//		
//	}
//	
//	public void updateAvaliableForBooking(AvaliableForBooking myProposal){
//		
//	}
//	
//	public AvaliableForBooking getAvaliableForBooking(){
//		
//	}
	
	
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
