package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import classes.Note;
import classes.Opinion;
import classes.Status;
import classes.Token;
import classes.User;
import database.daoActivity;
import database.daoAvaliableBook;
import database.daoBooking;
import database.daoClient;
import database.daoInstructor;
import database.daoOpinion;
import database.daoSessionToken;
import database.daoStatus;
import database.daoUser;

@SuppressWarnings(value = {"unchecked", "unused"})
@Service
public class LogicLayer {
	private InstructorLayer insLayer;
	private ActivityLayer actLayer;
	private BookingLayer bokLayer;
	private UserLayer useLayer;
	private ClientLayer cliLayer;
	private TokenLayer tokLayer;
	private NoteLayer notLayer;
	private OpinionLayer opiLayer;
	private MailLayer maiLayer;
	
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
		bokLayer.inicializarInnerBookingID();
		bokLayer.inicializarActiveBookingID();
		actLayer.inicializarActivityID();
		notLayer.inicializarNoteID();
	
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
		actLayer.addActivity(activity);
	}
	
	/**
	 * Set inactive an activity from the database. The ssNumber is required.
	 * @param idActivity of the activity
	 */
	public void inactiveActivity(int code){
		actLayer.inactiveActivity(code);
	}
	
	/**
	 * Set inactive an activity from the database.
	 * @param The activity
	 */
	public void inactiveActivity(Activity activity){
		actLayer.inactiveActivity(activity);
	}
	
	
	/**
	 * Set active an activity from the database. The ssNumber is required.
	 * @param idActivity of the activity
	 */
	public void activateActivity(int code){
		actLayer.activateActivity(code);	
	}
	
	/**
	 * Set active an activity from the database.
	 * @param The activity
	 */
	public void activateActivity(Activity activity){
		actLayer.activateActivity(activity);
	}
	
	/**
	 * Update an activity from the database. This operation is only allowed when the activity was registered before in the database.
	 * @param The activity
	 */
	public void updateActivity(Activity activity){
		actLayer.updateActivity(activity);
	}
	
	/**
	 * Given a code a activity is delivered. If the activity doesn't exist in the database it returns null 
	 *@return An activity or null
	 */
	public Activity getActivity(int code){
		return actLayer.getActivity(code);
	}
	
	/**Given a code a activity is delivered. If the activity doesn't exist in the database it returns null 
	 * @param name
	 * @return An activity or null
	 */
	public Activity getActivity(String name){
		return actLayer.getActivity(name);
	}
	
	/**
	 * Get all the activities from the database
	 * @return A collection of Activity  with all activities
	 */
	public Collection<Activity> getAllActivities(){ //Devuelvo solo lista de actividades para facilitar tarea a la vista
		return actLayer.getAllActivities();
		
	}
	
	/** Get all the activities that are active in the database
	 * @return A collection of Activity with all active activities
	 */
	public Collection<Activity> getAllActivitiesActive() {
		return actLayer.getAllActivitiesActive();
	}
	
	
	/** Get all the activities that are active in the database
	 * @return A collection of Activity with all inactive activities
	 */
	public Collection<Activity> getAllActivitiesInactive() {
		return actLayer.getAllActivitiesInactive();
	}

	
	/*
	 * BOOKING ZONE
	 */

	/** Add the booking in the database
	 * @param booking
	 */
	public void addBooking(Booking booking){
		bokLayer.addBooking(booking);
	}
	
	/** Delete the booking in the database. The innerBooking is required.
	 * @param id
	 */
	public void deleteBooking(int id){
		bokLayer.deleteBooking(id);
	}
	
	/** Delete the booking in the database
	 * @param booking
	 */
	public void deleteBooking(Booking booking){
		bokLayer.deleteBooking(booking);
		
	}
	
	/** Retrieve the desired booking
	 * @param id
	 * @return The booking
	 */
	public Booking getBooking(int id) {
		return bokLayer.getBooking(id);
	}
	
	/**
	 * Get all the bookings from the database
	 * @return A collection of Booking  with all bookings
	 */
	public Collection<Booking> getAllBookings() {
		return bokLayer.getAllBookings();
	}
	
	
	/** Get all the BookingActivity related with a certain instructor
	 * @param instructor
	 * @return All the BookingActivity related with the instructor
	 */
	public Collection<BookingActivity> getAllBookingsActivityInstructor(Instructor instructor){
		return bokLayer.getAllBookingsActivityInstructor(instructor);
	}
	
	/** Get all the BookingActivity related with a certain client
	 * @param A Client
	 * @return All the BookingActivity related with the client
	 */
	public Collection<BookingActivity> getAllBookingsActivityClient(Client cl){
		return bokLayer.getAllBookingsActivityClient(cl);
	}
	
	/** Get all the BookingActivity which booking is active related with a certain client
	 * @param A Client
	 * @return All the BookingActivity with an active Booking related with the client
	 */
	public Collection<BookingActivity> getActiveBookings(Client client){
		return bokLayer.getActiveBookings(client);
	}
	
	/** Get all the BookingActivity that represents bookings done related with a certain client
	 * @param client
	 * @return All the BookingActivity done in the past related with the client
	 */
	public Collection<BookingActivity> getPastBookings(Client client){
		return bokLayer.getPastBookings(client);
	}
	
	/** Get all the BookingActivity which booking is active related with a certain instructor
	 * @param instructor
	 * @return All the BookingActivity with an active Booking related with the instructor
	 */
	public Collection<BookingActivity> getActiveBooking(Instructor instructor){
		return bokLayer.getActiveBooking(instructor);
	}
	
	/** Get all the BookingActivity that represents bookings done related with a certain instructor
	 * @param instructor
	 * @return All the BookingActivity done in the past related with the instructor
	 */
	public Collection<BookingActivity> getPastBooking(Instructor instructor){
		return bokLayer.getPastBooking(instructor);
	}
	
	/** Retrieve the number of pending Bookings
	 * @return the number of pending bookings
	 */
	public Integer getPendingBookingsCount(){
		return bokLayer.getPendingBookingsCount();
	}
	
	/*
	 * STATUS ZONE
	 */
	
	/** Add a Status in the database
	 * @param Status
	 */
	public void addStatus(Status status){
		bokLayer.addStatus(status);
	}
	
	/** Delete a Status from the database
	 * @param Status
	 */
	public void deleteStatus(Status status){
		bokLayer.deleteStatus(status);
	}
	
	/**update a Status in the database
	 * @param Status
	 */
	public void updateStatus(Status status){
		bokLayer.updateStatus(status);
	}
	
	/** Retrieves a status. InnerIdBooking is required
	 * @param idBooking
	 * @return The status
	 */
	public Status getStatus(int idBooking){
		return bokLayer.getStatus(idBooking);
		
	}
	
	/**
	 * Change the status of the Status to pending
	 * @param Status
	 */
	public void changeToPending(Status status) {
		bokLayer.changeToPending(status);
	}
	
	/** Change the status of the Status to declined
	 * @param Status
	 */
	public void changeToDeclined(Status status) {
		bokLayer.changeToDeclined(status);
	}
	
	/** Retrieve all the Bookings which are pending
	 * @return All the pending Bookings
	 */
	public Collection<Booking> getPendingBookings(){
		return bokLayer.getPendingBookings();
	}
	
	/** Get all the active Bookings registered in the system
	 * @return All the active Bookings
	 */
	public Collection<Booking> getActiveBookings(){
		return bokLayer.getActiveBookings();
	}
	
	/** Get all the declined Bookings registered in the system
	 * @return All the declined Bookings
	 */
	public Collection<Booking> getDeclinedBookings(){
		return bokLayer.getDeclinedBookings();
	}
	
	
	/** Retrieve a status.
	 * @param booking
	 * @return The status
	 */
	public Status getStatus(Booking booking){
		return bokLayer.getStatus(booking);
	}
	
	/** Retrieve all the status from the database
	 * @return
	 */
	public Collection<Status> getAllStatus(){
		return bokLayer.getAllStatus();
	}
	
	/** Assign a certain instructor with a Booking
	 * @param ssNumber
	 * @param idBooking
	 */
	public void assignInstructorToBooking(String ssNumber, int idBooking){
		bokLayer.assignInstructorToBooking(ssNumber, idBooking);
	}
	
	/** Make a certain booking declined
	 * @param idBooking
	 */
	public void declineBooking(int idBooking){
		bokLayer.declineBooking(idBooking);
	}
	
	/** Make a certain booking pending
	 * @param idBooking
	 */
	public void bookingToPending(int idBooking){
		bokLayer.bookingToPending(idBooking);
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
		return useLayer.loginUser(user, password);
	}
	
	/** Add a user in the database, this self encrypt the password
	 * @param user
	 */
	public void addUser(User user){
		useLayer.addUser(user);
		
	}
	
	/** Delete a user from the database
	 * @param identifier
	 */
	public void deleteUser(String identifier){
		useLayer.deleteUser(identifier);
		
	}
	
	/** Update an user in the database, does not update the password or type, for this <b>updateUserWithPasswordType</b>
	 * @param user
	 */
	public void updateUser(User user){
		useLayer.updateUser(user);
	}
	
	/**
	 * Update an user in the database, updates the password and the type. <b><i>The password must be plain text with encrypt</i></b>
	 * @param user
	 */
	public void updateUserWithPasswordType(User user) {
		useLayer.updateUserWithPasswordType(user);
	}
	
	/** Retrieve the desired user form the database. The identifier is required
	 * @param identifier
	 * @return
	 */
	public User getUser(String identifier){
		return useLayer.getUser(identifier);
		
	}
	
	/** Retrieve all the users from the database
	 * @return
	 */
	public Collection<User> getAllUsers(){
		return useLayer.getAllUsers();
	}
	
	
	/*
	 * CLIENT ZONE
	 */
	
	/** Add a client in the database
	 * @param client
	 */
	public void addClient(Client client){
		cliLayer.addClient(client);
		
	}
	
	/** Delete a client from the database
	 * @param client
	 */
	public void deleteClient(Client client){
		cliLayer.deleteClient(client);
		
	}
	
	/** Delete a client in the database. The clientID is required
	 * @param clientID
	 */
	public void deleteClient(String clientID){
		cliLayer.deleteClient(clientID);
	}
	
	/** Update a client in the database
	 * @param client
	 */
	public void updateClient(Client client){
		cliLayer.updateClient(client);
	}
	
	/** Retrieve the desired Client. The clientID is required
	 * @param clientID
	 * @return
	 */
	public Client getClient(String clientID){
		return cliLayer.getClient(clientID);
	}
	
	/** Retrieve the desired Client
	 * @param user
	 * @return The client
	 */
	public Client getClient(User user){
		return cliLayer.getClient(user);
		
	}
	
	/** Get all the clients registered in the system
	 * @return All the clients
	 */
	public Collection<Client> getAllClients(){
		return cliLayer.getAllClients();
		
	}
	
	/** Create an User which represents a instructor given
	 * @param instructor
	 * @return A User which represents a instructor
	 */
	public User createUserFrom(Instructor instructor) {
		return useLayer.createUserFrom(instructor);
	}
	
	
	/** Create a Client given a ClientRegister
	 * @param cl
	 * @return The desired Client
	 */
	public Client createClientFrom(ClientRegister cl) {
		return cliLayer.createClientFrom(cl);
	}
	
	/** Create a user given a ClientRegister
	 * @param cl
	 * @return The desired user
	 */ 
	public User createUserFrom(ClientRegister cl) {
		return useLayer.createUserFrom(cl);
	}
	
	/** Get the User count of the system
	 * @return The number of User registered
	 */
	public Integer getUserCount(){
		return useLayer.getUserCount();
	}
	
	/*
	 * TOKEN ZONE
	 */
	
	/** Set a token to an User
	 * @param userName
	 * @param tokenString
	 */
	public void setToken(String userName, String tokenString) {
		tokLayer.setToken(userName, tokenString);
	}
	
	/** Validate a token of an user
	 * @param userName
	 * @param tokenString
	 * @return A boolean value which represents its validation
	 */
	public boolean validateToken(String userName, String tokenString) {
		return tokLayer.validateToken(userName, tokenString);
	}
	
	/** Delete a token of an user
	 * @param tokenUser
	 */
	public void deleteToken(String tokenUser) {
		tokLayer.deleteToken(tokenUser);
	}
	
	/*
	 * NOTE ZONE
	 */
	
	public void addNote(Note note){
		notLayer.addNote(note);
	}
	
	public void deleteNote(int id){
		notLayer.deleteNote(id);
	}
	
	public void updateNote(Note note){
		notLayer.updateNote(note);
	}
	
	public Note getNote(int id){
		return notLayer.getNote(id);
	}
	
	public Collection<Note> getAllNotes(){
		return notLayer.getAllNotes();
	}
	
	
	/*
	 * OPINION ZONE 
	 */
	
	public void addOpinion(Opinion opinion){
		opiLayer.addOpinion(opinion);
	}
	
	public void deleteOpinion(int opinionid){
		opiLayer.deleteOpinion(opinionid);
	}
	
	public void updateOpinion(Opinion opinion){
		opiLayer.updateOpinion(opinion);
	}
	
	public Opinion getOpinion(String clientid, int idAct){
		return opiLayer.getOpinion(clientid, idAct);
	}
	
	public Collection<Opinion> getAllOpinions(){
		return opiLayer.getAllOpinions();
	}
	
	public void deleteAllOpinionsFromActivity(int idAct){
		opiLayer.deleteAllOpinionsFromActivity(idAct);
	}
	
	public Collection<Opinion> getOpinionsFromActivity(int idAct){
		return opiLayer.getOpinionsFromActivity(idAct);
	}
	
	public Collection<Opinion> getOpinionsFromClient(String clientID){
		return opiLayer.getOpinionsFromClient(clientID);
	}
	
	/*
	 * MAIL ZONE 
	 */
	
	public void enviarMailRegistrado(Client client){
		maiLayer.enviarMailRegistrado(client);
	}
	
	
//Setter inyectables ---------------------------------------------------------------------------------------------------------------------------
	
	public void setInsLayer(InstructorLayer insLayer) {
		this.insLayer = insLayer;
	}

	public void setActLayer(ActivityLayer actLayer) {
		this.actLayer = actLayer;
	}

	public void setBokLayer(BookingLayer bokLayer) {
		this.bokLayer = bokLayer;
	}

	public void setUseLayer(UserLayer useLayer) {
		this.useLayer = useLayer;
	}

	public void setCliLayer(ClientLayer cliLayer) {
		this.cliLayer = cliLayer;
	}

	public TokenLayer getTokLayer() {
		return tokLayer;
	}

	public void setTokLayer(TokenLayer tokLayer) {
		this.tokLayer = tokLayer;
	}

	public void setNotLayer(NoteLayer notLayer) {
		this.notLayer = notLayer;
	}

	public void setOpiLayer(OpinionLayer opiLayer) {
		this.opiLayer = opiLayer;
	}

	public void setMaiLayer(MailLayer maiLayer) {
		this.maiLayer = maiLayer;
	}

	
}
