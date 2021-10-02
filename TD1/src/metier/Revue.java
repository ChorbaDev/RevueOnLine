package metier;

public class Revue {
	private int id;
	private String titre;
	private String description;
	private double tarif_numero;
	private String visuel;
	private int id_p;

	public Revue(int id, String titre, String description, double tarif_numero, String visuel, int id_p) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.tarif_numero = tarif_numero;
		this.visuel = visuel;
		this.id_p = id_p;
	}

	public Revue(int id) {
		this.id = id;

	}

	public Revue(String titre, String description, double tarif_numero, String visuel, int id_p) {
		this.titre = titre;
		this.description = description;
		this.tarif_numero = tarif_numero;
		this.visuel = visuel;
		this.id_p = id_p;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Revue other = (Revue) obj;
		return id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTarif_numero() {
		return tarif_numero;
	}

	public void setTarif_numero(double tarif_numero) {
		this.tarif_numero = tarif_numero;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	public int getId_p() {
		return id_p;
	}

	public void setId_p(int id_p) {
		this.id_p = id_p;
	}

	@Override
	public String toString() {
		return "ID :" + id + "\nTitre :" + titre + "\nDescription :" + description + "\nTarif Numero :" + tarif_numero
				+ "\nVisuel :" + visuel + "\nID Periodicite :" + id_p + "\n__________________\n";
	}

}
