package database;

import java.io.InvalidClassException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import classes.Activity;

@Repository
public class daoActivity {

	private JdbcTemplate dataSource;
	
	public daoActivity() {
		super();
	}
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	/**
	 * This class makes an activity from the database outputs
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
				activity.setNombre(rs.getString("nombre"));
				activity.setDescription(rs.getString("description"));
				activity.setDescripcion(rs.getString("descripcion"));
				activity.setImage(rs.getString("image"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return activity;			
		}
	}
	
	/**
	 * Method to add an Activity into the DB
	 * @param class Activity
	 * @throws InvalidClassException 
	 */
	public void addElement(Activity activity) {
		String sql = "INSERT INTO activity(idact, name, leveldif, schedule, price, place, mingroup, maxgroup, isactive, nombre, description, descripcion, image) " +
                            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		dataSource.update(sql, activity.getIdAct(), activity.getName(), activity.getLevel(), activity.getSchedule(), activity.getPrice(),
				activity.getPlace(), activity.getMinimumGroup(), activity.getMaximumGroup(), activity.isActive(), activity.getNombre(), activity.getDescription(), activity.getDescripcion(), activity.getImage());
	}

	/**
	 * Method to remove an Activity from the DB
	 * @param element Integer with the idAct
	 */
	public void deleteElement(int idAct) {
		// delete activity
		String sql = "DELETE FROM activity WHERE idact = ?;";
		dataSource.update(sql, idAct);
		// delete relation activity-instructor
		sql = "DELETE FROM instruidas WHERE idact = ?;";
		dataSource.update(sql, idAct);
	}
	
	/**
	 * Method to update an Activity in the DB
	 * @param class Activity
	 */
	public void updateElement(Activity activity) {
		String sql = "UPDATE activity SET name = ?, leveldif = ?, schedule = ?," +
            "price = ?, place = ?, mingroup = ?, maxgroup = ?, isactive = ? , nombre = ?, description = ?, descripcion = ?, image = ?" +
                            "WHERE idact = ?;";
		dataSource.update(sql, activity.getName(), activity.getLevel(), activity.getSchedule(), activity.getPrice(), 
				activity.getPlace(), activity.getMinimumGroup(), activity.getMaximumGroup(), activity.isActive(), 
				activity.getNombre(), activity.getDescription(), activity.getDescripcion(), activity.getImage(), activity.getIdAct());
	}

	/**
	 * Method to obtain an Activity from the DB
	 * @param identifier Integer with the idAct
	 * @return an Activity with all the field, includes Instructors
	 */
	public Activity getElement(int idAct) {
		Activity act = null;
		String sql = "SELECT * FROM activity WHERE idact=?;";
		List<Activity> list = dataSource.query(sql, new ActivityMapper(), idAct);
		if (list.size() == 0 || list.size() > 1) act = null;
		act = list.get(0);
		
		if (act != null)
			act.setInstructors(getInstructorActivity(act.getIdAct()));
		return act;
	}

	/**
	 * Method to obtain an Activity from the DB
	 * @param String with the name
	 * @return an Activity with all the field, includes Instructors
	 */
	public Activity getElement(String name) {
		Activity act;
		String sql = "SELECT * FROM activity WHERE name = ?;";
		List<Activity> list = dataSource.query(sql, new ActivityMapper(), name);
		if (list.size() == 0 || list.size() > 1) act = null;
		act = list.get(0);
		
		if (act != null)
			act.setInstructors(getInstructorActivity(act.getIdAct()));
		return act;
	}

	/**
	 * Method to obtain all the activities from the DB
	 * @return Map<Integer, Activity>, key: idact, value: Activity with all the fields, includes Instructors
	 */
	public Map<Integer, Activity> getElements() {
		String sql = "SELECT * FROM activity;";
		List<Activity> list = dataSource.query(sql, new ActivityMapper());
		Map<Integer, Activity> map = new HashMap<Integer, Activity>();
		for(Activity act: list) {
			act.setInstructors(getInstructorActivity(act.getIdAct()));
			map.put(act.getIdAct(), act);
		}
		return map;
	}
	
