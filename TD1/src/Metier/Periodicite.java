package Metier;

import java.sql.*;

public class Periodicite {
    private int cle;
    private String libelle;

    public Periodicite(String ch) {
        this.libelle = ch;
    }

    public Periodicite(int cle, String libelle) {
        this.cle = cle;
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getCle() {
        return cle;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }


}
