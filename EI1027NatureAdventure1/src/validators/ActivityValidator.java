package validators;


import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Activity;

public class ActivityValidator implements Validator {
	String language = "EN"; 

	public ActivityValidator() {
		super();
		String idioma = LocaleContextHolder.getLocale().getLanguage();
		language = idioma.toUpperCase();
		
		
	}

	@Override
	public boolean supports(Class<?> cls) {
		return Activity.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Activity activity = (Activity) obj;
		if (activity.getPrice() <= 0) {
			if (language.equals("ES")) {
				errors.rejectValue("price", "negative value",
						"El precio debe ser mayor que 0");
			} else if (language.equals("EN") || language != null) {
				errors.rejectValue("price", "negative value",
						"The price must be greater than 0"); // El precio debe
																// ser mayor que
																// 0
			}

		}

		if (activity.getMinimumGroup() <= 0) {
			if (language.equals("ES")) {
				errors.rejectValue("minimumGroup", "cantidad",
						"El minimo de personas es mayor que 0");// El minimo de
																// personas es
																// mayor que 0
			} else if (language.equals("EN") || language != null) {
				errors.rejectValue("minimumGroup", "cantidad",
						"The minimum number of people must be greater than 0");// El
																				// minimo
																				// de
																				// personas
																				// es
																				// mayor
																				// que
																				// 0
			}

		}

		if (activity.getMaximumGroup() <= activity.getMinimumGroup()) {
			if (language.equals("ES")) {
				errors.rejectValue("maximumGroup", "diferencia",
						"Debe ser mayor que el grupo minimo");// Debe ser mayor
																// que el grupo
																// minimo
			} else if (language.equals("EN") || language != null) {
				errors.rejectValue("maximumGroup", "diferencia",
						"It must be greater than the minimum group");// Debe ser
																		// mayor
																		// que
																		// el
																		// grupo
																		// minimo
			}

		}

	}
}
