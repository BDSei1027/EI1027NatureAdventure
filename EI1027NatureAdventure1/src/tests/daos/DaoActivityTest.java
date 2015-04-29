package tests.daos;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import classes.Activity;
import database.oldDaos.DaoActivity;

/*	Como los test tiene que ser en el orden determinado
 *  los métodos se llaman de forma test<numero><nombre>
 *  el numero indicará el orden en el cual se ejecutaran
 *  para eso el @FixMethodOrder
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoActivityTest {


    /* La base de datos antes tiene que tener algunos datos añadidos para poder hacer las comprobaciones
    *   2031 - Volar - 3 - Mañana - 20.3 - Castellon - 2 - 7 - TRUE
    *   2032 - Nadar - 1 - Tarde - 12.2 - Benicarlo - 1 - 12 - FALSE
    *   2033 - Soñar - 2 - Mediodia - 9.23 - Cama - 1 - 2 - TRUE
    *   Añadir y actualizar y borrar en los test
    *   2034 - Explorar - 2 - Noche -  203.2 -  Antartidad - 4 - 7 - FALSE
    *   
    *   a5 y a6 no se debe añadir, por fallos con las reglas de integridad
    *   
    *   Instructores asignados a 2031
    *   ["2031","2033"]
    *   Instructores asignados a 2033
    *   ["2033"]
    *   Añadir y borrar en los test
    *   Instructor: 2032
    *   
     */

    static Map<Integer,Activity> expected;
    static Activity a1, a2, a3, a4;
    static DaoActivity dao;
    static List<String> l1, l2, l4;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
        dao = new DaoActivity();
        expected = new HashMap<Integer,Activity>();
        l1 = new LinkedList<String>();
        l1.add("2031");
        l1.add("2033");
        l2 = new LinkedList<String>();
        l2.add("2033");
        l4 = new LinkedList<String>();
		l4.add("2031");
		l4.add("2032");
		l4.add("2033");

        a1 = new Activity();
        a1.setIdAct(2031);
        a1.setName("Volar");
        a1.setLevel(3);
        a1.setSchedule("Mañana");
        a1.setPrice(20.3);
        a1.setPlace("Castellon");
        a1.setMinimumGroup(2);
        a1.setMaximumGroup(7);
        a1.setIsActive(true);
        a1.setInstructors(l1);
        expected.put(2031, a1);

        a2 = new Activity();
        a2.setIdAct(2032);
        a2.setName("Nadar");
        a2.setLevel(1);
        a2.setSchedule("Tarde");
        a2.setPrice(12.2);
        a2.setPlace("Benicarlo");
        a2.setMinimumGroup(1);
        a2.setMaximumGroup(12);
        a2.setIsActive(false);
        a2.setInstructors(new LinkedList<String>());
        expected.put(2032, a2);

        a3 = new Activity();
        a3.setIdAct(2033);
        a3.setName("Soñar");
        a3.setLevel(2);
        a3.setSchedule("Noche");
        a3.setPrice(9.23);
        a3.setPlace("Cama");
        a3.setMinimumGroup(1);
        a3.setMaximumGroup(2);
        a3.setIsActive(true);
        a3.setInstructors(l2);
        expected.put(2033, a3);

        a4 = new Activity();
        a4.setIdAct(2034);
        a4.setName("Explorar");
        a4.setLevel(2);
        a4.setSchedule("Noche");
        a4.setPrice(203.2);
        a4.setPlace("Antartida");
        a4.setMinimumGroup(4);
        a4.setMaximumGroup(8);
        a4.setIsActive(false);
        a4.setInstructors(new LinkedList<String>());
	}

	@Test
	public void test1GetActivities() {
		Map<Integer, Activity> result = dao.getActivities();
		assertTrue(result.containsKey(2031));
		assertTrue(result.containsKey(2032));
		assertTrue(result.containsKey(2033));
		assertFalse(result.containsKey(2034));
		
	}
	
	@Test
	public void test2GetActivity() {
        Activity result1 = dao.getActivity(2031);
        assertEquals(a1, result1);
        Activity result2 = dao.getActivity(2032);
        assertEquals(a2, result2);
        Activity result3 = dao.getActivity(2033);
        assertEquals(a3, result3);
	}

	@Test
	public void test3AddActivity() {
        dao.addActivity(a4);
        assertEquals(a4, dao.getActivity(2034));
	}

	@Test
	public void test4AssignInstructorToActivity() {
		a4.setInstructors(l4);
		dao.assignInstructorToActivity("2031", 2034);
		dao.assignInstructorToActivity("2032", 2034);
		dao.assignInstructorToActivity("2033", 2034);
		assertEquals(a4, dao.getActivity(2034));
	}
	
	@Test
	public void test5deleteInstructorFromActivity() {
		Activity a2update = new Activity(a2);
		a2update.getInstructors().add("2032");
		dao.assignInstructorToActivity("2032", 2032);
		assertEquals(a2update, dao.getActivity(2032));
		dao.deleteInstructorFromActivity("2032", 2032);
		assertEquals(a2, dao.getActivity(2032));
	}
	
	@Test
	public void test6deleteInstructorsFromActivity() {
		dao.deleteInstructorsFromActivity(2034);
		a4.setInstructors(new LinkedList<String>());
		assertEquals(a4, dao.getActivity(2034));
	}
	
	@Test
	public void test7UpdateActivity() {
        Activity a4updated = new Activity(a4);
        a4updated.setName("prueba");
        dao.updateActivity(a4updated);
        assertEquals(a4updated, dao.getActivity(2034));
	}

	@Test
	public void test8DeleteActivity() {
        dao.deleteActivity(2034);
        assertNull(dao.getActivity(2034));
	}
}
