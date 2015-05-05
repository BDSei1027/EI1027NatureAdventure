package database;


public interface DaoInterface {

	/**
	 * Method to add an Object into de DB
	 * @param element Object to add
	 */
	public  void addElement(Object element);
	
	/**
	 * Method to remove an Object from the DB
	 * @param element Identifier of the Object
	 */
	public void deleteElement(Object element);
	
	/**
	 * Method to update an Object in the DB
	 * @param element Objecto to update
	 */
	public void updateElement(Object element);
	
	/**
	 * Method to obtain an Object from the DB
	 * @param identifier Object's identifier
	 * @return Object
	 */
	public Object getElement(Object identifier);
	
	/**
	 * Method to obtain all the Objects from the DB
	 * @return Collections<Object>
	 */
	public Object getElements();
	
}
