package validators;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.ClientRegister;
import classes.User;

public class ClientRegisterValidator implements Validator{
	String language = "EN";
	
	public ClientRegisterValidator(HttpSession session) {
		super();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			language = user.getLanguage();
			if (language == null || language.equals("")) {
				language = "EN";
			}
		}
	}

	@Override
	public boolean supports(Class<?> cls) {
		return ClientRegister.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ClientRegister client = (ClientRegister) obj;
		
		if (client.getName().trim().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("name", "nameContent","Este campo no puede estar vacío");
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("name", "nameContent","This field cannot be null");
			}
			
		}
		if(client.getLastName().trim().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("lastName", "lastNameContent","Este campo no puede estar vacío");//Este campo no puede estar vacío
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("lastName", "lastNameContent","This field cannot be null");//Este campo no puede estar vacío
			}
			
		}
		if(!client.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			if(language.equals("ES")){
				errors.rejectValue("email", "emailAdrress","Debes introducir un email valido");//Debes introducir un email valido
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("email", "emailAdrress","A valid email address must be introduced");//Debes introducir un email valido
			}
					
		}
		if(client.getId().trim().length()!=9){
			if(language.equals("ES")){
				
			}else if(language.equals("EN") || language != null){
				
			}
			errors.rejectValue("id", "digitos","The length must be 9 characters");//La longitud debe ser de 9 carácteres
		}
		if(!client.isTocs()){
			if(language.equals("ES")){
				
			}else if(language.equals("EN") || language != null){
				
			}
			errors.rejectValue("tocs", "tocsContent","You must accept the conditions to register");//Debes aceptar las condiciones para poder registrarte
		}
		if(client.getLanguage().trim().equals("")){
			if(language.equals("ES")){
				
			}else if(language.equals("EN") || language != null){
				
			}
			errors.rejectValue("language", "languageChoice","You must choose your favorite language");//Debes escoger lenguaje preferido
		}
		if(client.getPassword().trim().length() == 0) {
			if(language.equals("ES")){
				
			}else if(language.equals("EN") || language != null){
				
			}
			errors.rejectValue("password", "digitos","The password cannot be null");//La contraseña esta vacía
		} else if(client.getPassword().trim().length()< 8) {
			if(language.equals("ES")){
				errors.rejectValue("password", "digitos","La longitud debe ser de al menos 8 carácteres");//La longitud debe ser de al menos 8 carácteres
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("password", "digitos","The length must be at least 8 characters");//La longitud debe ser de al menos 8 carácteres
			}
			
		}
	}

}
