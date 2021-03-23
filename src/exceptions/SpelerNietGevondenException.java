package exceptions;


@SuppressWarnings("serial")
public class SpelerNietGevondenException extends RuntimeException
{

	public SpelerNietGevondenException()
	{
		super("De speler gegevens werden niet gevonden");
	}

	public SpelerNietGevondenException(String message)
	{
		super(message);
	}
}
