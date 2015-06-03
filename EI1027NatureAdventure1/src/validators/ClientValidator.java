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
		
		if(client.getClientName().trim().equals("")){
			errors.rejectValue("name", "nameContent","This field cannot be null");//Este campo no puede estar vac�o
		}
		
		if(client.getClientLastName().trim().equals("")){
			errors.rejectValue("lastName", "lastNameContent","This field cannot be null");//Este campo no puede estar vac�o
		}
		
		if(!client.getClientEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			errors.rejectValue("email", "emailAdrress","A valid email address must be introduced");//Debes introducir un email valido
		}
		
	}

}
