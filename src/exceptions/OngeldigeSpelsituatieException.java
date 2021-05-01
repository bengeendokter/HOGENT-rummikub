package exceptions;

@SuppressWarnings("serial")
public class OngeldigeSpelsituatieException extends IllegalArgumentException {

	public OngeldigeSpelsituatieException() {
		super("Dit is een ongeldige spelsituatie (je kan beter je beurt resetten)");
	}

	public OngeldigeSpelsituatieException(String s) {
		super(s);
	}

}
