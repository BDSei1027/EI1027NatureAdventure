package exceptions;

public class InvalidGroupSizeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupSize;

	public InvalidGroupSizeException(int groupSize) {
		this.groupSize = groupSize;
	}
	
	@Override
	public void printStackTrace() {
		System.err.println("ERROR: El tamano del grupo "+groupSize+" es menor a 0");
	}

}
