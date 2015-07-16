package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aeat.valida.Validador;

import classes.Client;


public class ClientValidator implements Validator { 
	
	public ClientValidator() {
		super();
	}

	@Override
	public boolean supports(Class<?> cls) {
		return Client.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Client client = (Client) obj;
		
		if(client.getClientName().trim().equals("")){
			errors.rejectValue("name", "validator.clientvalidator.name","Este campo no puede estar vacío");
		}
		
		if (client.getClientLastName().trim().equals("")) {
			errors.rejectValue("lastName", "validator.clientvalidator.lastname",
						"Este campo no puede estar vacío");// Este campo no puede estar vacío
		}

		if (!client.getClientEmail().matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			errors.rejectValue("email", "validator.clientvalidator.email",
						"Debes introducir un email valido");// Debes introducir un email valido
		}
		
		Validador validadorAgenciaTributaria = new Validador();
		if(validadorAgenciaTributaria.checkNif(client.getClientId().toUpperCase())<0){
			errors.rejectValue("clientId", "validator.agencia", "El DNI introducido es incorrecto");
		}
	}

}
