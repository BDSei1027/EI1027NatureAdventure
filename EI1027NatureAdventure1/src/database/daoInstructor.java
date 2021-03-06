package database;

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
import classes.Instructor;

@Repository
public class daoInstructor {

	private JdbcTemplate dataSource;
	
	public daoInstructor() {
		super();
	}
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	/**
	 * This class makes a Instructor from the database outputs
	 */
	private static final class InstructorMapper implements RowMapper<Instructor> {
		public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Instructor instructor = new Instructor();
			try {
				instructor.setSsNumber(rs.getString("ssnumber"));
				instructor.setIdNumber(rs.getString("idnumber"));
				instructor.setName(rs.getString("name"));
				instructor.setLastName(rs.getString("lastname"));
				instructor.setEmail(rs.getString("email"));
				instructor.setTelephone(rs.getString("telephone"));
				instructor.setActive(rs.getBoolean("isActive"));
				if(rs.getDate("expireDate")!=null) instructor.setExpireDate(rs.getDate("expireDate").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return instructor;
		}
	}
	
	/**
	 * Method to add an Instructor into the DB
	 * @param element Instructor, class Instructor
	 */
	public void addElement(Instructor instructor) {
		String sql = "INSERT INTO Instructor(ssNumber,idNumber,name,lastname,email,telephone, isActive, expireDate) "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?);";
		dataSource.update(sql, instructor.getSsNumber(), instructor.getIdNumber(), instructor.getName(), instructor.getLastName(), instructor.getEmail(), instructor.getTelephone(), instructor.isActive(), instructor.getExpireDate());
		if (instructor.getActivities().size() != 0) {
			addActivities(instructor.getSsNumber(), instructor.getActivities());
		}
	}

	/**
	 * Method to remove an Instructor from the DB
	 * @param element String with the ssNumber
	 */
	public void deleteElement(String id) {
		String sql = "DELETE FROM instructor WHERE ssNumber = ?;";
		dataSource.update(sql, id);
		deleteInstructorFromActivities(id);
	}

	/**
	 * Method to update an Instructor in the DB
	 * @param element Instructor, class Instructor
	 */
	public void updateElement(Instructor instr) {
		String sql = "UPDATE instructor " + "SET "
					+ "idNumber = ?," + "name = ?," + "lastname = ?,"
					+ "email = ?," + "telephone = ?, " + " isActive = ?, " + "expireDate = ? " + "WHERE ssNumber = ?;";
		dataSource.update(sql, instr.getIdNumber(), instr.getName(),instr.getLastName(), instr.getEmail(), instr.getTelephone(), instr.isActive(), instr.getExpireDate(), instr.getSsNumber());
	}

	/**
	 * Method to obtain an Instructor from the DB
	 * @param identifier String with the ssNumber
	 * @return an Instructor with all the field, includes Activities
	 */
	public Instructor getElement(String id) {
		String sql;
		if (id.length() > 9) sql = "SELECT * FROM instructor WHERE ssnumber = ?;";
		else sql = "SELECT * FROM instructor WHERE idnumber = ?";
		
		Instructor instructor = null;
		List<Instructor> list = dataSource.query(sql, new InstructorMapper(), id);
		if (list.size() == 0 || list.size() < 1) instructor = null;
		else instructor = list.get(0);
		
		if (instructor != null) {
			instructor.setActivities(getActivitiesInstructor(id));
		}
		return instructor;
	}

	/**
	 * Method to obtain all the instructors from the DB
	 * @return Map<String, Instructor>, key: ssnumber, value: Instructor with all the fields, includes Activities
	 */
	public Map<String, Instructor> getElements() {
		String sql = "SELECT * FROM instructor;";
		Map<String, Instructor> map = new HashMap<String, Instructor>();
		List<Instructor> list = dataSource.query(sql, new InstructorMapper());
		for(Instructor i: list) {
			i.setActivities(getActivitiesInstructor(i.getSsNumber()));
			map.put(i.getSsNumber(), i);
		}
		return map;
	}

	/**
	 * Method to get the activities that the instructor can supervise
	 * @param id String with the ssnumber
	 * @return List<Integer> with the idact of the activities
	 */
	public List<Integer> getActivitiesInstructor(String id) {
		String sql = "SELECT idact FROM instruidas WHERE ssnumber = ?;";
		return (List<Integer>) dataSource.queryForList(sql, Integer.class, id);
	}
	
	/**
	 * Method to add an activity that the instructor can supervise into instruidas
	 * @param ssnum Instructor's identifier
	 * @param idact Activity's identifier
	 */
	public void addActivity(String ssnum, int idact) {
		String sql = "INSERT INTO instruidas(idact, ssnumber) VALUES(? ,?);";
		dataSource.update(sql, idact, ssnum);
	}
	
	/**
	 * Method to add some activities that the instructor can supervise into instruidas
	 * @param ssnum Instructor's identifier
	 * @param acts List<Integer> Activities' identifiers
	 */
	public void addActivities(String ssnum, List<Integer> acts) {
		if (acts.size() != 0) {
			StringBuilder sb = new StringBuilder("INSERT INTO instruidas(idact, ssnumber) VALUES ");
			for(int i = 0; i < acts.size(); i++) {
				if (i != 0) sb.append(", ");
				sb.append("(" + acts.get(i) + ", " + ssnum + ")");
			}
			sb.append(";");
			dataSource.update(sb.toString());
		}
	}
	
