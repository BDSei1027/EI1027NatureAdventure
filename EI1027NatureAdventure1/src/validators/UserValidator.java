package validators;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.User;

public class UserValidator implements Validator {
	MessageSource msgSrc;
	
	public UserValidator() {
		super();
	}
	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		if (user.getUser().trim().equals("")){
			errors.rejectValue("user", "obligatorio", msgSrc.getMessage("validator.uservalidator.user", null, LocaleContextHolder.getLocale()));
		}
		//password name
		if (user.getPassword().trim().equals("")){
			errors.rejectValue("password", "obligatorio", msgSrc.getMessage("validator.uservalidator.password", null, LocaleContextHolder.getLocale()));
		}
		if (user.getName().trim().equals("")){
			errors.rejectValue("name", "obligatorio", msgSrc.getMessage("validator.uservalidator.name", null, LocaleContextHolder.getLocale()));
		}
	}
	public MessageSource getMsgSrc() {
		return msgSrc;
	}
	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}

}
