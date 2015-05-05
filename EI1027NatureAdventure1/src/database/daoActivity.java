package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import classes.Activity;
import classes.Instructor;

@Repository
public class daoActivity implements DaoInterface {

	private JdbcTemplate dataSource;
	
	@Autowired()
	private void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	/**
	 * This class make an activity from the database outputs
	 */
	private static final class ActivityMapper implements RowMapper<Activity> {
		public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
			Activity activity = new Activity();
			try {
				activity.setIdAct(rs.getInt("idact"));
				activity.setName(rs.getString("name"));
				activity.setLevel(rs.getInt("leveldif"));
				activity.setSchedule(rs.getString("schedule"));
				activity.setPrice(rs.getFloat("price"));
				activity.setPlace(rs.getString("place"));
				activity.setMinimumGroup(rs.getInt("mingroup"));
				activity.setMaximumGroup(rs.getInt("maxgroup"));
				activity.setIsActive(rs.getBoolean("isactive"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return activity;			
		}
	}
	
	/**
	 * Method to add an Activity into the DB
	 * @see database.DaoInterface#addElement(java.lang.Object)
	 * @param element Activity, class Activity
	 * TODO Comprobar que lo que le pasamos es una actividad
	 */
	@Override
	public void addElement(Object element) {
		//TODO Mirar i hay que comprobarlo q sea Activity
		// Cast Object to Activity
		Activity activity = (Activity) element;
		String sql = "INSERT INTO activity(idact, name, leveldif, schedule, price, place, mingroup, maxgroup, isactive) " +
                            "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		dataSource.update(sql, activity.getIdAct(), activity.getName(), activity.getLevel(), activity.getSchedule(), activity.getPrice(),
				activity.getPlace(), activity.getMinimumGroup(), activity.getMaximumGroup());
		
		// Get the instructors who can teach it
		final List<String> listIns = activity.getInstructors();
		addInstructors(activity.getIdAct(), listIns);
	}

	/**
	 * Method to remove an Activity from the DB
	 * @see database.DaoInterface#deleteElement(java.lang.Object)
	 * @param element Integer with the idAct
	 */
	@Override
	public void deleteElement(Object element) {
		// Cast Object to int
		int id = (int) element;
		// delete activity
		String sql = "DELETE FROM activity WHERE idact = ?";
		dataSource.update(sql, id);
		// delete relation activity-instructor
		sql = "DELETE FROM instruidas WHERE idact = ?";
		dataSource.update(sql, id);
	}
	
	/**
	 * Method to update an Activity in the DB
	 * @see database.DaoInterface#updateElement(java.lang.Object)
	 * @param element Activity, class Instructor
	 * TODO Comprobar que es de la clase Activity
	 */
	@Override
	public void updateElement(Object element) {
		// Cast Objecto to Activity
		Activity activity = (Activity) element;
		String sql = "UPDATE activity SET name = ?, leveldif = ?, schedule = ?," +
            "price = ?, place = ?, mingroup = ?, maxgroup = ?, isactive = ? " +
                            "WHERE idact = ?";
		dataSource.update(sql, activity.getIdAct());
	}

	/**
	 * Method to obtain an Activity from the DB
	 * @see database.DaoInterface#getElement(java.lang.Object)
	 * @param identifier Integer with the idAct
	 * @return an Activity with all the field, includes Instructors
	 */
	@Override
	public Object getElement(Object identifier) {
		//TODO Mirando si se puede hacer por joins, improbable
		int id = (int) identifier;
		String sql = "SELECT * FROM activity WHERE idact=?";
		Activity act = dataSource.queryForObject(sql, new ActivityMapper(), id);
		act.setInstructors(getInstructorActivity(act.getIdAct()));
		return act;
	}

	/**
	 * Method to obtain all the activities from the DB
	 * @see database.DaoInterface#getElements()
	 * @return Map<Integer, Activity>, key: idact, value: Activity with all the fields, includes Instructors
	 */
	@Override
	public Object getElements() {
		String sql = "SELECT * FROM activity";
		List<Activity> list = dataSource.query(sql, new ActivityMapper());
		Map<Integer, Activity> map = new HashMap<Integer, Activity>();
		for(Activity act: list) {
			act.setInstructors(getInstructorActivity(act.getIdAct()));
			map.put(act.getIdAct(), act);
		}
		return map;
	}
	
	/**
	 * Method to get the instructors that can supervise this activity
	 * @param idAct Integer with the idact
	 * @return List<String> with the ssNumber of the instructors
	 */
	private List<String> getInstructorActivity(int idAct) {
    	String sql = "SELECT ssNumber from instruidas WHERE idAct=?";
    	return (List<String>) dataSource.queryForList(sql, String.class, idAct);
	}
	
	// TODO cambiamos el nombre?
	/**
	 * Methos to add an Instructor that can supervise the activity
	 * @param idact Activity's identifier
	 * @param ssnum Instructor's identifier
	 */
	public void addInstructor(int idact, String ssnum) {
		String sql = "INSERT INTO instruidas(idact, ssnumber) VALUES(?, ?)";
		dataSource.update(sql, idact, ssnum);
	}
		
	// TODO cambiamos el nombre?
	/**
	 * Method to add some instructors that can supervise an activity
	 * @param idact Activity's identifier
	 * @param listSS List<String> Instructors' identifiers
	 */
	public void addInstructors(int idact, List<String> listSS) {
		if (listSS.size() != 0) {
			StringBuilder sb = new StringBuilder("INSERT INTO instruidas(idact, ssnumber) VALUES ");
			for(int i = 0; i < listSS.size(); i++) {
				if ( i != 0 ) sb.append(", ");
				sb.append("(" + idact + ", " + listSS.get(i) + ")");
			 }
			 dataSource.update(sb.toString());
		}
	}
	
	/**
	 * Method to remove an Instructor that can supervise the activity
	 * @param idact Activity's identifier
	 * @param ssnum Instructor's identifier
	 */
	public void deleteActivityFromInstructor(int idact, String ssnum) {
		String sql = "DELETE FROM instruidas WHERE idact = ?, ssnumber = ?";
		dataSource.update(sql, idact, ssnum);
	}
	
	/**
	 * Method to remove all the instructors than can supervise the activity
	 * @param idact Activity's Identifier
	 */
	public void deleteActivityFromInstructors(int idact) {
		String sql = "DELETE FROM instruidas WHERE idact = ?";
		dataSource.update(sql, idact);
	}
	
	/**
	 * Method to obtain the maximum identifier of the activity
	 * @return Integer with the maximum ID
	 */
	public Integer getMaxID() {
		String sql = "SELECT MAX(idact) FROM activity";
		return dataSource.queryForObject(sql, Integer.class);
	}
}
