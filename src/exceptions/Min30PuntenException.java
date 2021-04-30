package exceptions;

@SuppressWarnings("serial")
public class Min30PuntenException extends IllegalArgumentException{

	public Min30PuntenException() {
		super("De speler moet minimum 30 punten "
				+ "hebben bij eerste keer aanleggen "
				+ "op het gemeenschappelijk veld");
	}

	public Min30PuntenException(String s) {
		super(s);
	}
	
	

}
