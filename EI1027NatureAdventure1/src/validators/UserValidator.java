package validators;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.User;

public class UserValidator implements Validator {
	String language = "EN"; 
	
	public UserValidator(HttpSession session) {
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
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		if (user.getUser().trim().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("name", "obligatorio", "Es necesario introducir un valor");//Es necesario introducir un valor
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("name", "obligatorio", "A value must be introduced");//Es necesario introducir un valor
			}
			

		}
		
	}

}
