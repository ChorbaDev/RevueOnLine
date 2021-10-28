package modele.metier;

import java.util.Objects;

public class Client {
    private int cle;
    private String nom;
    private String prenom;
    private Adresse adresse;

    public Client(int cle, String nom, String prenom, Adresse adresse) {
        this.cle = cle;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public Client(String nom, String prenom, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public Client(int id) {
        this.cle = id;
    }

    public Client() {

    }

    public void setCle(int cle) {
        this.cle = cle;
    }

    public int getCle() {
        return cle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Client client = (Client) o;
        return getCle() == client.getCle();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCle(), getNom(), getPrenom(), adresse);
    }

    @Override
    public String toString() {
        return "\nClé: " + cle +
                "\nNom: " + nom +
                "\nPrénom: " + prenom +
                "\nAdresse: " + adresse;
    }

    public boolean equalsTout(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Client client = (Client) o;
        return Objects.equals(getNom(), client.getNom())
                && Objects.equals(getPrenom(), client.getPrenom()) && Objects.equals(adresse, client.adresse);
    }
}
