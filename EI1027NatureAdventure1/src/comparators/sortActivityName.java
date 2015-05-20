package comparators;

import java.util.Comparator;

import classes.Activity;

public class sortActivityName implements Comparator<Activity> {

	private char sort;
	
	public sortActivityName(char sortMode) {
		this.sort = sortMode;
	}

	@Override
	public int compare(Activity theActivity, Activity otherActivity) {
		int result = theActivity.getName().compareTo(otherActivity.getName());
		if(sort == 'a') return result ;
		return result * -1;
	}

}
