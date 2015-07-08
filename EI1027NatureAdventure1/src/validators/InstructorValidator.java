package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aeat.valida.Validador;

import classes.Instructor;


public class InstructorValidator implements Validator {
	
	public InstructorValidator() {
		super();
	}
	@Override
	public boolean supports(Class<?> cls) {
		return Instructor.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Instructor instructor = (Instructor) obj;
		
		if(instructor.getSsNumber().trim().length() <= 9){
			errors.rejectValue("ssNumber","validator.instructorvalidator.ssnumber","La longitud debe ser mayor que 9 carácteres");//La longitud debe ser mayor que 9 carácteres 
		}
		
		if(instructor.getName().trim().equals("")){
				errors.rejectValue("name", "validator.instructorvalidator.name","Este campo no puede estar vacío");//Este campo no puede estar vacío
		}
		
		if(instructor.getLastName().trim().equals("")){
			errors.rejectValue("lastName", "validator.instructorvalidator.lastname","Este campo no puede estar vacío");//Este campo no puede estar vacío
		}
		
		if(!instructor.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			errors.rejectValue("email", "validator.instructorvalidator.email","Debes introducir un email valido");//Debes introducir un email valido
		}
		Validador validadorAgenciaTributaria = new Validador();
		if(validadorAgenciaTributaria.checkNif(instructor.getIdNumber().toUpperCase())<0){
			errors.rejectValue("idNumber", "validator.instructorvalidator.idnumber","The id Number is not correct");//El número id no es correcto
		}
	}

}
