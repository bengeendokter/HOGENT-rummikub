package exceptions;

@SuppressWarnings("serial")
public class FoutePositieException extends IllegalArgumentException {

	public FoutePositieException() {
		super("De positie van doel en/of bron moet verwijzen naar een correct positie (rij van 1 tot 13)");
	}

	public FoutePositieException(String s) {
		super(s);
	}
	
}
