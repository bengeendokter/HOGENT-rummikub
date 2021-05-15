package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.FoutePositieException;
import exceptions.GeenPlaatsOpRijException;
import exceptions.GeenSerieOfRijException;

public class StenenSet
{
	private List<Steen> stenen;
	
	/**
	 * Use Case 3:
	 * Constructor van StenenSet waaraan een lijst van stenen moet meegeven worden
	 * 
	 * @param stenen	lijst van stenen die in de set zitten
	 */
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
	 * Geeft de steen terug in een StenenSet via een index
	 * 
	 * @param 	indexSteen int positie om de steen te vinden
	 * @return	gevonden steen
	 */
	public Steen geefSteen(int indexSteen) throws FoutePositieException
	{
		if(indexSteen >= 13)
		{
			throw new FoutePositieException();
		}

		return getStenen().get(indexSteen);
	}
	
	/**
	 * Use Case 3:
	 * Voegt een steen toe aan de set (serie of rij)
	 * 
	 * @param indexSteen	int positie die aangeeft waar de steen toegevoegd moet worden
	 * @param steen			steen die toegevoegd moet worden in een serie of rij
	 */
	public void voegSteenToe(int indexSteen, Steen steen) throws GeenPlaatsOpRijException, FoutePositieException
	{
		// we controleren of de laatste plek van de Set leeg is
		// indien de plek leeg is kunnen we een steen plaatsen, anders gooien we een exception
		if(geefSteen(stenen.size() - 1) != null)
		{
			throw new GeenPlaatsOpRijException();
		}
		
		if(indexSteen >= 13)
		{
			throw new FoutePositieException();
		}
		
		// we voegen de steen toe op de meegegeven index en verwijderen het laatste null element zodat we weer 1 elementen hebben
		stenen.add(indexSteen, steen);
		stenen.remove(stenen.size() - 1);
		// vul potentieel gat tussen de hiervoor laatste steen en de huidig geplaatste steen op
		// dit doen we door alle null stenen naar achter te verplaatsen
		Collections.sort(stenen, Comparator.nullsLast(null));
	}
	
	/**
	 * Use Case 3:
	 * Verwijdert een steen uit de set (serie of rij)
	 * 
	 * @param indexSteen	int positie die aangeeft waar de steen verwijderd moet worden 
	 * @return				geeft de verwijderde steen terug
	 */
	public Steen removeSteen(int indexSteen) throws FoutePositieException
	{
		// zoek de steen op de gegeven positie
		Steen steen = geefSteen(indexSteen);
		// verwijder de steen door deze in te stellen op null
		stenen.set(indexSteen, null);
		// vul het gat in de rij op door alle nullen naar achter te plaatsen
		Collections.sort(stenen, Comparator.nullsLast(null));
		// geef de gevraagde steen terug (kan null zijn)
		return steen;
	}
	
	/**
	 * Use Case 3:
	 * Geeft terug of de StenenSet volledig leeg is of niet
	 * 
	 * @return	true indien de set volledig leeg is
	 */
	public boolean isLeeg()
	{
		return stenen.stream().allMatch(steen -> steen == null);
	}
	
