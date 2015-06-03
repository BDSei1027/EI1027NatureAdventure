package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.ClientRegister;

public class ClientRegisterValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return ClientRegister.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ClientRegister client = (ClientRegister) obj;
		if (client.getName().trim().equals("")){
			errors.rejectValue("name", "nameContent","Este campo no puede estar vac�o");
		}
		if(client.getLastName().trim().equals("")){
			errors.rejectValue("lastName", "lastNameContent","This field cannot be null");//Este campo no puede estar vac�o
		}
		if(!client.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
					errors.rejectValue("email", "emailAdrress","A valid email address must be introduced");//Debes introducir un email valido
		}
		if(client.getId().trim().length()!=9){
			errors.rejectValue("id", "digitos","The length must be 9 characters");//La longitud debe ser de 9 car�cteres
		}
		if(!client.isTocs()){
			errors.rejectValue("tocs", "tocsContent","You must accept the conditions to register");//Debes aceptar las condiciones para poder registrarte
		}
		if(client.getLanguage().trim().equals("")){
			errors.rejectValue("language", "languageChoice","You must choose your favorite language");//Debes escoger lenguaje preferido
		}
		if(client.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "digitos","The password cannot be null");//La contrase�a esta vac�a
		} else if(client.getPassword().trim().length()< 8) {
			errors.rejectValue("password", "digitos","The length must be at least 8 characters");//La longitud debe ser de al menos 8 car�cteres
		}
	}

}
