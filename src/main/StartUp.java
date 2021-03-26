package main;

import domein.DomeinController;
import cui.UseCase2Applicatie;

public class StartUp
{

	public static void main(String[] args)
	{
		DomeinController controller = new DomeinController();
		new UseCase2Applicatie(controller);
	}
}
