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
public class daoSessionToken {
	private JdbcTemplate dataSource;

	public daoSessionToken() {
		super();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = new JdbcTemplate(dataSource);
	}

	/**
	 * This class makes a Token from the database outputs
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
	 * @param element
	 *            Token, class Token
	 */
	public void addElement(Token tk) {
		String sql = "INSERT INTO sessionTokens(username, token)"
				+ " VALUES(?, ?);";
		dataSource.update(sql, tk.getUser(), tk.getToken());
	}

	/**
	 * Method to remove a Token from the DB
	 * @param element
	 *            String with the token's user
	 */
	public void deleteElement(String user) {
		String sql = "DELETE FROM sessionTokens WHERE username = ?;";
		dataSource.update(sql, user);

	}

	/**
	 * Method to update a Token in the DB
	 * @param element
	 *            Token
	 */
	public void updateElement(Token tk) {
		String sql = "UPDATE sessionTokens SET token = ? WHERE username = ?;";
		dataSource.update(sql, tk.getToken(), tk.getUser());

	}

	/**
	 * Method to obtain a Token from the DB
	 * @param identifier
	 *            String with the token's identifier
	 * @return a Token with all the field
	 */
	public Token getElement(String user) {
		String sql = "SELECT * FROM sessionTokens WHERE username = ?;";
		List<Token> list = dataSource.query(sql, new TokenMapper(), user);
		if (list.size() == 0 || list.size() < 1)
			return null;
		else
			return list.get(0);

	}

	/**
	 * Method to obtain all the Token from the DB
	 * @return Map<String, Token>, key: token's user, value: Token
	 */
	public Map<String, Token> getElements() {
		String sql = "SELECT * FROM sessionTokens;";
		List<Token> list = dataSource.query(sql, new TokenMapper());
		Map<String, Token> map = new HashMap<String, Token>();
		for (Token tk : list)
			map.put(tk.getUser(), tk);
		return map;
	}

}
