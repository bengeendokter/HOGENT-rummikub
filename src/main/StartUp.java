package main;

import domein.DomeinController;
import cui.UseCase1Applicatie;

public class StartUp
{

	public static void main(String[] args)
	{
		DomeinController controller = new DomeinController();
		new UseCase1Applicatie(controller).aanmeldenSpelers();;
	}
}
