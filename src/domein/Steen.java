package domein;

import java.util.ArrayList;
import java.util.Arrays;

public class Steen
{
	private int getal;
	private String kleur;
	private boolean isJoker;
	
	public Steen(boolean isJoker)
	{
		this(0, "joker", true);
		
		if(!isJoker)
		{
			throw new IllegalArgumentException("Deze constructor mag enkel aangeroepen worden voor jokers!");
		}
	}
	
	public Steen(int getal, String kleur)
	{
		this(getal, kleur, false);
	}
	
	public Steen(int getal, String kleur, boolean isJoker)
	{
		// eerst moet joker ingesteld worden wegens controle in andere setters
		setJoker(isJoker);
		
		setGetal(getal);
		setKleur(kleur);
	}
	
	private final void setGetal(int getal)
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
	
	private final void setKleur(String kleur)
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
	
	private final void setJoker(boolean isJoker)
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
