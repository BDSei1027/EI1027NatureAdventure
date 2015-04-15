package classes;

import java.util.List;


/**
 * Created by serrano on 11/03/15.
 */
public class Instructor {
	private String ssNumber;
	private String idNumber;
	private String name;
	private String lastName;
	private String email;
	private String telephone;
	private boolean isActive;
	private List<Integer> activities;
	
	

	public Instructor(){
		super();
	}
	
	public Instructor(Instructor monitor) {
		super();
		setSsNumber(monitor.getSsNumber());
		setIdNumber(monitor.getIdNumber());
		setName(monitor.getName());
		setLastName(monitor.getLastName());
		setEmail(monitor.getEmail());
		setTelephone(monitor.getTelephone());
		setActive(monitor.isActive());
		setActivities(monitor.getActivities());
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) return false;
        if ( this == obj ) return true;
        if ( ! (obj instanceof Instructor) ) return false;
        Instructor instructor = (Instructor) obj;
        if ( !ssNumber.equals(instructor.getSsNumber()) ) return false;
        if ( !idNumber.equals(instructor.getIdNumber()) ) return false;
        if ( !name.equals(instructor.getName())) return false;
        if ( !lastName.equals(instructor.getLastName()) ) return false;
        if ( !email.equals(instructor.getEmail()) ) return false;
        if ( !telephone.equals(instructor.getTelephone()) ) return false;
        if ( isActive != instructor.isActive() ) return false;
        if ( !activities.containsAll(instructor.getActivities()) ) return false;
        
		return true;
	}

	/*
    SETTERS Y GETTERS
     */
	
	public String getSsNumber() {
		return ssNumber;
	}

	public void setSsNumber(String ssNumber) {
		this.ssNumber = ssNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public List<Integer> getActivities() {
		return activities;
	}
	
	public void setActivities(List<Integer> list) {
		this.activities = list;
	}
}
