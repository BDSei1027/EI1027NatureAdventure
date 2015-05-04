package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import classes.Instructor;
//TODO getMaxID, deleteInstructorFromActivities getElements
@Repository
public class daoInstructor implements DaoInterface {

	private JdbcTemplate dataSource;
	
	@Autowired
	private void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
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
	
	
	@Override
	public void addElement(Object element) {
		Instructor instr = (Instructor) element;
		String sql = "INSERT INTO Instructor(ssNumber,idNumber,name,lastname,email,telephone, isActive) "
							+ "values(?, ?, ?, ?, ?, ?,?)";
		dataSource.update(sql, instr.getSsNumber(), instr.getIdNumber(), instr.getName(), instr.getLastName(), instr.getEmail(), instr.getTelephone(), instr.isActive());
		
		//TODO Mirar en instruidas
	}

	@Override
	public void deleteElement(Object element) {
		String id = (String) element;
		String sql = "DELETE FROM instructor WHERE ssNumber = ?";
		dataSource.update(sql, id);
	}

	@Override
	public void updateElement(Object element) {
		Instructor instr = (Instructor) element;
		String sql = "UPDATE instructor " + "SET "
					+ "idNumber = ?," + "name = ?," + "lastname = ?,"
					+ "email = ?," + "telephone = ?, " + " isActive = ? " + "WHERE ssNumber = ?";
		dataSource.update(sql, instr.getIdNumber(), instr.getName(),instr.getLastName(), instr.getEmail(), instr.getTelephone(), instr.isActive(), instr.getSsNumber());
	}

	@Override
	public Object getElement(Object identifier) {
		String id = (String) identifier;
		String sql = "SELECT * FROM instructor WHERE = ?";
		Instructor instructor = dataSource.queryForObject(sql, new InstructorMapper(), id);
		instructor.setActivities(getActivitiesInstructor(id));
		return instructor;
	}

	@Override
	public Object getElements() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Integer> getActivitiesInstructor(String id) {
		String sql = "SELECT idact FROM instruidas WHERE ssnumber = ?";
		return (List<Integer>) dataSource.queryForList(sql, Integer.class, id);
	}
	
	// TODO cambiamos el nombre?
	public void addActivity(String ssnum, int idact) {
		String sql = "INSERT INTO instruidas(idact, ssnumber) VALUES(? ,?)";
		dataSource.update(sql, idact, ssnum);
	}
	
	// TODO cambiamos el nombre?
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
	
}
