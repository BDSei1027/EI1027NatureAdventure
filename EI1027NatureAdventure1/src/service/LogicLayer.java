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
import classes.ClientRegister;
import classes.Instructor;
import classes.Status;
import classes.Token;
import classes.User;
import database.daoActivity;
import database.daoAvaliableBook;
import database.daoBooking;
import database.daoClient;
import database.daoInstructor;
import database.daoSessionToken;
import database.daoStatus;
import database.daoUser;

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
	private daoSessionToken daoToken;
	

	//ID autoincrementales
	private int innerBookingID;
	private int activeBookingID;
	private int activityID;
	
	// Encriptacion
	private PasswordEncryptor encryptor = new BasicPasswordEncryptor(); 
	
	// Recuperacion de passwords
	//private RecoverPasswordSystem recoverPass = new RecoverPasswordSystem();
	
	private InstructorLayer insLayer;
	
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
		try {
		this.innerBookingID = daoBooking.getMaxInnerID();
		} catch (Exception e) {
			this.innerBookingID = 0;
			System.out.println("innerbooking id = 0");
		}
		try {
		this.activeBookingID = daoBooking.getMaxActiveID();
		} catch (Exception e) {
			this.activeBookingID = 0;
			System.out.println("idBooking = 0");
		}
		try {
		this.activityID = daoActivity.getMaxID();
		} catch (Exception e) {
			this.activityID = 0;
			System.out.println("activityID = 0");
		}
	}
	
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
		insLayer.addInstructor(instructor);
		
	}
	
	
	/**
	 * Set inactive an instructor from the database. The ssNumber is required
	 * @param ssnumber of the instructor
	 * 
	 */
	public void inactiveInstructor(String code){
		insLayer.inactiveInstructor(code);
	}
	
	public void activeInstructor(String code) {
		insLayer.activeInstructor(code);
	}
	
	/**
	 * Set inactive an instructor from the database.
	 * @param the instructor
	 * 
	 */
	public void inactiveInstructor(Instructor instructor){
		insLayer.inactiveInstructor(instructor);
		
	}
	
	/**
	 * Update an instructor from the database. This operation is only allowed when the instructor was registered before in the database
	 * @param The instructor
	 */
	public void updateInstructor(Instructor instructor){
		insLayer.updateInstructor(instructor);
	}

	/**
	 * Given a ssNumber a Instructor is delivered. If the instructor doesn't exist in the database it returns null 
	 *@return an instructor or null
	 */
	public Instructor getInstructor(String ssNumber){
		return insLayer.getInstructor(ssNumber);
	}
	
	public Instructor getInstructor(User user){
		return insLayer.getInstructor(user);
	}
	
	/**
	 * Get all the instructors from the database
	 * @return A collection of Instructor with all instructors
	 */
	public Collection<Instructor> getAllInstructors(){ //Devuelvo solo lista de Instructores para facilitar tarea a la vista
		return insLayer.getAllInstructors();
	}
	
	/**
	 * Get all the instructors, which are active
	 * @return A collection of active Instructors 
	 */
	public Collection<Instructor> getAllInstructorsActive() {
		return insLayer.getAllInstructorsActive();
	}
	
	/**
	 * Get all the instructors, which are inactive
	 * @return A collection of inactive Instructors 
	 */
	public Collection<Instructor> getAllInstructorsInacctive() {
		return insLayer.getAllInstructorsInacctive();
	}

	/**
	 * change the active property of the instructor in the database
	 * @param The instructor 
	 */
	public void setInstructorAvailable(Instructor instructor){
		insLayer.setInstructorAvailable(instructor);
	}
		
	/**
	 * Method to obtain the number of bookings the instructor have to teach
	 * @param ssnum Instructor's ssnumber
	 * @return Number of bookings to do
	 */
	public Integer getBookingsToDo(String ssnum) {
		return insLayer.getBookingsToDo(ssnum);
	}
	
	/**
	 * Method to obtain the number of bookings the instructor have to teach
	 * @param ssnum Instructor to query
	 * @return Number of bookings to do
	 */
	public Integer getBookingsToDo(Instructor instructor) {
		return insLayer.getBookingsToDo(instructor);
	}
	
	
	/** Method to obtain the list of instructors avaliable to do the booking that day
	 * @param idAct
	 * @return
	 */
	public Collection<Instructor> getAvaliableInstructorsToAssign(int innerIdBooking){ //idAct, innerIdBooking
		return insLayer.getAvaliableInstructorsToAssign(innerIdBooking);
	}
	
	
	
	/*
	 * Instruidas subzone
	 */
	
	/** 
	 * Method to obtain all the activities which are instructed by a certain instructor
	 * @param instructor
	 * @return All the activities instructed by the instructor given
	 */
	public Collection<Activity> getAllActivities(Instructor instructor){
		return insLayer.getAllActivities(instructor);
	}
	
	/**
	 * Get all the activities, that the instructor does not teach
	 * @param instructor Class Instructor
	 * @return Collection<Activity>
	 */
	public Collection<Activity> getNoInstruidasActivities(Instructor instructor) {
		return insLayer.getNoInstruidasActivities(instructor);
	}

	/**
	 * Method to add an activity to the instructor for teach it
	 * @param idMonitor ssnumber of the instructor
	 * @param idActivity idact of the activity
	 */
	public void addInstructed(String idMonitor, Integer idActivity) {
		insLayer.addInstructed(idMonitor, idActivity);
	}
	
	/**
	 * Method to remove an activity that the instructor can teach
	 * @param idMonitor ssnumber of the instructor
	 * @param idActivity idact of the activity
	 */
	public void removeInstructed(String idMonitor, Integer idActivity) {
		insLayer.removeInstructed(idMonitor, idActivity);
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
	 * Set inactive an activity from the database. The ssNumber is required.
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
	 * Set active an activity from the database. The ssNumber is required.
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
	 * Update an activity from the database. This operation is only allowed when the activity was registered before in the database.
	 * @param The activity
	 */
	public void updateActivity(Activity activity){
		daoActivity.updateElement(activity);
	}
	
	/**
	 * Given a code a activity is delivered. If the activity doesn't exist in the database it returns null 
	 *@return An activity or null
	 */
	public Activity getActivity(int code){
		Activity myActivity = (Activity) daoActivity.getElement(code);
		return myActivity;
	}
	
	/**Given a code a activity is delivered. If the activity doesn't exist in the database it returns null 
	 * @param name
	 * @return An activity or null
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
		Map<Integer,Activity> allInstructors = (Map<Integer,Activity>) daoActivity.getElements();
		Collection<Activity> allInstructorsClasses= allInstructors.values();
		return allInstructorsClasses;
		
	}
	
	/** Get all the activities that are active in the database
	 * @return A collection of Activity with all active activities
	 */
	public Collection<Activity> getAllActivitiesActive() {
		Map<Integer, Activity> map = (Map<Integer, Activity>) daoActivity.getElementsActive();
		Collection<Activity> collection = map.values();
		return collection;
	}
	
	
	/** Get all the activities that are active in the database
	 * @return A collection of Activity with all inactive activities
	 */
	public Collection<Activity> getAllActivitiesInactive() {
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
		daoBooking.deleteElement(id);
	}
	
	/** Delete the booking in the database
	 * @param booking
	 */
	public void deleteBooking(Booking booking){
		this.deleteBooking(booking.getInnerIdBooking());
		
	}
	
	/** Retrieve the desired booking
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
	public Collection<Booking> getAllBookings() {
		Map<Integer,Booking> allBookings = (Map<Integer,Booking>) daoBooking.getElements();
		Collection<Booking> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	
	/** Get all the BookingActivity related with a certain instructor
	 * @param instructor
	 * @return All the BookingActivity related with the instructor
	 */
	public Collection<BookingActivity> getAllBookingsActivityInstructor(Instructor instructor){
		Map<Integer,BookingActivity> allBookings = (Map<Integer,BookingActivity>) daoBooking.getElementsBookingActivityInstructor(instructor.getSsNumber());
		Collection<BookingActivity> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	/** Get all the BookingActivity related with a certain client
	 * @param A Client
	 * @return All the BookingActivity related with the client
	 */
	public Collection<BookingActivity> getAllBookingsActivityClient(Client cl){
		Map<Integer,BookingActivity> allBookings = (Map<Integer,BookingActivity>) daoBooking.getElementsBookingActivityClient(cl.getClientId());
		Collection<BookingActivity> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	/** Get all the BookingActivity which booking is active related with a certain client
	 * @param A Client
	 * @return All the BookingActivity with an active Booking related with the client
	 */
	public Collection<BookingActivity> getActiveBookings(Client client){
		String idClient = client.getClientId();
		Map<Integer,BookingActivity> allBookings = (Map<Integer,BookingActivity>) daoBooking.getActiveBookingsWithClient(idClient);
		Collection<BookingActivity> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	/** Get all the BookingActivity that represents bookings done related with a certain client
	 * @param client
	 * @return All the BookingActivity done in the past related with the client
	 */
	public Collection<BookingActivity> getPastBookings(Client client){
		String idClient = client.getClientId();
		Map<Integer,BookingActivity> allBookings = (Map<Integer,BookingActivity>) daoBooking.getPastBookingsWithClient(idClient);
		Collection<BookingActivity> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	/** Get all the BookingActivity which booking is active related with a certain instructor
	 * @param instructor
	 * @return All the BookingActivity with an active Booking related with the instructor
	 */
	public Collection<BookingActivity> getActiveBooking(Instructor instructor){
		String ssNumber = instructor.getSsNumber();
		Map<Integer,BookingActivity> allBookings = (Map<Integer,BookingActivity>) daoBooking.getActiveBookingsWithInstructor(ssNumber);
		Collection<BookingActivity> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	/** Get all the BookingActivity that represents bookings done related with a certain instructor
	 * @param instructor
	 * @return All the BookingActivity done in the past related with the instructor
	 */
	public Collection<BookingActivity> getPastBooking(Instructor instructor){
		String ssNumber = instructor.getSsNumber();
		Map<Integer,BookingActivity> allBookings = (Map<Integer,BookingActivity>) daoBooking.getPastBookingsWithInstructor(ssNumber);
		Collection<BookingActivity> allBookingsClasses= allBookings.values();
		return allBookingsClasses;
	}
	
	/** Retrieve the number of pending Bookings
	 * @return the number of pending bookings
	 */
	public Integer getPendingBookingsCount(){
		return daoBooking.getPendingBookingsCount();
	}
	
	/*
	 * STATUS ZONE
	 */
	
	/** Add a Status in the database
	 * @param Status
	 */
	public void addStatus(Status status){
		daoStatus.addElement(status);
	}
	
	/** Delete a Status from the database
	 * @param Status
	 */
	public void deleteStatus(Status status){
		daoStatus.deleteElement(status.getIDbooking());
	}
	
	/**update a Status in the database
	 * @param Status
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
	 * Change the status of the Status to pending
	 * @param Status
	 */
	public void changeToPending(Status status) {
		daoStatus.deleteElement(status.getIDbooking());
	}
	
	/** Change the status of the Status to declined
	 * @param Status
	 */
	public void changeToDeclined(Status status) {
		status.setDateRevision(new Date());
		status.setSsNumber(null);
		status.setStatus("declined");
		daoStatus.updateElement(status);
	}
	
	/** Retrieve all the Bookings which are pending
	 * @return All the pending Bookings
	 */
	public Collection<Booking> getPendingBookings(){
		Map<Integer,Booking> allBookings = (Map<Integer,Booking>) daoBooking.getPendingBookings();
		Collection<Booking> allBookingsPending= allBookings.values();
		return allBookingsPending;
	}
	
	/** Get all the active Bookings registered in the system
	 * @return All the active Bookings
	 */
	public Collection<Booking> getActiveBookings(){
		Map<Integer,Booking> allBookings = (Map<Integer,Booking>) daoBooking.getActiveBookings();
		Collection<Booking> allBookingsActive= allBookings.values();
		return allBookingsActive;
	}
	
	/** Get all the declined Bookings registered in the system
	 * @return All the declined Bookings
	 */
	public Collection<Booking> getDeclinedBookings(){
		Map<Integer,Booking> allBookings = (Map<Integer,Booking>) daoBooking.getDeclinedBookings();
		Collection<Booking> allBookingsDecline= allBookings.values();
		return allBookingsDecline;
	}
	
	
	/** Retrieve a status.
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
	
	/** Assign a certain instructor with a Booking
	 * @param ssNumber
	 * @param idBooking
	 */
	public void assignInstructorToBooking(String ssNumber, int idBooking){
		Status myStatus = this.getStatus(idBooking);
		if(myStatus==null) return;
		myStatus.setSsNumber(ssNumber);
		myStatus.setDateRevision(new Date());
		myStatus.setStatus("accepted");
		this.updateStatus(myStatus);
		
		Booking bok = this.getBooking(idBooking);
		bok.setIdBooking(++activeBookingID);
		daoBooking.updateElement(bok);
	}
	
	/** Make a certain booking declined
	 * @param idBooking
	 */
	public void declineBooking(int idBooking){
		Status myStatus = this.getStatus(idBooking);
		if(myStatus==null) return;
		myStatus.setDateRevision(new Date());
		myStatus.setSsNumber(null);
		myStatus.setStatus("declined");
		this.updateStatus(myStatus);
		
		Booking bok = this.getBooking(idBooking);
		bok.setIdBooking(++activeBookingID);
		daoBooking.updateElement(bok);
	}
	
	/** Make a certain booking pending
	 * @param idBooking
	 */
	public void bookingToPending(int idBooking){
		Status myStatus = this.getStatus(idBooking);
		if(myStatus==null) return;
		myStatus.setDateRevision(new Date());
		myStatus.setStatus("pending");
		myStatus.setSsNumber(null);
		this.updateStatus(myStatus);
		
		Booking bok = this.getBooking(idBooking);
		bok.setIdBooking(null);
		daoBooking.updateElement(bok);
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
	
	/** Retrieve all the users from the database
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
		daoClient.deleteElement(clientID);
	}
	
	/** Update a client in the database
	 * @param client
	 */
	public void updateClient(Client client){
		if (this.getClient(client.getClientId())==null) return;
		daoClient.updateElement(client);
	}
	
	/** Retrieve the desired Client. The clientID is required
	 * @param clientID
	 * @return
	 */
	public Client getClient(String clientID){
		Client myClient= (Client) daoClient.getElement(clientID);
		return myClient;
	}
	
	/** Retrieve the desired Client
	 * @param user
	 * @return The client
	 */
	public Client getClient(User user){
		return (Client) daoClient.getElement(user.getUser());
		
	}
	
	/** Get all the clients registered in the system
	 * @return All the clients
	 */
	public Collection<Client> getAllClients(){
		Map<String,Client> allClients =  (Map<String, Client>) daoClient.getElements();
		Collection<Client> allClientsClasses = allClients.values();
		return allClientsClasses;
		
	}
	
	/** Create an User which represents a instructor given
	 * @param instructor
	 * @return A User which represents a instructor
	 */
	public User createUserFrom(Instructor instructor) {
		User newUser = new User();
		newUser.setUser(instructor.getIdNumber());
		newUser.setPassword(instructor.getTelephone());
		newUser.setName(instructor.getName());
		newUser.setLanguage("EN");
		newUser.setType(1);
		return newUser;
	}
	
	
	/** Create a Client given a ClientRegister
	 * @param cl
	 * @return The desired Client
	 */
	public Client createClientFrom(ClientRegister cl) {
		Client client = new Client();
		client.setClientId(cl.getId());
		client.setClientName(cl.getName());
		client.setClientLastName(cl.getLastName());
		client.setClientEmail(cl.getEmail());
		return client;
	}
	
	/** Create a user given a ClientRegister
	 * @param cl
	 * @return The desired user
	 */
	public User createUserFrom(ClientRegister cl) {
		User newUser = new User();
		newUser.setUser(cl.getId());
		newUser.setPassword(cl.getPassword());
		newUser.setName(cl.getName());
		newUser.setLanguage(cl.getLanguage());
		newUser.setType(2);
		return newUser;
	}
	
	/** Get the User count of the system
	 * @return The number of User registered
	 */
	public Integer getUserCount(){
		return daoUser.getUserCount();
	}
	
	/*
	 * TOKEN ZONE
	 */
	
	/** Set a token to an User
	 * @param userName
	 * @param tokenString
	 */
	public void setToken(String userName, String tokenString) {
		Token token = new Token(); token.setUser(userName); token.setToken(tokenString);
		try {
			daoToken.addElement(token);
		} catch (Exception e) {
			daoToken.updateElement(token);
		}

	}
	
	/** Validate a token of an user
	 * @param userName
	 * @param tokenString
	 * @return A boolean value which represents its validation
	 */
	public boolean validateToken(String userName, String tokenString) {
		Token token = (Token) daoToken.getElement(userName);
		
		if (token == null) return false;
		if (token.getToken().equals(tokenString)) return true;
		return false;
	}
	
	/** Delete a token of an user
	 * @param tokenUser
	 */
	public void deleteToken(String tokenUser) {
		daoToken.deleteElement(tokenUser);
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
	
	public void setDaoToken(daoSessionToken daoToken){
		this.daoToken = daoToken;
	}

	public InstructorLayer getInsLayer() {
		return insLayer;
	}

	public void setInsLayer(InstructorLayer insLayer) {
		this.insLayer = insLayer;
	}

	
}
