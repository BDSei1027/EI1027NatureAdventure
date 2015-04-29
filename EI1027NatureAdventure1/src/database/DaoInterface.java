package database;


public interface DaoInterface {

	public abstract void addElement(Object element);
	
	public abstract void deleteElement(Object element);
	
	public abstract void updateElement(Object element);
	
	public abstract Object getElement(Object identifier);
	
	public abstract Object getElements();
	
}
