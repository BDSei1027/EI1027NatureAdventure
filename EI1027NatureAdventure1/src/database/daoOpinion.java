package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import classes.Opinion;

@Repository
public class daoOpinion {
	
	private JdbcTemplate dataSource;
	
	public daoOpinion() {
		super();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = new JdbcTemplate(dataSource);
	}
	
	/**
	 * This class makes a opinion from the database outputs
	 */
	private static final class OpinionMapper implements RowMapper<Opinion> {
		public Opinion mapRow(ResultSet rs, int rowNum) throws SQLException {
			Opinion op = new Opinion();
			try {
				op.setOpinionId(rs.getInt("opinionid"));
				op.setAuthor(rs.getString("author"));
				op.setDate(rs.getDate("date"));
				op.setIdAct(rs.getInt("idact"));
				op.setOpinion(rs.getString("opinion"));
				op.setScore(rs.getInt("score"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return op;
		}
	}
	
	public void addElement(Opinion opinion) {
		String sql = "INSERT INTO opinion(opinionid, author, date, idact, opinion, score) "
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		dataSource.update(sql, opinion.getOpinionId(), opinion.getAuthor(), opinion.getDate(), opinion.getIdAct(), opinion.getOpinion(), opinion.getScore());
	}

	public void deleteElement(int opinionid) {
		String sql = "DELETE FROM opinion WHERE opinionid = ?;";
		dataSource.update(sql, opinionid);
	}
	
	public void deleteElementFromActivity(int idAct) {
		String sql = "DELETE FROM opinion WHERE idact = ?;";
		dataSource.update(sql, idAct);
	}
	
	public void updateElement(Opinion opinion) {
		String sql = "UPDATE opinion SET author = ?, date = ?, opinion = ?, score = ? WHERE opinionid = ?;";
		dataSource.update(sql, opinion.getAuthor(), opinion.getDate(), opinion.getOpinion(), opinion.getScore(), opinion.getOpinionId(), opinion.getIdAct());
	}
	
	public Opinion getElement(String clientid, int idAct) {
		String sql = "SELECT * FROM opinion WHERE clientid = ? AND idact = ?";
		List<Opinion> list = dataSource.query(sql, new OpinionMapper(), clientid, idAct);
		if (list.isEmpty() || list.size() > 1) return null;
		return list.get(0);
	}
	
	public List<Opinion> getElements() {
		String sql = "SELECT * FROM opinion;";
		return dataSource.query(sql, new OpinionMapper());
	}
	
	public List<Opinion> getOpinionsFromActivity(int idAct) {
		String sql = "SELECT * FROM opinion WHERE idact = ? ORDER BY score DESC;";
		return dataSource.query(sql, new OpinionMapper(), idAct);
	}
	

	public int getMaxID() {
		String sql = "SELECT MAX(opinionid) FROM opinion;";
		return dataSource.queryForObject(sql, Integer.class);
	}
}
