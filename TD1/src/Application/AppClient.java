package Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DAOFactory.DAOFactory;
import DAOFactory.ListeMemoireDAOFactory;
import Metier.Client;

public abstract class AppClient {

	public static void manipClient(Scanner sc, DAOFactory daof) throws SQLException {
		int choixOperation;
		do {
			choixOperation = Integer.parseInt(sc.nextLine());
		} while (choixOperation < 1 || choixOperation > 4);
		switch (choixOperation) {
		case 1:
			createClient(sc, daof);
			;
			break;
		case 2:
			requestClient(sc, daof);
			break;
		case 3:
			updateClient(sc, daof);
			;
			break;
		case 4:
			deleteClient(sc, daof);
			;
			break;
		default:
			break;
		}
	}

	private static void deleteClient(Scanner sc, DAOFactory daof) throws SQLException {
		int id;
		System.out.print("ID Client :");
		id = Integer.parseInt(sc.nextLine());
		Client cl = new Client(id);
		daof.getClientDAO().delete(cl);
	}

	private static void updateClient(Scanner sc, DAOFactory daof) throws SQLException {
		int id;
		String nom, prenom, no_rue, voie, code_postal, ville, pays;
		System.out.print("ID Client :");
		id = Integer.parseInt(sc.nextLine());
		System.out.print("Nom :");
		nom = sc.nextLine();
		System.out.print("Prenom :");
		prenom = sc.nextLine();
		System.out.print("No rue:");
		no_rue = sc.nextLine();
		System.out.print("Voie :");
		voie = sc.nextLine();
		System.out.print("Code postal :");
		code_postal = sc.nextLine();
		System.out.print("Ville :");
		ville = sc.nextLine();
		System.out.print("Pays :");
		pays = sc.nextLine();
		Client cl = new Client(id, nom, prenom, no_rue, voie, code_postal, ville, pays);
		daof.getClientDAO().update(cl);

	}

	private static void requestClient(Scanner sc, DAOFactory daof) throws SQLException {
		System.out.println("Affichage par:\n1-ID\n2-Nom & Prenom\n3-Tout\n");
		int choix;
		do {
			choix = Integer.parseInt(sc.nextLine());
		} while (choix < 1 || choix > 4);
		switch (choix) {
		case 1:
			reqIdClient(sc, daof);
			break;
		case 2:
			reqNPClient(sc, daof);
			break;
		case 3:
			reqAllClient(sc, daof);
			break;
		default:
			break;
		}

	}

	private static void reqAllClient(Scanner sc, DAOFactory daof) throws SQLException {
		ArrayList<Client> listeC = daof.getClientDAO().findAll();
		for (Client c : listeC)
			System.out.println(c.toString());

	}

	private static void reqNPClient(Scanner sc, DAOFactory daof) throws SQLException {
		String nom, prenom;
		System.out.print("Nom :");
		nom = sc.nextLine();
		System.out.print("Prenom :");
		prenom = sc.nextLine();
		ArrayList<Client> listeCL = daof.getClientDAO().getByNomPrenom(nom, prenom);
		for (Client cl : listeCL)
			System.out.println(cl.toString());

	}

	private static void reqIdClient(Scanner sc, DAOFactory daof) throws SQLException {
		int id = Integer.parseInt(sc.nextLine());
		Client cl = daof.getClientDAO().getById(id);
		System.out.println(cl.toString());
	}

	private static void createClient(Scanner sc, DAOFactory daof) throws SQLException {
		String nom, prenom, no_rue, voie, code_postal, ville, pays;
		boolean isLM = daof instanceof ListeMemoireDAOFactory;
		Client cl;
		int id = 0;
		if (isLM) {
			System.out.print("ID Client :");
			id = Integer.parseInt(sc.nextLine());
		}
		System.out.print("Nom :");
		nom = sc.nextLine();
		System.out.print("Prenom :");
		prenom = sc.nextLine();
		System.out.print("No rue:");
		no_rue = sc.nextLine();
		System.out.print("Voie :");
		voie = sc.nextLine();
		System.out.print("Code postal :");
		code_postal = sc.nextLine();
		System.out.print("Ville :");
		ville = sc.nextLine();
		System.out.print("Pays :");
		pays = sc.nextLine();
		cl = new Client(id, nom, prenom, no_rue, voie, code_postal, ville, pays);
		daof.getClientDAO().create(cl);

	}

}
