package exceptions;

@SuppressWarnings("serial")
public class SplitsenMetEenException extends IllegalArgumentException {

	public SplitsenMetEenException() {
		super("Als je wilt splitsen moet de rij bestaan en mag het niet leeg zijn. De kolom mag niet 1 (eeste steen) en de laatste steen zijn");
	}

	public SplitsenMetEenException(String s) {
		super(s);
	}
	
}
