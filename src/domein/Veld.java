package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.FoutePositieException;

public class Veld
{
	private List<StenenSet> stenenSets;
	private final boolean isGv;
	
	// TODO boolean zitInWerkveld in constructor StenenSet of in controle methode
	
	/**
	 * Use Case 3:
	 * Default constructor van Veld die een nieuw veld aanmaakt.
	 * Wordt gebruikt in het begin van een spel of om het werkveld te resetten
	 */
	public Veld(boolean isGv)
	{
		// Een gv heeft default 13 rijen en een wv 2. Elke rij/StenenSet heeft plaats voor 13 stenen
		this
		(
				new ArrayList<>(Arrays.asList(new StenenSet[ isGv ? 13 : 2]))
				.stream()
				.map(nullObject -> new StenenSet(new ArrayList<>(Arrays.asList(new Steen[13]))))
				.collect(Collectors.toList())
				, isGv
		);
	}
	
	/**
	 * Use Case 3:
	 * Constructor van Veld die een nieuw veld aanmaakt aan de hand van een lijst van StenenSets.
	 * Wordt gebruikt in de klasse Beurt om een copy van een bestaand gemeenschappelijk veld in op te slaan
	 * 
	 * @param stenenSets
	 */
	public Veld(List<StenenSet> stenenSets, boolean isGv)
	{
		this.stenenSets = stenenSets;
		this.isGv = isGv;
	}
	
	// TODO implementeer controles, hoe?
	
	public List<StenenSet> getStenenSets()
	{
		return stenenSets;
	}
	
	public boolean isGV()
	{
		return isGv;
	}
	
	/**
	 * Use Case 3:
	 * Voegt steen toe in een bestaande of tegelijkertijd aangemaakte StenenSet
	 * 
	 * @param positieSteen	array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 						kolom om de juiste steen van de juiste StenenSet te vinden)	die aangeeft vanwaar de steen afkomstig is
	 * @param steen			steen die zal toegevoegd worden
	 */
	public void voegSteenToe(int[] positieSteen, Steen steen)
	{
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		// TODO nog plaats exception
		
		// indien setIdex te hoog, maak een nieuwe stenenSet aan
		if(setIndex >= stenenSets.size())
		{
			maakStenenSet(steen);
		}
		else
		{
			
			// TODO we controler best niet of al een steen ligt want zo kan je geen steen invoegen en wordt joker vervangen moeilijk
	    	// controleer of er al een steen ligt
			// geefSteen controleert ook of de positie geldig is
//	    	if(geefSteen(positieSteen) != null)
//			{
//				throw new DoelBevatSteenException();
//			}
			
	    	// indien er geen steen ligt plaatsen we de nieuwe steen in de stenenSet
			StenenSet set = stenenSets.get(setIndex);
			set.voegSteenToe(steenIndex, steen);
		}
	}
	
	/**
	 * Use Case 3: 
	 * Verwijdert een steen door gegeven array (rij en kolom) en geeft die terug
	 * 
	 * @param positieSteen	array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 						kolom om de juiste steen van de juiste StenenSet te vinden)	die aangeeft vanwaar de steen afkomstig is
	 * @return 				verwijderde steen
	 */
	public Steen removeSteen(int[] positieSteen)
	{		
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		controleerGeldigePositie(positieSteen);
		
		StenenSet set = stenenSets.get(setIndex);
		return set.removeSteen(steenIndex);
		
	}
	
	/**
	 * Use Case 3: 
	 * Splitst een rij of serie in nieuwe StenenSets door gegeven array (rij en kolom),
	 * verwijdert oorspronkelijke (niet gesplitste StenenSet) en voegt de nieuwe StenenSets toe aan attribuut stenenSets
	 * 
	 * @param positieSplitsing	array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 							kolom om de juiste steen van de juiste StenenSet te vinden) die aangeeft waar er gesplitst moet worden
	 */
	public void splitsRijOfSerie(int[] positieSplitsing)
	{
		int setIndex = positieSplitsing[0];
		int splitsIndex = positieSplitsing[1];
		
		StenenSet set = stenenSets.remove(setIndex);
		List<Steen> stenenList = set.getStenen();
		List<Steen> subList1 = stenenList.subList(0, splitsIndex);
		List<Steen> subList2 = stenenList.subList(splitsIndex, stenenList.size());
		
		StenenSet set1, set2;
		set1 = new StenenSet(subList1);
		set2 = new StenenSet(subList2);
		
		stenenSets.add(setIndex, set2);
		stenenSets.add(setIndex, set1);
	}
	
	/**
	 * Use Case 3:
	 * Maakt een StenenSet met een Steen en voegt die StenenSet toe aan attribuut stenenSets
	 * 
	 * @param steen	Steen die in een StenenSet moet
	 */
	public void maakStenenSet(Steen steen)
	{
		// maak een Steen list aan met default lengte 13
		Steen[] steenArray = new Steen[13];
		List<Steen> stenenList = new ArrayList<>(Arrays.asList(steenArray));
		
		// stel de steen in als het eerste element
		stenenList.set(0, steen);
		
		// maak hiermee een nieuwe stenenSet en voeg deze toe aan de stenenSets
		StenenSet newStenenSet = new StenenSet(stenenList);
		stenenSets.add(newStenenSet);
	}
	
	/**
	 * Use Case 3:
	 * Controleert elke set op geldigheid (zijn de rijen en series juist aangemaakt volgens DR_GELDIGE_SPELSITUATIE)
	 * door methode controleerSet() uit klasse StenenSet aan te roepen
	 */
	public void controleerVeld()
	{
		for(StenenSet set : stenenSets)
		{
			set.controleerSet();
		}
	}
	
	/**
	 * Use Case 3:
	 * ToString methode die een Veld weergeeft door elke StenenSet op een rij te plaatsen.
	 * Wordt gebruikt in de geefSpelOverzicht() methode van de klasse Spel
	 */
	@Override
	public String toString()
	{
		String resultaat = "";
		
		// kolomNrs
		resultaat += String.format("%3s", "");
		for(int kolomNr = 1; kolomNr < 14; kolomNr++)
		{
			resultaat += String.format("%02d%2s", kolomNr, "");
		}
		
		// rijen/stenenSets
		for(int rijNr = 0; rijNr < stenenSets.size(); rijNr++)
		{
			resultaat += String.format("%n%02d%1s", rijNr + 1, "");
			resultaat += stenenSets.get(rijNr).toString();
		}
		
		return resultaat;
	}
	
	/**
	 * Use Case 3:
	 * Geeft steen terug door middel van een positie (array) door methode geefSteen() uit klasse Set aan te roepen
	 * @param 	positieSteen array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 							kolom om de juiste steen van de juiste StenenSet te vinden) die helpt de steen te vinden
	 * @return					gevonden steen via positie array
	 */
	public Steen geefSteen(int[] positieSteen)
	{
		// steen kan null zijn
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		controleerGeldigePositie(positieSteen);

		StenenSet set = stenenSets.get(setIndex);
		return set.geefSteen(steenIndex);
	}
	
	private void controleerGeldigePositie(int[] positieSteen)
	{
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		// gooit exception indien setIdex/steenIndex te hoog
		if(setIndex >= stenenSets.size() || steenIndex >= 13 )
		{
			throw new FoutePositieException();
		}
	}
}
