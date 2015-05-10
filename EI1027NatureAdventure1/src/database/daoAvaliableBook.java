package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import classes.AvaliableForBooking;

@Repository
public class daoAvaliableBook implements DaoInterface {

	private JdbcTemplate dataSource;
	
	@Autowired
	private void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	/*
	 * RowMapper for the class Instructor
	 */
	private final static class AvaliableMapper implements RowMapper<AvaliableForBooking> {
		@Override
		public AvaliableForBooking mapRow(ResultSet rs, int rowNum) throws SQLException {
			AvaliableForBooking a = new AvaliableForBooking();
			try {
				a.setIdAct(rs.getInt("idAct"));
				a.setSsNumber(rs.getString("ssnumber"));
				a.setDate(rs.getDate("date"));
				a.setSchedule(rs.getString("schedule"));
				a.setAvaliblePlaces(rs.getInt("avaliableplaces"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return a;
		}
		
	}
	
	@Override
	public void addElement(Object element) {
		AvaliableForBooking a = (AvaliableForBooking) element;
		String sql = "INSERT INTO avaliableforbook (idact, ssnumber, date, schedule, avaliableplaces) "
				+ "VALUES(?, ?, ?, ?, ?);";
		dataSource.update(sql, a.getIdAct(), a.getSsNumber(), a.getDate(), a.getSchedule(), a.getAvaliblePlaces());
	}

	@Override
	public void deleteElement(Object element) {
		AvaliableForBooking a = (AvaliableForBooking) element;
		String sql = "DELETE FROM avaliableforbook WHERE ssnumber = ?, date = ?, schedule = ?;";
		dataSource.update(sql, a.getSsNumber(), a.getDate(), a.getSchedule());
	}

	@Override
	public void updateElement(Object element) {
		AvaliableForBooking a = (AvaliableForBooking) element;
		String sql = "UPDATE avaliableforbook SET avaliableplaces = ? WHERE ssnumber = ?, date = ?, schedule = ?;";
		dataSource.update(sql, a.getAvaliblePlaces(), a.getSsNumber(), a.getDate(), a.getSchedule());
	}

	@Override
	public Object getElement(Object identifier) {
		// TODO Por implementar
		return null;
	}

	@Override
	public Object getElements() {
		// TODO Por ahora devuelve una lista
		String sql = "SELECT * FROM avaliableforbook;";
		List<AvaliableForBooking> list = dataSource.query(sql, new AvaliableMapper());
		return list;
	}
	
	public List<AvaliableForBooking> getElements(int idAct) {
		String sql = "SELECT * FROM avaliableforbook WHERE idact = ?";
		List<AvaliableForBooking> list = dataSource.query(sql, new AvaliableMapper(), idAct);
		return list;
	}
	
	public AvaliableForBooking getElement(String ssnum, Date date, String schedule) {
		String sql = "SELECT * FROM avaliableforbook WHERE ssnumber = ?, date = ?, schedule = ?;";
		return dataSource.queryForObject(sql, new AvaliableMapper(), ssnum, date, schedule);
	}

}