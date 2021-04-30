package exceptions;


@SuppressWarnings("serial")
public class SteenAanleggenException extends IllegalArgumentException
{

	public SteenAanleggenException()
	{
		super("Steen kan niet aangelegd worden omdat er geen serie of rij gevormd kan worden. Als je nog altijd geen steen kan plaatsen, probeer te resetten");
	}

	public SteenAanleggenException(String s)
	{
		super(s);
	}
}
