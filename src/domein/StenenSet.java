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
	
	/**
	 * Use Case 3:
	 * Voegt een steen toe aan de set (serie of rij)
	 * @param indexSteen	int positie die aangeeft waar de steen toegevoegd moet worden
	 * @param steen		steen die toegevoegd moet worden in een serie of rij
	 */
	
	public void voegSteenToe(int indexSteen, Steen steen)
	{
		// TODO is index wel nodig?
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
	 * @param indexSteen	int positie die aangeeft waar de steen verwijderd moet worden 
	 * @return		geeft de verwijderde steen terug
	 */
	public Steen removeSteen(int indexSteen)
	{
		// TODO wat indien index te hoog? Fout of hoogst mogelijke index pakken?
		return stenen.remove(indexSteen);
	}
	
	/**
	 * Use Case 3:
	 * Controleert de set op geldigheid volgends DR_GELDIGE_SPELSITUATIE (serie of rij)
	 */
	public void controleerSet()
	{
		
	}

	@Override
	public String toString()
	{
		Stream<String> stringList = stenen.stream().map(Steen::toString);
		String output = stringList.collect(Collectors.joining("-"));		
		
		return output;
	}
}
