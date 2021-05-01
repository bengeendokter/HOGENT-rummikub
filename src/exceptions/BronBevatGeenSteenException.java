package exceptions;

@SuppressWarnings("serial")
public class BronBevatGeenSteenException extends IllegalArgumentException {

	public BronBevatGeenSteenException() {
		super("De positie van bron bevat geen steen. Kies een ander positie");
	}

	public BronBevatGeenSteenException(String s) {
		super(s);
	}
	
}

