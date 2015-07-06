package service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;

import classes.Booking;
import classes.BookingActivity;
import classes.Client;
import classes.Instructor;
import classes.Status;
import database.daoBooking;
import database.daoStatus;

@SuppressWarnings(value = {"unchecked", "unused"})
@Repository
public class BookingLayer {
	private int activeBookingID;
	private int innerBookingID;
	private daoBooking daoBooking;
	private daoStatus daoStatus;
	
	/*
	 * BOOKING ZONE
	 */

	/** Add the booking in the database
	 * @param booking
	 */
	public void addBooking(Booking booking){
		innerBookingID ++;
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
	
	
	
	
	public void setDaoBooking(daoBooking daoBooking) {
		this.daoBooking = daoBooking;
	}
	public void setDaoStatus(daoStatus daoStatus){
		this.daoStatus = daoStatus;
	}
	
	public void inicializarInnerBookingID(){
		try {
			this.innerBookingID = daoBooking.getMaxInnerID();
			} catch (Exception e) {
				this.innerBookingID = 0;
				System.out.println("innerbooking id = 0");
	}
	}
	
	public void inicializarActiveBookingID(){
		try {
			this.activeBookingID = daoBooking.getMaxActiveID();
			} catch (Exception e) {
				this.activeBookingID = 0;
				System.out.println("idBooking = 0");
			}
	}
}
