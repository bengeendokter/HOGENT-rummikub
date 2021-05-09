package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.FouteEersteZetException;
import exceptions.FoutePositieException;
import exceptions.GeenPlaatsOpRijException;
import exceptions.GeenSerieOfRijException;
import exceptions.GeenSpelerSteenOpPlaats;
import exceptions.Min30PuntenException;
import exceptions.SteenIsGeenJokerException;

// TODO testen testen testen
public class Spel
{
	// 2 x (4 kleuren x 13 getallen) + 2 jokers = 106 stenen
	// getal (1-13) kleur (zwart, rood, blauw en geel) en joker (true or false)
	private List<Steen> stenen;
	private List<Speler> spelers;
	private Speler spelerAanDeBeurt;
	private int spelerAanDeBeurtIndex;
	private Beurt beurtInBegin;
	private Beurt beurtVoorActie;
	private Veld gv;
	private Veld wv;
	
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
		setSpelerAanDeBeurt(spelers.get(0), 0);
		
		verdeelStenen();
	}
	
	private void setSpelerAanDeBeurt(Speler speler, int spelerAanDeBeurtIndex)
	{
		this.spelerAanDeBeurt = speler;
		this.spelerAanDeBeurtIndex = spelerAanDeBeurtIndex;
		spelers.set(spelerAanDeBeurtIndex, speler);
	}
	
	public Speler getSpelerAanDeBeurt()
	{
		return spelerAanDeBeurt;
	}
	
	/** 
	 * Use Case 3:
	 * Initialiseert attribuut velden en maakt daarin twee velden aan, gemeenschappelijk en werkveld
	 */
	private void maakVelden()
	{
		gv = new Veld(true); // gemeenschappelijkveld
		wv = new Veld(false); // werkveld
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
	 */
	private void verdeelStenen()
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
	 * @return	geeft true terug indien er een winnaar is
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
		beurtInBegin = new Beurt(spelerAanDeBeurt, gv);
	}
	
	/**
	 * Use Case 3:
	 * Beëindigt de beurt en controleert of er een geldige spel situatie is
	 * 
	 * @return	geeft de String voorstelling van de speler stenen terug voor moest de speler een extra steen krijgen
	 */
	public String beeindigBeurt() throws GeenSerieOfRijException, Min30PuntenException
	{
		// variabele om de speler stenen met de nieuw getrokken steen te kunnen terug geven
		String stenenMetExtra = "";
		
		try
		{
			// controleer geldigheid indien eerste zet
			if(spelerAanDeBeurt.isEersteZet())
			{
				if(isSteenAfgelegd() && !wv.isGeldigeEersteZet())
				{
					throw new Min30PuntenException();
				}
			}
			
			// controleer gemeenschappelijk veld, is niet noodzakelijk aangezien op einde van acties al controle is
			gv.controleerVeld();
			
			// controleer werk veld en verplaats rijen/series naar gv
			werkVeldSetsNaarGv();
			
			// controleer of speler steen heeft afgelegd
			if(!isSteenAfgelegd())
			{
				neemSteenUitPot();
			}
			else if(spelerAanDeBeurt.isEersteZet())
			{
				spelerAanDeBeurt.setEersteZet(false);
			}
			
			// voor dat we de speler veranderen slagen we eerst zijn stenen op en veranderen we attribuut isEersteKeer als hij al aangelegd heeft
			stenenMetExtra = spelerAanDeBeurt.toString();
			
			// bepaal volgende speler aan de beurt		
			int index = spelerAanDeBeurtIndex + 1;
			index %= spelers.size();
			
			// stel de volgende speler aan de beurt in
			setSpelerAanDeBeurt(spelers.get(index), index);
			
			// maak het WerkVeld leeg
			wv = new Veld(false);
		}
		catch(Min30PuntenException | GeenSerieOfRijException e)
		{
			throw e;
		}
		return stenenMetExtra;
	}
	
	/**
	 * Use Case 3:
	 * Reset de beurt
	 */
	public void resetBeurt()
	{
		setSpelerAanDeBeurt(beurtInBegin.getSpeler(), spelerAanDeBeurtIndex);
		gv = beurtInBegin.getGemeenschappelijkVeld();
		wv = new Veld(false);
		startBeurt();
	}
	
	/**
	 * Use Case 3:
	 * Slaat een Beurt object op om indien nodig de actie te kunnen resetten
	 */
	private void slaBeurtOp()
	{
		beurtVoorActie = new Beurt(spelerAanDeBeurt, gv, wv);
	}
	
	/**
	 * Use Case 3:
	 * Reset de actie voor moest de actie niet toegestaan zijn
	 */
	private void resetActie()
	{
		setSpelerAanDeBeurt(beurtVoorActie.getSpeler(), spelerAanDeBeurtIndex);
		gv = beurtVoorActie.getGemeenschappelijkVeld();
		wv = beurtVoorActie.getWerkVeld();
	}
	
	/**
	 * Use Case 3:
	 * Legt steen aan op een veld (gemeenschappelijk (doelVeldIndex = 0) of werkveld (doelVeldIndex = 1))
	 * 
	 * @param positieDoel	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft waar de steen aangelegd moet worden
	 * @param doelIsWv		boolean geeft true als steen naar het werkveld moet (false voor gemeen. veld)
	 * @param positieBron	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft vanwaar de steen afkomstig is
	 * @param bronIsWv		boolean geeft true als steen zich bevindt op het werkveld (false voor persoonlijke bezit)
	 */
	public void legSteenAan(int[] positieDoel, boolean doelIsWv, int[] positieBron, boolean bronIsWv)
	throws FouteEersteZetException, GeenSerieOfRijException, FoutePositieException
	, GeenPlaatsOpRijException, GeenSpelerSteenOpPlaats
	{
		slaBeurtOp();
		
		try
		{
			if(spelerAanDeBeurt.isEersteZet() && !doelIsWv)
			{
				throw new FouteEersteZetException();
			}

			
			// zoek het doelVeld
			Veld doelVeld;
			
			if(doelIsWv) // doel == werkveld
			{
				doelVeld = wv;
			}
			else // doel == gemeenschappelijkveld
			{
				doelVeld = gv;
			}

			// zoek de bronSteen
			Steen bronSteen;
			
			if(bronIsWv) // bron == werkveld
			{
				Veld bronVeld = wv;
				bronSteen = bronVeld.removeSteen(positieBron);
			}
			else // bron == spelerStenen
			{
				int bronSpelerSteenIndex = positieBron[0];
				bronSteen = spelerAanDeBeurt.removeSteen(bronSpelerSteenIndex);
			}
			
			// voeg de bronSteen aan het doelVeld
			doelVeld.voegSteenToe(positieDoel, bronSteen);
			
			// indien het doelVeld het gemeenschappelijk veld is controleren we het veld en resetten we indien nodig de actie
			if(!doelIsWv)
			{

				doelVeld.controleerVeld();
			}
		}
		catch(FouteEersteZetException | GeenSerieOfRijException | FoutePositieException
				| GeenPlaatsOpRijException | GeenSpelerSteenOpPlaats e)
		{
			resetActie();
			throw e;
		}
	}
	
	/**
	 * Use Case 3: 
	 * Rij of serie splitsen
	 * 
	 * @param positieDoel	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft waar er gesplitst moet worden
	 * @param doelIsWv		boolean geeft true als het op het werkveld moet gesplitst worden (false voor gemeen. veld)
	 */
	public void splitsRijOfSerie(int[] positieDoel, boolean doelIsWv)
	throws FouteEersteZetException, GeenSerieOfRijException, FoutePositieException
	, GeenPlaatsOpRijException, GeenSpelerSteenOpPlaats
	{
		slaBeurtOp();
		
		try
		{
			if(spelerAanDeBeurt.isEersteZet() && !doelIsWv)
			{
				throw new FouteEersteZetException();
			}
			
			Veld doelVeld;
			
			if(doelIsWv) // doel == werkveld
			{
				doelVeld  = wv;
			}
			else // doel == gemeenschappelijkveld
			{
				doelVeld  = gv;
			}
			
			doelVeld.splitsRijOfSerie(positieDoel);
			
			// indien het doelVeld het gemeenschappelijk veld is controleren we het veld en resetten we indien nodig de actie
			if(!doelIsWv)
			{
				doelVeld.controleerVeld();
			}
		}
		catch(FouteEersteZetException | GeenSerieOfRijException | FoutePositieException
				| GeenPlaatsOpRijException | GeenSpelerSteenOpPlaats e)
		{
			resetActie();
			throw e;
		}
	}
	
	/**
	 * Use Case 3:
	 * Vervangt een steen met een joker
	 * 
	 * @param positieDoel	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft waar een steen vervangen moet worden
	 * @param doelIsWv		boolean geeft true als steen op het werkveld vervangen moet worden (false voor gemeen. veld)
	 * @param positieBron	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft vanwaar een steen afkomstig is
	 * @param bronIsWv		boolean geeft true als steen zich bevindt op het werkveld (false voor persoonlijke bezit)
	 */
	public void vervangJoker(int[] positieDoel, boolean doelIsWv, int[] positieBron, boolean bronIsWv)
	throws FouteEersteZetException, GeenSerieOfRijException, FoutePositieException
	, GeenPlaatsOpRijException, GeenSpelerSteenOpPlaats, SteenIsGeenJokerException
	{
		slaBeurtOp();
		
		try
		{
			if(spelerAanDeBeurt.isEersteZet() && !doelIsWv)
			{
				throw new FouteEersteZetException();
			}
			
			
			// zoek het doelVeld
			Veld doelVeld;
			
			if(doelIsWv) // doel == werkveld
			{
				doelVeld = wv;
			}
			else // doel == gemeenschappelijkveld
			{
				doelVeld = gv;
			}
			
			Steen controleSteen = doelVeld.geefSteen(positieDoel);
			
			//controleert eerst de steen (moet een joker zijn)
			if(!controleSteen.isJoker())
			{
				throw new SteenIsGeenJokerException();
			}
			
			//als steen joker is, dan pas verwijderen
			Steen doelSteen = doelVeld.removeSteen(positieDoel);
			
			// zoek de bronSteen
			Steen bronSteen;
			Object bron;
			if(bronIsWv) // bron == werkveld
			{
				bron = wv;
				bronSteen = ((Veld) bron).removeSteen(positieBron);
			}
			else // bron == spelerStenen
			{
				bron = spelerAanDeBeurt;
				bronSteen = ((Speler) bron).removeSteen(positieBron[0]);
			}
			
			// voeg de bronSteen aan het doelVeld
			doelVeld.voegSteenToe(positieDoel, bronSteen);
			

			// voeg de doelSteen (joker) toe aan werkVeld
			wv.voegSteenToe(positieBron, doelSteen);
			
			// indien het doelVeld het gemeenschappelijk veld is controleren we het veld en resetten we indien nodig de actie
			if(!doelIsWv)
			{
				doelVeld.controleerVeld();
			}
		}
		catch(FouteEersteZetException | GeenSerieOfRijException | SteenIsGeenJokerException
				| FoutePositieException | GeenPlaatsOpRijException | GeenSpelerSteenOpPlaats e)
		{
			resetActie();
			throw e;
		}
	}
	
	/**
	 * Use Case 3:
	 * Vervangt een steen naar het werkveld
	 * 
	 * @param positieDoel	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft waar een steen verplaatst moet worden
	 * @param positieBron	array met 2 int elementen (rij om de juiste StenenSet te vinden, kolom om de juiste steen van de juiste StenenSet te vinden)
	 * 						die aangeeft vanwaar een steen afkomstig is
	 */
	public void verplaatsNaarWerkveld(int[] positieDoel, int[] positieBron)
	throws FouteEersteZetException, GeenSerieOfRijException, FoutePositieException
	, GeenPlaatsOpRijException, GeenSpelerSteenOpPlaats
	{
		slaBeurtOp();
		
		try
		{
			if(spelerAanDeBeurt.isEersteZet())
			{
				throw new FouteEersteZetException();
			}
			
			// zoek het doelVeld (werkveld)
			Veld doelVeld = wv;
			
			// zoek de bronSteen
			Veld bronVeld = gv;
			Steen bronSteen = bronVeld.removeSteen(positieBron);
			
			// voeg de bronSteen aan het doelVeld
			doelVeld.voegSteenToe(positieDoel, bronSteen);
			

			bronVeld.controleerVeld();

		}
		catch(FouteEersteZetException | GeenSerieOfRijException | FoutePositieException
				| GeenPlaatsOpRijException | GeenSpelerSteenOpPlaats e)
		{
			resetActie();
			throw e;
		}
	}
	
	/**
	 * Use Case 3:
	 * Controleert of een speler een steen afgelegd heeft
	 * 
	 * @return	boolean, true als er een steen afgelegd is
	 */
	private boolean isSteenAfgelegd()
	{
		int beginAantalStenen = beurtInBegin.getSpeler().getStenen().size();
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
	
	/**
	 * Use Case 3:
	 * Controleert het werkveld en verplaatst daarna de geldige sets naar het gemeenschappelijkveld
	 */
	private void werkVeldSetsNaarGv() throws GeenSerieOfRijException
	{
		slaBeurtOp();
		
		try
		{
			wv.controleerVeld();
			
			for(StenenSet set : wv.getStenenSets())
			{
				gv.addSet(set);
			}
		}
		catch(GeenSerieOfRijException e)
		{
			resetActie();
			throw e;
		}
	}
	
	/**
	 * Use Case 3:
	 * Geeft een volledig overzicht van de stenen van de speler, het werkveld en het gemeenschappelijkveld
	 * 
	 * @return	String array met de To Sting van het gemeenschappelijkveld (0), het werkveld (1) en de speler stenen (2) 
	 */
	public String[] geefSpelOverzicht()
	{
		String[] spelOverzichtArray = new String[3];
		spelOverzichtArray[0] = gv.toString();
		spelOverzichtArray[1] = wv.toString();
		spelOverzichtArray[2] = spelerAanDeBeurt.toString();
		return spelOverzichtArray;
	}
}
