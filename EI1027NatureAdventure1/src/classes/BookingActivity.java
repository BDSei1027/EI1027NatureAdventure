package classes;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BookingActivity {

	/*
	 * Clase de envoltura para mostrar datos en el cliente con los datos de
	 * actividad y su reserva
	 */
	
	private int id;
	private String name;
	private Date date;
	private String level;
	private int groupSize;
	private String place;
	private Date dateRevision;
	private int status;
	
	
	public BookingActivity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateRevision() {
		return dateRevision;
	}

	public void setDateRevision(Date dateRevision) {
		this.dateRevision = dateRevision;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public void setLevel(int level) {
		switch (level) {
			case 0:
				this.level = "Beginner";
				break;
			case 1:
				this.level = "Easy";
				break;
			case 2:
				this.level = "Medium";
				break;
			case 3:
				this.level = "High";
				break;
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

