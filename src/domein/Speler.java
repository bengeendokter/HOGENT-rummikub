package domein;

public class Speler {
	private String gebruikersnaam;
	private String wachtwoord;
	
	public Speler(String gebruikersnaam, String wachtwoord) {
		this.setGebruikersnaam(gebruikersnaam);
		this.setWachtwoord(wachtwoord);
	}
	
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public final void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	public final void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
}
