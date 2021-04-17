package domein;

import java.util.List;

public abstract class StenenSet
{
	private List<Steen> stenen;
	
	public StenenSet(List<Steen> stenen)
	{
		this.stenen = stenen;
	}
	
	public void verwijderSteen(int indexSteen)
	{
		stenen.remove(indexSteen);
	}
	
	public void voegSteenToe(int indexSteen, Steen steen)
	{
		stenen.add(indexSteen, steen);
	}
	
	public List<Steen> getStenen()
	{
		return stenen;
	}
}
