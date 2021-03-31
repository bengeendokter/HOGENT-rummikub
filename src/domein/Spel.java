package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Spel
{
	// 2 x (4 kleuren x 13 getallen) + 2 jokers = 106 stenen
	// getal (1-13) kleur (zwart, rood, blauw en geel) en joker (true or false)
	private List<Steen> stenen;
	private List<Speler> spelers;
	private Speler spelerAanDeBeurt;
	
	/**
	 * Use Case 2:
	 * Constructor van Spel die alle 106 stenen aanmaakt en bijhoudt in een lijst,
	 * de volgorde van de spelers bepaalt en de spelers elk 14 stenen geeft
	 * 
	 * @param spelers	een lijst met de aangemelde spelers
	 */
	public Spel(List<Speler> spelers)
	{
		maakStenen();
		
		// bepaal de speler volgorde en de eerste speler aan de beurt
		Collections.shuffle(spelers);
		this.spelers = spelers;
		setSpelerAanDeBeurt(spelers.get(0));
		
		verdeelStenen(spelers);
	}
	
	/**
	 * Use Case 2:
	 * Maakt de 106 stenen aan en stopt het in 'stenen'
	 */
	private void maakStenen()
	{
		stenen = new ArrayList<>();
		
		String[] kleuren =
		{ "zwart", "rood", "blauw", "geel" };
		
		// 2 keer overlopen, elke steen zit dubbel in de lijst
		for(int a = 0; a < 2; a++)
		{
			// 4 kleuren * 13 getallen
			// 4 kleuren
			for(int indexKleur = 0; indexKleur < 4; indexKleur++)
			{
				// 13 getallen
				for(int getalWaarde = 1; getalWaarde < 14; getalWaarde++)
				{
					stenen.add(new Steen(getalWaarde, kleuren[indexKleur], false));
				}
			}
			
			// 1 joker
			stenen.add(new Steen(0, "joker", true));
		}
		
		// geeft de stenen een willekeurige volgorde
		Collections.shuffle(stenen);
	}
	
	/**
	 * Use Case 2:
	 * Bepaalt de volgorde van de spelers en geeft elke speler 14 stenen
	 * 
	 * @param spelers	een lijst met de aangemelde spelers
	 */
	private void verdeelStenen(List<Speler> spelers)
	{
		// geef elke speler 14 stenen
		for(Speler speler : spelers)
		{
			for(int index = 1; index <= 14; index++)
			{
				speler.neemSteen(stenen.remove(stenen.size() - 1));
			}
		}
	}
	
	public void setSpelerAanDeBeurt(Speler speler)
	{
		this.spelerAanDeBeurt = speler;
	}
	
	public Speler getSpelerAanDeBeurt()
	{
		return spelerAanDeBeurt;
	}
	
	/**
	 * Use Case 2:
	 * Geeft terug of het spel ten einde is of niet
	 * 
	 * @return geeft	true terug indien er een winnaar is
	 */
	public boolean isEindeSpel()
	{
		for(Speler speler : spelers)
		{
			if(speler.isGewonnen())
			{
				return true;
			}
		}
		
		// indien geen spelers gewonnen
		return false;
	}
	
	/**
	 * Use Case 2:
	 * Berekent de scores van alle spelers,
	 * de winnaar krijgt het totaal aantal strafpunten van de andere spelers als positieve score
	 */
	public void berekenScore()
	{
		if(isEindeSpel())
		{
			Speler gewonnenSpeler = null;
			
			for(Speler speler : spelers)
			{
				if(speler.isGewonnen())
				{
					gewonnenSpeler = speler;
				}
				else
				{
					speler.berekenScore();
				}
			}
			
			spelers.remove(gewonnenSpeler);
			int scoreWinnaar = 0;
			
			for(Speler speler : spelers)
			{
				scoreWinnaar += speler.getScore() * -1;
			}
			
			gewonnenSpeler.setScore(scoreWinnaar);
			
			spelers.add(gewonnenSpeler);
		}
	}
}
