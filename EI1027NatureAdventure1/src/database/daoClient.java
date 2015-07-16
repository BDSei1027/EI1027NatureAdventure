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

import classes.Client;

@Repository
public class daoClient {
	
	private JdbcTemplate dataSource;
	
	public daoClient() {
		super();
	}
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	/**
	 * This class makes a Client from the database outputs
	 */
	private static final class ClientMapper implements RowMapper<Client> {
		public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
			Client cl = new Client();
			cl.setClientId(rs.getString("clientId"));
			cl.setClientName(rs.getString("clientName"));
			cl.setClientLastName(rs.getString("clientLastName"));
			cl.setClientEmail(rs.getString("clientEmail"));
			return cl;
		}
	}
	
	/**
	 * Method to add an Client into the DB
	 * @param element Client, class Client
	 */
	public void addElement(Client client) {
		String sql = "INSERT INTO Client(clientId, clientName, clientLastName, clientEmail) "
							+ "values(?, ?, ?, ?);";
		dataSource.update(sql, client.getClientId(), client.getClientName(), client.getClientLastName(), client.getClientEmail());
	}

	/**
	 * Method to remove an Client from the DB
	 * @param element String with the identifier
	 */
	public void deleteElement(String id) {
		String sql = "DELETE FROM Client WHERE clientId=?;";
		dataSource.update(sql, id);
	}

	/**
	 * Method to update an Client in the DB
	 * @param element Client, class Client
	 */
	public void updateElement(Client client) {
		String sql = "UPDATE Client " + "SET "
					+ "clientId = ?," 
					+ "clientName = ?," 
					+ "clientLastName = ?,"
					+ "clientEmail = ?" 
					+ " WHERE clientId = ?;";
		dataSource.update(sql, client.getClientId(), client.getClientName(), client.getClientLastName(), client.getClientEmail(), client.getClientId());
	}

	/**
	 * Method to obtain an Client from the DB
	 * @param identifier String with the identifier
	 * @return a Client with all the field
	 */
	public Client getElement(String id) {
		String sql = "SELECT * FROM Client WHERE clientId=?;";
		List<Client> list = dataSource.query(sql, new ClientMapper(), id);
		if (list.size() == 0 || list.size() < 1) return null;
		else return list.get(0);
	}

	/**
	 * Method to obtain all the clients from the DB
	 * @return Map<String, Client>, key: id, value: Client 
	 */
	public Map<String, Client> getElements() {
		String sql = "SELECT * FROM Client;";
		List<Client> list = dataSource.query(sql, new ClientMapper());
		Map<String, Client> map = new HashMap<String, Client>();
		for(Client cl: list) {
			cl.setNumberBooking(getNumberBookings(cl.getClientId()));
			map.put(cl.getClientId(), cl);
		}
		return map;
	}
	
	/**
	 * Method to obtain the number of bookings that this client made.
	 * @param id Client's identifier
	 * @return The number of bookings made by this client
	 */
	public int getNumberBookings(String id) {
		String sql = "SELECT COUNT(*) FROM booking WHERE clientId = ?;";
		return dataSource.queryForObject(sql, Integer.class, id);
	}

	
	/** Method to obtain the name of the client given a email
	 * @param email
	 * @return the name
	 */
	public String getClientId(String email){
		String sql = "SELECT clientid FROM client WHERE clientemail = ?;";
		return dataSource.queryForObject(sql, String.class, email);
	}
}
