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
import classes.BookingActivity;
import classes.Status;

@Repository
public class daoBooking {

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
	
	/**
	 * This class makes a Booking from the database outputs
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
	 * This class makes a BookingActivity from the database outputs
	 */
	private final static class BookingActivityMapper implements RowMapper<BookingActivity> {
		public BookingActivity mapRow(ResultSet rs, int rowNum) throws SQLException {
			BookingActivity booking = new BookingActivity();
			try {
				booking.setInnerIdBooking(rs.getInt("inneridbooking"));
				booking.setIdBooking(rs.getInt("idbooking"));
				booking.setNameActivity(rs.getString("name"));
				booking.setDateActivity(rs.getDate("dateactivity"));
				booking.setSchedule(rs.getString("schedule"));
				booking.setLevel(rs.getInt("leveldif"));
				booking.setGroupSize(rs.getInt("groupsize"));
				booking.setPrice(rs.getFloat("price"));
				booking.setPlace(rs.getString("place"));
				booking.setDateRevision(rs.getDate("daterevision"));
				booking.setStatus(rs.getString("status"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return booking;
		}
	}
	
	/**
	 * Method to add a Booking into the DB, also add the status in its table
	 * @param element Booking, class Booking
	 */
	public void addElement(Booking booking) {
		String sql = "INSERT INTO Booking(inneridbooking, groupSize, dateActivity, dateCreation, clientId, price, idAct, information, idBooking) "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		dataSource.update(sql, booking.getInnerIdBooking(), booking.getGroupSize(), booking.getDateActivity(), booking.getDateCreation(), booking.getClientId(), booking.getPrice(), booking.getIdAct(), booking.getInformation(), booking.getIdBooking());
		Status status = new Status();
		status.setIDbooking(booking.getInnerIdBooking());
		status.setStatus("pending");
		daoStatus.addElement(status);

	}

	/**
	 * Method to remove a Booking from the DB
	 * @param element Integer with the identifier
	 */
	public void deleteElement(Integer id) {
		// Si se borra la reserva el status tambien
		daoStatus.deleteElement(id);
		String sql = "DELETE FROM booking WHERE inneridbooking = ?;";
		dataSource.update(sql, id);
	}

	/**
	 * Method to update a Booking in the DB
	 * @param element Booking, class Booking
	 */
	public void updateElement(Booking booking) {
		String sql = "UPDATE Booking " + "SET "
				+ "groupSize = ?," 
				+ "dateActivity = ?,"
				+ "dateCreation = ?," 
				+ "clientId = ?," 
				+ "price = ?,"
				+ "idAct = ?," 
				+ "information = ?," 
				+ "idBooking = ?" 
				+ " WHERE innerIdBooking = ?;";
		dataSource.update(sql, booking.getGroupSize(), booking.getDateActivity(), booking.getDateCreation(), booking.getClientId(), booking.getPrice(),booking.getIdAct(), booking.getInformation(), booking.getIdBooking(), booking.getInnerIdBooking());
	}

	/**
	 * Method to obtain a Booking from the DB
	 * @param identifier Intenger with the identifier
	 * @return a Booking with all the field
	 */
	public Booking getElement(int id) {
		String sql = "SELECT inneridbooking, groupSize, dateActivity, dateCreation, clientId, price, idAct, information, idBooking"
				+ " FROM booking "
				+ "WHERE inneridbooking = ?;";
		List<Booking> list = dataSource.query(sql, new BookingMapper(), id);
		if (list.size() == 0 || list.size() < 1) return null;
		else {
			list.get(0).setStatus(daoStatus.getStatus(id));
			return list.get(0);
		}
	}

	/**
	 * Method to obtain all the bookings from the DB
	 * @return Map<Integer, Booking>, key: id, value: Booking 
	 */
	public Map<Integer, Booking> getElements() {
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
	 * Method to retrieve the necesary information for make the BookingActivties
	 * @return Map<Integer,BookingActivity>
	 */
	public Map<Integer, BookingActivity> getElementsBookingActivityInstructor(String ssn) {
		String sql = "SELECT book.inneridbooking, book.idbooking, act.name, book.dateactivity, act.schedule, act.leveldif, book.price, book.groupsize, act.place, st.daterevision, st.status "
				+ "FROM booking AS book JOIN status AS st USING(inneridbooking) JOIN activity AS act USING(idact) "
				+ "WHERE st.ssnumber = ?;";
		Map<Integer, BookingActivity> map = new HashMap<Integer, BookingActivity>();
		List<BookingActivity> list = dataSource.query(sql, new BookingActivityMapper(), ssn);
		for (BookingActivity book: list) {
			map.put(book.getIdBooking(), book);
		}
		return map;
	}
	
	/**
	 * Method to retrieve the necesary information for make the BookingActivties
	 * @return Map<Integer,BookingActivity>
	 */
	public Map<Integer, BookingActivity> getElementsBookingActivityClient(String id) {
		String sql = "SELECT book.inneridbooking, book.idbooking, act.name, book.dateactivity, act.schedule, act.leveldif, book.price, book.groupsize, act.place, st.daterevision, st.status "
				+ "FROM booking AS book JOIN status AS st USING(inneridbooking) JOIN activity AS act USING(idact) "
				+ "WHERE book.clientid = ?;";
		Map<Integer, BookingActivity> map = new HashMap<Integer, BookingActivity>();
		List<BookingActivity> list = dataSource.query(sql, new BookingActivityMapper(), id);
		for (BookingActivity book: list) {
			map.put(book.getIdBooking(), book);
		}
		return map;
	}
	
	/** Method to retrieve all the active bookingactivities with instructor
	 * @param ssNumber
	 * @return A map with the BookingActivity
	 */
	public Map<Integer, BookingActivity> getActiveBookingsWithInstructor(String ssNumber){
		String sql = "SELECT book.inneridbooking, book.idbooking, act.name, book.dateactivity, act.schedule, act.leveldif, book.price, book.groupsize, act.place, st.daterevision, st.status "
				+ "FROM booking AS book JOIN status AS st USING(inneridbooking) JOIN activity AS act USING(idact) "
				+ "WHERE st.ssnumber = ? AND book.dateactivity - CURRENT_DATE >= '0 days';";
		Map<Integer, BookingActivity> map = new HashMap<Integer, BookingActivity>();
		List<BookingActivity> list = dataSource.query(sql, new BookingActivityMapper(), ssNumber);
		for (BookingActivity book: list) {
			map.put(book.getIdBooking(), book);
		}
		return map;
	}
	
	/** Method to retrieve all the past bookingactivities with instructor
	 * @param ssNumber
	 * @return A map with the BookingActivity
	 */
	public Map<Integer, BookingActivity> getPastBookingsWithInstructor(String ssNumber){
		String sql = "SELECT book.inneridbooking, book.idbooking, act.name, book.dateactivity, act.schedule, act.leveldif, book.price, book.groupsize, act.place, st.daterevision, st.status "
				+ "FROM booking AS book JOIN status AS st USING(inneridbooking) JOIN activity AS act USING(idact) "
				+ "WHERE st.ssnumber = ? AND book.dateactivity - CURRENT_DATE < '0 days';";
		Map<Integer, BookingActivity> map = new HashMap<Integer, BookingActivity>();
		List<BookingActivity> list = dataSource.query(sql, new BookingActivityMapper(), ssNumber);
		for (BookingActivity book: list) {
			map.put(book.getIdBooking(), book);
		}
		return map;
	}
	
	/** Method to obtain the active bookings with client
	 * @param idClient
	 * @return A map with the BookingActivity
	 */
	public Map<Integer, BookingActivity> getActiveBookingsWithClient(String idClient) {
		String sql = "SELECT book.inneridbooking, book.idbooking, act.name, book.dateactivity, act.schedule, act.leveldif, book.price, book.groupsize, act.place, st.daterevision, st.status "
				+ "FROM booking AS book JOIN status AS st USING(inneridbooking) JOIN activity AS act USING(idact) "
				+ "WHERE book.clientid = ? AND book.dateactivity - CURRENT_DATE >= '0 days';";
		Map<Integer, BookingActivity> map = new HashMap<Integer, BookingActivity>();
		List<BookingActivity> list = dataSource.query(sql, new BookingActivityMapper(), idClient);
		for (BookingActivity book: list) {
			map.put(book.getIdBooking(), book);
		}
		return map;
	}

	/** Method to obtain all the bookings done with client
	 * @param idClient
	 * @return A map with the BookingActivity
	 */
	public Map<Integer, BookingActivity> getPastBookingsWithClient(String idClient) {
		String sql = "SELECT book.inneridbooking, book.idbooking, act.name, book.dateactivity, act.schedule, act.leveldif, book.price, book.groupsize, act.place, st.daterevision, st.status "
				+ "FROM booking AS book JOIN status AS st USING(inneridbooking) JOIN activity AS act USING(idact) "
				+ "WHERE book.clientid = ? AND book.dateactivity - CURRENT_DATE < '0 days';";
		Map<Integer, BookingActivity> map = new HashMap<Integer, BookingActivity>();
		List<BookingActivity> list = dataSource.query(sql, new BookingActivityMapper(), idClient);
		for (BookingActivity book: list) {
			map.put(book.getIdBooking(), book);
		}
		return map;
	}
	
	/** Method to obtain the number of pending bookings
	 * @return the number of pending bookings
	 */
	public Integer getPendingBookingsCount(){
		String sql = "SELECT COUNT(*) FROM status WHERE status LIKE 'pending';";
		return dataSource.queryForObject(sql, Integer.class);
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

	/** Method to obtain all the active bookings of the system
	 * @return A map with the Booking
	 */
	public Map<Integer, Booking> getActiveBookings(){
		String sql = "SELECT b.* FROM status AS s JOIN booking AS b USING(inneridbooking) WHERE status = 'accepted';";
		
		Map<Integer, Booking> map = new HashMap<Integer, Booking>();
		List<Booking> list = dataSource.query(sql, new BookingMapper());
		for (Booking book: list) {
			book.setStatus(daoStatus.getStatus(book.getInnerIdBooking()));
			map.put(book.getInnerIdBooking(), book);
		}
		return map;
	}
	
	/** Method to obtain all the pending bookings of the system
	 * @return A map with the Booking
	 */
	public Map<Integer, Booking> getPendingBookings(){
		String sql = "SELECT b.* FROM status AS s JOIN booking AS b USING(inneridbooking) WHERE status = 'pending';";
		
		Map<Integer, Booking> map = new HashMap<Integer, Booking>();
		List<Booking> list = dataSource.query(sql, new BookingMapper());
		for (Booking book: list) {
			book.setStatus(daoStatus.getStatus(book.getInnerIdBooking()));
			map.put(book.getInnerIdBooking(), book);
		}
		return map;
	}
	
	/** Method to obtain the declined bookings
	 * @return A map with the booking
	 */
	public Map<Integer, Booking> getDeclinedBookings(){
		String sql = "SELECT b.* FROM status AS s JOIN booking AS b USING(inneridbooking) WHERE status = 'declined';";
		
		Map<Integer, Booking> map = new HashMap<Integer, Booking>();
		List<Booking> list = dataSource.query(sql, new BookingMapper());
		for (Booking book: list) {
			book.setStatus(daoStatus.getStatus(book.getInnerIdBooking()));
			map.put(book.getInnerIdBooking(), book);
		}
		return map;
	}
	
}
