package tests.daos;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import classes.Status;
import database.oldDaos.DaoStatus;

/*	
 *  FixMethodOrder utilizado para ejecutar 
 *  los metodos de forma ascendente
 *  dependiendo de su nombre
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoStatusTest {

	
	static Map<Integer,Status> expected;
	static DaoStatus dao;
	static String[][] datosIniciales = {
		// Caso base existente en la db
		//   ID     DateRevision             Status			InstrSSN
			{"1",	"2012-05-02",			"aprobado",		"2031"},
			{"2",	"2012-03-24",			"aprobado",		"2032"}};
			
	
	@BeforeClass
	public static void setUpBeforeClass() {
		dao = new DaoStatus();
		expected= new HashMap<Integer, Status>();
		
		for(String[] statusData: datosIniciales){
			Status status = new Status();
			
			status.setIDbooking(Integer.parseInt(statusData[0]));
			status.setDateRevision(Date.valueOf(statusData[1]));
			status.setStatus(statusData[2]);
			status.setSsNumber(statusData[3]);
			
			expected.put(status.getIDbooking(), status);
			
			
		}

	}
	
	@Test
	public void test1GetStats() {
		Map<Integer, Status> result = dao.getStats();
		
		assertTrue(result.containsKey(1));
		assertTrue(result.containsKey(2));
		assertFalse(result.containsKey(7));
	}
	
	@Test
	public void test2GetStatus() {
		Status result1 = dao.getStatus(1);
        assertTrue(result1.equals(expected.get(1)));
        
        Status result2 = dao.getStatus(2);
        assertTrue(result2.equals(expected.get(2)));
        
         
		
	}
	
	//Los stats no se pueden anadir manualmente. Se crean automaticamente al crear la reserva
//	@Test
//	public void test3AddStatus() {
//		
//        dao.addStatus(expected.get(4));
//        assertEquals(expected.get(4), dao.getStatus(4));
//	}
	
	@Test
	public void test4UpdateStatus() {
        Status s2updated = new Status(expected.get(2));
        s2updated.setStatus("en espera");
        
        dao.updateStatus(s2updated);
        
        assertEquals(s2updated, dao.getStatus(2));
        
        s2updated.setStatus("aprobado");
        dao.updateStatus(s2updated);
	}
	
	//Los stats no se pueden borrar. Se borran al borrar la reserva
//	@Test
//	public void test5DeleteStatus() {
//        dao.deleteStatus(4);
//        assertNull(dao.getStatus(4));
//	}
	
	
}
