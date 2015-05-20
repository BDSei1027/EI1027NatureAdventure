package comparators;

import java.util.Comparator;

import classes.Activity;

public class sortActivityActive implements Comparator<Activity> {

	private char sort;

	public sortActivityActive(char sortMode) {
		this.sort = sortMode;
	}

	@Override
	public int compare(Activity theActivity, Activity otherActivity) {
		if(sort == 'a') 
			if (theActivity.isActive() && !otherActivity.isActive()) return 1;
		return 0;
	}

}
