package comparators;

import java.util.Comparator;

import classes.Activity;

public class sortActivityDate implements Comparator<Activity> {

	private char sort;

	public sortActivityDate(char sortMode) {
		this.sort = sortMode;
	}

	@Override
	public int compare(Activity theActivity, Activity otherActivity) {
		if(sort == 'a') return valueOf(theActivity.getSchedule()) - valueOf(otherActivity.getSchedule());
		return valueOf(otherActivity.getSchedule()) - valueOf(theActivity.getSchedule());
	}

	private int valueOf(String schedule) {
		if(schedule.equals("morning")) return 1;
		if(schedule.equals("afternoon")) return 0;
		if(schedule.equals("noon")) return -1;
		return 0;
	}



}
