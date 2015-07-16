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
public class daoUser {

	private JdbcTemplate dataSource;
	
	public daoUser() {
		super();
	}
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = new JdbcTemplate(datasource);
	}
	
	/**
	 * This class makes a User from the database outputs
	 */
	private final static class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			try {
				user.setUser(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setType(rs.getInt("type"));
				user.setLanguage(rs.getString("language"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		}
	}
	
	/**
	 * Method to add an User into the DB
	 * @param element class User
	 */
	public void addElement(User user) {
		String sql = "INSERT INTO login(id, password, name, type, language) VALUES(?, ?, ?, ?, ?);";
		dataSource.update(sql, user.getUser(), user.getPassword(), user.getName(), user.getType(), user.getLanguage());
	}

	/**
	 * Method to remove an User from the DB
	 * @param username String with the id
	 */
	public void deleteElement(String username) {
		String sql = "DELETE FROM login WHERE id = ?;";
		dataSource.update(sql, username);
	}

	/**
	 * Method to remove an User from the DB
	 * @param user class User
	 */
	public void deleteElement(User user) {
		String sql = "DELETE FROM login WHERE id = ?;";
		dataSource.update(sql, user.getUser());
	}
	
	/**
	 * Method to update an User in the DB, with password and type, better use <b>updateElementWithoutPassword</b>
	 * @param element Class User
	 */
	public void updateElement(User user) {
		String sql = "UPDATE login SET password = ?, name = ?, type = ?, language = ? WHERE id = ?;";
		dataSource.update(sql, user.getPassword(), user.getName(), user.getType(), user.getLanguage(), user.getUser());
	}

	/**
	 * Method to obtain an User from the DB
	 * @param identifier String with the id
	 * @return an User
	 */
	public User getElement(String user) {
		String sql = "SELECT * FROM login WHERE id = ?;";
		List<User> list = dataSource.query(sql, new UserMapper(), user);
		if (list.size() == 0 || list.size() > 1) return null;
		else return list.get(0);
	}

	/**
	 * Method to obtain all the users in the DB
	 * @return Map<Integer, User>, key: id, value: User
	 */
	public Map<String, User> getElements() {
		Map<String, User> map = new HashMap<String, User>();
		String sql = "SELECT * FROM login;";
		List<User> list = dataSource.query(sql, new UserMapper());
		for (User u : list) map.put(u.getUser(), u);
		return map;
	}
	
	/**
	 * Method to update an User, this does not change the password or type. It is more safe than updateElement(Obj element)
	 * @param element An User class
	 */
	public void updateElementWithoutPassword(Object element) {
		User u = (User) element;
		String sql = "UPDATE login SET name = ?, language = ? WHERE id = ?";
		dataSource.update(sql, u.getName(), u.getLanguage(), u.getUser());
	}

	public User getUserGivenAToken(String token){
		String sql ="SELECT u.* FROM sessiontokens AS st JOIN user AS u ON st.username = u.name WHERE st.token = ?;";
		return dataSource.queryForObject(sql, new UserMapper(), token);
	}
	
	/** Method to obtain the number of users registered in the system
	 * @return The number of users
	 */
	public Integer getUserCount(){
		String sql = "SELECT COUNT(*) FROM login;";
		return dataSource.queryForObject(sql, Integer.class);
	}
}
