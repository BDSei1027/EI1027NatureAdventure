package validators;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Activity;

public class ActivityValidator implements Validator {

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
		errors.rejectValue("price", "validator.activityvalidator.price",
						"El precio debe ser mayor que 0");
		}

		if (activity.getMinimumGroup() <= 0) {
				errors.rejectValue("minimumGroup", "validator.activityvalidator.minimumgroup",
						"El minimo de personas es mayor que 0");
		}

		if (activity.getMaximumGroup() <= activity.getMinimumGroup()) {
				errors.rejectValue("maximumGroup", "validator.activityvalidator.maximumgroup",
						"Debe ser mayor que el grupo minimo");
		

		}

	}
}
