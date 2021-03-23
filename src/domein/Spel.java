package domein;

import java.util.ArrayList;
import java.util.List;

public class Spel {
        //4 kleuren x 13 getallen x 2 + 2 jokers = 106 stenen
        //getal (1-13) en kleur (zwart, rood, blauw en geel)
        private List<Steen> stenen;
        
        public Spel() {
            stenen = new ArrayList<>();
            String[] kleuren = {"zwart", "rood", "blauw", "geel"};
            
            //2 keer lopen (normaal (met getal en kleur) + joker)
            for (int a = 0; a < 2; a++) {
                
                //4 kleuren * 13 getallen
                //4 kleuren
                for (int i = 0; i < 4; i++) {
                    
                    //13 getallen
                    for (int j = 1; j < 14; j++) {
                        stenen.add(new Steen(j, kleuren[i], false));
                    }
                }
                
                //1 joker
                stenen.add(new Steen(0, "joker", true));
            }
            
            setStenen(stenen);
        }
        
        //nog te implementeren
        public boolean isEindeSpel() {
            return false;
        }
        
        
        
        public List<Steen> getStenen() {
            return stenen;
        }

        private final void setStenen(List<Steen> stenen) {
            this.stenen = stenen;
        }

        //methode om de waarden van de stenen te controleren (geen lege of foute waarden)
        public String text() {
            String text = "";
            
            for (Steen s: stenen) {
                text += String.format("%s%n", s.toString());
            }
            
            return text;
        }
}