package cui;

import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomeinController;

public class UseCase1Applicatie
{
    private final DomeinController controller;
    
	public UseCase1Applicatie(DomeinController controller)
	{
		this.controller = controller;
	}
	
	// tijdelijke test methode, dit zoekt een gebruiker via gebruikersnaam en geeft het bijhorend wachtwoord terug
	public void geefSpeler()
	{	
		// de gebruikersnaam waarnaar gezocht wordt in de databank
		// IceBergUser58, IkBenBen, mns58, TUF
		String gebruikersnaam = "IkBenBen";
		String wachtwoord = "IkBenDokter";
		// nl = Nederlands of en = Engels
		String taal = "nl";
		
		Locale locale = new Locale(taal);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("cui.ResourceBundleCui", locale);
		
		
    	try
    	{	
            controller.meldAan(gebruikersnaam, wachtwoord);
        	System.out.print(String.format(resourceBundle.getString("msgSignIn")));
    	}
    	catch(IllegalArgumentException e)
    	{
    		System.out.print(resourceBundle.getString("msgPassWordIncorrect"));
    	}
    	catch(RuntimeException e)
    	{
    		System.out.print(resourceBundle.getString("msgUserNotFound"));
    	}

	}

}
