package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import exceptions.FoutePositieException;
import exceptions.GeenPlaatsOpRijException;
import exceptions.GeenSerieOfRijException;

public class Veld
{
	private List<StenenSet> stenenSets;

	/**
	 * Use Case 3:
	 * Default constructor van Veld die een nieuw veld aanmaakt.
	 * Wordt gebruikt in het begin van een spel of om het werkveld te resetten
	 * 
	 * @param isGv	boolean die bepaalt of het een gemeenschappelijkveld of een werkveld is 
	 */
	public Veld(boolean isGv)
	{
		// Een gv heeft default 13 rijen en een wv 2. Elke rij/StenenSet heeft plaats voor 13 stenen
		this
		(
			new ArrayList<>(Arrays.asList(new StenenSet[isGv ? 13 : 2]))
			.stream()
			.map(nullObject -> new StenenSet(new ArrayList<>(Arrays.asList(new Steen[13]))))
			.collect(Collectors.toList())
		);
	}

	/**
	 * Use Case 3:
	 * Constructor van Veld die een nieuw veld aanmaakt aan de hand van een lijst van StenenSets.
	 * Wordt gebruikt in de klasse Beurt om een copy van een bestaand gemeenschappelijk veld in op te slaan
	 * 
	 * @param stenenSets	de sets die zich op het veld bevinden
	 */
	public Veld(List<StenenSet> stenenSets)
	{
		this.stenenSets = stenenSets;
	}
	
	public List<StenenSet> getStenenSets()
	{
		return stenenSets;
	}

	/**
	 * Use Case 3:
	 * Geeft steen terug door middel van een positie (array) door methode geefSteen() uit klasse Set aan te roepen
	 * 
	 * @param 	positieSteen array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 			kolom om de juiste steen van de juiste StenenSet te vinden) die helpt de steen te vinden
	 * @return	gevonden steen via positie array
	 */
	public Steen geefSteen(int[] positieSteen) throws FoutePositieException
	{
		// steen kan null zijn
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		controleerGeldigePositie(setIndex, steenIndex);

		StenenSet set = stenenSets.get(setIndex);
		return set.geefSteen(steenIndex);
	}
	
	/**
	 * Use Case 3:
	 * Voegt steen toe in een bestaande of tegelijkertijd aangemaakte StenenSet
	 * 
	 * @param positieSteen	array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 						kolom om de juiste steen van de juiste StenenSet te vinden)	die aangeeft vanwaar de steen afkomstig is
	 * @param steen			steen die zal toegevoegd worden
	 */
	public void voegSteenToe(int[] positieSteen, Steen steen) throws GeenPlaatsOpRijException, FoutePositieException
	{
		int indexLaatsteSet = stenenSets.size() - 1;
		int setIndex = 	positieSteen[0];
		int steenIndex = positieSteen[1];
		

		// indien setIndex te hoog,
		if(setIndex > indexLaatsteSet)
		{
			maakStenenSet(steen);
		}
		// anders, voeg de steen toe aan de juiste set op de juiste positie
		else
		{
			StenenSet set = stenenSets.get(setIndex);
			set.voegSteenToe(steenIndex, steen);
		}
		sorteerSets();
	}
	
	/**
	 * Use Case 3: 
	 * Verwijdert een steen door gegeven array (rij en kolom) en geeft die terug
	 * 
	 * @param positieSteen	array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 						kolom om de juiste steen van de juiste StenenSet te vinden)	die aangeeft vanwaar de steen afkomstig is
	 * @return 				verwijderde steen
	 */
	public Steen removeSteen(int[] positieSteen) throws FoutePositieException
	{		
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		controleerGeldigePositie(setIndex, steenIndex);
		
		StenenSet set = stenenSets.get(setIndex);
		Steen steen = set.removeSteen(steenIndex);
		sorteerSets();
		return steen;
	}
	
	/**
	 * Use Case 3:
	 * Maakt een StenenSet met een Steen en voegt die StenenSet toe aan attribuut stenenSets
	 * 
	 * @param steen	Steen die in een StenenSet moet
	 */
	private void maakStenenSet(Steen steen)
	{
		// maak een Steen list aan met default lengte 13
		Steen[] steenArray = new Steen[13];
		List<Steen> stenenList = new ArrayList<>(Arrays.asList(steenArray));
		
		// stel de steen in als het eerste element
		stenenList.set(0, steen);
		
		// maak hiermee een nieuwe stenenSet en voeg deze toe aan de stenenSets
		StenenSet newStenenSet = new StenenSet(stenenList);
		addSet(newStenenSet);
	}
	
