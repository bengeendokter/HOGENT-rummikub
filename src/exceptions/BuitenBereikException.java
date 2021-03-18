package exceptions;

@SuppressWarnings("serial")
public class BuitenBereikException extends IllegalArgumentException
{

	public BuitenBereikException()
	{
		super("Het aantal gebruikers ligt buiten het bereik");
	}

	public BuitenBereikException(String s)
	{
		super(s);
	}

}
