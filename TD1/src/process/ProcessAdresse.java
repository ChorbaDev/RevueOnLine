package process;

import modele.metier.Adresse;

public class ProcessAdresse {
    public ProcessAdresse() {

    }

    /**
     * @param adresse Passe un objet adresse en param afin d'y applique tout les changements suivants:
     * @see #normalizePays(String)
     * @see #normalizeNomDeVoie(String)
     * @see #normalizeVille(String)
     * @see #normalizeNumDeVoie(String)
     * @see #normalizeCodePostal(String)
     */
    public void normalizeAdresse(Adresse adresse) {
        String paysNormalized = normalizePays(adresse.getPays());
        String villeNormalized = normalizeVille(adresse.getVille());
        String codePostalNormalized = normalizeCodePostal(adresse.getCode_postal());
        String nomDeVoieNormalized = normalizeNomDeVoie(adresse.getVoie());
        String numVoieNormalized = normalizeNumDeVoie(adresse.getNo_rue());
        adresse.setPays(paysNormalized);
        adresse.setVille(villeNormalized);
        adresse.setCode_postal(codePostalNormalized);
        adresse.setVoie(nomDeVoieNormalized);
        adresse.setNo_rue(numVoieNormalized);
    }

    /**
     * @param pays passe un pays et le francise
     * @return retourn un pays avec la première lettre en maj
     * @see #capitalize(String)
     */
    public String normalizePays(String pays) {
        pays = pays.trim().toLowerCase();
        String[] suisse = {"switzerland", "schweiz"};
        String[] lux = {"letzebuerg"};
        String[] belg = {"belgium"};
        if (existeDansTab(pays, suisse))
            return "Suisse";
        else if (existeDansTab(pays, lux))
            return "Luxembourg";
        else if (existeDansTab(pays, belg))
            return "Belgique";
        else
            return capitalize(pays);
    }

    /**
     * @param nomDeVoie passe un nom de voie en parametre et remplace toutes les abréviaitons
     * @return un nom de voie en ayant appliqué toutes les règles demandée
     */
    public String normalizeNomDeVoie(String nomDeVoie) {
        nomDeVoie = nomDeVoie.trim();
        String[] decompNom = nomDeVoie.split(" ");
        String[] boulevardAbv = {"boul", "boul.", "bd"};
        String[] faubourgAbv = {"faub.", "fg"};
        String[] avenueAbv = {"av.", "av"};
        String[] placeAbv = {"pl.", "pl"};
        decompNom = remplaceMots(decompNom, boulevardAbv, "boulevard");
        decompNom = remplaceMots(decompNom, faubourgAbv, "faubourg");
        decompNom = remplaceMots(decompNom, avenueAbv, "avenue");
        decompNom = remplaceMots(decompNom, placeAbv, "place");
        String ch = "";
        for (String elt : decompNom)
            ch += elt + " ";
        return ch.substring(0, ch.length() - 1);
    }

    /**
     * @param string chaine passe en param
     * @return cette même chaine avec la première lettre en majusucule et le reste en minuscule
     */
    private String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * @param elt mot a tester
     * @param tabTiret liste des mots à entourer
     * @return vrai si l'élement passé est bien celui autour duquel nous souhaitons mettre des tirets
     */
    private boolean existeDansTab(String elt, String[] tabTiret) {
        for (String tiret : tabTiret) {
            if (tiret.equals(elt))
                return true;
        }
        return false;
    }

    /**
     * @param decompVille Nom de la ville sous forme de tableau
     * @param aRemplacer mot a remplacer
     * @param par remplacé par
     * @return retourne le la ville avec les mots remplacés
     */
    private String[] remplaceMots(String[] decompVille, String[] aRemplacer, String par) {
        for (String aRemp : aRemplacer) {
            for (int i = 0; i < decompVille.length; i++) {
                if (decompVille[i].equals(aRemp))
                    decompVille[i] = par;
            }
        }
        return decompVille;
    }

    /**
     * @param decompVille Nom de la ville sous forme de tableau
     * @param tabTiret table des mots autours desquels il doit y avoir un tiret
     * @return La ville avec tous les tirets à la bonne place
     */
    private String ajouterTiret(String[] decompVille, String[] tabTiret) {
        String ch = "";
        //
        if (existeDansTab(decompVille[0], tabTiret)) {
            ch += decompVille[0] + '-';
        } else {
            ch += decompVille[0] = capitalize(decompVille[0]);
        }
        //
        for (int i = 1; i < decompVille.length - 1; i++) {
            if (existeDansTab(decompVille[i], tabTiret)) {
                ch += '-' + decompVille[i] + '-';
            } else {
                ch += decompVille[i] = capitalize(decompVille[i]);
            }

		}
		int l = decompVille.length - 1;
		if (existeDansTab(decompVille[l], tabTiret)) {
			ch += '-' + decompVille[l];
		}else if(l>0) ch += decompVille[l]= capitalize(decompVille[l]);

		return ch;
	}

    /**
     * @param ville ville à normaliser selon les règles de l'énoncé
     * @return ville normalisée
     * @see #normalizeAdresse(Adresse)
     */
    public String normalizeVille(String ville) {
        String ch = ville.trim();
        String[] decompVille = ch.split(" ");
        String[] tiret = {"lès", "le", "sous", "sur", "à", "aux", "Saint"};
        String[] aRemplacer = {"st"};
        String par = "Saint";
        decompVille = remplaceMots(decompVille, aRemplacer, par);
        ch = ajouterTiret(decompVille, tiret);
        return ch;
    }

    /**
     * @param codePostal code postal à normaliser selon les règles de l'énoncé
     * @return code postal normalisé
     * @see #normalizeAdresse(Adresse)
     */
    public String normalizeCodePostal(String codePostal) {
        codePostal = codePostal.replaceAll("[^\\d]", "");
        while (codePostal.length() < 5)
            codePostal = '0' + codePostal;
        return codePostal;
    }

    /**
     * @param numVoie numéro de voie à normaliser selon les règles de l'énoncé
     * @return numéro de voie normalisé
     * @see #normalizeAdresse(Adresse)
     */
    public String normalizeNumDeVoie(String numVoie) {
        numVoie = numVoie.trim();
        if (numVoie.charAt(numVoie.length() - 1) != ',')
            numVoie += ",";
        return numVoie + ' ';
    }

}