	// TODO verander DCD
	/**
	 * Use Case 3: 
	 * Splitst een rij of serie in nieuwe StenenSets door gegeven array (rij en kolom),
	 * verwijdert oorspronkelijke (niet gesplitste StenenSet) en geeft de nieuwe StenenSets terug
	 * 
	 * @param positieSplitsing	array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 							kolom om de juiste steen van de juiste StenenSet te vinden) die aangeeft waar er gesplitst moet worden
	 * @return					de 2 delen van de gesplitste set
	 */
	public StenenSet[] splitsRijOfSerie(int[] positieSplitsing)
	{
		int setIndex = positieSplitsing[0];
		int splitsIndex = positieSplitsing[1];
		
		StenenSet set = stenenSets.remove(setIndex);
		// voeg nieuwe set toe om terug aan 13 rijen te komen
		stenenSets.add(new StenenSet(new ArrayList<>(Arrays.asList(new Steen[13]))));

		List<Steen> stenenList = set.getStenen();
		List<Steen> subList1 = new ArrayList<>(stenenList.subList(0, splitsIndex));
		List<Steen> subList2 = new ArrayList<>(stenenList.subList(splitsIndex, stenenList.size()));
		
		// zorg dat we 2 sets van 13 hebben
		while(subList1.size() < 13)
		{
			subList1.add(null);
		}

		while(subList2.size() < 13)
		{
			subList2.add(null);
		}
		
		StenenSet set1, set2;
		set1 = new StenenSet(subList1);
		set2 = new StenenSet(subList2);
	
		sorteerSets();
		return new StenenSet[] {set1, set2};
	}
	
	/**
	 * Use Case 3: 
	 * Gooit een exception indien er een foute positie meegeven wordt
	 * 
	 * @param setIndex		positie van de set
	 * @param steenIndex	positie van de steen
	 */
	private void controleerGeldigePositie(int setIndex, int steenIndex) throws FoutePositieException
	{
		// gooit exception indien setIdex/steenIndex te hoog
		if(setIndex >= stenenSets.size() || steenIndex >= 13 )
		{
			throw new FoutePositieException();
		}
	}
	
	/**
	 * Use Case 3:
	 * Voegt een set toe aan het veld, indien de laatste set leeg is word deze in zijn plaats verwijderd
	 * 
	 * @param set	de set die toegevoegd moet worden
	 */
	public void addSet(StenenSet set)
	{
		int indexLaatsteSet = stenenSets.size() - 1;
		StenenSet laatsteSet = stenenSets.get(indexLaatsteSet);
		// voeg toe aan laatste lege stenenSet
		if(laatsteSet.isLeeg())
		{
			stenenSets.remove(indexLaatsteSet);
		}
		
		stenenSets.add(set);
		sorteerSets();
	}
//	
//	/**
//	 * Use Case 3:
//	 * Verwijderd een set uit het veld en geeft deze terug
//	 * 
//	 * @param setIndex	de index van de set die verwijderd moet worden
//	 * @return			de verwijderde set
//	 */
//	public StenenSet removeSet(int setIndex)
//	{
//		return stenenSets.remove(setIndex);
//	}
	
	/**
	 * Use Case 3:
	 * Sorteert de sets door alle lege sets achteraan te plaatsen
	 */
	private void sorteerSets()
	{
		stenenSets.sort(Comparator.comparing(StenenSet::isLeeg));
	}
	
	/**
	 * Use Case 3:
	 * Controleert of er een set is die geen joker bevat en minimum 30 punten waard is,
	 * deze methode controleert niet of de set een rij of serie is
	 * 
	 * @return	geeft true terug indien het veld een set bevat die 30 punten waard en geen joker bevat
	 */
	public boolean isGeldigeEersteZet()
	{
		return stenenSets.stream()
				  		 .filter(Predicate.not(StenenSet::isLeeg))
				  		 .map(StenenSet::getStenen)
				  		 // indien er een joker in de stenenSet zit word deze set niet mee geteld
				  		 .filter
				  		 (
				  			stenenList -> !stenenList.stream()
				  									.filter(steen -> steen != null)
				  									.anyMatch(Steen::isJoker)
				  				 
				  		 )
				  		 // zet elke stenenSet om in zijn waarde
						 .map
						 (
							 stenenList -> stenenList.stream()
							  						 .filter(steen -> steen != null)
							  						 .map(Steen::getGetal)
							  						 // berekend voor elke set de waarde
							  						 .reduce(0, (totaal, getalWaarde) -> totaal + getalWaarde)
						 )
						 // tel de waarden van alle sets op
						 .reduce(0, (totaal, setWaarde) -> totaal + setWaarde)
						 // controleer of de totaal waarde groter is dan 30
						 >= 30;
	}
	
	/**
	 * Use Case 3:
	 * Controleert elke set op geldigheid (zijn de rijen en series juist aangemaakt volgens DR_GELDIGE_SPELSITUATIE)
	 * door methode controleerSet() uit klasse StenenSet aan te roepen
	 */
	public void controleerVeld() throws GeenSerieOfRijException
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
}
