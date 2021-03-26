package domein;

import java.util.ArrayList;
import java.util.List;

public class Speler
{
	private String gebruikersnaam;
	private String wachtwoord;
	private int score;
	private List<Steen> stenen = new ArrayList<Steen>();

	
	public Speler(String gebruikersnaam, String wachtwoord)
	{
		this.setGebruikersnaam(gebruikersnaam);
		this.setWachtwoord(wachtwoord);
		this.setScore(0);
	}

	public String getGebruikersnaam()
	{
		return gebruikersnaam;
	}

	private void setGebruikersnaam(String gebruikersnaam)
	{
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getWachtwoord()
	{
		return wachtwoord;
	}

	private void setWachtwoord(String wachtwoord)
	{
		this.wachtwoord = wachtwoord;
	}
	
	public int getScore()
	{
		return score;
	}

	private void setScore(int score)
	{
		this.score = score;
	}
	
	/**
	 * Use Case 2:
	 * Voegt een steen toe aan de stenen van de speler
	 * 
	 * @param steen	de steen die toegevoegd moet worden bij de speler
	 */
	public void neemSteen(Steen steen)
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
}
