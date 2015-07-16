package classes;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Opinion {

	/* Class for the opinions of the users about an activity */
	
	private int opinionId;
	private String author;
	private int idAct;
	private String opinion;
	private Date date;
	private int score;
	
	public Opinion() {
		super();
	}


	public int getOpinionId() {
		return opinionId;
	}


	public void setOpinionId(int opinionId) {
		this.opinionId = opinionId;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
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
