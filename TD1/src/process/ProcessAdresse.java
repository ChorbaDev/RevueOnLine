package process;

import modele.metier.Adresse;

public class ProcessAdresse {
	public ProcessAdresse() {

	}

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

	public String normalizePays(String pays) {
		pays = pays.trim().toLowerCase();
		String[] suisse = { "switzerland", "schweiz" };
		String[] lux = { "letzebuerg" };
		String[] belg = { "belgium" };
		if (existeDansTab(pays, suisse))
			return "Suisse";
		else if (existeDansTab(pays, lux))
			return "Luxembourg";
		else if (existeDansTab(pays, belg))
			return "Belgique";
		else
			return capitalize(pays);
	}

	public String normalizeNomDeVoie(String nomDeVoie) {
		nomDeVoie = nomDeVoie.trim();
		String[] decompNom = nomDeVoie.split(" ");
		String[] boulevardAbv = { "boul", "boul.", "bd" };
		String[] faubourgAbv = { "faub.", "fg" };
		String[] avenueAbv = { "av.", "av" };
		String[] placeAbv = { "pl.", "pl" };
		decompNom = remplaceMots(decompNom, boulevardAbv, "boulevard");
		decompNom = remplaceMots(decompNom, faubourgAbv, "faubourg");
		decompNom = remplaceMots(decompNom, avenueAbv, "avenue");
		decompNom = remplaceMots(decompNom, placeAbv, "place");
		String ch = "";
		for (String elt : decompNom)
			ch += elt + " ";
		return ch.substring(0, ch.length() - 1);
	}

	private String capitalize(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	private boolean existeDansTab(String elt, String[] tabTiret) {
		for (String tiret : tabTiret) {
			if (tiret.equals(elt))
				return true;
		}
		return false;
	}

	private String[] remplaceMots(String[] decompVille, String[] aRemplacer, String par) {
		for (String aRemp : aRemplacer) {
			for (int i = 0; i < decompVille.length; i++) {
				if (decompVille[i].equals(aRemp))
					decompVille[i] = par;
			}
		}
		return decompVille;
	}

	private String ajouterTiret(String[] decompVille, String[] tabTiret) {
		String ch = "";
		//
		if (existeDansTab(decompVille[0], tabTiret)) {
			ch += decompVille[0] + '-';
		}else {
			ch += decompVille[0]= capitalize(decompVille[0]);
		}
		//
		System.out.println(ch);
		for (int i = 1; i < decompVille.length - 1; i++) {
			if (existeDansTab(decompVille[i], tabTiret)) {
				ch += '-' + decompVille[i] + '-';
			} else {
				ch += decompVille[i]= capitalize(decompVille[i]);
			}

		}
		System.out.println(ch);
		int l = decompVille.length - 1;
		if (existeDansTab(decompVille[l], tabTiret)) {
			ch += '-' + decompVille[l];
		}else if(l>0) ch += decompVille[l]= capitalize(decompVille[l]);
		System.out.println(ch);
		return ch;
	}

	public String normalizeVille(String ville) {
		String ch = ville.trim();
		String[] decompVille = ch.split(" ");
		String[] tiret = { "lès", "le", "sous", "sur", "à", "aux", "Saint" };
		String[] aRemplacer = { "st" };
		String par = "Saint";
		decompVille = remplaceMots(decompVille, aRemplacer, par);
		ch = ajouterTiret(decompVille, tiret);
		return ch;
	}

	public String normalizeCodePostal(String codePostal) {
		codePostal = codePostal.replaceAll("[^\\d]", "");
		while (codePostal.length() < 5)
			codePostal = '0' + codePostal;
		return codePostal;
	}

	public String normalizeNumDeVoie(String numVoie) {
		numVoie = numVoie.trim();
		if (numVoie.charAt(numVoie.length() - 1) != ',')
			numVoie += ",";
		return numVoie+' ';
	}

}
