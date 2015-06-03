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

import classes.Booking;
import classes.Status;

@Repository
public class daoBooking implements DaoInterface {

	private JdbcTemplate dataSource;
	private daoStatus daoStatus;
	
	public daoBooking() {
		super();
	}
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	public void setDaoStatus(daoStatus daoStatus) {
		this.daoStatus = daoStatus;
	}
	
	/*
	 * RowMapper for the class Booking
	 */
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
	
	/**
	 * Method to add a Booking into the DB
	 * @see database.DaoInterface#addElement(java.lang.Object)
	 * @param element Booking, class Booking
	 * TODO Comprobar que lo que le pasamos es un Bookin
	 */
	@Override
	public void addElement(Object element) {
		Booking book = (Booking) element;
		String sql = "INSERT INTO Booking(inneridbooking, groupSize, dateActivity, dateCreation, clientId, price, idAct, information, idBooking) "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		dataSource.update(sql, book.getInnerIdBooking(), book.getGroupSize(), book.getDateActivity(), book.getDateCreation(), book.getClientId(), book.getPrice(), book.getIdAct(), book.getInformation(), book.getIdBooking());
		Status status = new Status();
		status.setIDbooking(book.getInnerIdBooking());
		daoStatus.addElement(status);

	}

	/**
	 * Method to remove a Booking from the DB
	 * @see database.DaoInterface#deleteElement(java.lang.Object)
	 * @param element Integer with the identifier
	 */
	@Override
	public void deleteElement(Object element) {
		int id = (int) element;
		// Si se borra la reserva el status tambien
		daoStatus.deleteElement(id);
		String sql = "DELETE FROM booking WHERE inneridbooking = ?;";
		dataSource.update(sql, id);
		
		
		
	}

	/**
	 * Method to update a Booking in the DB
	 * @see database.DaoInterface#updateElement(java.lang.Object)
	 * @param element Booking, class Booking
	 * TODO Comprobar que es de la clase Booking
	 */
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
				+ " WHERE innerIdBooking = ?;";
		dataSource.update(sql, book.getInnerIdBooking(), book.getGroupSize(), book.getDateActivity(), book.getDateCreation(), book.getClientId(), book.getPrice(),book.getIdAct(), book.getInformation(), book.getIdBooking());
	}

	/**
	 * Method to obtain a Booking from the DB
	 * @see database.DaoInterface#getElement(java.lang.Object)
	 * @param identifier Intenger with the identifier
	 * @return a Booking with all the field
	 */
	@Override
	public Object getElement(Object identifier) {
		int id = (int) identifier;
		String sql = "SELECT * FROM booking WHERE inneridbooking = ?;";
		List<Booking> list = dataSource.query(sql, new BookingMapper(), id);
		if (list.size() == 0 || list.size() < 1) return null;
		else {
			list.get(0).setStatus(daoStatus.getStatus(id));
			return list.get(0);
		}
	}

	/**
	 * Method to obtain all the bookings from the DB
	 * @see database.DaoInterface#getElements()
	 * @return Map<Integer, Booking>, key: id, value: Booking 
	 */
	@Override
	public Object getElements() {
		String sql = "SELECT * FROM booking;";
		Map<Integer, Booking> map = new HashMap<Integer, Booking>();
		List<Booking> list = dataSource.query(sql, new BookingMapper());
		for (Booking book: list) {
			book.setStatus(daoStatus.getStatus(book.getInnerIdBooking()));
			map.put(book.getInnerIdBooking(), book);
		}
		return map;
	}
	
	/**
	 * Method to obtain the maximum inner Identifier of the booking
	 * @return Integer with the maximum ID
	 */
	public Integer getMaxInnerID() {
		String sql = "SELECT MAX(inneridbooking) FROM booking;";
		return dataSource.queryForObject(sql, Integer.class);
	}
	
	/**
	 * Method to obtain the maximum identifier of the activity
	 * @return Integer with the maximum ID
	 */
	public Integer getMaxActiveID() {
		String sql = "SELECT MAX(idbooking) FROM booking;";
		return dataSource.queryForObject(sql, Integer.class);
	}

}
