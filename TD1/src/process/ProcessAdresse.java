package process;

import metier.Adresse;

public class ProcessAdresse {

    public Adresse normalizeAdresse(Adresse adresse){
        String paysNormalized=normalizePays(adresse.getPays());
        String villeNormalized=adresse.getVille();
        String codePostalNormalized=adresse.getCode_postal();
        String nomDeVoieNormalized=adresse.getVoie();
        String numVoieNormalized=adresse.getNo_rue();
        Adresse adresseNormalized= new Adresse(numVoieNormalized,nomDeVoieNormalized,codePostalNormalized,villeNormalized,paysNormalized);

        return adresseNormalized;
    }
    public String normalizePays(String pays){
        return null;
    }
    public String normalizeVille(String Ville){
        return null;
    }
    public String normalizeCodePostal(String codePostal){
        return null;
    }
    public String normalizeNomDeVoie(String nomDeVoie){
        return null;
    }
   public String normalizeNumDeVoie(String numVoie){
        return null;
   }

}
