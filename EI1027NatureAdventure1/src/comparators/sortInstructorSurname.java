package comparators;

import java.util.Comparator;

import classes.Instructor;

public class sortInstructorSurname implements Comparator<Instructor> {

	private char sort;

	public sortInstructorSurname(char sortMode) {
		this.sort = sortMode;
	}

	@Override
	public int compare(Instructor theInstructor, Instructor otherInstructor) {
		if(sort == 'a') return theInstructor.getLastName().compareTo(otherInstructor.getLastName());
		return otherInstructor.getLastName().compareTo(theInstructor.getLastName());
	}

}
