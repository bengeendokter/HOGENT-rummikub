package cui;

import java.util.List;

import domein.DomeinController;

public class UseCase2Applicatie
{
	private final DomeinController controller;
	
	public UseCase2Applicatie(DomeinController controller)
	{
		this.controller = controller;
		start();
	}
	
	private void start()
	{
		startSpel();
		
		do
		{
			geefNaamSpelerAanBeurt();
			speelBeurt();
		}
		while(!controller.isEindeSpel());
		
		geefScoreOverzicht();
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
	
	// TODO tijdelijke methode voor UC2App, verwijder deze later
	private void speelBeurt()
	{
		controller.eindigSpel();
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
}