	/**
	 * Method to remove an activity that the instructor can supervise
	 * @param ssnum Instructor's identifier
	 * @param idact Activity's identifier
	 */
	public void deleteInstructorFromActivity(String ssnum, int idact) {
		String sql = "DELETE FROM instruidas WHERE ssnumber=? AND idact =?;";
		dataSource.update(sql, ssnum, idact);
	}
	
	/**
	 * Method to remove all the activities that the instructor can supervise
	 * @param ssnum Instructor's identifier
	 */
	public void deleteInstructorFromActivities(String ssnum) {
		String sql = "DELETE FROM instruidas WHERE ssnumber = ?;";
		dataSource.update(sql, ssnum);
	}
	
	/**
	 * Method to obtain the activities that the instructor can supervise
	 * @param ssnum Instructor's identifier
	 * @return List<Activity> Activities that the instructor can teach
	 */
	public List<Activity> getAllActivitiesFromInstructor(String ssnum){
		String sql = "SELECT act.* FROM instruidas AS i JOIN activity AS act USING (idAct) WHERE i.ssnumber = ?;";
		List<Activity> list = dataSource.query(sql, new Object[] {ssnum}, new RowMapper<Activity>() {
			@Override
			public Activity mapRow(ResultSet rs, int intRow) throws SQLException {
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
		});
		return list;
	}

	/** Method to obtain the active instructors of the system
	 * @return A map with the instructors
	 */
	public Map<String, Instructor> getElementsActive() {
		String sql = "SELECT * FROM instructor WHERE isactive = true;";
		Map<String, Instructor> map = new HashMap<String, Instructor>();
		List<Instructor> list = dataSource.query(sql, new InstructorMapper());
		for(Instructor i: list) {
			i.setActivities(getActivitiesInstructor(i.getSsNumber()));
			map.put(i.getSsNumber(), i);
		}
		return map;
	}

	/** Method to obtain the inactive instructors of the system
	 * @return A map with the instructors
	 */
	public Map<String, Instructor> getElementsInactive() {
		String sql = "SELECT * FROM instructor WHERE isactive = false;";
		Map<String, Instructor> map = new HashMap<String, Instructor>();
		List<Instructor> list = dataSource.query(sql, new InstructorMapper());
		for(Instructor i: list) {
			i.setActivities(getActivitiesInstructor(i.getSsNumber()));
			map.put(i.getSsNumber(), i);
		}
		return map;
	}

	/** Method to obtain all the activities without instructor
	 * @param ssnum
	 * @return A list with the activities
	 */
	public List<Activity> getAllActivitiesFromNoInstructor(String ssnum) {
		String subsql = "SELECT idact FROM instruidas WHERE ssnumber = ?";
		String sql = "SELECT act.* FROM activity AS act WHERE idact NOT IN (" + subsql + ");";
		List<Activity> list = dataSource.query(sql, new Object[] {ssnum}, new RowMapper<Activity>() {
			@Override
			public Activity mapRow(ResultSet rs, int intRow) throws SQLException {
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
		});
		return list;
	}
	
	/** Method to obtain the number of bookings related with a certain client
	 * @param ssnum
	 * @return The number of booking related to the client
	 */
	public Integer getNumberBookings(String ssnum) {
		String sql = "SELECT COUNT(*) FROM status as st JOIN booking as b USING(inneridbooking) WHERE st.ssnumber = ? AND CURRENT_DATE < dateactivity;";
		return dataSource.queryForObject(sql, new Object[] {ssnum}, Integer.class);
	}

	
	/** Method to get the available instructors to be assigned to a Booking
	 * @param idAct
	 * @param innerIdBooking
	 * @return A map with the instructors
	 */
	public Map<String, Instructor> getAvaliableInstructorsToAssign(int idAct, int innerIdBooking){
		String sql = "SELECT i.* " +
					"FROM instruidas as inidas JOIN instructor AS i USING(ssNumber)" +
					"WHERE inidas.idAct = ? AND  i.isactive = TRUE AND inidas.ssNumber " +
					"NOT IN (SELECT s.ssNumber " +
					"FROM Activity AS a JOIN instruidas AS inidas USING(idAct) JOIN status AS s USING(ssNumber) JOIN booking AS b USING(inneridbooking)" +
					"WHERE a.idAct = ? AND b.dateActivity = (SELECT b.dateActivity " +
					"FROM BOOKING AS b WHERE innerIdBooking = ?));";
					
		Map<String, Instructor> map = new HashMap<String, Instructor>();
		List<Instructor> list = dataSource.query(sql, new InstructorMapper(), idAct, idAct, innerIdBooking);
		for(Instructor i: list) {
			i.setActivities(getActivitiesInstructor(i.getSsNumber()));
			map.put(i.getSsNumber(), i);
		}
		return map;
	}
	//activo fecha booking anterior expiracion
	
}
