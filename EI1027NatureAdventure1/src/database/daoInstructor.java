package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import classes.Instructor;

@Repository
public class daoInstructor implements DaoInterface {

	private JdbcTemplate dataSource;
	
	@Autowired
	private void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	/*
	 * RowMapper for the class Instructor
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
			} catch (Exception e) {
				e.printStackTrace();
			}
			return instructor;
		}
	}
	
	/**
	 * Method to add an Instructor into the DB
	 * @see database.DaoInterface#addElement(java.lang.Object)
	 * @param element Instructor, class Instructor
	 * TODO Comprobar que lo que le pasamos es un Instructor
	 */
	@Override
	public void addElement(Object element) {
		Instructor instr = (Instructor) element;
		String sql = "INSERT INTO Instructor(ssNumber,idNumber,name,lastname,email,telephone, isActive) "
							+ "values(?, ?, ?, ?, ?, ?,?)";
		dataSource.update(sql, instr.getSsNumber(), instr.getIdNumber(), instr.getName(), instr.getLastName(), instr.getEmail(), instr.getTelephone(), instr.isActive());
		
		addActivities(instr.getSsNumber(), instr.getActivities());
	}

	/**
	 * Method to remove an Instructor from the DB
	 * @see database.DaoInterface#deleteElement(java.lang.Object)
	 * @param element String with the ssNumber
	 */
	@Override
	public void deleteElement(Object element) {
		String id = (String) element;
		String sql = "DELETE FROM instructor WHERE ssNumber = ?";
		dataSource.update(sql, id);
		deleteInstructorFromActivities(id);
	}

	/**
	 * Method to update an Instructor in the DB
	 * @see database.DaoInterface#updateElement(java.lang.Object)
	 * @param element Instructor, class Instructor
	 * TODO Comprobar que es de la clase instructor
	 */
	@Override
	public void updateElement(Object element) {
		Instructor instr = (Instructor) element;
		String sql = "UPDATE instructor " + "SET "
					+ "idNumber = ?," + "name = ?," + "lastname = ?,"
					+ "email = ?," + "telephone = ?, " + " isActive = ? " + "WHERE ssNumber = ?";
		dataSource.update(sql, instr.getIdNumber(), instr.getName(),instr.getLastName(), instr.getEmail(), instr.getTelephone(), instr.isActive(), instr.getSsNumber());
	}

	/**
	 * Method to obtain an Instructor from the DB
	 * @see database.DaoInterface#getElement(java.lang.Object)
	 * @param identifier String with the ssNumber
	 * @return an Instructor with all the field, includes Activities
	 */
	@Override
	public Object getElement(Object identifier) {
		String id = (String) identifier;
		String sql = "SELECT * FROM instructor WHERE = ?";
		Instructor instructor = dataSource.queryForObject(sql, new InstructorMapper(), id);
		instructor.setActivities(getActivitiesInstructor(id));
		return instructor;
	}

	/**
	 * Method to obtain all the instructors from the DB
	 * @see database.DaoInterface#getElements()
	 * @return Map<String, Instructor>, key: ssnumber, value: Instructor with all the fields, includes Activities
	 */
	@Override
	public Object getElements() {
		String sql = "SELECT * FROM instructor";
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
	private List<Integer> getActivitiesInstructor(String id) {
		String sql = "SELECT idact FROM instruidas WHERE ssnumber = ?";
		return (List<Integer>) dataSource.queryForList(sql, Integer.class, id);
	}
	
	// TODO cambiamos el nombre?
	/**
	 * Method to add an activity that the instructor can supervise into instruidas
	 * @param ssnum Instructor's identifier
	 * @param idact Activity's identifier
	 */
	public void addActivity(String ssnum, int idact) {
		String sql = "INSERT INTO instruidas(idact, ssnumber) VALUES(? ,?)";
		dataSource.update(sql, idact, ssnum);
	}
	
	// TODO cambiamos el nombre?
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
			dataSource.update(sb.toString());
		}
	}
	
	/**
	 * Method to remove an activity that the instructor can supervise
	 * @param ssnum Instructor's identifier
	 * @param idact Activity's identifier
	 */
	public void deleteInstructorFromActivity(String ssnum, int idact) {
		String sql = "DELETE FROM instruidas WHERE ssnumber = ?, idact = ?";
		dataSource.update(sql, ssnum, idact);
	}
	
	/**
	 * Method to remove all the activities that the instructor can supervise
	 * @param ssnum Instructor's identifier
	 */
	public void deleteInstructorFromActivities(String ssnum) {
		String sql = "DELETE FROM instruidas WHERE ssnumber = ?";
		dataSource.update(sql, ssnum);
	}
}