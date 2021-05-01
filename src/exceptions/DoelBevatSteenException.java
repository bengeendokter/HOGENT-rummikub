package exceptions;

@SuppressWarnings("serial")
public class DoelBevatSteenException extends IllegalArgumentException {

	public DoelBevatSteenException() {
		super("De positie van doel bevat een steen. Kies een ander positie");
	}

	public DoelBevatSteenException(String s) {
		super(s);
	}
	
}
