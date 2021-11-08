package modele.metier;

import dao.Persistance;
import daofactory.DaoFactory;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Objects;

public class Revue {
    private int id;
    private String titre;
    private String description;
    private double tarif_numero;
    private Image visuel;
    private int id_p;

    /**
     * Constructeur revue avec les paramètres suivants :
     * @param id id revue
     * @param titre titre de la revue
     * @param description description de la revue
     * @param tarif_numero tarif de la revue
     * @param visuel image de la revue
     * @param id_p id de la periodicite
     */
    public Revue(int id, String titre, String description, double tarif_numero, Image visuel, int id_p) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.tarif_numero = tarif_numero;
        this.visuel = visuel;
        this.id_p = id_p;
    }

    /**
     * Constructeur revue avec les paramètres suivants :
     * @param id id revue
     */
    public Revue(int id) {
        this.id = id;

    }

    /**
     * Constructeur revue vide
     */
    public Revue() {

    }

    /**
     * Constructeur revue avec les paramètres suivants :
     * @param titre titre de la revue
     * @param description description de la revue
     * @param tarif_numero tarif de la revue
     * @param visuel image de la revue
     * @param id_p id de la periodicite
     */
    public Revue(String titre, String description, double tarif_numero, Image visuel, int id_p) {
        this.titre = titre;
        this.description = description;
        this.tarif_numero = tarif_numero;
        this.visuel = visuel;
        this.id_p = id_p;
    }

    /**
     * Constructeur revue avec les paramètres suivants :
     * @param id id revue
     * @param titre titre de la revue
     * @param description description de la revue
     * @param tarif_numero tarif de la revue
     * @param id_p id de la periodicite
     */
    public Revue(int id, String titre, String description, double tarif_numero, int id_p) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.tarif_numero = tarif_numero;
        this.id_p = id_p;
    }


    /**
     * Constructeur revue avec les paramètres suivants :
     * @param titre titre de la revue
     * @param descp description de la revue
     * @param tarif tarif de la revue
     * @param id_p id de la periodicite
     */
	public Revue(String titre, String descp, double tarif, int id_p) {
		this.titre = titre;
		this.description = descp;
		this.tarif_numero = tarif;
		this.id_p = id_p;
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

    /**
     * @return renvoie un inputstream de l'image
     * @throws IOException
     */
    public InputStream getVisuel() throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(visuel, null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpeg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        return is;
    }

    public Image getVisuelImg() {
        return visuel;
    }

    public void setVisuel(Image visuel) {
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
                + "\nID Periodicite :" + id_p + "\n";
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Revue revue = (Revue) o;
		return id == revue.id;
	}

	public boolean equalsTout(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Revue revue = (Revue) o;
		return Double.compare(revue.tarif_numero, tarif_numero) == 0 && id_p == revue.id_p && titre.equals(revue.titre) && description.equals(revue.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(titre, description, tarif_numero, visuel, id_p);
	}
}
