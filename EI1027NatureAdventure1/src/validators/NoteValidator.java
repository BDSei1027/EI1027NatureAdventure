package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Note;

public class NoteValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Note.class.equals(cls);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO IMPLEMENTAR
		
	}

	
	
}
