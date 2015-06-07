package validators;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Email;

public class EmailValidator implements Validator{
	String language = "EN"; 
	
	public EmailValidator() {
		super();
		String idioma = LocaleContextHolder.getLocale().getLanguage();
		language = idioma.toUpperCase();
	}
	
	@Override
	public boolean supports(Class<?> cls) {
		return Email.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Email email = (Email) obj;
		
		if(email.getTo().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("to","destinatarioNulo","Este campo no puede ser nulo");//Este campo no puede ser nulo
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("to","destinatarioNulo","This field cannot be null");//Este campo no puede ser nulo
			}
			
		}
	}

}
