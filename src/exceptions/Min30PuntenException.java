package exceptions;

@SuppressWarnings("serial")
public class Min30PuntenException extends IllegalArgumentException
{
	public Min30PuntenException()
	{
		super("De speler moet minimum 30 punten hebben bij het eerste keer aanleggen");
	}
	
	public Min30PuntenException(String s)
	{
		super(s);
	}
}
