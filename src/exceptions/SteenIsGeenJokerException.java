package exceptions;

@SuppressWarnings("serial")
public class SteenIsGeenJokerException extends IllegalArgumentException
{

	public SteenIsGeenJokerException()
	{
		super("De gekozen steen op het veld is geen joker");
	}

	public SteenIsGeenJokerException(String s)
	{
		super(s);
	}
}
