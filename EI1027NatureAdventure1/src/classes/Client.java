package classes;

import org.springframework.stereotype.Component;

@Component
public class Client {
	
	//Clave primaria
	private String clientId;
	
	//Datos del cliente
	private String clientName;
	private String clientLastName;
	private String clientEmail;
	
//------------------------------------------------------------------------------------------------------------------------------------------------
//Constructores
	public Client(){
		super();
	}
	
	//Copia del estado del objeto reserva que se pasa como parametro
	public Client(Client cliente){
		clientId = cliente.clientId;
		clientName = cliente.clientName;
		clientLastName = cliente.clientLastName;
		clientEmail = cliente.clientEmail;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------
//Comparacion
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (clientEmail == null) {
			if (other.clientEmail != null)
				return false;
		} else if (!clientEmail.equals(other.clientEmail))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (clientLastName == null) {
			if (other.clientLastName != null)
				return false;
		} else if (!clientLastName.equals(other.clientLastName))
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		return true;
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------
//Getters y setters
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	
	
	
}
