package validators;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.DoublePassword;
import classes.User;

public class DoublePasswordValidator  implements Validator {
	String language = "EN"; 
	
	public DoublePasswordValidator() {
		super();
		String idioma = LocaleContextHolder.getLocale().getLanguage();
		language = idioma.toUpperCase();
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
			}else{
				errors.rejectValue("password", "obligatorio", "The password must be the same");
			}
		}
		//TODO Implementar
		
	}

}
