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

public class Revue {
    private int id;
    private String titre;
    private String description;
    private double tarif_numero;
    private Image visuel;
    private int id_p;

    public Revue(int id, String titre, String description, double tarif_numero, Image visuel, int id_p) {
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

    public Revue() {

    }

    public Revue(String titre, String description, double tarif_numero, Image visuel, int id_p) {
        this.titre = titre;
        this.description = description;
        this.tarif_numero = tarif_numero;
        this.visuel = visuel;
        this.id_p = id_p;
    }

    public Revue(int id, String titre, String description, double tarif_numero, int id_p) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.tarif_numero = tarif_numero;
        this.id_p = id_p;
    }

    public Revue(String test, String descp, double v, int i) {
        this.titre = titre;
        this.description = description;
        this.tarif_numero = tarif_numero;
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
        if (titre.isEmpty())
            throw new RuntimeException("Le titre doit être renseigné");
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.isEmpty())
            throw new RuntimeException("La description doit être renseigné");
        this.description = description;
    }

    public double getTarif_numero() {
        return tarif_numero;
    }

    public void setTarif_numero(double tarif_numero) {
        if (tarif_numero <= 0)
            throw new ArithmeticException("Le tarif doit être strictement positif");
        this.tarif_numero = tarif_numero;
    }

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

}
