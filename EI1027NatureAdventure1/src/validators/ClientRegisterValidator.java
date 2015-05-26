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
			errors.rejectValue("name", "nameContent","Este campo no puede estar vacío");
		}
		if(client.getLastName().trim().equals("")){
			errors.rejectValue("lastName", "lastNameContent","Este campo no puede estar vacío");
		}
		if(!client.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
					errors.rejectValue("email", "emailAdrress","Debes introducir un email valido");
		}
		if(client.getId().trim().length()!=9){
			errors.rejectValue("id", "digitos","La longitud debe ser de 9 carácteres");
		}
		if(!client.isTocs()){
			errors.rejectValue("tocs", "tocsContent","Debes aceptar las condiciones para poder registrarte");
		}
		if(client.getLanguage().trim().equals("")){
			errors.rejectValue("language", "languageChoice","Debes escoger lenguaje preferido");
		}
		if(client.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "digitos","La contraseña esta vacía");
		} else if(client.getPassword().trim().length()< 8) {
			errors.rejectValue("password", "digitos","La longitud debe ser de almenos 8 carácteres");
		}
	}

}
