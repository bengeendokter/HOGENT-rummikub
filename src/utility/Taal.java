package utility;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import exceptions.FoutieveTaalInvoerException;

public class Taal
{
	
    private final static ResourceBundle ENGELS =
    		ResourceBundle.getBundle("resources.ResourceBundle", new Locale("en"));
    private final static ResourceBundle NEDERLANDS =
    		ResourceBundle.getBundle("resources.ResourceBundle", new Locale("nl"));
	private ResourceBundle resourceBundle;
	
	private final static List<String> TALEN = Arrays.asList("nl", "en");
	
	// default constructor
	public Taal()
	{
		this("nl");
	}
	
	// constructor met meegegeven taal
	public Taal(String taal)
	{
		setResourceBundle(taal);
	}
	
	// stelt de juiste taal in
	public void setResourceBundle(String taal)
	{
		String taalLower = taal.toLowerCase();
		
		if(!TALEN.contains(taalLower))
		{
			throw new FoutieveTaalInvoerException(
					"Taal moet \"nl\" of \"en\" zijn\n"
					+ "Language must be \"nl\" or \"en\"");
		}
		
		if(taalLower.equals("nl"))
		{
			resourceBundle = NEDERLANDS;
		}
		else // taalLower.equals("en")
		{
			resourceBundle = ENGELS;
		}
	}

	public String getMessages(String message)
	{
		return resourceBundle.getString(message);
	}
	
	public void veranderTaal()
	{
    	setResourceBundle(resourceBundle.equals(NEDERLANDS) ? "en" : "nl");
	}
}
