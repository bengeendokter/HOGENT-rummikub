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
	
	/**
	 * Use Case 1:
	 * Gooit een exception indien het aantal spelers buiten het bereik ligt
	 * 
	 * @param aantal	het aantal spelers die moeten geregistreerd worden
	 */
	public void registreerAantal(int aantal)
	{
		if (aantal < 2 || aantal > 4)
		{
			throw new BuitenBereikException("Aantal spelers is kleiner dan 2 of groter dan 4");
		}
	}
	
// Use Case 1 functies
	/**
	 * Use Case 1:
	 * Meld de gebruikers aan, gooit een exception indien het aanmelden mislukt
	 * 
	 * @param gebruikersnaam	de gebruikersnaam waarnaar gezocht wordt in de databank
	 * @param wachtwoord		het wachtwoord waarnaar gezocht wordt in de databank
	 */
	public void meldAan(String gebruikersnaam, String wachtwoord)
	{
		Speler gevondenSpeler = spelerrepo.geefSpeler(gebruikersnaam, wachtwoord);

		if (geefLijstGebruikersnaam().contains(gebruikersnaam))
		{
			throw new ReedsAangemeldException();
		}

		spelers.add(gevondenSpeler);
	}

	/**
	 * Use Case 1:
	 * Geeft een lijst van de aangemelde gebruikers
	 * 
	 * @return	een List van gebruikersnamen
	 */
	public List<String> geefLijstGebruikersnaam()
	{
		List<String> gebruikersnamen = new ArrayList<>();

		for (Speler speler : spelers)
		{
			gebruikersnamen.add(speler.getGebruikersnaam());
		}
		return gebruikersnamen;
	}

// Use Case 2 functies
	
// Taal functies
	/**
	 * Stelt de taal in, dit kan Nederlands of Engels zijn
	 * 
	 * @param taalcode	de afkorting van de taal die moet ingesteld worden (nl of en)
	 */
	public void setTaal(String taalcode)
	{
		taal.setResourceBundle(taalcode);
	}

	/**
	 * Geeft de gevraagde output terug in de huidig ingestelde taal
	 * 
	 * @param message	de sleutel van de output die moet gegeven worden
	 * @return			output tekst in de huidig ingestelde taal
	 */
	public String getMessages(String message)
	{
		return taal.getMessages(message);
	}
	
	/**
	 * Veranderd de huidig ingestelde taal, methode voor de taal knop in de GUI
	 */
	public void veranderTaal()
	{
		taal.veranderTaal();
	}
}
