package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import daofactory.DaoFactory;
import modele.metier.Periodicite;

public abstract class AppPeriodicite {

	public static void manipPeriodicite(Scanner sc, DaoFactory daof, int choixCRUD) throws SQLException, IOException, ClassNotFoundException {
		switch (choixCRUD) {
		case 1:
			createPeriodicite(sc, daof);
			;
			break;
		case 2:
			requestPeriodicite(sc, daof);
			break;
		case 3:
			updatePeriodicite(sc, daof);
			;
			break;
		case 4:
			deletePeriodicite(sc, daof);
			;
			break;
		default:
			break;
		}
	}

	private static void deletePeriodicite(Scanner sc, DaoFactory daof) throws SQLException, ClassNotFoundException {
		int id;
		System.out.print("ID Periodicite :");
		id = Application.verifID(sc);
		Periodicite p = new Periodicite(id);
		daof.getPeriodiciteDAO().delete(p);
	}

	private static void updatePeriodicite(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		String libelle;
		int id;
		System.out.print("ID Periodicite :");
		id = Application.verifID(sc);
		System.out.print("Libelle :");
		libelle = sc.nextLine();
		Periodicite p = new Periodicite(id, libelle);
		daof.getPeriodiciteDAO().update(p);

	}

	private static void requestPeriodicite(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Affichage par:\n" + "1-ID\n" + "2-Libelle\n" + "3-Tout\n");
		int choix = Application.verifChoix(sc, 1, 3);
		switch (choix) {
		case 1:
			reqIdPeriodicite(sc, daof);
			break;
		case 2:
			reqLibellePeriodicite(sc, daof);
			break;
		case 3:
			reqAllPeriodicite(sc, daof);
			break;
		default:
			break;
		}
	}

	private static void reqAllPeriodicite(Scanner sc, DaoFactory daof) throws SQLException, ClassNotFoundException {
		ArrayList<Periodicite> listeP = daof.getPeriodiciteDAO().findAll();
		for (Periodicite p : listeP)
			System.out.println(p.toString());

	}

	private static void reqLibellePeriodicite(Scanner sc, DaoFactory daof) throws SQLException, ClassNotFoundException {
		String lib = sc.nextLine();
		ArrayList<Periodicite> listeP = daof.getPeriodiciteDAO().getByLibelle(lib);
		for (Periodicite p : listeP)
			System.out.println(p.toString());
	}

	private static void reqIdPeriodicite(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		int id = Application.verifID(sc);
		Periodicite ab = daof.getPeriodiciteDAO().getById(id);
		System.out.println(ab.toString());

	}

	private static void createPeriodicite(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		System.out.println("ID : ");
		int id = Application.verifID(sc);
		String libelle;
		System.out.print("Libelle :");
		libelle = sc.nextLine();
		Periodicite p = new Periodicite(id, libelle);
		daof.getPeriodiciteDAO().create(p);
	}

}
