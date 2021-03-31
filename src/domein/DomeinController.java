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
	private Spel spel;

	public DomeinController()
	{
		spelerrepo = new SpelerRepository();
		spelers = new ArrayList<>();
		taal = new Taal();
	}
	
	
// Use Case 1 functies
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
	/**
	 * Use Case 2:
	 * Start een nieuw spel door een Spel object aan te maken
	 */
	public void startSpel()
	{
		this.spel = new Spel(spelers);
	}
	
	/**
	 * Use Case 2:
	 * Geeft de gebruikersnaam van de speler die momenteel aan de beurt is
	 * 
	 * @return	de gebruikersnaam van de speler aan de beurt
	 */
	public String geefNaamSpelerAanBeurt()
	{
		return spel.getSpelerAanDeBeurt().getGebruikersnaam();
	}
	
	/**
	 * Use Case 2:
	 * Geeft een overzicht van de score per speler
	 * 
	 * @return	een lijst met de gebruikersnamen en scores van de spelers
	 */
	public List<String> geefScoreOverzicht()
	{
		spel.berekenScore();
		
		List<String> puntenlijst = new ArrayList<String>();
		
		for(Speler speler : spelers)
		{
			puntenlijst.add(String.format("%13s: %4d", speler.getGebruikersnaam(), speler.getScore()));
		}
		
		return puntenlijst;
	}
	
	/**
	 * Use Case 2:
	 * Geeft terug of het spel ten einde is of niet
	 * 
	 * @return	true indien een speler geen stenen meer heeft, anders false
	 */
	public boolean isEindeSpel()
	{
		return spel.isEindeSpel();
	}
	
	// TODO tijdelijke methode voor UC2App, verwijder deze later
	// Verwijdert alle stenen van een speler door een nieuwe speler aan te maken, dit is nu de winnaar
	public void eindigSpel()
	{
		Speler winnaar = spelers.get(1);
		winnaar = new Speler(winnaar.getGebruikersnaam(), winnaar.getWachtwoord());
		spelers.set(1, winnaar);
	}
	
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
