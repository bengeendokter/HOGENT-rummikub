package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.BuitenBereikException;
import exceptions.ReedsAangemeldException;
import utility.Taal;

public class DomeinController
{

	private final SpelerRepository spelerrepo;
	private List<Speler> spelers;
	private Taal taal;

	public DomeinController()
	{
		spelerrepo = new SpelerRepository();
		spelers = new ArrayList<>();
		taal = new Taal();
	}

	public void registreerAantal(int aantal)
	{

		if (aantal < 2 || aantal > 4)
		{

			throw new BuitenBereikException("Aantal spelers is kleiner dan 2 of groter dan 4");
		}
	}

// SpelerRepository functies
	public void meldAan(String gebruikersnaam, String wachtwoord)
	{
		Speler gevondenSpeler = spelerrepo.geefSpeler(gebruikersnaam, wachtwoord);

		if (geefLijstGebruikersnaam().contains(gebruikersnaam))
		{

			throw new ReedsAangemeldException();
		}

		spelers.add(gevondenSpeler);
	}


	public List<String> geefLijstGebruikersnaam()
	{
		List<String> gebruikersnamen = new ArrayList<>();

		for (Speler speler : spelers)
		{

			gebruikersnamen.add(speler.getGebruikersnaam());
		}
		return gebruikersnamen;
	}

	public List<Speler> getSpelers()
	{
		return spelers;
	}

// Taal functies
	public void setResourceBundle(String taalcode)
	{
		taal.setResourceBundle(taalcode);
	}

	public String getMessages(String message)
	{
		return taal.getMessages(message);
	}

	public void veranderTaal()
	{
		taal.veranderTaal();
	}
}
