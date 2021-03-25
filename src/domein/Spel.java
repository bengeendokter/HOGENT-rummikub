package domein;

import java.util.ArrayList;
import java.util.List;

public class Spel {
        // 2 x (4 kleuren x 13 getallen) + 2 jokers = 106 stenen
        // getal (1-13) kleur (zwart, rood, blauw en geel) en joker (true or false)
        private List<Steen> stenen;
        //private List<Speler> volgordeSpelers;
        
        /**
         * Use Case 2
         * Constructor van Spel die alle 106 stenen aanmaakt een bijhoud in een lijst
         */
        public Spel() {
            stenen = new ArrayList<>();
            String[] kleuren = {"zwart", "rood", "blauw", "geel"};
            
            // 2 keer overlopen, elke steen zit dubbel in de lijst
            for (int a = 0; a < 2; a++) {
                
            // 4 kleuren * 13 getallen
                // 4 kleuren
                for (int indexKleur = 0; indexKleur < 4; indexKleur++) {
                    
                    // 13 getallen
                    for (int getalWaarde = 1; getalWaarde < 14; getalWaarde++) {
                        stenen.add(new Steen(getalWaarde, kleuren[indexKleur], false));
                    }
                }
                
                // 1 joker
                stenen.add(new Steen(0, "joker", true));
            }
            
            setStenen(stenen);
        }
        
        // nog te implementeren
        public boolean isEindeSpel() {
            return false;
        }
        
        public List<Steen> getStenen() {
            return stenen;
        }

        private final void setStenen(List<Steen> stenen) {
            this.stenen = stenen;
        }

        // test methode om de waarden van de stenen te controleren (geen lege of foute waarden)
        public String text() {
            String text = "";
            
            for (Steen s: stenen) {
                text += String.format("%s%n", s.toString());
            }
            
            return text;
        }
}