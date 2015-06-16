package validators;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aeat.valida.Validador;

import classes.Instructor;


public class InstructorValidator implements Validator {
	String language = "EN"; 
	
	public InstructorValidator() {
		super();
		String idioma = LocaleContextHolder.getLocale().getLanguage();
		language = idioma.toUpperCase();
	}
	@Override
	public boolean supports(Class<?> cls) {
		return Instructor.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Instructor instructor = (Instructor) obj;
		
		if(instructor.getSsNumber().trim().length() <= 9){
			if(language.equals("ES")){
				errors.rejectValue("ssNumber","longitud","La longitud debe ser mayor que 9 carácteres");//La longitud debe ser mayor que 9 carácteres 
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("ssNumber","longitud","The length must be greater than 9 characters");//La longitud debe ser mayor que 9 carácteres 
			}
			
		}
		
		if(instructor.getName().trim().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("name", "nameContent","Este campo no puede estar vacío");//Este campo no puede estar vacío
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("name", "nameContent","This field cannot be null");//Este campo no puede estar vacío
			}
			
		}
		
		if(instructor.getLastName().trim().equals("")){
			if(language.equals("ES")){
				errors.rejectValue("lastName", "lastNameContent","Este campo no puede estar vacío");//Este campo no puede estar vacío
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("lastName", "lastNameContent","This field cannot be null");//Este campo no puede estar vacío
			}
			
		}
		
		if(!instructor.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			if(language.equals("ES")){
				errors.rejectValue("email", "emailAdrress","Debes introducir un email valido");//Debes introducir un email valido
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("email", "emailAdrress","A valid email address must be introduced");//Debes introducir un email valido
			}
			
		}
		Validador validadorAgenciaTributaria = new Validador();
		if(validadorAgenciaTributaria.checkNif(instructor.getIdNumber().toUpperCase())<0){
			if(language.equals("ES")){
				errors.rejectValue("idNumber", "digitos","The id Number is not correct");//El número id no es correcto
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("idNumber", "digitos","The id Number is not correct");//El número id no es correcto
			}
			
		}
	}

}
