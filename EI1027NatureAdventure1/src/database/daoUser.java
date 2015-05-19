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

import classes.User;

@Repository
public class daoUser implements DaoInterface {

	private JdbcTemplate dataSource;
	
	public daoUser() {
		super();
	}
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	/*
	 * RowMapper for the class User
	 */
	private final static class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			try {
				user.setUser(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getInt("type"));
				user.setLanguage(rs.getString("language"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		}
		
	}
	
	@Override
	public void addElement(Object element) {
		User u = (User) element;
		String sql = "INSERT INTO login(id,password,type,language) VALUES(?, ?, ?, ?);";
		dataSource.update(sql, u.getUser(), u.getPassword(), u.getType(), u.getLanguage());
	}

	@Override
	public void deleteElement(Object element) {
		String user = "";
		if (element instanceof String)
			user = (String) element;
		else if (element instanceof User) {
			User usuario = (User) element;
			user = usuario.getUser();
		}
		String sql = "DELETE FROM login WHERE id = ?;";
		dataSource.update(sql, user);

	}

	@Override
	public void updateElement(Object element) {
		User u = (User) element;
		String sql = "UPDATE login SET password = ?, type = ?, language = ? WHERE id = ?;";
		dataSource.update(sql, u.getPassword(), u.getType(), u.getLanguage(), u.getUser());

	}

	@Override
	public Object getElement(Object identifier) {
		String user = (String) identifier;
		String sql = "SELECT * FROM login WHERE id = ?;";
		List<User> list = dataSource.query(sql, new UserMapper(), user);
		if (list.size() == 0 || list.size() > 1) return null;
		else return list.get(0);
	}

	@Override
	public Object getElements() {
		Map<String, User> map = new HashMap<String, User>();
		String sql = "SELECT * FROM login;";
		List<User> list = dataSource.query(sql, new UserMapper());
		for (User u : list) map.put(u.getUser(), u);
		return map;
	}

}
