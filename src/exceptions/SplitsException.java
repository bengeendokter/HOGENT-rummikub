package exceptions;

@SuppressWarnings("serial")
public class SplitsException extends IllegalArgumentException {

	public SplitsException() {
		super("De splits positie is fout");
	}

	public SplitsException(String s) {
		super(s);
	}
	
}
