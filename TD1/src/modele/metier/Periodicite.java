package modele.metier;

import java.util.Objects;

/**
 * Classe metier periodicité qui permet d'avoir une periodicté pour un abonnement qui sera par exemple mensuel annuel ou autre
 */
public class Periodicite {
    private int cle;
    private String libelle;

    public Periodicite(String ch) {
        this.libelle = ch;
    }

    public Periodicite() {
    }

    /**
     * @param cle id unique de la periodicté
     * @param libelle Libellé de la periodicité
     */
    public Periodicite(int cle, String libelle) {
        this.cle = cle;
        this.libelle = libelle;
    }

    public Periodicite(int cle) {
        this.cle = cle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cle;
        return result;
    }

    /**
     * @param obj passage d'un objet periodicite en parametre
     * @return vrai si l'id de la clé est égal à celui de la clé de l'objet passé en parametre
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Periodicite other = (Periodicite) obj;
        if (cle != other.cle)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getLibelle();
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

    /**
     * @param o passage d'un objet periodicite en parametre
     * @return vrai si tous les attributs de l'objet sont égaux à ceux de l'objet passé en parametre
     * @see Abonnement#equalsTout(Object)
     * @see Client#equalsTout(Object)
     * @see Revue#equalsTout(Object)
     */
    public boolean equalsTout(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Periodicite periodicite = (Periodicite) o;
        return Objects.equals(getLibelle(), periodicite.getLibelle());
    }

}
