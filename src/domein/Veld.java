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
	
	// TODO implementeer controles, hoe?
	
	public void voegSteenToe(int[] positieSteen, Steen steen)
	{
		int setIndex = positieSteen[0];
		int steenIndex = positieSteen[1];
		
		// TODO indien geen set is moet er 1 aangemaakt worden
		// wat indien te hoog? automatisch verkleinen of fout?
		
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
		int setIndex = positieSplitsing[0];
		int splitsIndex = positieSplitsing[1];
		
		StenenSet set = stenenSets.remove(setIndex);
		List<Steen> stenenList = set.getStenen();
		List<Steen> subList1 = stenenList.subList(0, splitsIndex);
		List<Steen> subList2 = stenenList.subList(splitsIndex, stenenList.size());
		
		StenenSet set1, set2;
		set1 = new StenenSet(subList1);
		set2 = new StenenSet(subList2);

		stenenSets.add(setIndex, set2);
		stenenSets.add(setIndex, set1);
	}
	
	public void maakStenenSet(List<Steen> stenen)
	{
		StenenSet set = new StenenSet(stenen);	
		stenenSets.add(set);
	}
	
	public void controleerVeld()
	{
		for(StenenSet set : stenenSets)
		{
			set.controleerSet();
		}
	}

	@Override
	public String toString()
	{
		// TODO implementeer
//		String output = "";
//		
//		for(StenenSet set : stenenSets)
//		{
//			String setString = set.toString();
//		}
		
		return null;
	}
}
