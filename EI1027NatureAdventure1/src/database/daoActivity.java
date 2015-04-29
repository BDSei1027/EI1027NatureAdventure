package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import classes.Activity;



@Repository
public class daoActivity implements DaoInterface {
	
	private JdbcTemplate dataSource;
	
	@Autowired()
	public void setDataSource(DataSource datasource) {
		System.out.println(datasource);
		this.dataSource = new JdbcTemplate(datasource);
		System.out.println(this.dataSource);
	}
	
	public daoActivity() {
		super();
	}
	/**
	 * This class make and activity from the database outputs
	 */
	private static final class ActivityMapper implements RowMapper<Activity> {
		public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
			Activity activity = new Activity();
			activity.setIdAct(rs.getInt("idact"));
			activity.setName(rs.getString("name"));
			activity.setLevel(rs.getInt("leveldif"));
			activity.setSchedule(rs.getString("schedule"));
			activity.setPrice(rs.getDouble("price"));
			activity.setPlace(rs.getString("place"));
			activity.setMinimumGroup(rs.getInt("mingroup"));
			activity.setMaximumGroup(rs.getInt("maxgroup"));
			activity.setIsActive(rs.getBoolean("isactive"));
			return activity;			
		}
	}
	
	/**
	 * Method for add a new Activity to the DB
	 * @param element Must be a class Activity to add.
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
			// Forma cachi
		if (listIns.size() != 0) {
			StringBuilder sb = new StringBuilder("INSERT INTO instruidas(idact, ssnumber) VALUES ");
			for(int i = 0; i < listIns.size(); i++) {
				if ( i != 0 ) sb.append(", ");
				sb.append("(" + activity.getIdAct() + ", " + listIns.get(i) + ")");
			 }
			 dataSource.update(sb.toString());
		}
		/* Forma no cachi y sobrecarga la conexion
		 * sql = "INSERT INTO instruidas(idact, ssnumber) VALUES(?,?)";
		dataSource.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@SuppressWarnings("unused")
			@Override
			public void setValues(PreparedStatement ps, int arg) throws SQLException {
				String instructor = listIns.get(arg);
			}
			
			@Override
			public int getBatchSize() {
				return listIns.size();
			}
		}); */
	}

	/**
	 * Method to delete the Activity from the DB
	 * @param element Must be an int which is the ID
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
	
	@Override
	public void updateElement(Object element) {
		// Cast Objecto to Activity
		Activity activity = (Activity) element;
		String sql = "UPDATE activity SET name = ?, leveldif = ?, schedule = ?," +
            "price = ?, place = ?, mingroup = ?, maxgroup = ?, isactive = ? " +
                            "WHERE idact = ?";
		dataSource.update(sql, activity.getIdAct());
	}

	private List<String> getInstructorActivity(int idAct) {
        	String sql = "SELECT ssNumber from instruidas WHERE idAct=?";
        	return (List<String>) dataSource.queryForList(sql, String.class, idAct);
	}

	@Override
	public Object getElement(Object identifier) {
		//TODO Mirando si se puede hacer por joins, improbable
		int id = (int) identifier;
		String sql = "SELECT * FROM activity WHERE idact=?";
		Activity act = dataSource.queryForObject(sql, new ActivityMapper(), id);
		act.setInstructors(getInstructorActivity(act.getIdAct()));
		return act;
	}

	@Override
	public Object getElements() {
		//TODO Tiene que haber otra forma de hacerlo, segun StackOverflow es la forma correcta
		String sql = "SELECT * FROM activity";
		List<Activity> list = dataSource.query(sql, new ActivityMapper());
		Map<Integer, Activity> map = new HashMap<Integer, Activity>();
		for(Activity act: list) {
			act.setInstructors(getInstructorActivity(act.getIdAct()));
			map.put(act.getIdAct(), act);
		}
		return map;
	}
	
}
