package process;

import metier.Adresse;

public class ProcessAdresse {

	public Adresse normalizeAdresse(Adresse adresse) {
		String paysNormalized = normalizePays(adresse.getPays());
		String villeNormalized = normalizeVille(adresse.getVille());
		String codePostalNormalized = normalizeCodePostal(adresse.getCode_postal());
		String nomDeVoieNormalized = normalizeNomDeVoie(adresse.getVoie());
		String numVoieNormalized = normalizeNumDeVoie(adresse.getNo_rue());
		Adresse adresseNormalized = new Adresse(numVoieNormalized, nomDeVoieNormalized, codePostalNormalized,
				villeNormalized, paysNormalized);
		return adresseNormalized;
	}

	public String normalizePays(String pays) {
		return null;
	}

	public String normalizeNomDeVoie(String nomDeVoie) {
		return null;
	}

	private String reforme(String str, String[] tab) {
		for (String elt : tab) {
			String ch = elt;
			if (str.contains(str))
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
		ville = reforme(ville, preposition);
		//
		String[] saints = { " St ", " Saint " };
		ville = reforme(ville, saints);
		return ville;
	}

	public String normalizeCodePostal(String codePostal) {
		codePostal = codePostal.replaceAll("[^\\d]", "");
		while (codePostal.length() < 5)
			codePostal = '0' + codePostal;
		return codePostal;
	}

	public String normalizeNumDeVoie(String numVoie) {
		String arr[] = numVoie.split(" ", 2);
		numVoie = arr[0] + ", " + arr[1];
		return numVoie;
	}

}
