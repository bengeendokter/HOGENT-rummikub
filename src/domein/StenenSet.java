package domein;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StenenSet
{
	private List<Steen> stenen;
	
	public StenenSet(List<Steen> stenen)
	{
		this.stenen = stenen;
	}
	
	public List<Steen> getStenen()
	{
		return stenen;
	}
	
	public void voegSteenToe(int indexSteen, Steen steen)
	{
		// TODO wat indien index te hoog? Fout of hoogst mogelijke index pakken?
		stenen.add(indexSteen, steen);
	}
	
	public Steen removeSteen(int indexSteen)
	{
		// TODO wat indien index te hoog? Fout of hoogst mogelijke index pakken?
		return stenen.remove(indexSteen);
	}
	
	public void controleerSet()
	{
		
	}

	@Override
	public String toString()
	{
		Stream<String> stringList = stenen.stream().map(steen -> steen.toString());
		String output = stringList.collect(Collectors.joining("-"));		
		
		return output;
	}
}
