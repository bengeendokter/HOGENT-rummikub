package exceptions;


@SuppressWarnings("serial")
public class FoutieveTaalInvoerException extends IllegalArgumentException
{

	public FoutieveTaalInvoerException()
	{
		super("Er is een foute taal meegegeven\n"
				+ "A wrong language has been given");
	}

	public FoutieveTaalInvoerException(String s)
	{
		super(s);
	}
}
