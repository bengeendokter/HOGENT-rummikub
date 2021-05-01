package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.DoelBevatSteenException;
import exceptions.FoutePositieException;
import exceptions.NogPlaatsOpVeldException;
import exceptions.OngeldigInvoerException;

public class Veld
{
	private List<StenenSet> stenenSets;
	//toegevoegd
	private final boolean isGV;
	
	//TODO boolean zitInWerkveld in constructor 
	
	/**
	 * Use Case 3:
	 * Default constructor van Veld die een nieuw veld aanmaakt.
	 * Wordt gebruikt in het begin van een spel of om het werkveld te resetten
	 */
	public Veld(boolean isGV)
	{
		// GV creeren als ArrayList van StenenSets met default lengte 13

		this.isGV = isGV;
		
			List<StenenSet> setArray = new ArrayList<>();
			for (int i = 0; i < ((isGV)? 13 : 2); i++) {
				setArray.add(new StenenSet(Arrays.asList(new Steen[13])));
			}
			
			this.stenenSets = setArray;
		
		//this(new ArrayList<StenenSet>());
	}
	
	/**
	 * Use Case 3:
	 * Constructor van Veld die een nieuw veld aanmaakt aan de hand van een lijst van StenenSets.
	 * Wordt gebruikt in de klasse Beurt om een copy van een bestaand gemeenschappelijk veld in op te slaan
	 * 
	 * @param stenenSets
	 */
	public Veld(List<StenenSet> stenenSets, boolean isGV)
	{
		this.isGV = isGV;
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
	 * 
	 * @param positieSteen	array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 						kolom om de juiste steen van de juiste StenenSet te vinden)	die aangeeft vanwaar de steen afkomstig is
	 * @param steen			steen die zal toegevoegd worden
	 */
	public void voegSteenToe(int[] positieSteen, Steen steen)
	//kan eventueel boolean doelIsWv als derde parameter krijgen
	{
		int setIndex = positieSteen[0];
        int steenIndex = positieSteen[1];
        
        boolean waardeNull = false;
		
        /*if(setIndex >= stenenSets.size())
        {
        	for (StenenSet s : stenenSets) {
    			
    			waardeNull = false;
    			
    			for (Steen o : s.getStenen()) {
    				if (o != null) {
    					waardeNull = true;
    					break;
    				}
    			}
    			
    			if (!waardeNull) {
    				throw new NogPlaatsOpVeldException();
    			}
    			
        		if (waardeNull) {
        			maakStenenSet(steen);
        		}
        	}}
        else
        {
            StenenSet set = stenenSets.get(setIndex);
            set.voegSteenToe(steenIndex, steen);
        }*/
        
        /*if (!doelIsWv) {
		    if(setIndex >= stenenSets.size() || steenIndex >= 13) {
		    	
		    	//controle op hoge index bij kolom
		    	if (steenIndex >= 13)
		    		throw new FoutePositieException();
		    	
		    	//controle op hoge index bij rij     	
		    	for (StenenSet s : stenenSets) {
					
					waardeNull = false;
					
					for (Steen o : s.getStenen()) {
						if (o != null) {
							waardeNull = true;
							break;
						}
					}
					
					if (!waardeNull) {
						throw new NogPlaatsOpVeldException();
					}
					
		    		if (waardeNull) {
		    			maakStenenSet(steen);
		    		}
		    	}}
		    else
		    {
		    	if (geefSteen(positieSteen) != null)
		    		throw new DoelBevatSteenException();
		    	
		        StenenSet set = stenenSets.get(setIndex);
		        set.voegSteenToe(steenIndex, steen);
		    }
        } else if (doelIsWv) {
        	if(setIndex >= stenenSets.size() || steenIndex >= 13) {
		    		throw new FoutePositieException();
        	}
		    else
		    {
		    	if (geefSteen(positieSteen) != null)
		    		throw new DoelBevatSteenException();
		    	
		        StenenSet set = stenenSets.get(setIndex);
		        set.voegSteenToe(steenIndex, steen);
		    }
        }*/
        if(setIndex >= stenenSets.size() || steenIndex >= 13) {
	    	
	    	//controle op hoge index bij kolom
	    	if (steenIndex >= 13)
	    		throw new FoutePositieException();
	    	
	    	//controle op hoge index bij rij     	
	    	for (StenenSet s : stenenSets) {
				
				waardeNull = false;
				
				for (Steen o : s.getStenen()) {
					if (o != null) {
						waardeNull = true;
						break;
					}
				}
				
				if (!waardeNull) {
					throw new NogPlaatsOpVeldException();
				}
				
	    		if (waardeNull) {
	    			maakStenenSet(steen);
	    		}
	    	}}
	    else
	    {
	    	if (geefSteen(positieSteen) != null)
	    		throw new DoelBevatSteenException();
	    	
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
		
		stenenSets.add(new StenenSet(Arrays.asList(new Steen[13])));
		
		/*
		List<Steen> stenenList = new ArrayList<>(Arrays.asList(steen));
		StenenSet set = new StenenSet(stenenList);	
		stenenSets.add(set);
		*/
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
		/*String output = "";
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
		*/
		
		String resultaat = "";
		
		resultaat += String.format("%3s", "");
		for (int i = 1; i < 14; i++) {
			resultaat += String.format("%2d%2s", i, " ");
		}
		
		for (int i = 0; i < stenenSets.size(); i++) {
			resultaat += String.format("%n%02d%1s", i+1,"");
			resultaat += stenenSets.get(i).toString();
		}
		
		return resultaat;
		
	}
	
	/**
	 * Use Case 3:
	 * Geeft steen terug door middel van een positie (array) door methode geefSteen() uit klasse Set aan te roepen
	 * @param 	positieSteen array met 2 int elementen (rij om de juiste StenenSet te vinden,
	 * 							kolom om de juiste steen van de juiste StenenSet te vinden) die helpt de steen te vinden
	 * @return					gevonden steen via positiearray
	 */
	public Steen geefSteen(int[] positieSteen) {
		//TODO exception als steen niet bestaat (foute positie bv)
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		// controle op indexen
		/*if (setIndex < stenenSets.size()) {
			if (steenIndex < stenenSets.get(setIndex).getStenen().size()) {
				
				StenenSet set = stenenSets.get(setIndex);
				return set.geefSteen(steenIndex);
			} else {
				throw new OngeldigInvoerException();
			}
		} else {
			throw new OngeldigInvoerException();
		}*/
		StenenSet set = stenenSets.get(setIndex);
		return set.geefSteen(steenIndex);
	}

	// Toegevoegd
	public boolean isGV() {
		return isGV;
	}
	
	
}
