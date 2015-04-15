package classes;

import java.sql.Date;

import exceptions.InvalidGroupSizeException;
import exceptions.InvalidPriceException;


/**
 * Created by cdd on 16/03/15.
 */
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
	
	
	
	private Activity activity; //La actividad que se realizara
	private Client client; //El cliente que solicito la reserva
	private Instructor instructor; //El monitor asignado (puede ser null hasta que la reserva se apruebe)

	private Status status;

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
		
		activity = reserva.activity;
		client = reserva.client;
		instructor = reserva.instructor;
		
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


	public void setGroupSize(int groupSize) throws InvalidGroupSizeException {
		if(groupSize<0) throw new InvalidGroupSizeException(groupSize);
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


	public void setPrice(float price) throws InvalidPriceException {
		if(price<0.0) throw new InvalidPriceException(price);
		this.price = price;
	}


	public String getInformation() {
		return information;
	}


	public void setInformation(String information) {
		this.information = information;
	}


	public Activity getActivity() {
		return activity;
	}


	public void setActivity(Activity activity) {
		this.activity = activity;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Instructor getInstructor() {
		return instructor;
	}


	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	
	public Status getStatus(){
		return status;
	}

	
	public void setStatus(Status status) {
		this.status = status;
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
		if (activity == null) {
			if (other.activity != null) {
				return false;
			}
		} else if (!activity.equals(other.activity)) {
			return false;
		}
		if (client == null) {
			if (other.client != null) {
				return false;
			}
		} else if (!client.equals(other.client)) {
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
		if (instructor == null) {
			if (other.instructor != null) {
				return false;
			}
		} else if (!instructor.equals(other.instructor)) {
			return false;
		}
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price)) {
			return false;
		}
		return true;
	}

	
	
}
