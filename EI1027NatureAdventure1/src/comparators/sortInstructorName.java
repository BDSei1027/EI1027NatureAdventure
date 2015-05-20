package comparators;

import java.util.Comparator;

import classes.Instructor;

public class sortInstructorName implements Comparator<Instructor> {

	private char sort;

	public sortInstructorName(char sortMode) {
		this.sort = sortMode;
	}

	@Override
	public int compare(Instructor theInstructor, Instructor otherInstructor) {
		if(sort == 'a') return theInstructor.getName().compareTo(otherInstructor.getName());
		return otherInstructor.getName().compareTo(theInstructor.getName());
	}

}