	/**
	 * Use Case 3:
	 * Controleert de set op geldigheid volgens DR_GELDIGE_SPELSITUATIE (serie of rij)
	 */
	public void controleerSet() throws GeenSerieOfRijException
	{
		boolean isSerie = true;
		boolean isRij = true;
		
		if(!isLeeg())
		{
			// we houden rekening met het uitzonderlijke geval dat de eerste 2 stenen van de set jokers zijn
			
		// 1. controle of set minimum 3 stenen heeft
			int grootte = (int) stenen.stream().filter(steen -> steen != null).count();
			if(grootte < 3)
			{
				throw new GeenSerieOfRijException("Een serie of rij heeft minumum 3 stenen");
			}
			
		// 2. controleer of set een serie is
			Iterator<Steen> iterator = stenen.iterator();
			Steen previous = null;
			Steen huidigeSteen = null;
			int serieGetal = 0;
			List<String> vorigeKleuren = new ArrayList<>();
			
			// we nemen alvast het eerste element, dit kan niet null zijn want de Set heeft minimum 3 stenen
			previous = iterator.next();
			
			// indien de eerste steen een joker is pakken we de volgende steen
			if(previous.isJoker())
			{
				previous = iterator.next();
			}

			// indien de tweede steen een joker is pakken we de volgende steen (er zijn maar 2 jokers in het spel)
			if(previous.isJoker())
			{
				previous = iterator.next();
			}
			
			// we stellen een paar eigenschappen van de serie in
			vorigeKleuren.add(previous.getKleur());
			serieGetal = previous.getGetal();
			
			// serie mag max 4 stenen zijn, een joker kan niet gebruikt worden als 5e/6e steen
			if(grootte > 4)
			{
				isSerie = false;
			}
			
			// we overlopen de stenen zolang de set nog altijd een serie is
			while(isSerie && iterator.hasNext())
			{
				huidigeSteen = iterator.next();
				
				// indien de steen niet null is gaan we verder
				if(huidigeSteen != null)
				{
					// we controleren of de getalWaarde overal dezelfde is
					if(huidigeSteen.getGetal() != serieGetal)
					{
						// de getal waarde komt niet overeen, de set is enkel een serie als de steen een joker is
						isSerie = huidigeSteen.isJoker();
					}
					// als we een steen vinden met eenzelfde kleur als een vorige steen is het geen serie
					if(vorigeKleuren.contains(huidigeSteen.getKleur()))
					{
						isSerie = false;
					}
					// indien de steen geen joker is voegen we de kleur toe aan de vorige stenen
					if(!huidigeSteen.isJoker())
					{
						vorigeKleuren.add(huidigeSteen.getKleur());
					} 
				}
			}
			
		// 3. controleer of set een rij is in oplopende volgorde
			iterator = stenen.iterator();
			int vorigGetal = 0;
			String rijKleur = "";
			
			// we maken variabelen aan voor de controle: een joker mag niet voor 1 of na 13 liggen
			// ook 2 jokers voor een 2 of na een 12 zijn niet toegestaan
			boolean vorigIsJoker = false;
			boolean vorigIsDertien = false;
			boolean vorigIsTwaalf = false;
			boolean jokerNaTwaalf = false;
			int vorigIsJokerCount = 0;
			
			// we nemen alvast het eerste element, dit kan niet null zijn want de Set heeft minimum 3 stenen
			previous = iterator.next();
			
			// indien de eerste steen een joker is pakken we de volgende steen
			if(previous.isJoker())
			{
				previous = iterator.next();
				vorigIsJoker = true;
				vorigIsJokerCount++;
			}

			// indien de tweede steen een joker is pakken we de volgende steen (er zijn maar 2 jokers in het spel)
			if(previous.isJoker())
			{
				previous = iterator.next();
				vorigIsJokerCount++;
			}
			
			// we stellen een paar eigenschappen van de rij in
			vorigGetal = previous.getGetal();
			rijKleur = previous.getKleur();
			
			// indien er in het begin een joker voor een 1 ligt is de set geen rij
			if(vorigIsJoker && vorigGetal == 1)
			{
				isRij = false;
			}
			else if(vorigIsJokerCount == 2 && vorigGetal == 2)
			{
				isRij = false;
			}
			
			// we overlopen de stenen zolang de set nog altijd een rij is
			while(isRij && iterator.hasNext())
			{
				huidigeSteen = iterator.next();
				
				// indien de steen niet null is gaan we verder
				if(huidigeSteen != null)
				{
					// we controleren of de getalWaarde oplopend is en of de rijKleur overal overeenkomt
					if(vorigGetal != huidigeSteen.getGetal() - 1 || rijKleur != huidigeSteen.getKleur())
					{
						// de getal waarde of kleur klopt niet, de set is enkel een rij als de steen een joker is
						isRij = huidigeSteen.isJoker();
					}
					
					// indien joker na een 13 ligt is de set geen rij
					if(huidigeSteen.getGetal() == 13)
					{
						vorigIsDertien = true;
					}
					else if(vorigIsDertien && huidigeSteen.isJoker())
					{
						isRij = false;
					}
					
					// indien twee jokers na een 12 liggen is de set geen rij
					if(huidigeSteen.getGetal() == 12)
					{
						vorigIsTwaalf = true;
					}
					else if(jokerNaTwaalf && huidigeSteen.isJoker())
					{
						isRij = false;
					}
					else if(vorigIsTwaalf && huidigeSteen.isJoker())
					{
						jokerNaTwaalf = true;
					}

					
					// we stellen de volgende getalwaarde in
					vorigGetal++;
				}
			}
			
			// 4. controleer of set een rij is in aflopende volgorde
			// indien de set geldig is slaan we deze controle over
			if(!isRij && !isSerie)
			{
				// we zetten isRij terug op true
				isRij = true;
				
				iterator = stenen.iterator();
				vorigGetal = 0;
				rijKleur = "";
				// we maken variabelen aan voor de controle: een joker mag niet voor 1 of na 13 liggen
				// ook 2 jokers voor een 2 of na een 12 zijn niet toegestaan
				vorigIsJoker = false;
				boolean vorigIsEen = false;
				boolean vorigIsTwee = false;
				boolean jokerNaTwee = false;
				vorigIsJokerCount = 0;
				// we nemen alvast het eerste element, dit kan niet null zijn want de Set heeft minimum 3 stenen
				previous = iterator.next();
				// indien de eerste steen een joker is pakken we de volgende steen
				if(previous.isJoker())
				{
					previous = iterator.next();
					vorigIsJoker = true;
					vorigIsJokerCount++;
				}
				// indien de tweede steen een joker is pakken we de volgende steen (er zijn maar 2 jokers in het spel)
				if(previous.isJoker())
				{
					previous = iterator.next();
					vorigIsJokerCount++;
				}
				// we stellen een paar eigenschappen van de rij in
				vorigGetal = previous.getGetal();
				rijKleur = previous.getKleur();
				// indien er in het begin een joker voor een 13 ligt is de set geen rij
				if(vorigIsJoker && vorigGetal == 13)
				{
					isRij = false;
				}
				else if(vorigIsJokerCount == 2 && vorigGetal == 12)
				{
					isRij = false;
				}
				// we overlopen de stenen zolang de set nog altijd een rij is
				while(isRij && iterator.hasNext())
				{
					huidigeSteen = iterator.next();
					
					// indien de steen niet null is gaan we verder
					if(huidigeSteen != null)
					{
						// we controleren of de getalWaarde oplopend is en of de rijKleur overal overeenkomt
						if(vorigGetal != huidigeSteen.getGetal() + 1 || rijKleur != huidigeSteen.getKleur())
						{
							// de getal waarde of kleur klopt niet, de set is enkel een rij als de steen een joker is
							isRij = huidigeSteen.isJoker();
						}
						
						// indien joker na een 1 ligt is de set geen rij
						if(huidigeSteen.getGetal() == 1)
						{
							vorigIsEen = true;
						}
						else if(vorigIsEen && huidigeSteen.isJoker())
						{
							isRij = false;
						}
						
						// indien twee jokers na een 2 liggen is de set geen rij
						if(huidigeSteen.getGetal() == 2)
						{
							vorigIsTwee = true;
						}
						else if(jokerNaTwee && huidigeSteen.isJoker())
						{
							isRij = false;
						}
						else if(vorigIsTwee && huidigeSteen.isJoker())
						{
							jokerNaTwee = true;
						}
						
						// we stellen de volgende getalwaarde in
						vorigGetal--;
					}
				} 
			}
		}
		
		// 5. gooi exception indien set geen serie en geen rij is
		if(!isSerie && !isRij)
		{
			throw new GeenSerieOfRijException();
		}
	}
	
	/**
	 * Use Case 3:
	 * ToString methode die de StenenSets weergeeft door de Stenen te joinen met een '-'
	 * bv. JOK-G02-G03, B01-Z01-R01-G01...
	 * 
	 * @return	string weergave van al de stenen in de set
	 */
	@Override
	public String toString()
	{
		Stream<String> stringList = stenen.stream().filter(steen -> steen != null).map(Steen::toString);
		String output = stringList.collect(Collectors.joining("-"));
		
		return output;
	}
}
