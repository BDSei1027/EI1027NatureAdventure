package validators;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.BookingActivity;


public class BookingActivityValidator implements Validator{
	String language = "EN"; 
	
	public BookingActivityValidator() {
		super();
		String idioma = LocaleContextHolder.getLocale().getLanguage();
		language = idioma.toUpperCase();
	}

	@Override
	public boolean supports(Class<?> cls) {
		return BookingActivity.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		BookingActivity myActivity = (BookingActivity) obj;
		if(myActivity.getIdBooking()<0){
			if(language.equals("ES")){
				errors.rejectValue("id","valorNegativo","Hay un problema con el id");//Hay un problema con el id
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("id","valorNegativo","There is a problem with the id");//Hay un problema con el id
			}
			
		}
		if(myActivity.getNameActivity().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("name","nombreNulo","Este campo no puede ser nulo");//Este campo no puede ser nulo
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("name","nombreNulo","This field cannot be null");//Este campo no puede ser nulo
			}
			
		}
		if(myActivity.getPlace().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("place","lugarNulo","Este campo no puede ser nulo");//Este campo no puede ser nulo
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("place","lugarNulo","This field cannot be null");//Este campo no puede ser nulo
			}
			
		}
		if(myActivity.getGroupSize()<=0){
			if(language.equals("ES")){
				errors.rejectValue("groupSize","valorNegativo","Este campo no puede ser menor o igual a cero");//Este campo no puede ser menor o igual a cero
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("groupSize","valorNegativo","This field cannot be lower or equal than 0");//Este campo no puede ser menor o igual a cero
			}
			
		}
		if(myActivity.getLevel().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("groupSize","valorNegativo","Este campo no puede ser menor o igual a cero");//Este campo no puede ser menor o igual a cero
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("groupSize","valorNegativo","This field cannot be lower or equal than 0");//Este campo no puede ser menor o igual a cero
			}
			
		}
		if(myActivity.getPrice()<=0){
			if(language.equals("ES")){
				errors.rejectValue("price", "negative value", "El precio debe ser mayor que 0"); //El precio debe ser mayor que 0
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("price", "negative value", "The price must be greater than 0"); //El precio debe ser mayor que 0
			}
			errors.rejectValue("price", "negative value", "The price must be greater than 0"); //El precio debe ser mayor que 0
		}
	}

}
