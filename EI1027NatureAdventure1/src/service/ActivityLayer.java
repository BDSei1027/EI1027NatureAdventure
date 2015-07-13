package service;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Repository;

import classes.Activity;
import database.daoActivity;

@SuppressWarnings(value = {"unchecked", "unused"})
@Repository 
public class ActivityLayer {
	private int activityID;
	private daoActivity daoActivity;
	
	/*
	 * ACTIVITY ZONE
	 */
	
	/**
	 * Add a activity in the database.
	 * @param The activity
	 */
	public void addActivity(Activity activity){ //Dependencia de activityID de LogicLayer
		this.activityID ++;
		activity.setIdAct(activityID);
		daoActivity.addElement(activity);
	}
	
	/**
	 * Set inactive an activity from the database. The ssNumber is required.
	 * @param idActivity of the activity
	 */
	public void inactiveActivity(int code){
		Activity myActivity = this.getActivity(code);
		myActivity.setIsActive(false);
		this.updateActivity(myActivity);
	}
	
	/**
	 * Set inactive an activity from the database.
	 * @param The activity
	 */
	public void inactiveActivity(Activity activity){
		this.inactiveActivity(activity.getIdAct());	
	}
	
	
	/**
	 * Set active an activity from the database. The ssNumber is required.
	 * @param idActivity of the activity
	 */
	public void activateActivity(int code){
		Activity myActivity = this.getActivity(code);
		myActivity.setIsActive(true);
		this.updateActivity(myActivity);	
	}
	
	/**
	 * Set active an activity from the database.
	 * @param The activity
	 */
	public void activateActivity(Activity activity){
		this.inactiveActivity(activity.getIdAct());	
	}
	
	/**
	 * Update an activity from the database. This operation is only allowed when the activity was registered before in the database.
	 * @param The activity
	 */
	public void updateActivity(Activity activity){
		daoActivity.updateElement(activity);
	}
	
	/**
	 * Given a code a activity is delivered. If the activity doesn't exist in the database it returns null 
	 *@return An activity or null
	 */
	public Activity getActivity(int code){
		Activity myActivity = (Activity) daoActivity.getElement(code);
		return myActivity;
	}
	
	/**Given a code a activity is delivered. If the activity doesn't exist in the database it returns null 
	 * @param name
	 * @return An activity or null
	 */
	public Activity getActivity(String name){
		Activity myActivity = (Activity) daoActivity.getElement(name);
		return myActivity;
	}
	
	/**
	 * Get all the activities from the database
	 * @return A collection of Activity  with all activities
	 */
	public Collection<Activity> getAllActivities(){ //Devuelvo solo lista de actividades para facilitar tarea a la vista
		Map<Integer,Activity> allInstructors = (Map<Integer,Activity>) daoActivity.getElements();
		Collection<Activity> allInstructorsClasses= allInstructors.values();
		return allInstructorsClasses;
		
	}
	
	/** Get all the activities that are active in the database
	 * @return A collection of Activity with all active activities
	 */
	public Collection<Activity> getAllActivitiesActive() {
		Map<Integer, Activity> map = (Map<Integer, Activity>) daoActivity.getElementsActive();
		Collection<Activity> collection = map.values();
		return collection;
	}
	
	
	/** Get all the activities that are active in the database
	 * @return A collection of Activity with all inactive activities
	 */
	public Collection<Activity> getAllActivitiesInactive() {
		Map<Integer, Activity> map = (Map<Integer, Activity>) daoActivity.getElementsInactive();
		Collection<Activity> collection = map.values();
		return collection;
	}

	public void setDaoActivity(daoActivity daoActivity) {
		this.daoActivity = daoActivity;
	}

	public void inicializarActivityID(){
		try {
			this.activityID = daoActivity.getMaxID();
			} catch (Exception e) {
				this.activityID = 0;
				System.out.println("activityID = 0");
			}
	}
}
