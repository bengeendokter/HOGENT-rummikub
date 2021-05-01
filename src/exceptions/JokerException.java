	package exceptions;


@SuppressWarnings("serial")
public class JokerException extends IllegalArgumentException
{

	public JokerException()
	{
		super("De joker mag niet gebruikt worden bij het eerste keer aanleggen op gemeenschappelijk veld");
	}

	public JokerException(String s)
	{
		super(s);
	}
}

