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
	public void validate(Object obj, Errors errors) {
		Note note = (Note) obj;
		
		if(note.getTitle().trim().equals("")){
			errors.rejectValue("title","validator.notevalidator.title", "El t�tulo no puede estar vac�o");
		}
	}
}
