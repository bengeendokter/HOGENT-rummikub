package exceptions;


@SuppressWarnings("serial")
public class NogPlaatsOpVeldException extends IllegalArgumentException
{

public NogPlaatsOpVeldException()
{
	super("Er is nog plaats vrij op het gemeenschappelijk veld. Probeer je stenen daarop te leggen");
}

public NogPlaatsOpVeldException(String s)
{
	super(s);
}
}

