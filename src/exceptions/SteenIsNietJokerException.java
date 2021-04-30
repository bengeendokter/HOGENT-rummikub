package exceptions;

@SuppressWarnings("serial")
public class SteenIsNietJokerException extends IllegalArgumentException
{

	public SteenIsNietJokerException()
	{
		super("De gekozen steen op het gemeenschappelijk veld is geen joker");
	}

	public SteenIsNietJokerException(String s)
	{
		super(s);
	}
}
