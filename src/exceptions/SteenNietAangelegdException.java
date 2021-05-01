package exceptions;

@SuppressWarnings("serial")
public class SteenNietAangelegdException extends IllegalArgumentException {

	public SteenNietAangelegdException() {
		super("Je hebt geen stenen aangelegd, daarvoor krijg je een steen uit de pot");
	}

	public SteenNietAangelegdException(String s) {
		super(s);
	}

}
