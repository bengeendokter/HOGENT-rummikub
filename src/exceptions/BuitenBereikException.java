package exceptions;

public class BuitenBereikException extends IllegalArgumentException {

	public BuitenBereikException() {
		super("The amount of users needs to be a number between 2 and 4");
	}

	public BuitenBereikException(String s) {
		super(s);
	}
	

}
