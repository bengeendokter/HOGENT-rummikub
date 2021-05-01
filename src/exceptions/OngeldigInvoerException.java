package exceptions;

@SuppressWarnings("serial")
public class OngeldigInvoerException extends IllegalArgumentException {

	public OngeldigInvoerException() {
		super("De positie van doel en/of bron "
				+ "mag niet leeg zijn en moet een strikt "
				+ "positief getal zijn");
	}

	public OngeldigInvoerException(String s) {
		super(s);
	}
	
}
