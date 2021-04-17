package domein;

public class Beurt
{
	private Speler speler;
	private GemeenschappelijkVeld gv;
	
	public Beurt(Speler speler, GemeenschappelijkVeld gv)
	{
		this.speler = speler;
		this.gv = gv;
	}

	public Speler getSpeler()
	{
		return speler;
	}

	public GemeenschappelijkVeld getGv()
	{
		return gv;
	}
}
