package modele.metier;

import java.util.Objects;

public class Adresse {
    private String no_rue;
    private String voie;
    private String code_postal;
    private String ville;
    private String pays;

    public Adresse(String no_rue, String voie, String code_postal, String ville, String pays) {
        this.no_rue = no_rue;
        this.voie = voie;
        this.code_postal = code_postal;
        this.ville = ville;
        this.pays = pays;
    }

    public String getNo_rue() {
        return no_rue;
    }

    public void setNo_rue(String no_rue) {
        this.no_rue = no_rue;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return Objects.equals(getNo_rue(), adresse.getNo_rue()) && Objects.equals(getVoie(), adresse.getVoie()) && Objects.equals(getCode_postal(), adresse.getCode_postal()) && Objects.equals(getVille(), adresse.getVille()) && Objects.equals(getPays(), adresse.getPays());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNo_rue(), getVoie(), getCode_postal(), getVille(), getPays());
    }

}
