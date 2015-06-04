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

import classes.Token;

@Repository
public class daoSessionToken implements DaoInterface {
	private JdbcTemplate dataSource;

	public daoSessionToken() {
		super();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = new JdbcTemplate(dataSource);
	}

	/*
	 * RowMapper for the class Token
	 */
	private static final class TokenMapper implements RowMapper<Token> {
		public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
			Token tk = new Token();
			tk.setUser(rs.getString("username"));
			tk.setToken(rs.getString("token"));
			return tk;
		}
	}

	/**
	 * Method to add a token into the DB
	 * 
	 * @see database.DaoInterface#addElement(java.lang.Object)
	 * @param element
	 *            Token, class Token
	 */
	@Override
	public void addElement(Object element) {
		if (!Token.class.equals(element))
			return;
		Token tk = (Token) element;
		String sql = "INSERT INTO sessionTokens(username, token)"
				+ " VALUES(?, ?);";
		dataSource.update(sql, tk.getUser(), tk.getToken());
	}

	/**
	 * Method to remove a Token from the DB
	 * 
	 * @see database.DaoInterface#deleteElement(java.lang.Object)
	 * @param element
	 *            String with the token's user
	 */
	@Override
	public void deleteElement(Object element) {
		String user = (String) element;
		String sql = "DELETE FROM sessionTokens WHERE username = ?;";
		dataSource.update(sql, user);

	}

	/**
	 * Method to update a Token in the DB
	 * 
	 * @see database.DaoInterface#updateElement(java.lang.Object)
	 * @param element
	 *            Token
	 */
	@Override
	public void updateElement(Object element) {
		if (!Token.class.equals(element))
			return;
		Token tk = (Token) element;
		String sql = "UPDATE sessionTokens SET username = ?, " + "token = ?;";
		dataSource.update(sql, tk.getUser(), tk.getToken());

	}

	/**
	 * Method to obtain a Token from the DB
	 * 
	 * @see database.DaoInterface#getElement(java.lang.Object)
	 * @param identifier
	 *            String with the token's identifier
	 * @return a Token with all the field
	 */
	@Override
	public Object getElement(Object identifier) {
		String user = (String) identifier;
		String sql = "SELECT * FROM sessionTokens WHERE username = ?;";
		List<Token> list = dataSource.query(sql, new TokenMapper(), user);
		if (list.size() == 0 || list.size() < 1)
			return null;
		else
			return list.get(0);

	}

	/**
	 * Method to obtain all the Token from the DB
	 * 
	 * @see database.DaoInterface#getElements()
	 * @return Map<String, Token>, key: token's user, value: Token
	 */
	@Override
	public Object getElements() {
		String sql = "SELECT * FROM sessionTokens;";
		List<Token> list = dataSource.query(sql, new TokenMapper());
		Map<String, Token> map = new HashMap<String, Token>();
		for (Token tk : list)
			map.put(tk.getUser(), tk);
		return map;
	}

}
