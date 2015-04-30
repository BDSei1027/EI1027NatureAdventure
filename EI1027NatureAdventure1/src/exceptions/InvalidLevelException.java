package exceptions;

public class InvalidLevelException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int level;
	
	public InvalidLevelException(int level) {
		this.level = level;
	}
	
	@Override
	public void printStackTrace() {
		if (level < 0)
			System.err.println("ERROR: The current level is " + level + ", lower than 0");
		else if (level > 3)
			System.err.println("ERROR: The current level is " + level + ", greater than 3");
	}
}
