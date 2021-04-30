package exceptions;

@SuppressWarnings("serial")
public class OngeldigInvoerException extends IllegalArgumentException {

	public OngeldigInvoerException() {
		super("De positie van doel en/of bron "
				+ "mag niet leeg zijn, moet een strikt "
				+ "positief getal zijn en moet verwijzen "
				+ "naar een correct positie");
	}

	public OngeldigInvoerException(String s) {
		super(s);
	}
	
}
