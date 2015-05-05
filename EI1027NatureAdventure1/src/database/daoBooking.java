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

import classes.Booking;
import classes.Status;

@Repository
public class daoBooking implements DaoInterface {

	private JdbcTemplate dataSource;
	private daoStatus daoStatus;
	
	@Autowired
	private void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	@Autowired
	private void setDaoStatus(DaoInterface daoStatus) {
		this.daoStatus = (daoStatus) daoStatus;
	}
	
	private final static class BookingMapper implements RowMapper<Booking> {
		public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
			Booking booking = new Booking();
			try {
				booking.setInnerIdBooking(rs.getInt("inneridbooking"));
				booking.setGroupSize(rs.getInt("groupSize"));
				booking.setDateActivity(rs.getDate("dateactivity"));
				booking.setDateCreation(rs.getDate("datecreation"));
				booking.setClientId(rs.getString("clientid"));
				booking.setPrice(rs.getFloat("price"));
				booking.setIdAct(rs.getInt("idact"));
				booking.setInformation(rs.getString("information"));
				booking.setIdBooking(rs.getInt("idbooking"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return booking;
		}
	}
	
	@Override
	public void addElement(Object element) {
		Booking book = (Booking) element;
		String sql = "INSERT INTO Booking(inneridbooking, groupSize, dateActivity, dateCreation, clientId, price, idAct, information, idBooking) "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		dataSource.update(sql, book.getInnerIdBooking(), book.getGroupSize(), book.getDateActivity(), book.getDateCreation(), book.getClientId(), book.getPrice(), book.getIdAct(), book.getInformation(), book.getIdBooking());
		Status status = new Status();
		status.setIDbooking(book.getInnerIdBooking());
		daoStatus.addElement(status);

	}

	@Override
	public void deleteElement(Object element) {
		int id = (int) element;
		String sql = "DELETE FROM booking WHERE inneridbooking = ?";
		dataSource.update(sql, id);
		
		// TODO Si se borra la reserva el status tambien
		// daoStatus.deleteElement(id);
	}

	@Override
	public void updateElement(Object element) {
		Booking book = (Booking) element;
		String sql = "UPDATE Booking " + "SET "
				+ "innerIdBooking = ?," 
				+ "groupSize = ?," 
				+ "dateActivity = ?,"
				+ "dateCreation = ?," 
				+ "clientId = ?," 
				+ "price = ?,"
				+ "idAct = ?," 
				+ "information = ?," 
				+ "idBooking = ?" 
				+ " WHERE innerIdBooking = ?";
		dataSource.update(sql, book.getInnerIdBooking(), book.getGroupSize(), book.getDateActivity(), book.getDateCreation(), book.getClientId(), book.getPrice(),book.getIdAct(), book.getInformation(), book.getIdBooking());
	}

	@Override
	public Object getElement(Object identifier) {
		int id = (int) identifier;
		String sql = "SELECT * FROM booking WHERE inneridbooking = ?";
		return dataSource.queryForObject(sql, new BookingMapper(), id);
	}

	@Override
	public Object getElements() {
		String sql = "SELECT * FROM booking";
		Map<Integer, Booking> map = new HashMap<Integer, Booking>();
		List<Booking> list = dataSource.query(sql, new BookingMapper());
		for (Booking book: list) map.put(book.getInnerIdBooking(), book);
		return map;
	}
	
	public Integer getMaxInnerID() {
		String sql = "SELECT MAX(inneridbooking) FROM booking";
		return dataSource.queryForObject(sql, Integer.class);
	}
	
	public Integer getMaxActiveID() {
		String sql = "SELECT MAX(idbooking) FROM booking";
		return dataSource.queryForObject(sql, Integer.class);
	}

}
