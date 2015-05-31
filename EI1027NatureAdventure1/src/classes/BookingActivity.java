package classes;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BookingActivity {

	private int id;
	private String name;
	private Date date;
	private Date dateRevision;
	private int groupSize;
	private String place;
	
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
}

