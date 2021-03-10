package domein;

import persistentie.SpelerMapper;

public class SpelerRepository
{

	private final SpelerMapper mapper;

	public SpelerRepository()
	{
		mapper = new SpelerMapper();
	}

	public Speler geefSpeler(String gebruikersnaam)
	{
		return mapper.geefSpeler(gebruikersnaam);
	}
}
