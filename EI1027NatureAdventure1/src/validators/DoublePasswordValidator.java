package validators;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.DoublePassword;
import classes.User;

public class DoublePasswordValidator  implements Validator {
	String language = "EN"; 
	
	public DoublePasswordValidator(HttpSession session) {
		super();
		User user = (User) session.getAttribute("user");
		if(user!=null){
		language = user.getLanguage();
		if(language == null || language.equals("")){
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
		DoublePassword dp = (DoublePassword) obj;
		
		if (!dp.getPassword().equals(dp.getConfirmation())) {
			if(language.equals("ES")){
				errors.rejectValue("password", "obligatorio", "Contraseña incorrecta");
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("password", "obligatorio", "The password must be the same");
			}
		}
		//TODO Implementar
		
	}

}
