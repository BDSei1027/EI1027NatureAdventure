package classes;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Activity {
    private int idAct;
    private String name;
    private int level;
    private String schedule;
    private float price;
    private String place;
    private int minimumGroup;
    private int maximumGroup;
    private boolean isActive;
    private String nombre;
    private String description;
    private String Descripcion;
    private List<String> qualifiedInstructors;

    public Activity() {
        super();
    }

    public Activity(Activity actividad){
        super();
        setIdAct(actividad.getIdAct());
        setName(actividad.getName());
        setLevel(actividad.getLevel());
        setSchedule(actividad.getSchedule());
        setPrice(actividad.getPrice());
        setPlace(actividad.getPlace());
        setMinimumGroup(actividad.getMinimumGroup());
        setMaximumGroup(actividad.getMaximumGroup());
        setIsActive(actividad.isActive());
        setInstructors(actividad.getInstructors());
    }

    @Override
    public boolean equals( Object obj) {
        if ( obj == null ) return false;
        if ( this == obj ) return true;
        if ( ! (obj instanceof Activity ) ) return false;
        Activity actComprobar = (Activity) obj;
        if ( idAct != actComprobar.getIdAct() ) return false;
        if ( !name.equals(actComprobar.getName()) ) return false;
        if ( level != actComprobar.getLevel() ) return false;
        if ( !schedule.equals(actComprobar.getSchedule()) ) return false;
        if ( price != actComprobar.getPrice() ) return false;
        if ( !place.equals(actComprobar.getPlace()) ) return false;
        if ( minimumGroup != actComprobar.getMinimumGroup() ) return false;
        if ( maximumGroup != actComprobar.getMaximumGroup() ) return false;
        if ( isActive != actComprobar.isActive()) return false;
        if ( !qualifiedInstructors.containsAll(actComprobar.getInstructors())) return false;
        return true;
    }
    
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Activity, ID: " + idAct + ", name: " + name);
		sb.append(", level: " + level + ", schedule: " + schedule);
		sb.append(", price: " + price + ", place: " + place);
		sb.append(", group: [" + minimumGroup + ", " + maximumGroup + "]"
				+ ", isActive: " + isActive);
    	return sb.toString();
    	
    }

    /*
    SETTERS Y GETTERS
     */

    public int getIdAct() {
        return idAct;
    }

    public void setIdAct(int codAct) {
        this.idAct = codAct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level){
    	this.level = level;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMinimumGroup() {
        return minimumGroup;
    }

    public void setMinimumGroup(int minimumGroup){
        this.minimumGroup = minimumGroup;
    }

    public int getMaximumGroup() {
        return maximumGroup;
    }

    public void setMaximumGroup(int maximumGroup){
    	this.maximumGroup = maximumGroup;
    }
    
    public void setIsActive(boolean state) {
    	this.isActive = state;
    }
    
    public boolean isActive() {
    	return this.isActive;
    }
    
    public List<String> getInstructors() {
    	return qualifiedInstructors;
    }
    
    public void setInstructors(List<String> array) {
    	this.qualifiedInstructors = array;
    }
    
    public int getNInstructors() {
    	return this.qualifiedInstructors.size();
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
    
}
