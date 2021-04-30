package exceptions;

@SuppressWarnings("serial")
public class SteenKanJokerNietVervangenException extends IllegalArgumentException {

	public SteenKanJokerNietVervangenException() {
		super("De gekozen steen kan de joker "
				+ "niet vervangen in de serie of rij");
	}

	public SteenKanJokerNietVervangenException(String s) {
		super(s);
	}

}
