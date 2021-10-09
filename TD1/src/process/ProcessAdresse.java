package process;

import metier.Adresse;

public class ProcessAdresse {

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

	private String reformeNomVoie(String nomDeVoie, String[] tab, String prefix) {
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
		nomDeVoie = reformeNomVoie(nomDeVoie, boulevardAbv, "boulevard");
		String[] faubourgAbv = { " faub. ", " fg " };
		nomDeVoie = reformeNomVoie(nomDeVoie, faubourgAbv, "faubourg");
		return nomDeVoie;
	}

	private String reformeVille(String str, String[] tab) {
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

	public String normalizeVille(String ville) {
		ville = ville.substring(0, 1).toUpperCase() + ville.substring(1);
		//
		String[] preposition = { " l�s ", " le ", " sous ", " � ", " aux " };
		ville = reformeVille(ville, preposition);
		//
		String[] saints = { " St ", " Saint " };
		ville = reformeVille(ville, saints);
		return ville;
	}

	public String normalizeCodePostal(String codePostal) {
		codePostal = codePostal.replaceAll("[^\\d]", "");
		while (codePostal.length() < 5)
			codePostal = '0' + codePostal;// autre question
		return codePostal;
	}

	public String normalizeNumDeVoie(String numVoie) {
		String arr[] = numVoie.split(" ", 2);
		numVoie = arr[0] + ", " + arr[1];
		return numVoie;
	}

}
