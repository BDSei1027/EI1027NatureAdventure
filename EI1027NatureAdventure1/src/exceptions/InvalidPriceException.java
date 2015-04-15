package exceptions;

public class InvalidPriceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float price;

	public InvalidPriceException(float price) {
		this.price = price;
	}

	@Override
	public void printStackTrace() {
		System.err.println("ERROR: El precio "+price+" es menor que 0.");
	}
}
