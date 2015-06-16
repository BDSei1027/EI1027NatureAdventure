package validators;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.ClientRegister;

public class ClientRegisterValidator implements Validator{
	String language = "EN"; 
	
	public ClientRegisterValidator() {
		super();
		String idioma = LocaleContextHolder.getLocale().getLanguage();
		language = idioma.toUpperCase();
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
				errors.rejectValue("name", "nameContent","Este campo no puede estar vac�o");
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("name", "nameContent","This field cannot be null");
			}
			
		}
		if(client.getLastName().trim().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("lastName", "lastNameContent","Este campo no puede estar vac�o");//Este campo no puede estar vac�o
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("lastName", "lastNameContent","This field cannot be null");//Este campo no puede estar vac�o
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
				errors.rejectValue("id", "digitos","Este campo debe tener 9 car�cteres");
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("id", "digitos","This field must be at least 9 characters");
			}
			
		}
		if(!client.isTocs()){
			if(language.equals("ES")){
				errors.rejectValue("tocs", "tocsContent","Debes aceptar las condiciones para registrarte");
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("tocs", "tocsContent","You must accept the conditions to register");
			}
			
		}
		if(client.getLanguage().trim().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("language", "languageChoice","Debes escoger lenguaje preferido");
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("language", "languageChoice","You must choose your favorite language");
			}
			
		}
		if(client.getPassword().trim().length() == 0) {
			if(language.equals("ES")){
				errors.rejectValue("password", "digitos","La contrase�a esta vac�a");
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("password", "digitos","The password cannot be null");
			}
			
		} else if(client.getPassword().trim().length()< 8) {
			if(language.equals("ES")){
				errors.rejectValue("password", "digitos","La longitud debe ser de al menos 8 car�cteres");//La longitud debe ser de al menos 8 car�cteres
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("password", "digitos","The length must be at least 8 characters");//La longitud debe ser de al menos 8 car�cteres
			}
			
		}
	}

}
