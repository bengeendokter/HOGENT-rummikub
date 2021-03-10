package domein;


public class DomeinController{
	
	private final SpelerRepository spelerrepo;
	
	public DomeinController()
	{
		spelerrepo = new SpelerRepository();
	}
	
	// tijdelijke test methode
	public Speler geefSpeler(String gebruikersnaam)
	{
		return spelerrepo.geefSpeler(gebruikersnaam);
	}
}
