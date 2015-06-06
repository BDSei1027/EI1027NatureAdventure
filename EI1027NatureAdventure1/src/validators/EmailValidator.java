package validators;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Email;
import classes.User;

public class EmailValidator implements Validator{
	String language = "EN";
	
	public EmailValidator(HttpSession session) {
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
