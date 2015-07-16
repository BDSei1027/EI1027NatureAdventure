package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aeat.valida.Validador;

import classes.ClientRegister;

public class ClientRegisterValidator implements Validator{
	
	public ClientRegisterValidator() {
		super();
	}

	@Override
	public boolean supports(Class<?> cls) {
		return ClientRegister.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ClientRegister client = (ClientRegister) obj;
		
		if (client.getName().trim().equals("")){
			errors.rejectValue("name", "validator.clientregistervalidator.name","Este campo no puede estar vacío");
		}
		if(client.getLastName().trim().equals("")){
			errors.rejectValue("lastName", "validator.clientregistervalidator.lastname","Este campo no puede estar vacío");//Este campo no puede estar vacío
		}
		if(!client.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			errors.rejectValue("email", "validator.clientregistervalidator.email","Debes introducir un email valido");//Debes introducir un email valido	
		}
		if(client.getId().trim().length()!=9){
			errors.rejectValue("id", "validator.clientregistervalidator.language","Este campo debe tener 9 carácteres");
		}
		if(!client.isTocs()){
			errors.rejectValue("tocs", "validator.clientregistervalidator.tocs","Debes aceptar las condiciones para registrarte");
		}
		if(client.getLanguage().trim().equals("")){
			errors.rejectValue("language", "validator.clientregistervalidator.id","Debes escoger lenguaje preferido");
		}
		if(client.getPassword().trim().length() == 0) {
				errors.rejectValue("password", "validator.clientregistervalidator.password01","La contraseña esta vacía");
		} else if(client.getPassword().trim().length()< 8) {
			errors.rejectValue("password", "validator.clientregistervalidator.password02","La longitud debe ser de al menos 8 carácteres");//La longitud debe ser de al menos 8 carácteres
		}
		Validador validadorAgenciaTributaria = new Validador();
		if(validadorAgenciaTributaria.checkNif(client.getId().toUpperCase())<0){
			errors.rejectValue("id", "validator.agencia", "El DNI introducido es incorrecto");
		}
	}
}
