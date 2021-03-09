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
	private void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	private void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
}
