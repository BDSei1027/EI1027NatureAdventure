package validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.DoublePassword;
import classes.User;

public class DoublePasswordValidator  implements Validator {
	@Autowired
	MessageSource msgSrc; 
	
	public DoublePasswordValidator() {
		super();
	}
	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		DoublePassword dp = (DoublePassword) obj;
		if (!dp.getPassword().equals(dp.getConfirmation())) {
			errors.rejectValue("password", "obligatorio", msgSrc.getMessage("validator.doublepasswordvalidator.password", null, LocaleContextHolder.getLocale()));
		}
	}
	public MessageSource getMsgSrc() {
		return msgSrc;
	}
	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}

}
