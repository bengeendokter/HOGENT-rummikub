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
	private List<Veld> velden; // [gemeenschappelijkveld, werkveld]
	
	/**
	 * Use Case 2:
	 * Constructor van Spel die alle 106 stenen aanmaakt en bijhoudt in een lijst,
	 * de volgorde van de spelers bepaalt en de spelers elk 14 stenen geeft
	 * 
	 * @param spelers	een lijst met de aangemelde spelers
	 */
	public Spel(List<Speler> spelers)
	{
		maakVelden();
		maakStenen();
		
		// bepaal de speler volgorde en de eerste speler aan de beurt
		Collections.shuffle(spelers);
		this.spelers = spelers;
		setSpelerAanDeBeurt(spelers.get(0));
		
		verdeelStenen(spelers);
	}
	
	public void setSpelerAanDeBeurt(Speler speler)
	{
		this.spelerAanDeBeurt = speler;
	}
	
	public Speler getSpelerAanDeBeurt()
	{
		return spelerAanDeBeurt;
	}
	
	private void maakVelden()
	{
		velden.add(new Veld()); // gemeenschappelijkveld
		velden.add(new Veld()); // werkveld
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
				speler.voegSteenToe(stenen.remove(stenen.size() - 1));
			}
		}
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
	public void startBeurt()
	{
		beurt = new Beurt(spelerAanDeBeurt, velden.get(0));
	}
	
	/**
	 * Use Case 3:
	 * Beëindigt de beurt
	 */
	public void beeindigBeurt()
	{
		// TODO controleer geldige spelsituatie
		
		// controleer of speler steen heeft afgelegd
		if(!isSteenAfgelgelegd())
		{
			neemSteenUitPot();
		}
		
		// bepaal volgende speler aan de beurt		
		int index = spelers.indexOf(spelerAanDeBeurt);
		index++;

		// stel de volgende speler aan de beurt in
		spelerAanDeBeurt = spelers.get(index);
		
		// maak het WerkVeld leeg
		velden.set(1, new Veld());
	}
	
	/**
	 * Use Case 3:
	 * Reset de beurt
	 */
	public void resetBeurt()
	{
		spelerAanDeBeurt = beurt.getSpeler();
		velden.set(0, beurt.getGemeenschappelijkVeld());
		velden.set(1, new Veld());
	}

	public void legSteenAan(int[] positieDoel, boolean doelIsWv, int[] positieBron, boolean bronIsWv)
	{
		// zoek het doelVeld
		int doelVeldIndex;
		
		if(doelIsWv) // doel == werkveld
		{
			doelVeldIndex = 1;
		}
		else // doel == gemeenschappelijkveld
		{
			doelVeldIndex = 0;
		}
		
		Veld doelVeld = velden.get(doelVeldIndex);
		
		// zoek de bronSteen
		Steen bronSteen;
		if(bronIsWv) // bron == werkveld
		{
			Veld bronVeld = velden.get(1);
			bronSteen = bronVeld.removeSteen(positieBron);
		}
		else // bron == spelerStenen
		{
			int bronSpelerSteenIndex = positieBron[0];
			bronSteen = spelerAanDeBeurt.removeSteen(bronSpelerSteenIndex);
		}
		
		// voeg de bronSteen aan het doelVeld
		doelVeld.voegSteenToe(positieDoel, bronSteen);
	}
	
	public void splitsRijOfSerie(int[] positieDoel, boolean doelIsWv)
	{
		int doelVeldIndex;
		
		if(doelIsWv) // doel == werkveld
		{
			doelVeldIndex = 1;
		}
		else // doel == gemeenschappelijkveld
		{
			doelVeldIndex = 0;
		}
		
		Veld doelVeld = velden.get(doelVeldIndex);
		doelVeld.splitsRijOfSerie(positieDoel);
	}
	
	/**
	 * Use Case 3:
	 * Vervangt een positie met een Joker
	 * 
	 * @param positieJoker	de plaats van de Joker in veld
	 * @param indexSteen	is plaats van de Steen in hand
	 */
	public void vervangJoker(int[] positieDoel, boolean doelIsWv, int[] positieBron, boolean bronIsWv)
	{		
		// zoek het doelVeld
		int doelVeldIndex;
		
		if(doelIsWv) // doel == werkveld
		{
			doelVeldIndex = 1;
		}
		else // doel == gemeenschappelijkveld
		{
			doelVeldIndex = 0;
		}
		
		Veld doelVeld = velden.get(doelVeldIndex);
		Steen doelSteen = doelVeld.removeSteen(positieDoel);
		
		// zoek de bronSteen
		Steen bronSteen;
		Object bron;
		if(bronIsWv) // bron == werkveld
		{
			bron = velden.get(1);
			bronSteen = ((Veld) bron).removeSteen(positieBron);
		}
		else // bron == spelerStenen
		{
			bron = spelerAanDeBeurt;
			bronSteen = ((Speler) bron).removeSteen(positieBron[0]);
		}
		
		// voeg de bronSteen aan het doelVeld
		doelVeld.voegSteenToe(positieDoel, bronSteen);
		
		// voeg de doelSteen toe aan bronVeld/Speler
		if(bron instanceof Veld)
		{
			((Veld) bron).voegSteenToe(positieBron, doelSteen);
		}
		else
		{
			((Speler) bron).voegSteenToe(doelSteen);
		}
	}
	
	public void verplaatsNaarWerkveld(int[] positieDoel, int[] positieBron)
	{
		// zoek het doelVeld (werkveld)
		Veld doelVeld = velden.get(1);
		
		// zoek de bronSteen
		Veld bronVeld = velden.get(0);
		Steen bronSteen = bronVeld.removeSteen(positieBron);

		// voeg de bronSteen aan het doelVeld
		doelVeld.voegSteenToe(positieDoel, bronSteen);
	}
	
	private boolean isSteenAfgelgelegd()
	{
		int beginAantalStenen = beurt.getSpeler().getStenen().size();
		int huidigAantalStenen = spelerAanDeBeurt.getStenen().size();
		
		return beginAantalStenen != huidigAantalStenen;
	}
	
	private void neemSteenUitPot()
	{
		spelerAanDeBeurt.voegSteenToe(stenen.remove(stenen.size() - 1));
	}
	
//	private void controleerSpel()
//	{
//		
//	}
	
	public String[] geefSpelOverzicht()
	{ 
		return null;
	}
}
