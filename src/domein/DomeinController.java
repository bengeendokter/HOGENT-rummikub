package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.ReedsAangemeldException;
import utility.Taal;

/*public class DomeinController{
	
	private final SpelerRepository spelerrepo;
	
	public DomeinController()
	{
		spelerrepo = new SpelerRepository();
	}
	
	
	public void registreerAantal(int aantal)
	{
		if(aantal < 2 || aantal > 4)
		{
			throw new IllegalArgumentException("Aantal spelers is kleiner dan 2 of groter dan 4");
		}
	}
	
	public void meldAan(String gebruikersnaam, String wachtwoord)
	{
		Speler gevondenSpeler = spelerrepo.geefSpeler(gebruikersnaam, wachtwoord);
		
		if(geefLijstGebruikersnaam().contains(gebruikersnaam))
		{
			throw new ReedsAangemeldException();
		}
		
		if(gevondenSpeler != null)
		{
			spelerrepo.voegSpelerToe(gevondenSpeler);
		}
		else
		{
			throw new IllegalArgumentException("Wachtwoord is incorrect");
		}
	}
	
	public List<String> geefLijstGebruikersnaam()
	{
		List<Speler> spelers = spelerrepo.getSpelers();
		List<String> gebruikersnamen = new ArrayList<>();
		for(Speler speler : spelers)
		{
			gebruikersnamen.add(speler.getGebruikersnaam());
		}
		return gebruikersnamen;		
	}
	
	@SuppressWarnings("exports")
	public Taal getTaal(String taal)
	{
		return new Taal(taal);
	}
}*/
public class DomeinController{
    
    private final SpelerRepository spelerrepo;
    //spelers worden bijgehouden door controller, niet door de repository
    private List<Speler> spelers;
    
    public DomeinController()
    {
        spelerrepo = new SpelerRepository();
        //lijst van de spelers die bijgehouden moeten worden
        spelers = new ArrayList<>();
    }
    
    //voegSpelerToe = add aan de lijst van spelers
    /*public void voegSpelerToe(Speler speler)
    {
        spelers.add(speler);
    }*/
    
    public void registreerAantal(int aantal)
    {
        if(aantal < 2 || aantal > 4)
        {
            throw new IllegalArgumentException("Aantal spelers is kleiner dan 2 of groter dan 4");
        }
    }
    
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        Speler gevondenSpeler = spelerrepo.geefSpeler(gebruikersnaam, wachtwoord);
        
        if(geefLijstGebruikersnaam().contains(gebruikersnaam))
        {
            throw new ReedsAangemeldException();
        }
        
        if(gevondenSpeler != null)
        {
            //spelerrepo.voegSpelerToe(gevondenSpeler);\
            spelers.add(gevondenSpeler);
        }
        else
        {
            throw new IllegalArgumentException("Wachtwoord is incorrect");
        }
    }
    
    public List<String> geefLijstGebruikersnaam()
    {
        //List<Speler> spelers = spelerrepo.getSpelers();
        List<String> gebruikersnamen = new ArrayList<>();
        for(Speler speler : spelers) //kan ook met for(Speler speler : getSpelers())
        {
            gebruikersnamen.add(speler.getGebruikersnaam());
        }
        return gebruikersnamen;        
    }

    public List<Speler> getSpelers() {
        return spelers;
    }
}
