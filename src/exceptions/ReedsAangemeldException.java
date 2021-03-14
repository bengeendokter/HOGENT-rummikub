package exceptions;


@SuppressWarnings("serial")
public class ReedsAangemeldException extends IllegalArgumentException
{

	public ReedsAangemeldException()
	{
		super("De gebruiker is al aangemeld");
	}

	public ReedsAangemeldException(String s)
	{
		super(s);
	}
}
