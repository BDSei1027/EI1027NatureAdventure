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

import classes.Status;

@Repository
public class daoStatus implements DaoInterface {
//TODO Poner los comentarios
	private JdbcTemplate dataSource;
	
	@Autowired
	private void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	private static final class StatusMapper implements RowMapper<Status> {
		public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
			Status st = new Status();
			st.setIDbooking(rs.getInt("inneridbooking"));
			st.setSsNumber(rs.getString("ssnumber"));
			st.setStatus(rs.getString("status"));
			st.setDateRevision(rs.getDate("dateRevision"));
			return st;
		}
	}
	
	@Override
	public void addElement(Object element) {
		Status st = (Status) element;
		String sql = "INSERT INTO Status(inneridbooking, dateRevision, status, ssnumber)"
				+ " VALUES(?, ?, ?, ?)";
		dataSource.update(sql, st.getIDbooking(), st.getDateRevision(), st.getStatus(), st.getSsNumber());
	}

	@Override
	public void deleteElement(Object element) {
		int id = (int) element;
		String sql = "DELETE FROM Status WHERE inneridbooking = ?";
		dataSource.update(sql, id);
	}

	@Override
	public void updateElement(Object element) {
		Status st = (Status) element;
		String sql = "UPDATE Status SET inneridbooking = ?, "
				+ "dateRevision = ?, "
				+ "status = ?, "
				+ "ssnumber = ?";
		dataSource.update(sql, st.getIDbooking(), st.getDateRevision(), st.getStatus(), st.getSsNumber());
	}

	@Override
	public Object getElement(Object identifier) {
		int id = (int) identifier;
		String sql = "SELECT * FROM Status WHERE inneridbooking = ?";
		return dataSource.queryForObject(sql, new StatusMapper(), id);
	}

	@Override
	public Object getElements() {
		// TODO Tiene que haber otra forma de hacerlo
		String sql = "SELECT * FROM Status";
		List<Status> list = dataSource.query(sql, new StatusMapper());
		Map<Integer, Status> map = new HashMap<Integer, Status>();
		for(Status st: list) map.put(st.getIDbooking(), st);
		return map;
	}

}
