package at.wifi.swdev.rechteck.data;

import java.io.Serializable;

public class Rechteck implements Serializable {

    private double seiteA;
    private double seiteB;
    private double umfang;
    private double flaeche;
    private boolean berechnet;

    public Rechteck() {
    }

    public Rechteck(double seiteA, double seiteB) {
        this.seiteA = seiteA;
        this.seiteB = seiteB;
        this.umfang = 0;
        this.flaeche = 0;
        this.berechnet = false;
    }

    public boolean isBerechnet() {
        return berechnet;
    }

    public void setBerechnet(boolean berechnet) {
        this.berechnet = berechnet;
    }

    public double getSeiteA() {
        return seiteA;
    }

    public void setSeiteA(double seiteA) {
        this.seiteA = seiteA;
    }

    public double getSeiteB() {
        return seiteB;
    }

    public void setSeiteB(double seiteB) {
        this.seiteB = seiteB;
    }

    public double getUmfang() {
        return umfang;
    }

    public void setUmfang(double umfang) {
        this.umfang = umfang;
    }

    public double getFlaeche() {
        return flaeche;
    }

    public void setFlaeche(double flaeche) {
        this.flaeche = flaeche;
    }

    @Override
    public String toString() {
        return "toString: Rechteck{" + "seiteA=" + seiteA + ", seiteB=" + seiteB + ", umfang=" + umfang + ", flaeche=" + flaeche + ", berechnet=" + berechnet + '}';
    }
    
    public void calc() {
        this.umfang = (2 * this.seiteA) + (2 * this.seiteB);
        this.flaeche = this.seiteA * this.seiteB;
        this.berechnet = true;
    }
    
}
