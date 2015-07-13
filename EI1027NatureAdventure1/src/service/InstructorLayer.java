package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import classes.Activity;
import classes.Booking;
import classes.Instructor;
import classes.User;
import database.daoBooking;
import database.daoInstructor;

@SuppressWarnings(value = {"unchecked", "unused"})
@Repository 
public class InstructorLayer {

	private daoBooking daoBooking;
	private daoInstructor daoInstructor;
	
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
	
	/** Set active an instructor from the database.
	 * @param the code
	 */
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
	public Collection<Instructor> getAllInstructorsActive() {
		Map<String, Instructor> map = (Map<String, Instructor>) daoInstructor.getElementsActive();
		Collection<Instructor> collection = map.values();
		return collection;
	}
	
	/**
	 * Get all the instructors, which are inactive
	 * @return A collection of inactive Instructors 
	 */
	public Collection<Instructor> getAllInstructorsInacctive() {
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
	
	
	/** Method to obtain the list of instructors avaliable to do the booking that day
	 * @param idAct
	 * @return
	 */
	public Collection<Instructor> getAvaliableInstructorsToAssign(int innerIdBooking){ //idAct, innerIdBooking
		Booking myBooking = (Booking) daoBooking.getElement(innerIdBooking);
		int idAct = myBooking.getIdAct();
		Map<String,Instructor> allInstructorsAvaliableMap = (Map<String,Instructor>) daoInstructor.getAvaliableInstructorsToAssign(idAct, innerIdBooking);
		Collection<Instructor> allInstructorsAvaliable= allInstructorsAvaliableMap.values();
		return allInstructorsAvaliable;
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


	public daoBooking getDaoBooking() {
		return daoBooking;
	}


	public void setDaoBooking(daoBooking daoBooking) {
		this.daoBooking = daoBooking;
	}


	public daoInstructor getDaoInstructor() {
		return daoInstructor;
	}


	public void setDaoInstructor(daoInstructor daoInstructor) {
		this.daoInstructor = daoInstructor;
	}
	
}
