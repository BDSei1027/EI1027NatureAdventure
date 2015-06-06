package validators;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Status;
import classes.User;

public class StatusValidator implements Validator {
	String language ="EN";
	
	public StatusValidator(HttpSession session) {
		super();
		User user = (User) session.getAttribute("user");
		if(user!=null){
			language = user.getLanguage();
			if (language == null || language.equals("")) {
				language = "EN";
			}
		}
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
