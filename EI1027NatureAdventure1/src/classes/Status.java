package classes;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Status {

	//Clave primaria
	private int IDbooking;
	
	//Datos del estado de la reserva
	private Date dateRevision;
	private String status;
	private String ssNumber; //Monitor
	
	
//------------------------------------------------------------------------------------------------------------------------------------------------
//Constructores
	public Status() {
		super();
	}
	
	public Status(Status estado){
		IDbooking = estado.IDbooking;
		dateRevision = estado.dateRevision;
		status = estado.status;
		ssNumber = estado.ssNumber;
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
		if (!(obj instanceof Status)) {
			return false;
		}
		Status other = (Status) obj;
		if (IDbooking != other.IDbooking) {
			return false;
		}
		if (dateRevision == null) {
			if (other.dateRevision != null) {
				return false;
			}
		} else if (!dateRevision.equals(other.dateRevision)) {
			return false;
		}
		if (ssNumber == null) {
			if (other.ssNumber != null) {
				return false;
			}
		} else if (!ssNumber.equals(other.ssNumber)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}
		

	
//------------------------------------------------------------------------------------------------------------------------------------------------
//Getters y setters

	
	public int getIDbooking() {
		return IDbooking;
	}
	
	public void setIDbooking(int iDbooking) {
		IDbooking = iDbooking;
	}
	
	public Date getDateRevision() {
		return dateRevision;
	}
	
	public void setDateRevision(Date dateRevision) {
		this.dateRevision = dateRevision;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSsNumber() {
		return ssNumber;
	}
	
	public void setSsNumber(String ssNumber) {
		this.ssNumber = ssNumber;
	}




	
}
