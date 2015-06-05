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
	 * @see database.DaoInterface#addElement(java.lang.Object)
	 * @param element class User
	 */
	@Override
	public void addElement(Object element) {
		if(!(element instanceof User)) return;
		User u = (User) element;
		String sql = "INSERT INTO login(id, password, name, type, language) VALUES(?, ?, ?, ?, ?);";
		dataSource.update(sql, u.getUser(), u.getPassword(), u.getName(), u.getType(), u.getLanguage());
	}

	/**
	 * Method to remove an User from the DB
	 * @see database.DaoInterface#deleteElement(java.lang.Object)
	 * @param element String with the id or class User
	 */
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

	/**
	 * Method to update an User in the DB, with password and type, better use <b>updateElementWithoutPassword</b>
	 * @see database.DaoInterface#updateElement(java.lang.Object)
	 * @param element Class User
	 */
	@Override
	public void updateElement(Object element) {
		if(!(element instanceof User)) return;
		User u = (User) element;
		String sql = "UPDATE login SET password = ?, name = ?, type = ?, language = ? WHERE id = ?;";
		dataSource.update(sql, u.getPassword(), u.getName(), u.getType(), u.getLanguage(), u.getUser());

	}

	/**
	 * Method to obtain an User from the DB
	 * @see database.DaoInterface#getElement(java.lang.Object)
	 * @param identifier String with the id
	 * @return an User
	 */
	@Override
	public Object getElement(Object identifier) {
		String user = (String) identifier;
		String sql = "SELECT * FROM login WHERE id = ?;";
		List<User> list = dataSource.query(sql, new UserMapper(), user);
		if (list.size() == 0 || list.size() > 1) return null;
		else return list.get(0);
	}

	/**
	 * Method to obtain all the users in the DB
	 * @see database.DaoInterface#getElements()
	 * @return Map<Integer, User>, key: id, value: User
	 */
	@Override
	public Object getElements() {
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

}
