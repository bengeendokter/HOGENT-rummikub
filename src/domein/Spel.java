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
	
	/** Use Case 3:
	 * Initialiseert attribuut velden en maakt daarin twee velden aan, gemeenschappelijk en werkveld
	 */
	private void maakVelden()
	{
		velden = new ArrayList<>();
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
	public String beeindigBeurt()
	{
		// TODO controleer geldige spelsituatie
		
		// variabele om de speler stenen met de nieuw getrokken steen te kunnen terug geven
		String stenenMetExtra;
		
		// controleer of speler steen heeft afgelegd
		if(!isSteenAfgelgelegd())
		{
			neemSteenUitPot();
		}
		
		// voor dat we de speler veranderen slagen we eerst zijn stenen op
		stenenMetExtra = spelerAanDeBeurt.toString();
		
		// bepaal volgende speler aan de beurt		
		int index = spelers.indexOf(spelerAanDeBeurt) + 1;
		index %= spelers.size();

		// stel de volgende speler aan de beurt in
		spelerAanDeBeurt = spelers.get(index);
		
		// maak het WerkVeld leeg
		velden.set(1, new Veld());
		
		return stenenMetExtra;
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
		startBeurt();
	}

	/**
	 * Use Case 3:
	 * Legt steen aan op een veld
	 * (gemeenschappelijk (doelVeldIndex = 0) of werkveld (doelVeldIndex = 1))
	 * @param positieDoel array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * die aangeeft waar de steen aangelegd moet worden
	 * @param doelIsWv boolean geeft true als steen naar het werkveld moet (false voor gemeen. veld)
	 * @param positieBron array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * die aangeeft vanwaar de steen afkomstig is
	 * @param bronIsWv boolean geeft true als steen zich bevindt op het werkveld (false voor persoonlijke bezit)
	 */
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
	
	/**
	 * Use Case 3: 
	 * Rij of serie splitsen
	 * @param positieDoel	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * die aangeeft waar er gesplitst moet worden
	 * @param doelIsWv	boolean geeft true als het op het werkveld moet geplitst worden (false voor gemeen. veld)
	 */
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
	 * Vervangt een steen met een joker
	 * @param positieDoel array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * die aangeeft waar een steen vervangen moet worden
	 * @param doelIsWv boolean geeft true als steen op het werkveld vervangen moet worden (false voor gemeen. veld)
	 * @param positieBron array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * die aangeeft vanwaar een steen afkomstig is
	 * @param bronIsWv boolean geeft true als steen zich bevindt op het werkveld (false voor persoonlijke bezit)
	 */
	public void vervangJoker(int[] positieDoel, boolean doelIsWv, int[] positieBron, boolean bronIsWv)
	{	
		////moet doelsteen niet gecontroleerd worden (is het joker of niet (bv met steen.toString() == "JOK") ?
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
			////moet dit niet bronSteen = ((Speler) bron).removeSteen(positieBron[1]); zijn omdat de kolom de steen aanduidt?
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
	
	
	/**
	 * Use Case 3:
	 * Vervangt een steen naar het werkveld
	 * @param positieDoel array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * die aangeeft waar een steen verplaatst moet worden
	 * @param positieBron	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * die aangeeft vanwaar een steen afkomstig is
	 */
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
	
	/**
	 * Use Case 3:
	 * Controleert of een speler een steen afgelegd heeft
	 * @return	boolean true als steen afgelegd is
	 */
	//veranderen in isSteenAfgelegd?
	private boolean isSteenAfgelgelegd()
	{
		int beginAantalStenen = beurt.getSpeler().getStenen().size();
		int huidigAantalStenen = spelerAanDeBeurt.getStenen().size();
		
		return beginAantalStenen != huidigAantalStenen;
	}
	
	/**
	 * Use Case 3: 
	 * Speler voegt een willekeurig steen aan zijn bezit door middel van de pot
	 */
	
	private void neemSteenUitPot()
	{
		spelerAanDeBeurt.voegSteenToe(stenen.remove(stenen.size() - 1));
	}
	
//	private void controleerGemeenschappelijkVeld()
//	{
//		velden.get(0).controleerVeld();
//	}
//
//	private void controleerWerkVeld()
//	{
//		velden.get(1).controleerVeld();
//	}
	
//	private void controleerSpel()
//	{
//		// is deze methode nodig?
//	}
	
	public String[] geefSpelOverzicht()
	{ 
		String[] spelOverzichtArray = new String[3];
		spelOverzichtArray[0] = velden.get(0).toString();
		spelOverzichtArray[1] = velden.get(1).toString();
		spelOverzichtArray[2] = spelerAanDeBeurt.toString();
		return spelOverzichtArray;
	}
}
