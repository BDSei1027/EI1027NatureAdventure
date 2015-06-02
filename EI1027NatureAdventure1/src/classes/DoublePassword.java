package classes;

import org.springframework.stereotype.Component;

@Component
public class DoublePassword {

	/*
	 * Clase para envolver la salida del formulario de recuperar la contraseña
	 */
	
	private String password;
	private String confirmation;
	
	public DoublePassword() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String pass) {
		this.confirmation = pass;
	}
}
