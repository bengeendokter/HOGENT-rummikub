package domein;

public class Beurt
{
	private Speler speler;
	private Veld gv;
	
	public Beurt(Speler speler, Veld gv)
	{
		this.speler = speler;
		this.gv = gv;
	}

	public Speler getSpeler()
	{
		return speler;
	}

	public Veld getGemeenschappelijkVeld()
	{
		return gv;
	}
}
