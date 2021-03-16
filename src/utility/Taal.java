package utility;

import java.util.Locale;
import java.util.ResourceBundle;

public class Taal
{
	
    private final static ResourceBundle ENGELS =
    		ResourceBundle.getBundle("resources.ResourceBundle", new Locale("en"));
    private final static ResourceBundle NEDERLANDS =
    		ResourceBundle.getBundle("resources.ResourceBundle", new Locale("nl"));
	private ResourceBundle resourceBundle = NEDERLANDS;
	
	public Taal()
	{
		
	}
	
	/*
	public String getMessages()
	{
		
	}
	*/
	
	public void veranderTaal()
	{
		
	}
}
