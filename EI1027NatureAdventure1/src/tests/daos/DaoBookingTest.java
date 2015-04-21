package tests.daos;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import classes.Booking;
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
		//Datos internos
		// InnID   gSize     dActivity            dCreation         CliID   GPrice  	ActID      info   BookID
			{"1",	"4",	"2012-03-14",		"2012-03-9", 		"X1", 	"81.2",		"2031",	 	"",	   "1"},
			{"2",	"7",	"2012-01-24",		"2011-12-4",		"X1",	"85.4",		"2032",	 	"",	   "2"},
			
		//Dato de test
			{"3",	"7",	"2011-12-24",		"2011-12-4", 		"X2",	"64.61",	"2033",	 	"",	   null}};
	
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
			booking.setClientId(reserva[4]);
			booking.setPrice(Float.parseFloat(reserva[5]));
			booking.setIdAct(Integer.parseInt(reserva[6]));
			booking.setInformation(reserva[7]);
			if(reserva[8]!=null) booking.setIdBooking(Integer.parseInt(reserva[8]));
			 
			expected.put(booking.getInnerIdBooking(), booking);
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
        a3updated.setInformation("randomInfo");
        dao.updateBooking(a3updated);
        assertEquals(a3updated, dao.getBooking(3));
	}
	
	@Test
	public void test5DeleteBookings() {
        dao.deleteBooking(3);
        assertNull(dao.getBooking(3));
	}
	
}
