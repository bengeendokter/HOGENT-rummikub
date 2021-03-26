package cui;

import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.FoutieveTaalInvoerException;

public class UseCase2Applicatie
{
	private boolean fouteInput = true;
	private Scanner input = new Scanner(System.in);
	
	private final DomeinController controller;
	
	public UseCase2Applicatie(DomeinController controller)
	{
		this.controller = controller;
		
		precondities();
		stelTaalIn();
		
		startSpel();
		geefNaamSpelerAanBeurt();
		geefScoreOverzicht();
		isEindeSpel();
	}


	private void precondities()
	{
			controller.meldAan("IkBenBen", "IkBenDokter");
			controller.meldAan("mns58", "myDiscordPassword");
			controller.meldAan("IceBergUser58", "hogenthogent123");
			controller.meldAan("TUF", "Thangz");
	}
	
	public void stelTaalIn()
	{
		do
		{
			try
			{
				System.out.print("Taal/Language [nl, en]: ");
				String taal = input.nextLine();

				controller.setTaal(taal);

				fouteInput = false;
			}
			catch (FoutieveTaalInvoerException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while (fouteInput);

		System.out.println();
	}
	
	private void startSpel()
	{
		controller.startSpel();
		System.out.println(controller.getMessages("startSpel"));
		
		System.out.println();
	}

	private void geefNaamSpelerAanBeurt()
	{
		String gebruikersnaam = controller.geefNaamSpelerAanBeurt();
		System.out.println(String.format(controller.getMessages("aanBeurt"), gebruikersnaam));
		
		System.out.println();
	}

	private void geefScoreOverzicht()
	{
		System.out.println(controller.getMessages("eindScore"));
		
		List<String> lijstScores = controller.geefScoreOverzicht();
		for(String score : lijstScores)
		{
			System.out.println(score);
		}
		
		System.out.println();
	}

	private void isEindeSpel()
	{
		System.out.println(controller.getMessages("eindeSpel") + " " + controller.isEindeSpel());
		
		System.out.println();
	}
}
