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
		if(pays.equalsIgnoreCase("letzebuerg")){
			return "Luxembourg";
		}else if(pays.equalsIgnoreCase("belgium"))
			return "Belgique";
		else if (pays.equalsIgnoreCase("switzerland")||pays.equalsIgnoreCase("schweiz"))
			return "Suisse";
		else return pays.substring(0,1).toUpperCase()+pays.substring(1).toLowerCase().trim();
	}

	public String normalizeNomDeVoie(String nomDeVoie) {
		nomDeVoie=nomDeVoie.trim();
		String[] boulevardAbv={"boul","boul.","bd"};
		String[] faubourgAbv={"faub.","fb"};

		if (nomDeVoie.contains("av"))
			return nomDeVoie.replace("av","avenue");
		else if (nomDeVoie.contains("pl"))
			return nomDeVoie.replace("pl","place");
		for(String elt: boulevardAbv){
			if (nomDeVoie.contains(elt))
				return nomDeVoie.replace(elt,"boulevard");
		}
		for(String elt:faubourgAbv){
			if (nomDeVoie.contains(elt))
				return nomDeVoie.replace(elt,"faubourg");
		}


		return nomDeVoie;
	}

	private String reforme(String str, String[] tab) {
		for (String elt : tab) {
			String ch = elt;
			if (str.contains(str))//j'ai une question
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
			codePostal = '0' + codePostal;//autre question
		return codePostal;
	}

	public String normalizeNumDeVoie(String numVoie) {
		String arr[] = numVoie.split(" ", 2);
		numVoie = arr[0] + ", " + arr[1];
		return numVoie;
	}

}
