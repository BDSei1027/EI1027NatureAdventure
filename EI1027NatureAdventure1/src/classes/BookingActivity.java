package classes;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BookingActivity {

	/*
	 * Clase de envoltura para mostrar datos en el cliente e instructores 
	 * con los datos de la actividad y la reserva
	 */
	
	private int idBooking;
	private String nameActivity;
	private Date dateActivity;
	private String schedule;
	private String level;
	private int groupSize;
	private String place;
	private Date dateRevision;
	private int status;
	
	
	public BookingActivity() {
		super();
	}

	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int id) {
		this.idBooking = id;
	}

	public String getNameActivity() {
		return nameActivity;
	}

	public void setNameActivity(String name) {
		this.nameActivity = name;
	}

	public Date getDateActivity() {
		return dateActivity;
	}

	public void setDateActivity(Date date) {
		this.dateActivity = date;
	}
	
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
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
	
	public void setStatus(String status) {
		status = status.toLowerCase();
		switch (status) {
		case "accepted":
			this.status = 1;
			break;
		case "declined":
			this.status = 2;
			break;
		default:
			this.status = 0;
			break;
		}
	}
}

