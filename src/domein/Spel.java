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
	private Beurt beurt;
	private Veld velden;
	/*hoe gv en wv*/
	
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
	 * Maakt de 106 stenen aan en stopt deze in stenen
	 */
	private void maakStenen()
	{
		stenen = new ArrayList<>();
		
		String[] kleuren = {"zwart", "rood", "blauw", "geel"};
		
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
					stenen.add(new Steen(getalWaarde, kleuren[indexKleur]));
				}
			}
			
			// 1 joker
			stenen.add(new Steen(true));
		}
		
		// geeft de stenen een willekeurige volgorde
		Collections.shuffle(stenen);
	}
	
	/**
	 * Use Case 2:
	 * Geeft elke speler 14 stenen
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
				// TODO maak aparte neemSteen methode met "speler" en "steen" argumenten in deze klasse Spel
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
	

	/**
	 * Use Case 3:
	 * Start een nieuw beurt door een Beurt object aan te maken
	 */
	public void startBeurt() {
		beurt = new Beurt (spelerAanDeBeurt, null);
		//hoe ook met gemeen veld als parameter
	}
	
	/**
	 * Use Case 3:
	 * Beeindigd de beurt
	 */
	public void beeindigBeurt() {
		
	}
	
	/**
	 * Use Case 3:
	 * Reset de beurt
	 */
	public void resetBeurt() {
		
	}
	
	/**
	 * Use Case 3:
	 * vervangt een positie met een Joker
	 * @param positieJoker is persoonlijk bezit van de speler of steen uit het werkveld
	 * @param indexSteen is plaats waar Joker zal geplaatst worden
	 */
	public void vervangJoker(int[] positieJoker, int indexSteen) {
		
	}
	
	public void verwijderSteenSpeler(int indexSteen) {
		spelerAanDeBeurt.verwijderSteen(indexSteen);
	}
	
	public void voegSteenToeSpeler(Steen steen) {
		spelerAanDeBeurt.voegSteenToe(steen);
	}
	
	public void verwijderSteenVeld(int[] positieSteen) {
		
	}
	
	public void voegSteenToeVeld(int[] positieSteen, Steen steen) {
		
	}
	
	public void splitsRijOfSerie(int[] positieSplitsing) {
		
	}
}
