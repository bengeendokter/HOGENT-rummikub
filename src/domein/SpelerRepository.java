package domein;

import persistentie.SpelerMapper;

public class SpelerRepository
{

	private final SpelerMapper mapper;

	public SpelerRepository()
	{
		mapper = new SpelerMapper();
	}

	/**
	 * UC1
	 * speler opvragen
	 * @param gebruikersnaam
	 * @param wachtwoord
	 * @return
	 */
	
	public Speler geefSpeler(String gebruikersnaam, String wachtwoord)
	{
		Speler speler = mapper.geefSpeler(gebruikersnaam, wachtwoord);
		return speler;
	}
}
