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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public boolean isEmpty() {
		if (password == null && confirmation == null) return true;
		return false;
	}
}
