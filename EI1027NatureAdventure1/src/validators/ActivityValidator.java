package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Activity;
import classes.Instructor;

public class ActivityValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return Activity.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Activity activity = (Activity) obj;
		if(activity.getPrice()<=0){
			errors.rejectValue("price", "negative value", "El precio debe ser mayor que 0");
		}
		
		if(activity.getMinimumGroup()<=0){
			errors.rejectValue("minimumGroup","cantidad","El minimo de personas es mayor que 0");
		}
		
		if(activity.getMaximumGroup()<= activity.getMinimumGroup()){
			errors.rejectValue("maximumGroup","diferencia", "Debe ser mayor que el grupo minimo");
		}
		
	}

}
