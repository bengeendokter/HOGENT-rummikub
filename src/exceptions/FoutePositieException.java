package exceptions;

@SuppressWarnings("serial")
public class FoutePositieException extends IllegalArgumentException {

	public FoutePositieException() {
		super("De positie van doel en/of bron ligt buiten het bereik (een rij gaat van 1 tot 13)");
	}

	public FoutePositieException(String s) {
		super(s);
	}
	
}
