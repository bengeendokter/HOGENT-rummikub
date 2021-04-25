package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Veld
{
	private List<StenenSet> stenenSets;
	
	public Veld()
	{
		this(new ArrayList<>());
	}
	
	public Veld(List<StenenSet> stenenSets)
	{
		this.stenenSets = stenenSets;
	}
	
	// TODO implementeer controles, hoe?
	
	public List<StenenSet> getStenenSets()
	{
		return stenenSets;
	}

	/**
	 * Use Case 3:
	 * Voegt steen toe in een bestaande of tegelijkertijd aangemaakte StenenSet
	 * @param positieSteen 	array met 2 int elementen (rij van de juiste stenenset van attribuut stenenSets, kolom is de steen van de juiste stenenset)
	 * @param steen 	steen die zal toegevoegd worden
	 */
	public void voegSteenToe(int[] positieSteen, Steen steen)
	{
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		if(setIndex >= stenenSets.size())
		{
			maakStenenSet(steen);
		}
		else
		{
			StenenSet set = stenenSets.get(setIndex);
			set.voegSteenToe(steenIndex, steen);
		}
	}
	
	/**
	 * Use Case 3: 
	 * Verwijdert een steen door gegeven array (rij en kolom) en geeft die terug
	 * @param positieSteen 	array met 2 int elementen (rij van de juiste stenenset van attribuut stenenSets, kolom is de steen van de juiste stenenset)
	 * @return 	verwijderde steen
	 */
	public Steen removeSteen(int[] positieSteen)
	{
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		StenenSet set = stenenSets.get(setIndex);
		return set.removeSteen(steenIndex);
	}

	/**
	 * Use Case 3: 
	 * Splitst een rij of serie in nieuwe StenenSets door gegeven array (rij en kolom), verwijdert oorspronkelijke (niet gesplitste StenenSet)
	 * en voegt de nieuwe StenenSets toe aan attribuut stenenSets
	 * @param positieSplitsing 	array met 2 int elementen (rij van de juiste stenenset van attribuut stenenSets, kolom als positie van splitsing van de juiste stenenset)
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
	 * @param steen		Steen die in een StenenSet moet
	 */
	public void maakStenenSet(Steen steen)
	{
		List<Steen> stenenList = new ArrayList<>(Arrays.asList(steen));
		StenenSet set = new StenenSet(stenenList);	
		stenenSets.add(set);
	}
	
	/**
	 * Use Case 3:
	 * Controleert elke set op geldigheid (zijn de rijen en series juist aangemaakt volgens DR_GELDIGE_SPELSITUATIE)
	 * door methode 'controleerSet()' van StenenSet aan te roepen
	 */
	public void controleerVeld()
	{
		for(StenenSet set : stenenSets)
		{
			set.controleerSet();
		}
	}

	@Override
	public String toString()
	{
		String output = "";
		String kolomNrs = String.format("%3s", "");
		for(int i = 1; i < 14; i++)
		{
			kolomNrs += String.format("%02d%2s", i, "");
		}
		
		output = kolomNrs + "\n";
		
		for(int i = 0; i < stenenSets.size(); i++)
		{
			String rijNr = String.format("%02d%1s", i + 1, "");
			String setString = stenenSets.get(i).toString();
			output += rijNr + setString + "\n";
		}
		
		return output;
	}
}
