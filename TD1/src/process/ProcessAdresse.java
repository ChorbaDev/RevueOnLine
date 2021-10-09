package process;

import metier.Adresse;

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
		if (pays.trim().equalsIgnoreCase("letzebuerg")) {
			return "Luxembourg";
		} else if (pays.trim().equalsIgnoreCase("belgium"))
			return "Belgique";
		else if (pays.trim().equalsIgnoreCase("switzerland") || pays.equalsIgnoreCase("schweiz"))
			return "Suisse";
		else
			return pays.trim().substring(0, 1).toUpperCase() + pays.trim().substring(1).toLowerCase();
	}

	private String reforme2(String nomDeVoie, String[] tab, String prefix) {
		for (String prefAbv : tab) {
			String ch1 = prefAbv;
			String ch2 = prefAbv.substring(1);
			String ch3 = prefAbv.substring(0, prefAbv.length() - 1);
			String ch = prefix;
			if (nomDeVoie.contains(ch1)) {
				ch = ch1;
				prefix = " " + prefix + " ";
			} else if (nomDeVoie.contains(ch2)) {
				ch = ch2;
				prefix = " " + prefix;
			} else if (nomDeVoie.contains(ch3)) {
				ch = ch3;
				prefix += " ";
			}
			nomDeVoie = nomDeVoie.replace(ch, prefix);
		}
		return nomDeVoie;
	}

	public String normalizeNomDeVoie(String nomDeVoie) {
		nomDeVoie = nomDeVoie.trim();
		String[] boulevardAbv = { " boul ", " boul. ", " bd " };
		String[] faubourgAbv = { " faub. ", " fg " };
		String[] avenueAbv = { " av. ", " av " };
		nomDeVoie = reforme2(nomDeVoie, boulevardAbv, "boulevard");
		// nomDeVoie = reforme2(nomDeVoie, , "boulevard");
		nomDeVoie = reforme2(nomDeVoie, faubourgAbv, "faubourg");
		return nomDeVoie;
	}

	private String reforme1(String str, String[] tab) {
		for (String elt : tab) {
			String ch = elt;
			if (str.contains(elt))
				ch = '-' + elt.trim() + '-';
			else if (str.contains(elt.substring(1)))
				ch = elt.trim() + '-';
			else if (str.contains(elt.substring(0, elt.length() - 1)))
				ch = '-' + elt.trim();
			str = str.replace(elt, ch);
		}
		return str;
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

	//////////////
	private String ajouterTiret(String[] decompVille, String[] tabTiret) {
		String ch = "";
		//
		if (existeDansTab(tabTiret[0], tabTiret)) {
			ch += tabTiret[0] + '-';
			tabTiret[0] = capitalize(tabTiret[0]);
		}
		//
		for (int i = 1; i < tabTiret.length - 1; i++) {
			if (existeDansTab(tabTiret[i], tabTiret)) {
				ch += '-' + tabTiret[i] + '-';
				tabTiret[i] = capitalize(tabTiret[i]);
			}
		}
		return ch;
	}

	public String normalizeVille(String ville) {
		ville.trim();
		String[] decompVille = ville.split(" ");
		String[] tiret = { "lès", "le", "sous", "sur", "à", "aux", "Saint" };
		String[] aRemplacer = { "st" };
		String par = "Saint";
		decompVille = remplaceMots(decompVille, aRemplacer, par);
		ville = ajouterTiret(decompVille, tiret);
		return ville;
	}

	/*
	 * ville = ville.substring(0, 1).toUpperCase() + ville.substring(1); // String[]
	 * preposition = { " lès ", " le ", " sous ", " sur ", " à ", " aux " }; ville =
	 * reforme1(ville, preposition); // String[] saints = { " St " }; ville =
	 * reforme2(ville, saints, "Saint"); ville = reforme1(ville, saints); return
	 * ville;
	 */
	public String normalizeCodePostal(String codePostal) {
		codePostal = codePostal.replaceAll("[^\\d]", "");
		while (codePostal.length() < 5)
			codePostal = '0' + codePostal;// autre question
		return codePostal;
	}

	public String normalizeNumDeVoie(String numVoie) {
		numVoie = numVoie.trim();
		if (numVoie.charAt(numVoie.length() - 1) != ',')
			return numVoie + ", ";
		else
			return numVoie;
	}

}
