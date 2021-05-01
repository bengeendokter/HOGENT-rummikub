package exceptions;

@SuppressWarnings("serial")
public class VeldControleBeurtBeindigenException extends IllegalArgumentException
{

	public VeldControleBeurtBeindigenException()
	{
		super("Beurt kan niet beëindigd worden wegens series en/of rijen in je velden die niet kloppen. Als je dit niet kan oplossen, probeer je beurt te resetten");
	}

	public VeldControleBeurtBeindigenException(String s)
	{
		super(s);
	}
	
	

}

