package modele.metier;

public class Periodicite {
	private int cle;
	private String libelle;

	public Periodicite(String ch) {
		this.libelle = ch;
	}

	public Periodicite() {
	}

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
		return  getLibelle();
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		if(libelle.isEmpty())
			throw new RuntimeException("Le libellé doit être renseigné");
		this.libelle = libelle;
	}

	public int getCle() {
		return cle;
	}

	public void setCle(int cle) {
		this.cle = cle;
	}

}
