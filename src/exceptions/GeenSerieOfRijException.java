package exceptions;

@SuppressWarnings("serial")
public class GeenSerieOfRijException extends IllegalArgumentException
{
	
	public GeenSerieOfRijException()
	{
		super("De stenen set is geen serie of rij");
	}
	
	public GeenSerieOfRijException(String s)
	{
		super(s);
	}
}
