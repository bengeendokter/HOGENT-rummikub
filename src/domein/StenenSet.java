package domein;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StenenSet
{
	private List<Steen> stenen;
	
	public StenenSet(List<Steen> stenen)
	{
		this.stenen = stenen;
	}
	
	public List<Steen> getStenen()
	{
		return stenen;
	}
	
	//TODO boolean zitInWerkveld in constructor 
	
	/**
	 * Use Case 3:
	 * Voegt een steen toe aan de set (serie of rij)
	 * 
	 * @param indexSteen	int positie die aangeeft waar de steen toegevoegd moet worden
	 * @param steen			steen die toegevoegd moet worden in een serie of rij
	 */
	public void voegSteenToe(int indexSteen, Steen steen)
	{
		// TODO index wrs wel nodig bij gv maar niet bij wv, hier onderscheid in maken?
		if(indexSteen >= stenen.size())
		{
			stenen.add(steen);
		}
		else
		{
			stenen.add(indexSteen, steen);
		}
	}
	
	/**
	 * Use Case 3:
	 * Verwijdert een steen uit de set (serie of rij)
	 * 
	 * @param indexSteen	int positie die aangeeft waar de steen verwijderd moet worden 
	 * @return				geeft de verwijderde steen terug
	 */
	public Steen removeSteen(int indexSteen)
	{
		// TODO wat indien index te hoog? Fout of hoogst mogelijke index pakken? We doen melding
		return stenen.remove(indexSteen);
	}
	
	/**
	 * Use Case 3:
	 * Controleert de set op geldigheid volgens DR_GELDIGE_SPELSITUATIE (serie of rij)
	 */
	public void controleerSet()
	{
		
	}
	
	/**
	 * Use Case 3:
	 * ToString methode die de StenenSets weergeeft door de Stenen te joinen met een '-'
	 * bv. JOK-G02-G03, B01-Z01-R01-G01...
	 */
	@Override
	public String toString()
	{
		Stream<String> stringList = stenen.stream().map(Steen::toString);
		String output = stringList.collect(Collectors.joining("-"));		
		
		return output;
	}
}
