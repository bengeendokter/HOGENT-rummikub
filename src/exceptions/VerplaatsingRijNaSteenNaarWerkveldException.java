package exceptions;

@SuppressWarnings("serial")
public class VerplaatsingRijNaSteenNaarWerkveldException extends IllegalArgumentException {

	public VerplaatsingRijNaSteenNaarWerkveldException() {
		super("De stenen rechts van het genomen "
				+ "steen in een rij zijn niet een "
				+ "plaatsje naar links opgeschoven");
	}

	public VerplaatsingRijNaSteenNaarWerkveldException(String s) {
		super(s);
	}

}
