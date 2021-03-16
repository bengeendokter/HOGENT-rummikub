package domein;

import java.util.ArrayList;
import java.util.List;

import persistentie.SpelerMapper;

/*public class SpelerRepository
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
}*/

public class SpelerRepository
{

    private final SpelerMapper mapper;
    //attribuut spelerslijst verwijderd (van repo)

    public SpelerRepository()
    {
        mapper = new SpelerMapper();
        //lijst van Spelers verwijderd
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
    
    //getSpeler en voegSpelerToe methodes verwijderd
}
