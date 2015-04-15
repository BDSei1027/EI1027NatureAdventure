package tests.daos;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import classes.Activity;
import classes.Booking;
import classes.Client;
import classes.Instructor;
import database.DaoBooking;

/*	
 *  FixMethodOrder utilizado para ejecutar 
 *  los metodos de forma ascendente
 *  dependiendo de su nombre
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoBookingTest {
	
	static Map<Integer, Booking> expected;
	static DaoBooking dao;
	static String[][] datosIniciales = {
		// InnID   gSize     dActivity            dCreation         CliID    Price  ActID    info       InstrID    BookID
			{"1",	"4",	"2012-03-14",		"2012-03-9", 		"X1", 	"14.0",	"2",	"puenting",	"2030",		"1"},
			{"2",	"7",	"2012-01-24",		"2011-12-4",		"X1",	"20.0",	"1",	"esqui",	"2030",		"2"},
			{"3",	"7",	"2011-12-24",		"2011-12-4", 		"X2",	"20.0",	"1",	"esqui",	null,		null}};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new DaoBooking();
		expected= new HashMap<Integer, Booking>();
		
		for(String[] reserva: datosIniciales){
			Booking booking = new Booking();
			booking.setInnerIdBooking(Integer.parseInt(reserva[0]));
			booking.setGroupSize(Integer.parseInt(reserva[1]));
			booking.setDateActivity(Date.valueOf(reserva[2]));
			booking.setDateCreation(Date.valueOf(reserva[3]));
			Client client = new Client(); client.setClientId(reserva[4]); booking.setClient(client);
			booking.setPrice(Float.parseFloat(reserva[5]));
			Activity activity = new Activity(); activity.setIdAct(Integer.parseInt(reserva[6]));booking.setActivity(activity);
			booking.setInformation(reserva[7]);
			Instructor instructor = new Instructor(); instructor.setIdNumber(reserva[8]); booking.setInstructor(instructor);
			booking.setIdBooking(Integer.parseInt(reserva[9]));
			
			expected.put(booking.getInnerIdBooking(), booking);
			dao.addBooking(booking);	
		}
	}
	
	@Test
	public void test1GetBookings() {
		Map<Integer, Booking> result = dao.getBookings();
		assertTrue(result.containsKey(1));
		assertTrue(result.containsKey(2));
		assertFalse(result.containsKey(3));
	}
	
	@Test
	public void test2GetBooking() {
		Booking result1 = dao.getBooking(1);
        assertTrue(result1.equals(expected.get(1)));
        
        Booking result2 = dao.getBooking(2);
        assertTrue(result2.equals(expected.get(2)));
        
		
	}
	
	@Test
	public void test3AddBookings() {
        dao.addBooking(expected.get(3));
        assertEquals(expected.get(3), dao.getBooking(3));
	}
	
	@Test
	public void test4UpdateBookings() {
        Booking a3updated = new Booking(expected.get(3));
        Client cliente = new Client(); cliente.setClientName("NuevoNombre");cliente.setClientId("X2");a3updated.setClient(cliente);
        dao.updateBooking(a3updated);
        assertEquals(a3updated, dao.getBooking(3));
	}
	
	@Test
	public void test5DeleteBookings() {
        dao.deleteBooking(3);
        assertNull(dao.getBooking(3));
	}
	
	@AfterClass
	public static void closeTest() {
		dao.deleteBooking(1);
		dao.deleteBooking(2);
		dao.deleteBooking(3);
	}
	
}
