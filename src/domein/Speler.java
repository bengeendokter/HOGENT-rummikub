package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import exceptions.GeenSpelerSteenOpPlaats;

public class Speler implements Comparable<Speler>
{
	private String gebruikersnaam;
	private String wachtwoord;
	private int score;
	private int totaalScore;
	private List<Steen> stenen;
	private boolean isEersteZet;
	
	/**
	 * Use Case 2:
	 * Constructor die een Speler object aanmaakt aan de hand van de gebruikersnaam en wachtwoord
	 * 
	 * @param gebruikersnaam	de naam van de Speler
	 * @param wachtwoord		het wachtwoord van de Speler
	 * @param totaalScore		de totaal score van alle gespeelde spellen
	 */
	public Speler(String gebruikersnaam, String wachtwoord, int totaalScore)
	{
		this(gebruikersnaam, wachtwoord, new ArrayList<>(), totaalScore);
	}
	
	/**
	 * Use Case 3:
	 * Constructor die een Speler object aanmaakt aan de hand van de gebruikersnaam, het wachtwoord en een lijst van Stenen.
	 * Deze Constructor wordt vooral gebruikt de constructor van de klasse Beurt
	 * 
	 * @param gebruikersnaam	de naam van de Speler
	 * @param wachtwoord		het wachtwoord van de Speler
	 * @param stenen			een lijst van Stenen
	 * @param totaalScore		de totaal score van alle gespeelde spellen
	 */
	public Speler(String gebruikersnaam, String wachtwoord, List<Steen> stenen, int totaalScore)
	{
		this.stenen = stenen;
		this.setGebruikersnaam(gebruikersnaam);
		this.setWachtwoord(wachtwoord);
		this.setScore(0);
		this.setTotaalScore(totaalScore);
		setEersteZet(true);
	}
	
	public String getGebruikersnaam()
	{
		return gebruikersnaam;
	}
	
	private void setGebruikersnaam(String gebruikersnaam)
	{
		if(gebruikersnaam == null || gebruikersnaam.isBlank())
		{
			throw new IllegalArgumentException("De gebruikersnaam mag niet leeg zijn");
		}
		
		this.gebruikersnaam = gebruikersnaam;
		
	}
	
	public String getWachtwoord()
	{
		return wachtwoord;
	}
	
	private void setWachtwoord(String wachtwoord)
	{
		if(wachtwoord == null || wachtwoord.isBlank())
		{
			throw new IllegalArgumentException("Het wachtwoord mag niet leeg zijn");
		}
		else
		{
			this.wachtwoord = wachtwoord;
		}
	}
	
	public int getScore()
	{
		return score;
	}
	
	public final void setScore(int score)
	{
		this.score = score;
	}
	
	public int getTotaalScore()
	{
		return totaalScore;
	}

	private void setTotaalScore(int totaalScore)
	{
		this.totaalScore = totaalScore;
	}

	public boolean isEersteZet()
	{
		return isEersteZet;
	}
	
	public final void setEersteZet(boolean isEersteKeer)
	{
		this.isEersteZet = isEersteKeer;
	}
	
	/**
	 * Use Case 2:
	 * Voegt een steen toe aan de stenen van de speler
	 * 
	 * @param steen	de steen die toegevoegd moet worden bij de speler
	 */
	public void voegSteenToe(Steen steen)
	{
		stenen.add(steen);
	}
	
	/**
	 * Use Case 2:
	 * Geeft terug of de speler gewonnen is of niet
	 * 
	 * @return	geeft true terug indien de speler geen stenen meer heeft
	 */
	public boolean isGewonnen()
	{
		if(stenen.size() == 0)
		{
			return true;
		}
		// else(stenen.size() > 0)
		return false;
	}
	
	/**
	 * Use Case 2:
	 * Berekend de score van de speler aan de hand van de waarde van hun stenen 
	 */
	public void berekenScore()
	{
		for(Steen steen : stenen)
		{
			if(steen.isJoker())
			{
				score -= 25;
			}
			else
			{
				score -= steen.getGetal();
			}
		}
	}
	
	public List<Steen> getStenen()
	{
		return stenen;
	}
	
	/**
	 * Use Case 4:
	 * Update de totaal score door er de huidige score bij op te tellen
	 */
	public void updateTotaalScore()
	{
		totaalScore += score;
	}
	
	/**
	 * Use Case 3:
	 * Verwijdert een Steen op een bepaalde index van de spelerStenen en geeft deze Steen ook terug
	 * 
	 * @param indexSteen	index van de steen die moet verwijderd worden
	 * @return				Steen object van de verwijderde Steen
	 */
	public Steen removeSteen(int indexSteen) throws GeenSpelerSteenOpPlaats
	{		
		// indexSteen moet verwijzen naar een Speler steen
		if(indexSteen >= stenen.size())
		{
			throw new GeenSpelerSteenOpPlaats();
		}
		
		return stenen.remove(indexSteen);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(gebruikersnaam);
	}
	
	/**
	 * Use Case 3:
	 * Equals methode die bepaalt dat twee spelers gelijk zijn indien hun gebruikersnamen dezelfde zijn
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(!(obj instanceof Speler))
		{
			return false;
		}
		Speler other = (Speler) obj;
		return Objects.equals(gebruikersnaam, other.gebruikersnaam);
	}
	
	/**
	 * Use Case 4:
	 * Sorteert spelers eerst volgens totaalScore en daarna volgens gebruikersnaam
	 * 
	 * @param speler	andere speler waarmee vergeleken wordt
	 */
	@Override
	public int compareTo(Speler speler)
	{
		int vergelijk = Integer.compare(totaalScore, speler.getTotaalScore()) * -1;
		
		if(vergelijk == 0)
		{
			vergelijk = gebruikersnaam.compareTo(speler.getGebruikersnaam());
		}
		
		return vergelijk;
	}
	
	/**
	 * Use Case 3:
	 * ToString methode die de Speler weergeeft als een lijst van zijn huidige stenen.
	 * Deze methode wordt gebruikt in geefSpelOverzicht() van de klasse Spel
	 */
	@Override
	public String toString()
	{
		// we sorteren eerst de stenen
		Collections.sort(stenen);
		
		String output = "";
		String steenString;
		
		int huidigNr = 1;
		String steenNrs;
		
		for(int steenIndex = 0; steenIndex < stenen.size(); steenIndex++)
		{
			// indien we een nieuwe rij moeten beginnen
			if((steenIndex % 13) == 0)
			{
				// indien dit niet de eerste lijn is, laat een regel open
				if(steenIndex != 0)
				{
					output += "\n\n";
				}
				
				// plaats nieuwe nummer rij
				steenNrs = "";
				for(int i = 1; i < 14; i++)
				{
					steenNrs += String.format("%02d%2s", huidigNr++, "");
				}
				output += steenNrs + "\n";
			}
			
			// voeg steen toe aan output
			steenString = stenen.get(steenIndex).toString();
			output += String.format("%-4s", steenString);
		}
		
		return output;
	}
}
