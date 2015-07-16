package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import classes.Note;

@Repository
public class daoNote {

	private JdbcTemplate dataSource;
	
	public daoNote() {
		super();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = new JdbcTemplate(dataSource);
	}
	
	/**
	 * This class makes a note from the database outputs
	 */
	private static final class NoteMapper implements RowMapper<Note> {
		public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
			Note note = new Note();
			try {
				note.setId(rs.getInt("id"));
				note.setDateCreation(rs.getDate("datecreation"));
				note.setTitle(rs.getString("title"));
				note.setNote(rs.getString("note"));
				note.setRead(rs.getBoolean("isread"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return note;
		}
	}
	
	
	public void addElement(Note note) {
		String sql= "INSERT INTO note(datecreation, title, note, isread)"
				+ "values(?, ?, ?, ?, ?);";
		dataSource.update(sql, note.getDateCreation(), note.getTitle(), note.getNote(), note.isRead());
	}
	
	public void deleteElement(int id) {
		String sql = "DELETE FROM note WHERE id = ?;";
		dataSource.update(sql, id);
	}
	
	public void updateElement(Note note) {
		String sql = "UPDATE note SET datecreation = ?, title = ?, note = ?, isread = ? WHERE id = ?;";
		dataSource.update(sql, note.getDateCreation(), note.getTitle(), note.getNote(), note.isRead(), note.getId());
	}
	
	public Note getElement(int id) {
		String sql = "SELECT * FROM note WHERE id = ?;";
		List<Note> list = dataSource.query(sql, new NoteMapper(), id);
		if (list.isEmpty() || list.size() > 1) return null;
		return list.get(0);
	}
	
	public List<Note> getElements() {
		String sql = "SELECT * FROM note;";
		return dataSource.query(sql, new NoteMapper());
	}
	
	public Integer getMaxID() {
		String sql = "SELECT MAX(id) FROM note;";
		return dataSource.queryForObject(sql, Integer.class);
	}
}
