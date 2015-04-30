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
//TODO Poner los comentarios
	private JdbcTemplate dataSource;
	
	@Autowired
	private void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
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
	
	@Override
	public void addElement(Object element) {
		Client cl = (Client) element;
		String sql = "INSERT INTO Client(clientId, clientName, clientLastName, clientEmail) "
							+ "values(?, ?, ?, ?)";
		dataSource.update(sql, cl.getClientId(), cl.getClientName(), cl.getClientLastName(), cl.getClientEmail());
	}

	@Override
	public void deleteElement(Object element) {
		String id = (String) element;
		String sql = "DELETE FROM Client WHERE clientId=?";
		dataSource.update(sql, id);
	}

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

	@Override
	public Object getElement(Object identifier) {
		String id = (String) identifier;
		String sql = "SELECT * FROM Client WHERE clientId=?";
		return dataSource.queryForObject(sql, new ClientMapper(), id);
	}

	@Override
	public Object getElements() {
		//TODO Tiene que haber otra forma de hacer
		String sql = "SELECT * FROM Client";
		List<Client> list = dataSource.query(sql, new ClientMapper());
		Map<String, Client> map = new HashMap<String, Client>();
		for(Client cl: list) map.put(cl.getClientId(), cl);
		return map;
	}

}
