package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Beurt
{
	private Speler speler;
	private Veld gv;
	
	public Beurt(Speler speler, Veld gv)
	{
		this.speler = new Speler(speler.getGebruikersnaam(), speler.getWachtwoord(), (List<Steen>) new ArrayList<>(speler.getStenen()));
		this.gv = new Veld(gv.getStenenSets().stream()
				.map(stenenSet -> new StenenSet(stenenSet.getStenen()))
				.collect(Collectors.toList()));
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
