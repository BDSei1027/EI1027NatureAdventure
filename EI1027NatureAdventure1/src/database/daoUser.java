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

import classes.User;

@Repository
public class daoUser implements DaoInterface {

	private JdbcTemplate dataSource;
	
	@Autowired
	private void setDataSource(DataSource datasource) {
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
				user.setUser(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getString("type"));
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
		String sql = "INSERT INTO User(user,password,type, language) VALUES(?, ?, ?);";
		dataSource.update(sql, u.getUser(), u.getPassword(), u.getType());
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
		String sql = "DELETE FROM User WHERE user = ?;";
		dataSource.update(sql, user);

	}

	@Override
	public void updateElement(Object element) {
		User u = (User) element;
		String sql = "UPDATE User SET password = ?, type = ?, language = ? WHERE user = ?;";
		dataSource.update(sql, u.getPassword(), u.getType(), u.getUser());

	}

	@Override
	public Object getElement(Object identifier) {
		String user = (String) identifier;
		String sql = "SELECT * FROM user WHERE user = ?;";
		return dataSource.queryForObject(sql, new UserMapper(), user);
	}

	@Override
	public Object getElements() {
		Map<String, User> map = new HashMap<String, User>();
		String sql = "SELECT * FROM user;";
		List<User> list = dataSource.query(sql, new UserMapper());
		for (User u : list) map.put(u.getUser(), u);
		return map;
	}

}
