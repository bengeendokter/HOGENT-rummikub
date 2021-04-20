package domein;

import java.util.ArrayList;
import java.util.List;

public class Veld
{
	private List<StenenSet> stenenSets;
	
	public Veld()
	{
		stenenSets = new ArrayList<>();
	}
	
	public void voegSteenToe(int[] positieSteen, Steen steen)
	{
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		StenenSet set = stenenSets.get(setIndex);
		set.voegSteenToe(steenIndex, steen);
	}
	
	public Steen removeSteen(int[] positieSteen)
	{
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		StenenSet set = stenenSets.get(setIndex);
		return set.removeSteen(steenIndex);
	}

	public void splitsRijOfSerie(int[] positieSplitsing)
	{
/*		int setIndex = positieSplitsing[0];
		int splitsIndex = positieSplitsing[1];
		
		StenenSet set = stenenSets.remove(setIndex);
		List<Steen> stenenList = set.getStenen();
		List<Steen> subList1 = stenenList.subList(0, splitsIndex);
		List<Steen> subList2 = stenenList.subList(splitsIndex, stenenList.size());
		
		
		StenenSet set1, set2;
		if(set instanceof Serie)
		{
			set1 = new Serie(subList1);
			set2 = new Serie(subList2);
		}
		else
		{
			set1 = new Rij(subList1);
			set2 = new Rij(subList2);
		}
		
		stenenSets.add(setIndex, set2);
		stenenSets.add(setIndex, set1);*/
	}
	
	public void maakStenenSet(List<Steen> stenen)
	{
//		StenenSet set;
//		if(isSerie)
//		{
//			set = new Serie(stenen);
//		}
//		else
//		{
//			set = new Rij(stenen);
//		}
//		
//		stenenSets.add(set);
	}
	
	public void controleerVeld()
	{
		
	}

	@Override
	public String toString()
	{
		return null;
	}
}
