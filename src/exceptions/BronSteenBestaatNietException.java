package exceptions;

@SuppressWarnings("serial")
public class BronSteenBestaatNietException extends IllegalArgumentException
{
	
	public BronSteenBestaatNietException()
	{
		super("De aangeduide steen in bron bestaat niet");
	}
	
	public BronSteenBestaatNietException(String s)
	{
		super(s);
	}	
}

