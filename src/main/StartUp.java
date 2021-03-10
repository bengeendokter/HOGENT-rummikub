package main;

import domein.DomeinController;
import cui.UseCase1Applicatie;

public class StartUp
{

	public static void main(String[] args)
	{
		DomeinController controller = new DomeinController();
		UseCase1Applicatie app = new UseCase1Applicatie(controller);
		
		//tijdelijke test methode
		app.geefSpeler();
	}
}
