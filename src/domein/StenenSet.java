package domein;

import java.util.List;

public class StenenSet
{
	private List<Steen> stenen;
	
	public StenenSet(List<Steen> stenen)
	{
		this.stenen = stenen;
	}
	
	public void voegSteenToe(int indexSteen, Steen steen)
	{
		stenen.add(indexSteen, steen);
	}
	
	public Steen removeSteen(int indexSteen)
	{
		return stenen.remove(indexSteen);
	}
	
	public void controleerSet()
	{
		
	}

	@Override
	public String toString()
	{
		// sorteer de stenen eerst
		return null;
	}
}
