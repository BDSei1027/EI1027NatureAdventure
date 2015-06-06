package validators;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Client;
import classes.User;

public class ClientValidator implements Validator {
	String language;
	
	public ClientValidator(HttpSession session) {
		super();
		User user = (User) session.getAttribute("user");
		language = user.getLanguage();
		if(language == null || language.equals("")){
			language = "EN";
		}
	}

	@Override
	public boolean supports(Class<?> cls) {
		return Client.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Client client = (Client) obj;
		
		if(client.getClientName().trim().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("name", "nameContent","Este campo no puede estar vacío");
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("name", "nameContent","This field cannot be null");
			}
		}
		
		if (client.getClientLastName().trim().equals("")) {
			if (language.equals("ES")) {
				errors.rejectValue("lastName", "lastNameContent",
						"Este campo no puede estar vacío");// Este campo no puede estar vacío
			} else if (language.equals("EN") || language != null) {
				errors.rejectValue("lastName", "lastNameContent",
						"This field cannot be null");// Este campo no puede estar vacío
			}
		}

		if (!client.getClientEmail().matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			if (language.equals("ES")) {
				errors.rejectValue("email", "emailAdrress",
						"Debes introducir un email valido");// Debes introducir un email valido
			} else if (language.equals("EN") || language != null) {
				errors.rejectValue("email", "emailAdrress",
						"A valid email address must be introduced");// Debes introducir un email valido
			}
		}

	}

}
