package domein;

import java.util.ArrayList;
import java.util.List;

import persistentie.SpelerMapper;

public class SpelerRepository
{

	private final SpelerMapper mapper;
	private List<Speler> spelers;

	public SpelerRepository()
	{
		mapper = new SpelerMapper();
		spelers = new ArrayList<>();
	}

	public Speler geefSpeler(String gebruikersnaam, String wachtwoord)
	{
		Speler speler = mapper.geefSpeler(gebruikersnaam);
		
		if (speler.getWachtwoord().equals(wachtwoord))
		{
			return speler;
		}
        return null;
	}
	
	public void voegSpelerToe(Speler speler)
	{
		spelers.add(speler);
	}

	
	public List<Speler> getSpelers()
	{
		return spelers;
	}
}
