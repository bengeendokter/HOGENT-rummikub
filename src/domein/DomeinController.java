package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.BronSteenBestaatNietException;
import exceptions.BuitenBereikException;
import exceptions.FouteEersteZetException;
import exceptions.FoutePositieException;
import exceptions.FoutieveTaalInvoerException;
import exceptions.GeenPlaatsOpRijException;
import exceptions.GeenSerieOfRijException;
import exceptions.GeenSpelerSteenOpPlaats;
import exceptions.Min30PuntenException;
import exceptions.NullSteenNaarWerkveldException;
import exceptions.ReedsAangemeldException;
import exceptions.SpelerNietGevondenException;
import exceptions.SplitsenMetEenException;
import exceptions.SteenIsGeenJokerException;
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
	public void registreerAantal(int aantal) throws BuitenBereikException
	{
		if(aantal < 2 || aantal > 4)
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
	throws ReedsAangemeldException, SpelerNietGevondenException, RuntimeException
	{
		Speler gevondenSpeler = spelerrepo.geefSpeler(gebruikersnaam, wachtwoord);
		
		if(spelers.contains(gevondenSpeler))
		{
			throw new ReedsAangemeldException();
		}
		
		spelers.add(gevondenSpeler);
	}
	
	// TODO tijdelijke methode om zonder internet het programma te testen, verwijder later
//	public void meldAanOffline()
//	{
//		Speler speler1 = new Speler("IkBenBen", "IkBenDokter", 0);
//		Speler speler2 = new Speler("mns58", "myDiscordPassword", 0);
//		
//		spelers.add(speler1);
//		spelers.add(speler2);
//	}
	
	/**
	 * Use Case 1:
	 * Geeft een lijst van de aangemelde gebruikers
	 * 
	 * @return	een List van gebruikersnamen
	 */
	public List<String> geefLijstOverzicht()
	{
		List<String> overzicht = new ArrayList<>();
		
		Collections.sort(spelers);
		
		for(int i = 0; i < spelers.size(); i++)
		{
			overzicht.add(String.format("%d. %-15s %4d", i + 1, spelers.get(i).getGebruikersnaam() + ":", spelers.get(i).getTotaalScore()));
		}

		return overzicht;
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
	 * Geeft een overzicht van de score per speler en update lokaal de totaal score van elke speler
	 * 
	 * @return	een lijst met de gebruikersnamen en scores van de spelers
	 */
	public List<String> geefScoreOverzicht()
	{
		spel.berekenScore();
		
		List<String> puntenlijst = new ArrayList<String>();
		
		spelers.stream()
			   .forEach(speler ->
			   {
				   speler.updateTotaalScore();
				   puntenlijst.add(String.format("%-15s %4d", speler.getGebruikersnaam() + ":", speler.getScore()));
			   });
		
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
//	public void eindigSpel()
//	{
//		Speler winnaar = spelers.get(1);
//		winnaar = new Speler(winnaar.getGebruikersnaam(), winnaar.getWachtwoord(), winnaar.getTotaalScore());
//		spelers.set(1, winnaar);
//	}
	
// Use Case 3 functies
	/**
	 * Use Case 3:
	 * Start een nieuw beurt door methode startBeurt() uit klasse Spel aan te roepen
	 */
	public void startBeurt()
	{
		spel.startBeurt();
	}
	
	/**
	 * Use Case 3:
	 * Beëindigt de beurt door de methode beeindigBeurt() uit klasse Spel aan te roepen
	 * 
	 * @return	geeft de toString van de speler stenen terug
	 * 			om weer te geven of de speler aan de beurt een extra steen kreeg of niet
	 */
	public String beeindigBeurt() throws GeenSerieOfRijException, Min30PuntenException
	{
		return spel.beeindigBeurt();
	}
	
	/**
	 * Use Case 3:
	 * Reset de beurt door de methode resetBeurt() uit klasse spel aan te roepen
	 */
	public void resetBeurt()
	{
		spel.resetBeurt();
	}
	
	/**
	 * Use Case 3:
	 * Legt steen aan op een veld (gemeenschappelijk (doelVeldIndex = 0) of werkveld (doelVeldIndex = 1))
	 * door methode legSteenAan() uit klasse Spel aan te roepen
	 * 
	 * @param positieDoel	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft waar de steen aangelegd moet worden
	 * @param doelIsWv 		boolean geeft true als steen naar het werkveld moet (false voor gemeen. veld)
	 * @param positieBron 	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft vanwaar de steen afkomstig is
	 * @param bronIsWv		boolean geeft true als steen zich bevindt op het werkveld (false voor persoonlijke bezit)
	 */
	public void legSteenAan(int[] positieDoel, boolean doelIsWv, int[] positieBron, boolean bronIsWv)
	throws FouteEersteZetException, GeenSerieOfRijException, FoutePositieException
	, GeenPlaatsOpRijException, GeenSpelerSteenOpPlaats
	{
		spel.legSteenAan(positieDoel, doelIsWv, positieBron, bronIsWv);
	}
	

	/**
	 * Use Case 3: 
	 * Rij of serie splitsen door methode splitsRijOfSerie() uit klasse Spel aan te roepen 
	 * 
	 * @param positieDoel	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft waar er gesplitst moet worden
	 * @param doelIsWv		boolean geeft true als het op het werkveld moet gesplitst worden (false voor gemeen. veld)
	 */
	public void splitsRijOfSerie(int[] positieDoel, boolean doelIsWv)
	throws FouteEersteZetException, GeenSerieOfRijException, FoutePositieException
	, GeenPlaatsOpRijException, GeenSpelerSteenOpPlaats, SplitsenMetEenException
	{
		spel.splitsRijOfSerie(positieDoel, doelIsWv);
	}
	
	/**
	 * Use Case 3:
	 * Vervangt een steen met een joker door methode vervangJoker() uit klasse Spel aan te roepen
	 * 
	 * @param positieDoel	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft waar een steen vervangen moet worden
	 * @param doelIsWv		boolean geeft true als steen op het werkveld vervangen moet worden (false voor gemeen. veld)
	 * @param positieBron	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft vanwaar een steen afkomstig is
	 * @param bronIsWv		boolean geeft true als steen zich bevindt op het werkveld (false voor persoonlijke bezit)
	 */
	public void vervangJoker(int[] positieDoel, boolean doelIsWv, int[] positieBron, boolean bronIsWv)
	throws FouteEersteZetException, GeenSerieOfRijException, FoutePositieException
	, GeenPlaatsOpRijException, GeenSpelerSteenOpPlaats, SteenIsGeenJokerException, BronSteenBestaatNietException
	{
		spel.vervangJoker(positieDoel, doelIsWv, positieBron, bronIsWv);
	}
	
	/**
	 * Use Case 3:
	 * Vervangt een steen naar het werkveld door methode verplaatsNaarWerkveld() uit klasse Spel aan te roepen
	 * 
	 * @param positieDoel	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft waar een steen verplaatst moet worden
	 * @param positieBron	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft vanwaar een steen afkomstig is
	 */
	public void verplaatsNaarWerkveld(int[] positieDoel, int[] positieBron)
	throws FouteEersteZetException, GeenSerieOfRijException, FoutePositieException
	, GeenPlaatsOpRijException, GeenSpelerSteenOpPlaats, NullSteenNaarWerkveldException
	{
		spel.verplaatsNaarWerkveld(positieDoel, positieBron);
	}
	
	/**
	 * Use Case 3:
	 * Geeft een volledig overzicht van de stenen van de speler, het werkveld en het gemeenschappelijkveld
	 * 
	 * @return	String array met de To Sting van het gemeenschappelijkveld (0), het werkveld (1) en de speler stenen (2) 
	 */
	public String[] geefSpelOverzicht()
	{
		return spel.geefSpelOverzicht();
	}
	
	/**
	 * Use Case 4:
	 * Upload de totaal score van elke speler naar de databank
	 */
	public void updateTotaalScore() throws RuntimeException
	{
		spelers.stream()
			   .forEach(speler -> spelerrepo.updateTotaalScore(speler));
	}
	
// Taal functies
	/**
	 * Stelt de taal in, dit kan Nederlands of Engels zijn
	 * 
	 * @param taalcode	de afkorting van de taal die moet ingesteld worden (nl of en)
	 */
	public void setTaal(String taalcode) throws FoutieveTaalInvoerException
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
