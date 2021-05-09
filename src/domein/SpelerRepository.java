package domein;

import exceptions.SpelerNietGevondenException;
import persistentie.SpelerMapper;

public class SpelerRepository
{
	private final SpelerMapper mapper;

	public SpelerRepository()
	{
		mapper = new SpelerMapper();
	}

	/**
	 * Use Case 1:
	 * Vraagt de spelers op in de databank, indien de speler niet gevonden wordt is er een exception gegooid
	 * 
	 * @param gebruikersnaam	de gebruikersnaam waarnaar gezocht wordt in de databank
	 * @param wachtwoord		het wachtwoord waarnaar gezocht wordt in de databank
	 * @return					een Speler object met de meegegeven gebruikersnaam en wachtwoord 
	 */
	public Speler geefSpeler(String gebruikersnaam, String wachtwoord) throws SpelerNietGevondenException, RuntimeException
	{
		Speler speler = mapper.geefSpeler(gebruikersnaam, wachtwoord);
		return speler;
	}
	
	/**
	 * Use Case 4:
	 * Update de totaalScore van de speler in de databank
	 * 
	 * @param speler	de speler waarvan de score geüpdate wordt
	 */
	public void updateTotaalScore(Speler speler) throws RuntimeException
	{
		mapper.updateTotaalScore(speler);
	}
}
