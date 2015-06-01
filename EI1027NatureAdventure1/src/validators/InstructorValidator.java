package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aeat.valida.Validador;

import classes.Instructor;
import classes.User;

public class InstructorValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Instructor.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Instructor instructor = (Instructor) obj;
		
		if(instructor.getSsNumber().trim().length() <= 9){
			errors.rejectValue("ssNumber","longitud","La longitud debe ser mayor que 9 carácteres"); 
		}
		
		if(instructor.getName().trim().equals("")){
			errors.rejectValue("name", "nameContent","Este campo no puede estar vacío");
		}
		
		if(instructor.getLastName().trim().equals("")){
			errors.rejectValue("lastName", "lastNameContent","Este campo no puede estar vacío");
		}
		
		if(!instructor.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			errors.rejectValue("email", "emailAdrress","Debes introducir un email valido");
		}
		Validador validadorAgenciaTributaria = new Validador();
		if(validadorAgenciaTributaria.checkNif(instructor.getIdNumber().toUpperCase())<0){
			errors.rejectValue("idNumber", "digitos","El idNumber no es correcto");
		}
	}

}
