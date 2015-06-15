package validators;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Activity;


public class ActivityValidator implements Validator {
	@Autowired
	MessageSource msgSrc;

	public ActivityValidator() {
		super();
	}

	@Override
	public boolean supports(Class<?> cls) {
		return Activity.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Activity activity = (Activity) obj;
		if (activity.getPrice() <= 0) {
			errors.rejectValue("price", "negative value",
			msgSrc.getMessage("validator.activityvalidator.price", null, LocaleContextHolder.getLocale()));
		}

		if (activity.getMinimumGroup() <= 0) {
			errors.rejectValue("minimumGroup", "cantidad",
					msgSrc.getMessage("validator.activityvalidator.minimumgroup", null, LocaleContextHolder.getLocale()));
		}

		if (activity.getMaximumGroup() <= activity.getMinimumGroup()) {
			errors.rejectValue("maximumGroup", "diferencia",
					msgSrc.getMessage("validator.", null, LocaleContextHolder.getLocale()));

		}

	}

	public MessageSource getMsgSrc() {
		return msgSrc;
	}

	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}
	
}
