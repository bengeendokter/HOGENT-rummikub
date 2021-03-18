package domein;

import persistentie.SpelerMapper;

public class SpelerRepository
{

	private final SpelerMapper mapper;

	public SpelerRepository()
	{
		mapper = new SpelerMapper();
	}

	public Speler geefSpeler(String gebruikersnaam, String wachtwoord)
	{
		Speler speler = mapper.geefSpeler(gebruikersnaam, wachtwoord);
		return speler;
	}
}
