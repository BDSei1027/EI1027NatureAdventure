package classes;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AvaliableForBooking {

	private int idAct;
	private String ssNumber;
	private Date date;
	private String schedule;
	private int AvaliblePlaces;
	
	public AvaliableForBooking() {
		super();
	}

	public int getIdAct() {
		return idAct;
	}

	public void setIdAct(int idAct) {
		this.idAct = idAct;
	}

	public String getSsNumber() {
		return ssNumber;
	}

	public void setSsNumber(String ssNumber) {
		this.ssNumber = ssNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getAvaliblePlaces() {
		return AvaliblePlaces;
	}

	public void setAvaliblePlaces(int avaliblePlaces) {
		AvaliblePlaces = avaliblePlaces;
	}
}
