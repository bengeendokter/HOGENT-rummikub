package exceptions;

@SuppressWarnings("serial")
public class GeenSpelerSteenOpPlaats extends IllegalArgumentException
{
	
	public GeenSpelerSteenOpPlaats()
	{
		super("De steen is buiten het bereik van de speler stenen");
	}
	
	public GeenSpelerSteenOpPlaats(String s)
	{
		super(s);
	}
}
