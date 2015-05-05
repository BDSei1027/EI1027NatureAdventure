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

import classes.Client;

@Repository
public class daoClient implements DaoInterface {
	
	private JdbcTemplate dataSource;
	
	@Autowired
	private void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	/*
	 * RowMapper for the class Client
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
	 * @see database.DaoInterface#addElement(java.lang.Object)
	 * @param element Client, class Client
	 * TODO Comprobar que lo que le pasamos es un Client
	 */
	@Override
	public void addElement(Object element) {
		Client cl = (Client) element;
		String sql = "INSERT INTO Client(clientId, clientName, clientLastName, clientEmail) "
							+ "values(?, ?, ?, ?)";
		dataSource.update(sql, cl.getClientId(), cl.getClientName(), cl.getClientLastName(), cl.getClientEmail());
	}

	/**
	 * Method to remove an Client from the DB
	 * @see database.DaoInterface#deleteElement(java.lang.Object)
	 * @param element String with the identifier
	 */
	@Override
	public void deleteElement(Object element) {
		String id = (String) element;
		String sql = "DELETE FROM Client WHERE clientId=?";
		dataSource.update(sql, id);
	}

	/**
	 * Method to update an Client in the DB
	 * @see database.DaoInterface#updateElement(java.lang.Object)
	 * @param element Client, class Client
	 * TODO Comprobar que es de la clase Client
	 */
	@Override
	public void updateElement(Object element) {
		Client cl = (Client) element;
		String sql = "UPDATE Client " + "SET "
					+ "clientId = ?," 
					+ "clientName = ?," 
					+ "clientLastName = ?,"
					+ "clientEmail = ?" 
					+ " WHERE clientId = ?";
		dataSource.update(sql, cl.getClientId(), cl.getClientName(), cl.getClientLastName(), cl.getClientEmail(), cl.getClientId());
	}

	/**
	 * Method to obtain an Client from the DB
	 * @see database.DaoInterface#getElement(java.lang.Object)
	 * @param identifier String with the identifier
	 * @return a Client with all the field
	 */
	@Override
	public Object getElement(Object identifier) {
		String id = (String) identifier;
		String sql = "SELECT * FROM Client WHERE clientId=?";
		return dataSource.queryForObject(sql, new ClientMapper(), id);
	}

	/**
	 * Method to obtain all the clients from the DB
	 * @see database.DaoInterface#getElements()
	 * @return Map<String, Client>, key: id, value: Client 
	 */
	@Override
	public Object getElements() {
		String sql = "SELECT * FROM Client";
		List<Client> list = dataSource.query(sql, new ClientMapper());
		Map<String, Client> map = new HashMap<String, Client>();
		for(Client cl: list) map.put(cl.getClientId(), cl);
		return map;
	}

}
