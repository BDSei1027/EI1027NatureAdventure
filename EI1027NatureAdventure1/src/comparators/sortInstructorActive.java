package comparators;

import java.util.Comparator;

import classes.Instructor;

public class sortInstructorActive implements Comparator<Instructor> {

	private char sort;

	public sortInstructorActive(char sortMode) {
		this.sort = sortMode;
	}

	@Override
	public int compare(Instructor theInstructor, Instructor otherInstructor) {
		if(sort == 'a')
			if(theInstructor.isActive() && !otherInstructor.isActive()) return 1;
		return -1;
	}

}
