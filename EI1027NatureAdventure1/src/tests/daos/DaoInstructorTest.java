package tests.daos;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import classes.Instructor;
import database.oldDaos.DaoInstructor;

/*	Como los test tiene que ser en el orden determinado
 *  los métodos se llaman de forma test<numero><nombre>
 *  el numero indicará el orden en el cual se ejecutaran
 *  para eso el @FixMethodOrder
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoInstructorTest {
	/* La base de datos antes tiene que tener algunos datos añadidos para poder hacer las comprobaciones
	 * 2031 - 200001W - David - Serrano - emailDavid@email.com - 900000000 -true
	 * 2032 - 200002J - Borja - Blasco - emailBorja@email.com - 900000001 - false
	 * 2033 - 200003H - Catalin - Damian - emailCatalin@email.com - 900000002 - true
	 * Añadir, Actualizar y borrar en los test
	 * 2034 - 200004T - Prueba - Test - emailPrueba@email.com - 900000003 -true
	 * 
	 *   Actividades asignadas a 2031
	 *   [2031]
	 *   Actividades asignadas a 2033
	 *   [2031, 2033]
	 */
	
	static Map<String,Instructor> expected;
	static Instructor i1,i2,i3,i4;
	static List<Integer> l1, l3;
	static DaoInstructor dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new DaoInstructor();
		l1 = new LinkedList<Integer>();
		l1.add(2031);
		l3 = new LinkedList<Integer>();
		l3.add(2031);
		l3.add(2033);
		
		expected= new HashMap<String, Instructor>();
		i1 = new Instructor();
		i1.setSsNumber("2031");
		i1.setIdNumber("200001W");
		i1.setName("David");
		i1.setLastName("Serrano");
		i1.setEmail("emailDavid@email.com");
		i1.setTelephone("900000000");
		i1.setActive(true);
		i1.setActivities(l1);
		expected.put(i1.getSsNumber(),i1);
		
		i2 = new Instructor();
		i2.setSsNumber("2032");
		i2.setIdNumber("200002J");
		i2.setName("Borja");
		i2.setLastName("Blasco");
		i2.setEmail("emailBorja@email.com");
		i2.setTelephone("900000001");
		i2.setActive(false);
		i2.setActivities(new LinkedList<Integer>());
		expected.put(i2.getSsNumber(),i2);
		
		i3 = new Instructor();
		i3.setSsNumber("2033");
		i3.setIdNumber("200003H");
		i3.setName("Catalin");
		i3.setLastName("Damian");
		i3.setEmail("emailCatalin@email.com");
		i3.setTelephone("900000003");
		i3.setActive(true);
		i3.setActivities(l3);
		expected.put(i3.getSsNumber(),i3);
		
		i4 = new Instructor();
		i4.setSsNumber("2034");
		i4.setIdNumber("200004T");
		i4.setName("Prueba");
		i4.setLastName("Test");
		i4.setEmail("emailPrueba@email.com");
		i4.setTelephone("900000003");
		i4.setActivities(new LinkedList<Integer>());
		i4.setActive(true);
	}
	
	@Test
	public void test1GetInstructors() {
		Map<String,Instructor> result = dao.getInstructors();
		assertTrue(result.containsKey("2031"));
		assertTrue(result.containsKey("2032"));
		assertTrue(result.containsKey("2033"));
		assertFalse(result.containsKey("2034"));
	}
	
	@Test
	public void test2GetInstructor() {
		Instructor result1 = dao.getInstructor("2031");
        assertTrue(result1.equals(i1));
        Instructor result2 = dao.getInstructor("2032");
        assertTrue(result2.equals(i2));
        Instructor result3 = dao.getInstructor("2033");
        assertTrue(result3.equals(i3));
	}
		
	@Test
	public void test3AddInstructor() {
        dao.addInstructor(i4);
        assertEquals(i4, dao.getInstructor("2034"));
	}
	
	@Test
	public void test4UpdateInstructor() {
        Instructor i4updated = new Instructor(i4);
        i4updated.setName("NuevoNombre");
        i4updated.setActive(true);
        dao.updateInstructor(i4updated);
        assertEquals(i4updated, dao.getInstructor("2034"));
	}
	
	@Test
	public void test5DeleteInstructor() {
        dao.deleteInstructor("2034");
        assertNull(dao.getInstructor("2034"));
	}
	
	@Test
	public void test6DeleteInstructorFromActivities() {
		Instructor i3updated = new Instructor(i3);
		i3updated.setActivities(new LinkedList<Integer>());
		dao.deleteInstructorFromActivities("2033");
		assertEquals(i3updated, dao.getInstructor("2033"));
	}
	
	
	/**
	 * Para no ensuciar el test de instructores con actividades para
	 * poder hacer las pruebas con la tabla instruidas y restaurar la
	 * tabla de nuevo uso este método, no hace ningun import ni nada
	 * que pueda obstruir a todo el test
	 * @throws Exception
	 */
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		database.oldDaos.DaoActivity daoAct = new database.oldDaos.DaoActivity();
		daoAct.assignInstructorToActivity("2033", 2033);
		daoAct.assignInstructorToActivity("2033", 2031);
	}

}
