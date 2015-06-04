package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Activity;

public class ActivityValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return Activity.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Activity activity = (Activity) obj;
		if(activity.getPrice()<=0){
			errors.rejectValue("price", "negative value", "The price must be greater than 0"); //El precio debe ser mayor que 0
		}
		
		if(activity.getMinimumGroup()<=0){
			errors.rejectValue("minimumGroup","cantidad","The minimum number of people must be greater than 0");//El minimo de personas es mayor que 0
		}
		
		if(activity.getMaximumGroup()<= activity.getMinimumGroup()){
			errors.rejectValue("maximumGroup","diferencia", "It must be greater than the minimum group");//Debe ser mayor que el grupo minimo
		}
		
	}

}
