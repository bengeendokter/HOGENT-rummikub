package domein;

import java.util.ArrayList;
import java.util.Arrays;

public class Steen implements Comparable<Steen>
{
	private int getal;
	private String kleur;
	private boolean isJoker;
	
	/**
	 * Use Case 2:
	 * Constructor van Steen waaraan een boolean moet meegegeven worden.
	 * Deze constructor kan enkel gebruikt worden voor het maken van jokers
	 * 
	 * @param isJoker	boolean die bepaalt of een steen een joker is,
	 * 					deze mag enkel true zijn in deze constructor
	 */
	public Steen(boolean isJoker)
	{
		this(0, "joker", true);
		
		if(!isJoker)
		{
			throw new IllegalArgumentException("Deze constructor mag enkel aangeroepen worden voor jokers!");
		}
	}
	
	/**
	 * Use Case 2:
	 * Constructor van Steen waaraan een getal en kleur moet meegegeven worden.
	 * Deze constructor kan enkel gebruikt worden voor het maken van stenen die geen jokers zijn
	 * 
	 * @param getal		getal waarde van de steen
	 * @param kleur		kleur van de steen
	 */
	public Steen(int getal, String kleur)
	{
		this(getal, kleur, false);
	}
	
	/**
	 * Use Case 2:
	 * Constructor van Steen waaraan een getal, kleur en boolean moet meegegeven worden.
	 * Deze constructor kan gebruikt worden voor het maken van alle Stenen
	 * 
	 * @param getal		getal waarde van de steen, 0 indien een joker
	 * @param kleur		kleur van de steen, 'joker' indien een joker
	 * @param isJoker	boolean die bepaalt of een steen een joker is, true indien een joker
	 */
	public Steen(int getal, String kleur, boolean isJoker)
	{
		// eerst moet joker ingesteld worden wegens controle in andere setters
		setJoker(isJoker);
		
		setGetal(getal);
		setKleur(kleur);
	}
	
	private void setGetal(int getal)
	{
		if(isJoker)
		{
			if(getal != 0)
			{
				throw new IllegalArgumentException("Het getal moet 0 als de steen een joker is!");
			}
		}
		else
		{
			if(getal < 1 || getal > 13)
			{
				throw new IllegalArgumentException("Het getal moet tussen 1 en 13 liggen!");
			} 
		}
		
		this.getal = getal;
	}
	
	private void setKleur(String kleur)
	{
		ArrayList<String> kleuren = new ArrayList<>(Arrays.asList("zwart", "rood", "blauw", "geel"));
		
		if(isJoker)
		{
			if(!kleur.equals("joker"))
			{
				throw new IllegalArgumentException("De kleur moet 'joker' zijn als de steen een joker is!");
			}
		}
		else
		{
			if(!kleuren.contains(kleur))
			{
				throw new IllegalArgumentException("De kleur moet zwart, rood, blauw of geel zijn!");
			} 
		}
		
		this.kleur = kleur;
	}
	
	private void setJoker(boolean isJoker)
	{
		this.isJoker = isJoker;
	}
	
	public int getGetal()
	{
		return getal;
	}
	
	public String getKleur()
	{
		return kleur;
	}
	
	public boolean isJoker()
	{
		return isJoker;
	}
	
	/**
	 * Use Case 3:
	 * CompareTo methode die de Stenen eerst sorteert op kleur en daarna op getal waarde,
	 * jokers worden achteraan geplaatst
	 * 
	 * @param steen	andere steen waarmee vergeleken wordt
	 */
	@Override
	public int compareTo(Steen steen)
	{
		// controleer of 1 van de 2 een joker is
		if(steen.getKleur().equals("joker"))
		{
			return -1;
		}
		else if(kleur.equals("joker"))
		{
			return 1;
		}
		
		// vergelijk eerst de kleuren
		int vergelijk = kleur.compareTo(steen.getKleur());
		
		// indien kleuren gelijk, vergelijk de getallen 
		if(vergelijk == 0)
		{
			vergelijk = Integer.compare(getal, steen.getGetal());
		}

		return vergelijk;
	}
	
	/**
	 * Use Case 2:
	 * ToString methode die de steen weergeeft als een String van 3 karakters,
	 * bv. JOK, G01, B11...
	 * 
	 * @return	steen in de vorm van 3 karakters
	 */
	@Override
	public String toString()
	{
		if(isJoker)
		{
			return "JOK";
		}
		
		return String.format("%S%02d"
				, kleur.charAt(0)
				, getal);
	}
}
