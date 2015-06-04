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

import classes.Status;

@Repository
public class daoStatus implements DaoInterface {

	private JdbcTemplate dataSource;
	
	public daoStatus() {
		super();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = new JdbcTemplate(dataSource);
	}
	
	/*
	 * RowMapper for the class Status
	 */
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
	
	/**
	 * Method to add an Status into the DB
	 * @see database.DaoInterface#addElement(java.lang.Object)
	 * @param element Status, class Status
	 */
	@Override
	public void addElement(Object element) {
		if(!Status.class.equals(element)) return;
		Status st = (Status) element;
		String sql = "INSERT INTO Status(inneridbooking, dateRevision, status, ssnumber)"
				+ " VALUES(?, ?, ?, ?);";
		dataSource.update(sql, st.getIDbooking(), st.getDateRevision(), st.getStatus(), st.getSsNumber());
	}

	/**
	 * Method to remove an Status from the DB
	 * @see database.DaoInterface#deleteElement(java.lang.Object)
	 * @param element Integer with the booking's identifier
	 */
	@Override
	public void deleteElement(Object element) {
		int id = (int) element;
		String sql = "DELETE FROM Status WHERE inneridbooking = ?;";
		dataSource.update(sql, id);
	}

	/**
	 * Method to update an Status in the DB
	 * @see database.DaoInterface#updateElement(java.lang.Object)
	 * @param element Status, class Status
	 */
	@Override
	public void updateElement(Object element) {
		if(!Status.class.equals(element)) return;
		Status st = (Status) element;
		String sql = "UPDATE Status SET inneridbooking = ?, "
				+ "dateRevision = ?, "
				+ "status = ?, "
				+ "ssnumber = ?;";
		dataSource.update(sql, st.getIDbooking(), st.getDateRevision(), st.getStatus(), st.getSsNumber());
	}

	/**
	 * Method to obtain an Status from the DB
	 * @see database.DaoInterface#getElement(java.lang.Object)
	 * @param identifier Integer with the booking's identifier
	 * @return a Status with all the field
	 */
	@Override
	public Object getElement(Object identifier) {
		int id = (int) identifier;
		String sql = "SELECT * FROM Status WHERE inneridbooking = ?;";
		List<Status> list = dataSource.query(sql, new StatusMapper(), id);
		if (list.size() == 0 || list.size() < 1) return null;
		else return list.get(0);
	}

	/**
	 * Method to obtain all the status from the DB
	 * @see database.DaoInterface#getElements()
	 * @return Map<Integer, Status>, key: booking's id, value: Status 
	 */
	@Override
	public Object getElements() {
		String sql = "SELECT * FROM Status;";
		List<Status> list = dataSource.query(sql, new StatusMapper());
		Map<Integer, Status> map = new HashMap<Integer, Status>();
		for(Status st: list) map.put(st.getIDbooking(), st);
		return map;
	}
	
	public String getStatus(int id) {
		String sql = "SELECT status FROM Status WHERE inneridbooking = ?;";
		List<String> list = dataSource.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet arg0, int arg1) throws SQLException {
				return arg0.getString(1);
			}
		}, id);
		if (list.size() == 0 || list.size() < 1) return null;
		else {
			return list.get(0);
		}
	}

}