	/**
	 * Method to obtain all the activities with the level from the DB
	 * @param level Integer, the level of the activities
	 * @return List<Activity> Only contains the activities with this level
	 */
	public List<Activity> getElementsWithLevel(int level){		
		String sql = "SELECT * FROM activity WHERE leveldif = ?;";
		List<Activity> list = dataSource.query(sql, new ActivityMapper());
		for (Activity act : list) act.setInstructors(getInstructorActivity(act.getIdAct()));
		return list;
	}
	
	/**
	 * Method to obtain all the activities with this schedule from the DB
	 * @param schedule String, the shedule of the activities
	 * @return List<Activity> Only contains the activities with this schedule
	 */
	public List<Activity> getElementsWithSchedule(String schedule) {
		String sql = "SELECT * FROM activity WHERE schedule = ?;";
		List<Activity> list = dataSource.query(sql, new ActivityMapper());
		for (Activity act : list) act.setInstructors(getInstructorActivity(act.getIdAct()));
		return list;
	}
	
	/** Method to obtain all the activities that are inactive
	 * @return Map<Integer, Activity> Each pair content the idAct as the key and the activity itself as a value
	 */
	public Map<Integer, Activity> getElementsInactive() {
		String sql = "SELECT * FROM activity WHERE isactive = false;";
		Map<Integer, Activity> map = new HashMap<Integer, Activity>();
		List<Activity> list = dataSource.query(sql, new ActivityMapper());
		for(Activity i:list){
			map.put(i.getIdAct(), i);
		}
		return map;
	}
	
	/** Method to obtain all the activities that are active
	 * @return Map<Integer, Activity> Each pair content the idAct as the key and the activity itself as a value
	 */
	public Map<Integer, Activity> getElementsActive() {
		String sql = "SELECT * FROM activity WHERE isactive = true;";
		Map<Integer, Activity> map = new HashMap<Integer, Activity>();
		List<Activity> list = dataSource.query(sql, new ActivityMapper());
		for(Activity i:list){
			map.put(i.getIdAct(), i);
		}
		return map;
	}
	
	/**
	 * Method to get the instructors that can supervise this activity
	 * @param idAct Integer with the idact
	 * @return List<String> with the ssNumber of the instructors
	 */
	private List<String> getInstructorActivity(int idAct) {
    	String sql = "SELECT ssNumber from instruidas WHERE idAct=?;";
    	return (List<String>) dataSource.queryForList(sql, String.class, idAct);
	}
	

	/**
	 * Method to add an Instructor that can supervise the activity
	 * @param idact Activity's identifier
	 * @param ssnum Instructor's identifier
	 */
	public void addInstructor(int idact, String ssnum) {
		String sql = "INSERT INTO instruidas(idact, ssnumber) VALUES(?, ?);";
		dataSource.update(sql, idact, ssnum);
	}
		
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
			sb.append(";");
			dataSource.update(sb.toString());
		}
	}
	
	/**
	 * Method to remove an Instructor that can supervise the activity
	 * @param idact Activity's identifier
	 * @param ssnum Instructor's identifier
	 */
	public void deleteActivityFromInstructor(int idact, String ssnum) {
		String sql = "DELETE FROM instruidas WHERE idact = ? AND ssnumber = ?;";
		dataSource.update(sql, idact, ssnum);
	}
	
	/**
	 * Method to remove all the instructors than can supervise the activity
	 * @param idact Activity's Identifier
	 */
	public void deleteActivityFromInstructors(int idact) {
		String sql = "DELETE FROM instruidas WHERE idact = ?;";
		dataSource.update(sql, idact);
	}
	
	/**
	 * Method to obtain the maximum identifier of the activity
	 * @return Integer with the maximum ID
	 */
	public Integer getMaxID() {
		String sql = "SELECT MAX(idact) FROM activity;";
		return dataSource.queryForObject(sql, Integer.class);
	}
}
