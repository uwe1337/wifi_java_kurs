package at.wifi.swdev.rechteck.service;

import at.wifi.swdev.rechteck.data.Author;
import at.wifi.swdev.rechteck.data.Rechteck;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class RechteckService {

    public void setDataForRechteck(Rechteck rechteck, double seiteA, double seiteB) {
        rechteck.setSeiteA(seiteA);
        rechteck.setSeiteB(seiteB);
    }

    public String calcRechteck(Rechteck rechteck) {
        
        if(rechteck.getSeiteA() != (double) rechteck.getSeiteA()) return "Die Seite-A ist kein Datentyp Double.";        
        if(rechteck.getSeiteA() > 5000000000d) return "Die Seite-A ist zu Lang.";
        if(rechteck.getSeiteA() <= 0.00) return "Die Seite-A muss mind. 0,01cm lang sein.";
        
        if(rechteck.getSeiteB() != (double) rechteck.getSeiteB()) return "Die Seite-B ist kein Datentyp Double.";
        if(rechteck.getSeiteB() > 5000000000d) return "Die Seite-B ist zu Lang.";
        if(rechteck.getSeiteB() <= 0.00) return "Die Seite-B muss mind. 0,01cm lang sein.";
        
        rechteck.calc();
        
        return "";
    }
    
    public String getAuthor(Author author) {
        return author.getAuthor();
    }

}
