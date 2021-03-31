package cui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.BuitenBereikException;
import exceptions.FoutieveTaalInvoerException;
import exceptions.ReedsAangemeldException;
import exceptions.SpelerNietGevondenException;

public class UseCase1Applicatie
{

	private final DomeinController controller;
	private boolean fouteInput = true;
	private Scanner input = new Scanner(System.in);
	private int aantalGebruikers = 0;

	public UseCase1Applicatie(DomeinController controller)
	{
		this.controller = controller;
		start();
	}
	
	private void start()
	{
		stelTaalIn();
		selecteerAantalGebruikers();
		aanmeldenGebruikers();
		lijstGebruikersnamen();
	}

	// vraag taal
	// nl = Nederlands of en = Engels
	private void stelTaalIn()
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

	private void selecteerAantalGebruikers()
	{
		fouteInput = true;
		
		do
		{
			try
			{
				System.out.print(controller.getMessages("askNrUsers"));
				aantalGebruikers = input.nextInt();
				input.nextLine();

				controller.registreerAantal(aantalGebruikers);
				fouteInput = false;
			}
			catch (InputMismatchException e) // invoer is geen int
			{
				System.out.println(controller.getMessages("askNrUsersError"));
				input.nextLine();
			}
			catch (BuitenBereikException e) // invoer < 2 || invoer > 4
			{
				System.out.println(controller.getMessages("askNrUsersError"));
			}
		}
		while (fouteInput);

		System.out.println();
	}

	// meld alle gebruikers aan
	// data in de databank
	// ('IceBergUser58','hogenthogent123'),('IkBenBen','IkBenDokter'),('mns58','myDiscordPassword'),('TUF','Thangz')
	private void aanmeldenGebruikers()
	{
		String gebruikersnaam;
		String wachtwoord;

		for (int index = 1; index <= aantalGebruikers; index++)
		{
			fouteInput = true;
			System.out.println(String.format(controller.getMessages("userIndex"), index));

			do
			{
				try
				{
					System.out.print(controller.getMessages("userName"));
					gebruikersnaam = input.nextLine();

					System.out.print(controller.getMessages("PassWord"));
					wachtwoord = input.nextLine();

					controller.meldAan(gebruikersnaam, wachtwoord);
					fouteInput = false;
				}
				catch (ReedsAangemeldException e)
				{
					System.out.println(controller.getMessages("reedsAangemeld"));
				}
				catch(SpelerNietGevondenException e)
				{
					System.out.println(controller.getMessages("msgSignInFailed"));				
				}
				catch (RuntimeException e)
				{
					System.out.println(controller.getMessages("msgConnectionFailed"));
				}
			}
			while (fouteInput);

			System.out.println();
		}
	}

	// geef lijst gebruikersnamen
	private void lijstGebruikersnamen()
	{
		System.out.println(controller.getMessages("lijstNamen"));

		List<String> gebruikersnamen = controller.geefLijstGebruikersnaam();

		for (String naam : gebruikersnamen)
		{
			System.out.println(naam);
		}
		
		System.out.println();
	}
}
