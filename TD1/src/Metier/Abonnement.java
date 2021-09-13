package Metier;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Abonnement {
    private int id;
    private LocalDate date_debut;
    private LocalDate date_fin;
    int id_client;
    int id_revue;
    private final DateTimeFormatter FORMATAGE=DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Abonnement(String deb, String fin, int id_c, int id_r) {
        date_debut = LocalDate.parse(deb, FORMATAGE);
        date_fin = LocalDate.parse(fin, FORMATAGE);
        id_client = id_c;
        id_revue = id_r;
    }
    public Abonnement(int id, String deb, String fin, int id_c, int id_r) {
    	date_debut = LocalDate.parse(deb, FORMATAGE);
        date_fin = LocalDate.parse(fin, FORMATAGE);
        id_client = id_c;
        id_revue = id_r;
        this.id=id;
    }
    public Abonnement(int id, LocalDate deb, LocalDate fin, int id_c, int id_r) {
    	date_debut = deb;
        date_fin = fin;
        id_client = id_c;
        id_revue = id_r;
        this.id=id;
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
	


}
