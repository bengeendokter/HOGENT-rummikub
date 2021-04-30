package exceptions;

@SuppressWarnings("serial")
public class SteenNaarWerkveldException extends IllegalArgumentException{

	public SteenNaarWerkveldException() {
		super("De steen moet komen uit een serie (start- of eindsteen) "
				+ "of rij van get gemeenschappelijk veld");
	}

	public SteenNaarWerkveldException(String s) {
		super(s);
	}

}
