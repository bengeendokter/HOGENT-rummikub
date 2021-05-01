package domein;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.OngeldigInvoerException;

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
		{	if (bevatSteenLinks(indexSteen) && !bevatSteenRechts(indexSteen)) {
			String kleur1 = steen.getKleur();
			String kleur2 = geefSteen(indexSteen - 1).getKleur();
			
			if (kleur1 == kleur2) {
				int getalOrigineel = steen.getGetal();
				int getalLinks = geefSteen(indexSteen - 1).getGetal();
				if (getalLinks == getalOrigineel - 1) {
					if (getalLinks - 1 == 0) {
						throw new IllegalArgumentException("U mag de steen hier niet plaatsen!");
					} else {
						stenen.set(indexSteen, steen);
					}
				}else {
					throw new IllegalArgumentException("Getal van steen is niet groter!");
				}
			} else {
				int getalOrigineel = steen.getGetal();
				int getalLinks = geefSteen(indexSteen - 1).getGetal();
				
				if (getalOrigineel == getalLinks) {
					stenen.set(indexSteen, steen);
				} else {
					throw new IllegalArgumentException("Getallen/kleuren van de stenen komen niet overeen!");
				}
			}
		} else {
			if (!bevatSteenLinks(indexSteen) && bevatSteenRechts(indexSteen)) {
				String kleur1 = steen.getKleur();
				String kleur2 = geefSteen(indexSteen + 1).getKleur();
				
				if (kleur1 == kleur2) {
					int getalOrigineel = steen.getGetal();
					int getalRechts = geefSteen(indexSteen + 1).getGetal();
					if (getalRechts == getalOrigineel + 1) {
						if (getalRechts + 1 == 14) {
							throw new IllegalArgumentException("U mag de steen hier niet plaatsen!");
						} else {
							stenen.set(indexSteen, steen);
						}
					}
					else {
						throw new IllegalArgumentException("Getal van de steen is niet kleiner!");
					}
				} else {
					int getalOrigineel = steen.getGetal();
					int getalRechts = geefSteen(indexSteen + 1).getGetal();
					
					if (getalOrigineel == getalRechts) {
						stenen.set(indexSteen, steen);
					} else {
						throw new IllegalArgumentException("Getallen/kleuren van de stenen komen niet overeen!");
					}
				}
			} else {
				if(!bevatSteenLinks(indexSteen) && !bevatSteenRechts(indexSteen)) {
					stenen.set(indexSteen, steen);
				}
				
			}

		}
			/*stenen.set(indexSteen, steen);*/
			// stenen.add(indexSteen, steen);
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
		
		/*Steen steen = geefSteen(indexSteen);
		
		stenen.set(indexSteen, null);
		
		return steen;*/
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
		String output = "";
		
		for (int i = 0; i < stenen.size(); i++) {
			output += (stenen.get(i) == null)? "    ": stenen.get(i).toString();
		}
		
		//Stream<String> stringList = stenen.stream().map((stenen])Steen::toString);
		//String output = stringList.collect(Collectors.joining("-"));		
		
		return output;
	}
	
	/**
	 * Use Case 3:
	 * Geeft de steen terug in een StenenSet via een index
	 * @param 	indexSteen int positie om de steen te vinden
	 * @return	gevonden steen
	 */
	public Steen geefSteen(int indexSteen) {
		// if (indexSteen < getStenen().size()) {
			return getStenen().get(indexSteen);
		// } else {
		//	throw new OngeldigInvoerException();
		// }
	}
	
	public boolean bevatSteenRechts(int indexSteen) {
		if (indexSteen + 1 <= 12 && stenen.get(indexSteen + 1) != null) {
			return true;
		} else return false;
	}
	
	public boolean bevatSteenLinks(int indexSteen) {
		if (indexSteen - 1 >= 0 && stenen.get(indexSteen - 1) != null ) {
			return true;
		} else return false;
	}
}
