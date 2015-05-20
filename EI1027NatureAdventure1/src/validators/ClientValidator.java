package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Client;

public class ClientValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Client.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Client client = (Client) obj;
		if (client.getClientName().trim().equals("")){
			errors.rejectValue("nombre", "obligatorio",
                    "Es necesario introducir un valor");

		}
		
		if(client.getClientName().trim().equals("")){
			errors.rejectValue("name", "nameContent","Este campo no puede estar vacío");
		}
		
		if(client.getClientLastName().trim().equals("")){
			errors.rejectValue("lastName", "lastNameContent","Este campo no puede estar vacío");
		}
		
		if(!client.getClientEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			errors.rejectValue("email", "emailAdrress","Debes introducir un email valido");
		}
		
	}

}
