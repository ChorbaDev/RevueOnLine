package Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DAOFactory.DAOFactory;
import DAOFactory.ListeMemoireDAOFactory;
import Metier.Revue;

public abstract class AppRevue {

	public static void manipRevue(Scanner sc, DAOFactory daof) throws SQLException {
		int choixOperation;
		do {
			choixOperation = Integer.parseInt(sc.nextLine());
		} while (choixOperation < 1 || choixOperation > 4);
		switch (choixOperation) {
		case 1:
			createRevue(sc, daof);
			;
			break;
		case 2:
			requestRevue(sc, daof);
			break;
		case 3:
			updateRevue(sc, daof);
			;
			break;
		case 4:
			deleteRevue(sc, daof);
			;
			break;
		default:
			break;
		}
	}

	private static void deleteRevue(Scanner sc, DAOFactory daof) throws SQLException {
		int id;
		System.out.print("ID Revue:");
		id = Integer.parseInt(sc.nextLine());
		Revue revue = new Revue(id);
		daof.getRevueDAO().delete(revue);
	}

	private static void updateRevue(Scanner sc, DAOFactory daof) throws SQLException {
		String titre, description, visuel;
		double tarif;
		int id_p, id;
		System.out.print("ID Revue:");
		id = Integer.parseInt(sc.nextLine());
		System.out.print("Titre :");
		titre = sc.nextLine();
		System.out.print("Description :");
		description = sc.nextLine();
		System.out.print("Visuel :");
		visuel = sc.nextLine();
		System.out.print("Tarif :");
		tarif = Double.parseDouble(sc.nextLine());
		System.out.print("Periodicite (ID) :");
		id_p = Integer.parseInt(sc.nextLine());
		Revue revue = new Revue(id, titre, description, tarif, visuel, id_p);
		daof.getRevueDAO().update(revue);
	}

	private static void createRevue(Scanner sc, DAOFactory daof) throws SQLException {
		String titre, description, visuel;
		double tarif;
		int id_p, id = 0;
		boolean isLM = daof instanceof ListeMemoireDAOFactory;
		if (isLM) {
			System.out.print("ID Revue:");
			id = Integer.parseInt(sc.nextLine());
		}
		System.out.print("Titre :");
		titre = sc.nextLine();
		System.out.print("Description :");
		description = sc.nextLine();
		System.out.print("Visuel :");
		visuel = sc.nextLine();
		System.out.print("Tarif :");
		tarif = Double.parseDouble(sc.nextLine());
		System.out.print("Periodicite (ID) :");
		id_p = Integer.parseInt(sc.nextLine());
		Revue revue = new Revue(id, titre, description, tarif, visuel, id_p);
		daof.getRevueDAO().create(revue);
	}

	private static void requestRevue(Scanner sc, DAOFactory daof) throws SQLException {
		System.out.println("Affichage par:\n1-ID\n2-Titre\n3-Tout\n");
		int choix;
		do {
			choix = Integer.parseInt(sc.nextLine());
		} while (choix < 1 || choix > 4);
		switch (choix) {
		case 1:
			reqIdRevue(sc, daof);
			break;
		case 2:
			reqTitreRevue(sc, daof);
			break;
		case 3:
			reqAllRevue(sc, daof);
			break;
		default:
			break;
		}

	}

	private static void reqAllRevue(Scanner sc, DAOFactory daof) throws SQLException {
		ArrayList<Revue> listeR = daof.getRevueDAO().findAll();
		for (Revue r : listeR)
			System.out.println(r.toString());

	}

	private static void reqTitreRevue(Scanner sc, DAOFactory daof) throws SQLException {
		String titre = sc.nextLine();
		ArrayList<Revue> listR = daof.getRevueDAO().getByTitre(titre);
		for (Revue r : listR) {
			System.out.println(r.toString());
		}
	}

	private static void reqIdRevue(Scanner sc, DAOFactory daof) throws SQLException {
		int id = Integer.parseInt(sc.nextLine());
		Revue r = daof.getRevueDAO().getById(id);
		System.out.println(r.toString());
	}

}
