package classes;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ClientBookingEnvelope {

	private String id;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private String language;
	private boolean tocs;
	
	private int innerIdBooking;
	private Integer idBooking;
	private int groupSize;
	private Date dateActivity;
	private Date dateCreation;
	private float price;
	private String information;
	private int idAct;
	private String clientId;
	private int status;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientBookingEnvelope other = (ClientBookingEnvelope) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (dateActivity == null) {
			if (other.dateActivity != null)
				return false;
		} else if (!dateActivity.equals(other.dateActivity))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (groupSize != other.groupSize)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idAct != other.idAct)
			return false;
		if (idBooking == null) {
			if (other.idBooking != null)
				return false;
		} else if (!idBooking.equals(other.idBooking))
			return false;
		if (information == null) {
			if (other.information != null)
				return false;
		} else if (!information.equals(other.information))
			return false;
		if (innerIdBooking != other.innerIdBooking)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (status != other.status)
			return false;
		if (tocs != other.tocs)
			return false;
		return true;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public boolean isTocs() {
		return tocs;
	}
	public void setTocs(boolean tocs) {
		this.tocs = tocs;
	}
	public int getInnerIdBooking() {
		return innerIdBooking;
	}
	public void setInnerIdBooking(int innerIdBooking) {
		this.innerIdBooking = innerIdBooking;
	}
	public Integer getIdBooking() {
		return idBooking;
	}
	public void setIdBooking(Integer idBooking) {
		this.idBooking = idBooking;
	}
	public int getGroupSize() {
		return groupSize;
	}
	public void setGroupSize(int groupSize) {
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
	public void setPrice(float price) {
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
	public Client getClient() {
		Client client = new Client();
		client.setClientEmail(email);
		client.setClientId(clientId);
		client.setClientLastName(lastName);
		client.setClientName(name);
		return client;
	}
	public Booking getBooking() {
		Booking booking = new Booking();
		booking.setClientId(clientId);
		booking.setDateActivity(dateActivity);
		booking.setDateCreation(dateCreation);
		booking.setGroupSize(groupSize);
		booking.setIdAct(idAct);
		booking.setIdBooking(idBooking);
		booking.setInformation(information);
		booking.setInnerIdBooking(innerIdBooking);
		booking.setPrice(price);
		booking.setStatus(status);
		return booking;
	}

}
