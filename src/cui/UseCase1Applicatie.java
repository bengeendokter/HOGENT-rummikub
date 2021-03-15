package cui;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.ReedsAangemeldException;

public class UseCase1Applicatie
{
    private final DomeinController controller;
    
	public UseCase1Applicatie(DomeinController controller)
	{
		this.controller = controller;
	}
	
	// tijdelijke test methode, dit zoekt een gebruiker via gebruikersnaam en geeft het bijhorend wachtwoord terug
	public void aanmeldenSpelers()
	{	
		int aantalGebruikers = 0;
		// data in de databank
		// ('IceBergUser58','hogenthogent123'),('IkBenBen','IkBenDokter'),('mns58','myDiscordPassword'),('TUF','Thangz')
		String gebruikersnaam;
		String wachtwoord;
		// nl = Nederlands of en = Engels
		String taal = "";
		final List<String> TALEN = Arrays.asList("nl", "en");
		Scanner input = new Scanner(System.in);
		boolean fouteInput = true;
		
		// vraag taal
		do
		{
			try
			{
				System.out.print("Taal/Language [nl, en]: ");
				taal = input.nextLine();
				
				// indien opgegeven taal niet "nl" of "en"
				if(!TALEN.contains(taal.toLowerCase()))
				{
					throw new IllegalArgumentException(
							"Taal moet \"nl\" of \"en\" zijn\n"
							+ "Language must be \"nl\" or \"en\"");
				}
				
				fouteInput = false;
			}
			catch(IllegalArgumentException e)
			{
				System.out.println(e.getMessage());
			}
		}while(fouteInput);
		
		Locale locale = new Locale(taal);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("cui.ResourceBundleCui", locale);
		
		System.out.println();
		
		// vraag aantal gebruikers
		fouteInput = true;
		do
		{
			try
			{
				System.out.print(resourceBundle.getString("askNrUsers"));
				aantalGebruikers = Integer.parseInt(input.nextLine());
				
				controller.registreerAantal(aantalGebruikers);
				fouteInput = false;
			}
			catch(IllegalArgumentException e)
			{
				System.out.println(resourceBundle.getString("askNrUsersError"));
			}
		}
		while(fouteInput);
		
		System.out.println();
		
		// meld alle gebruikers aan
		for(int index = 1; index <= aantalGebruikers; index++)
		{
			fouteInput = true;
			System.out.println(String.format(resourceBundle.getString("userIndex"), index));
			
			do
			{
		    	try
		    	{	
		    		System.out.print(resourceBundle.getString("userName"));
		    		gebruikersnaam = input.nextLine();
		    		
		    		System.out.print(resourceBundle.getString("PassWord"));
		    		wachtwoord = input.nextLine();
		    		
		            controller.meldAan(gebruikersnaam, wachtwoord);
		        	fouteInput = false;
		    	}
		    	catch(ReedsAangemeldException e)
		    	{
		    		System.out.println(resourceBundle.getString("reedsAangemeld"));
		    	}
		    	catch(IllegalArgumentException e)
		    	{
		    		System.out.println(resourceBundle.getString("msgPassWordIncorrect"));
		    	}
		    	catch(RuntimeException e)
		    	{
		    		System.out.println(resourceBundle.getString("msgUserNotFound"));
		    	}
			}
			while(fouteInput);
			
			System.out.println();
		}
		
		// geef lijst gebruikersnamen		
		System.out.println(resourceBundle.getString("lijstNamen"));
		
		List<String> gebruikersnamen = controller.geefLijstGebruikersnaam();
		for(String naam : gebruikersnamen)
		{
			System.out.println(naam);
		}
    	
    	input.close();
	}

}
