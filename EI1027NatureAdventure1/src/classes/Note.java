package classes;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Note {

	/* Class for the notes of the adminPage */
	
	private int id;
	private Date dateCreation;
	private String note;
	private boolean isRead;
	
	public Note() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}
