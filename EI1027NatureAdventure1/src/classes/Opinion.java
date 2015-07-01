package classes;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Opinion {

	/* Class for the opinions of the users about an activity */
	
	private String clientId;
	private String clientFullName;
	private int idAct;
	private String opinion;
	private Date date;
	private int score;
	
	public Opinion() {
		super();
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public int getIdAct() {
		return idAct;
	}

	public void setIdAct(int idAct) {
		this.idAct = idAct;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
