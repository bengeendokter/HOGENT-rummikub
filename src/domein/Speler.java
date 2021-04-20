package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Speler
{
	private String gebruikersnaam;
	private String wachtwoord;
	private int score;
	private List<Steen> stenen;
	
	public Speler(String gebruikersnaam, String wachtwoord)
	{
		stenen = new ArrayList<>();
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
	
	/**
	 * Use Case 3:
	 * @return geeft de stenen van de speler in een lijst
	 */
	public List<Steen> getStenen()
	{
		// sorteer de stenen eerst
		return stenen;
	}
	
	public Steen removeSteen(int indexSteen)
	{
		return stenen.remove(indexSteen);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(gebruikersnaam);
	}
	
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
	
}
