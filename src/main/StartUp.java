package main;

import domein.DomeinController;
import cui.UseCase1Applicatie;
import cui.UseCase2Applicatie;

public class StartUp
{

	public static void main(String[] args)
	{
		try
		{
			DomeinController controller = new DomeinController();
			new UseCase1Applicatie(controller);
			new UseCase2Applicatie(controller);
		}
		catch(Exception e)
		{
			System.out.println("Het scherm kan niet geladen worden\n"
								+ "The screen cannot be loaded");
			System.out.println("Contacteer de ontwikkelaars als dit probleem blijft optreden\n"
								+ "Contact the developers if this problem keeps occurring");
		}
	}
}
