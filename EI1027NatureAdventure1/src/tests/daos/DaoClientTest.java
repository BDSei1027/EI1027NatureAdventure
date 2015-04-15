package tests.daos;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import classes.Client;
import database.DaoClient;

/*	
 *  FixMethodOrder utilizado para ejecutar 
 *  los metodos de forma ascendente
 *  dependiendo de su nombre
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoClientTest {

	
	static Map<String,Client> expected;
	static DaoClient dao;
	static String[][] datosIniciales = {
		// Caso base existente en la db
		//   ID      Nombre             Apellidos               Email
			{"X1",	"Borja",			"Blasco Garcia",				"emailGarcia@email.com"},
			{"X2",	"David",			"Serrano Hernandez",	"emailSerrano@email.com"},
			{"X3",	"Georgian Vlad",	"Nicu",					"emailGG@email.com"},
			{"X4",	"Joel",				"Rivera",				"emailJowhy@email.com"},
			
		// Caso no existente
			{"X5",	"Imanol", 			"Ropero Casado",		"emailIRC@email.com"}};
	
	@BeforeClass
	public static void setUpBeforeClass() {
		dao = new DaoClient();
		expected= new HashMap<String, Client>();
		
		for(String[] clientData: datosIniciales){
			Client client = new Client();
			
			client.setClientId(clientData[0]);
			client.setClientName(clientData[1]);
			client.setClientLastName(clientData[2]);
			client.setClientEmail(clientData[3]);
			
			expected.put(clientData[0], client);
			
		}
		
		
	}
	
	@Test
	public void test1GetClients() {
		Map<String, Client> result = dao.getClients();
		
		assertTrue(result.containsKey("X1"));
		assertTrue(result.containsKey("X2"));
		assertTrue(result.containsKey("X3"));
		assertTrue(result.containsKey("X4"));
		assertFalse(result.containsKey("X7"));
	}
	
	@Test
	public void test2GetClient() {
		Client result1 = dao.getClient("X1");
        assertTrue(result1.equals(expected.get("X1")));
        
        Client result2 = dao.getClient("X2");
        assertTrue(result2.equals(expected.get("X2")));
        
        Client result3 = dao.getClient("X3");
        assertTrue(result3.equals(expected.get("X3")));
        
        Client result4 = dao.getClient("X4");
        assertTrue(result4.equals(expected.get("X4")));
        
        
		
	}
	
	@Test
	public void test3AddClient() {
		
        dao.addClient(expected.get("X5"));
        assertEquals(expected.get("X5"), dao.getClient("X5"));
	}
	
	@Test
	public void test4UpdateClient() {
        Client a3updated = new Client(expected.get("X5"));
        a3updated.setClientLastName("SinNombre");
        
        dao.updateClient(a3updated);
        
        assertEquals(a3updated, dao.getClient("X5"));
	}
	
	@Test
	public void test5DeleteClient() {
        dao.deleteClient("X5");
        assertNull(dao.getClient("X5"));
	}
	
	
}
