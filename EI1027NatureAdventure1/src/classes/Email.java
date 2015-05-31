package classes;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Email {

	/*
	 * Clase para enviar mail el administrador desde la vista
	 * Es una clase envoltura para un formulario
	 */
	
	private String to;
	private String message;
	private Date dateMessage;
	
	public Email() {
		super();
	}
	
	public Email(Email email) {
		this.to = email.getTo();
		this.message = email.getMessage();
		this.dateMessage = email.getDateMessage();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(Date dateMessage) {
		this.dateMessage = dateMessage;
	}
	
}
