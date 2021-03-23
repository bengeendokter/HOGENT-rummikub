package domein;

public class Steen {
    private int getal;
    private String kleur;
    private boolean isJoker;
    
    
    public Steen(int getal, String kleur, boolean isJoker) {
        setGetal(getal);
        setKleur(kleur);
        setJoker(isJoker);
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