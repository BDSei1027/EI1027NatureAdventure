package comparators;

import java.util.Comparator;

import classes.Activity;

public class sortActivityId implements Comparator<Activity> {

	private char sort;

	public sortActivityId(char sortMode) {
		this.sort = sortMode;
	}

	@Override
	public int compare(Activity theActivity, Activity otherActivity) {
		if(sort == 'a') return theActivity.getIdAct() - otherActivity.getIdAct();
		return otherActivity.getIdAct() - theActivity.getIdAct();
	}

}
