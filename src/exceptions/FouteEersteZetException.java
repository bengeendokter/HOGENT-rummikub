package exceptions;

@SuppressWarnings("serial")
public class FouteEersteZetException extends IllegalArgumentException
{
	
	public FouteEersteZetException()
	{
		super("Als eerste zet moet je stenen aanlegen op het werkveld");
	}
	
	public FouteEersteZetException(String s)
	{
		super(s);
	}	
}
