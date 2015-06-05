package classes;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class Booking {
	//Clave primaria: ID interna
	private int innerIdBooking;
	
	//CLave alternativa: ID publica (puede ser null)
	private int idBooking;
	
	//Datos de la reserva
	private int groupSize;
	private Date dateActivity;
	private Date dateCreation; //De momento manual
	private float price;
	private String information;
	private int idAct;
	private String clientId;
	private int status;


//------------------------------------------------------------------------------------------------------------------------------------------------
//Constructores
	
	public Booking(){
		super();
	}
	
	
	//Copia del estado del objeto reserva que se pasa como parametro
	public Booking(Booking reserva) {
		innerIdBooking = reserva.innerIdBooking;
		idBooking = reserva.idBooking;
		
		groupSize = reserva.groupSize;
		dateActivity = reserva.dateActivity;
		dateCreation = reserva.dateCreation;
		price = reserva.price;
		information = reserva.information;
		idAct = reserva.idAct;
		clientId = reserva.clientId;
		status = reserva.status;
		
	}


//------------------------------------------------------------------------------------------------------------------------------------------------
//Comparacion

		@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Booking)) {
			return false;
		}
		Booking other = (Booking) obj;
		if (clientId == null) {
			if (other.clientId != null) {
				return false;
			}
		} else if (!clientId.equals(other.clientId)) {
			return false;
		}
		if (dateActivity == null) {
			if (other.dateActivity != null) {
				return false;
			}
		} else if (!dateActivity.equals(other.dateActivity)) {
			return false;
		}
		if (dateCreation == null) {
			if (other.dateCreation != null) {
				return false;
			}
		} else if (!dateCreation.equals(other.dateCreation)) {
			return false;
		}
		if (groupSize != other.groupSize) {
			return false;
		}
		if (idAct != other.idAct) {
			return false;
		}
		if (idBooking != other.idBooking) {
			return false;
		}
		if (information == null) {
			if (other.information != null) {
				return false;
			}
		} else if (!information.equals(other.information)) {
			return false;
		}
		if (innerIdBooking != other.innerIdBooking) {
			return false;
		}
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price)) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Booking: " + innerIdBooking + "[" + idBooking + "] " + idAct + ", " + status;
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------
//Getters y setters
	
	
	public int getInnerIdBooking() {
		return innerIdBooking;
	}
	
	
	public void setInnerIdBooking(int innerIdBooking) {
		this.innerIdBooking = innerIdBooking;
	}
	
	
	public int getIdBooking() {
		return idBooking;
	}
	
	
	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}
	
	
	public int getGroupSize() {
		return groupSize;
	}
	
	
	public void setGroupSize(int groupSize){
		this.groupSize = groupSize;
	}
	
	
	public Date getDateActivity() {
		return dateActivity;
	}
	
	
	public void setDateActivity(Date dateActivity) {
		this.dateActivity = dateActivity;
	}
	
	
	public Date getDateCreation() {
		return dateCreation;
	}
	
	
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
	public float getPrice() {
		return price;
	}
	
	
	public void setPrice(float price){
		this.price = price;
	}
	
	
	public String getInformation() {
		return information;
	}
	
	
	public void setInformation(String information) {
		this.information = information;
	}
	
	
	public int getIdAct() {
		return idAct;
	}
	
	
	public void setIdAct(int idAct) {
		this.idAct = idAct;
	}
	
	
	public String getClientId() {
		return clientId;
	}
	
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setStatus(String status) {
		if (status == null) {
			this.status = 0;
			return;
		}
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
