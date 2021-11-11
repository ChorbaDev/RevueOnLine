package modele.metier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Classe metier Abonnement
 */
public class Abonnement {

    private int id;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private int id_client;
    private int id_revue;
    private final DateTimeFormatter FORMATAGE = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    /**
     * Constructeur d'abonnement prenant les paramètres suivants :
     * @param deb date de début d'abonnement en String
     * @param fin date de fin d'abonnement en String
     * @param id_c identifiant du client
     * @param id_r identifiant de la revue
     */
    public Abonnement(String deb, String fin, int id_c, int id_r) {
        date_debut = LocalDate.parse(deb, FORMATAGE);
        date_fin = LocalDate.parse(fin, FORMATAGE);
        id_client = id_c;
        id_revue = id_r;
    }

    /**
     * Constructeur d'abonnement prenant les paramètres suivants :
     * @param id identifiant de l'abonnement
     * @param deb date de début d'abonnement en String
     * @param fin date de fin d'abonnement en String
     * @param id_c identifiant du client
     * @param id_r identifiant de la revue
     */
    public Abonnement(int id, String deb, String fin, int id_c, int id_r) {
        date_debut = LocalDate.parse(deb, FORMATAGE);
        date_fin = LocalDate.parse(fin, FORMATAGE);
        id_client = id_c;
        id_revue = id_r;
        this.id = id;
    }

    /**
     * Constructeur d'abonnement prenant les paramètres suivants :
     * @param id identifiant de l'abonnement
     * @param deb date de début d'abonnement en LocalDate
     * @param fin date de fin d'abonnement en LocalDate
     * @param id_c identifiant du client
     * @param id_r identifiant de la revue
     */
    public Abonnement(int id, LocalDate deb, LocalDate fin, int id_c, int id_r) {
        date_debut = deb;
        date_fin = fin;
        id_client = id_c;
        id_revue = id_r;
        this.id = id;
    }

    /**
     * Constructeur d'abonnement prenant les paramètres suivants :
     * @param id identifiant de l'abonnement
     */
    public Abonnement(int id) {
        this.id = id;
    }

    /**
     * Constructeur d'abonnement sans paramètres
     */
    public Abonnement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_revue() {
        return id_revue;
    }

    public void setId_revue(int id_revue) {
        this.id_revue = id_revue;
    }

    /**
     * @return ID:[id] <br>
     * Date debut : [date_debut] <br>
     * Date fin : [date_fin] <br>
     * ID (Client) : [id_client] <br>
     * ID (Revue) : [id_revue] <br>
     * -----------------------
     */
    @Override
    public String toString() {
        return "ID :" + id + "\nDate debut :" + date_debut + "\nDate fin :" + date_fin + "\nID (Client) :" + id_client
                + "\nID (Revue) :" + id_revue + "\n__________________\n";
    }


    /**
     * @param obj objet abonnement
     * @return vrai si l'id en paramètre égale l'id donné
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Abonnement other = (Abonnement) obj;
        return id == other.id;
    }


    /**
     * @param o objet abonnement
     * @return vrai si tous les attributs de l'objet passé en paramettre sont egaux aux attributs de l'obj donné
     * @see Revue#equalsTout(Object)
     * @see Periodicite#equalsTout(Object)
     * @see Abonnement#equalsTout(Object)
     */
	public boolean equalsTout(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Abonnement that = (Abonnement) o;
		return id_client == that.id_client && id_revue == that.id_revue && Objects.equals(date_debut, that.date_debut) && Objects.equals(date_fin, that.date_fin);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date_debut, date_fin, id_client, id_revue);
	}

}
