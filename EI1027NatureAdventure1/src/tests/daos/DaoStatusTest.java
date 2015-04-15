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
import database.DaoStatus;

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
		//   ID     DateRevision             Status
			{"1",	"2012-05-02",			"aprobado"},
			{"2",	"2012-03-24",			"aprobado"},
			{"3",	"2011-12-23",			"rechazado"},
			
		// Caso no existente
			{"4",	"2013-04-23", 			"aprobado"}};
	
	@BeforeClass
	public static void setUpBeforeClass() {
		dao = new DaoStatus();
		expected= new HashMap<Integer, Status>();
		
		for(String[] statusData: datosIniciales){
			Status status = new Status();
			
			status.setIDbooking(Integer.parseInt(statusData[0]));
			status.setDateRevision(Date.valueOf(statusData[1]));
			status.setStatus(statusData[2]);
			
			expected.put(status.getIDbooking(), status);
			
		}
	
		for(Status status: expected.values()){
			dao.addStatus(status);
		}
	}
	
	@Test
	public void test1GetStats() {
		Map<Integer, Status> result = dao.getStats();
		
		assertTrue(result.containsKey(1));
		assertTrue(result.containsKey(2));
		assertTrue(result.containsKey(3));
		assertFalse(result.containsKey(7));
	}
	
	@Test
	public void test2GetStatus() {
		Status result1 = dao.getStatus(1);
        assertTrue(result1.equals(expected.get(1)));
        
        Status result2 = dao.getStatus(2);
        assertTrue(result2.equals(expected.get(2)));
        
        Status result3 = dao.getStatus(3);
        assertTrue(result3.equals(expected.get(3)));
         
		
	}
	
	@Test
	public void test3AddStatus() {
		
        dao.addStatus(expected.get(4));
        assertEquals(expected.get(4), dao.getStatus(4));
	}
	
	@Test
	public void test4UpdateStatus() {
        Status a3updated = new Status(expected.get(4));
        a3updated.setStatus("en espera");
        
        dao.updateStatus(a3updated);
        
        assertEquals(a3updated, dao.getStatus(4));
	}
	
	@Test
	public void test5DeleteClient() {
        dao.deleteStatus(4);
        assertNull(dao.getStatus(4));
	}
	
	
}
