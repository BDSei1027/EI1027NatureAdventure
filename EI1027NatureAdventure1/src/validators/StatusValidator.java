package validators;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Status;

public class StatusValidator implements Validator {
	String language ="EN"; 
	
	public StatusValidator() {
		super();
		String idioma = LocaleContextHolder.getLocale().getLanguage();
		language = idioma.toUpperCase();
	}
	@Override
	public boolean supports(Class<?> cls) {
		return Status.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Status status = (Status) obj;
		if(status.getSsNumber().trim().length() <= 9){
			if(language.equals("ES")){
				errors.rejectValue("ssNumber","longitud","La longitud debe ser mayor que 9 caracteres");// La longitud debe ser mayor que 9 caracteres
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("ssNumber","longitud","The length must be greater than 9 characters");// La longitud debe ser mayor que 9 caracteres
			}
			
		}
		if(status.getStatus().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("status","status","Este campo no puede estar vacío");//Este campo no puede estar vacío
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("status","status","This field cannot be null");//Este campo no puede estar vacío
			}
			
		}
	}

}
