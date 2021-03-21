package domein;

import java.security.SecureRandom;

public class Steen {
	private int getal;
	private String kleur;
	private boolean isJoker;
	
	
	public Steen() {
		bepaalWaarde();
	}

	//bepaald getal (1-13) en kleur (zwart, rood, blauw en geel)
	private void bepaalWaarde() {
		//gebruikt voor random nummer
		SecureRandom sr = new SecureRandom();
		//gebruikt voor kleur
		String[] kleuren = {"zwart", "rood", "blauw", "geel", "joker"};
		
		//1-13 en 0 voor joker
		int getal1 = sr.nextInt(14);
		
		//bij 0 zal het automatisch een waarde "0" krijgen
		if (getal1 != 0)
		setGetal(getal1);
		
		String kleur1 = kleuren[sr.nextInt(5)];
		
		//bij "joker" zal het automatisch een waarde "null" krijgen
		if (kleur1 != "joker")
		setKleur(kleur1);
		
		//controleren of steen een joker is
		boolean joker = false;
		
		if ((getGetal() == 0) || (getKleur() == null))
			joker = true;
		
		setJoker(joker);
	}

	private final void setGetal(int getal) {
		this.getal = getal;
	}

	private final void setKleur(String kleur) {
		this.kleur = kleur;
	}

	private final void setJoker(boolean isJoker) {
		this.isJoker = isJoker;
	}

	public int getGetal() {
		return getal;
	}

	public String getKleur() {
		return kleur;
	}

	public boolean isJoker() {
		return isJoker;
	}

	@Override
	public String toString() {
		if (isJoker() == true)
			return "JOKER";
		
		return String.format("%d %s", getGetal(), getKleur());
	}
}
