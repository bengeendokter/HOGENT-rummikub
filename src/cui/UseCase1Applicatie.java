package cui;

import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Speler;

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
		String gebruikersnaam = "mns58";
		// nl = Nederlands of en = Engels
		String taal = "en";
		
		Locale locale = new Locale(taal);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("cui.ResourceBundle", locale);
		
		Speler speler = controller.geefSpeler(gebruikersnaam);
		System.out.printf(resourceBundle.getString("geefSpeler")
				,speler.getGebruikersnaam()
				,speler.getWachtwoord());
	}

}
