package exceptions;

@SuppressWarnings("serial")
public class GeenPlaatsOpRijException extends IllegalArgumentException
{
	
	public GeenPlaatsOpRijException()
	{
		super("De rij is vol, er kan geen steen bij aangelegd worden");
	}
	
	public GeenPlaatsOpRijException(String s)
	{
		super(s);
	}
}
