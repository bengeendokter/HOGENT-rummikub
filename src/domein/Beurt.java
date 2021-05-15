package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Beurt
{
	private Speler speler;
	private Veld gv;
	private Veld wv;
	
	/**
	 * Use Case 3:
	 * Constructor van Beurt die de spelerAanDeBeurt en het gemeenschappelijkveld opslaat
	 * waarbij vooral hun bijhorende stenen belangrijk zijn om de beurt later te kunnen resetten
	 * 
	 * @param speler	Speler object van de speler aan de beurt in het begin van zijn beurt
	 * @param gv		Veld object van het gemeenschappelijkveld in het begin van de beurt
	 */
	public Beurt(Speler speler, Veld gv)
	{
		this(speler, gv, new Veld(false));
	}
	
	/**
	 * Use Case 3:
	 * Constructor van Beurt die waaraan ook een werkveld kan meegegeven worden.
	 * Deze constructor word gebruikt om een niet geldige actie te kunnen terugdraaien
	 *  
	 * @param speler	Speler object van de speler aan het begin van de zet
	 * @param gv		Veld object van het gemeenschappelijkveld  aan het begin van de zet
	 * @param wv		Veld object van het werkveld  aan het begin van de zet
	 */
	public Beurt(Speler speler, Veld gv, Veld wv)
	{
		// we gebruiken de constructor van Speler waarbij we de spelerStenen kunnen meegeven
		this.speler = new Speler
				(
					speler.getGebruikersnaam()
					, speler.getWachtwoord()
					, (List<Steen>) new ArrayList<>(speler.getStenen()) // maakt een nieuwe List van de meegegeven spelerStenen
					, speler.getTotaalScore()
				);
		// we stellen het isEersteZet attribuut in
		this.speler.setEersteZet(speler.isEersteZet());

		// we gebruiken de constructor van Veld waarbij we de stenenSets kunnen meegeven
		this.gv = new Veld
				(
					gv.getStenenSets() // we nemen vragen de (List<StenenSets>) stenenSets op
					.stream()
					.map(stenenSet -> new StenenSet(new ArrayList<>(stenenSet.getStenen()))) // we zetten elke Set om naar een nieuwe Set
					.collect(Collectors.toList()) // we maken van alle Sets terug een list
				);
		// we gebruiken de constructor van Veld waarbij we de stenenSets kunnen meegeven
		this.wv = new Veld
				(
					wv.getStenenSets() // we nemen vragen de (List<StenenSets>) stenenSets op
					.stream()
					.map(stenenSet -> new StenenSet(new ArrayList<>(stenenSet.getStenen()))) // we zetten elke Set om naar een nieuwe Set
					.collect(Collectors.toList()) // we maken van alle Sets terug een list
				);
	}

	public Speler getSpeler()
	{
		return speler;
	}

	public Veld getGemeenschappelijkVeld()
	{
		return gv;
	}
	
	public Veld getWerkVeld()
	{
		return wv;
	}
}
