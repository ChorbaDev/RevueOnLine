package Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.Persistance;
import DAOFactory.DAOFactory;
import Metier.Client;

public abstract class AppClient {

	public static void manipClient(Scanner sc) throws SQLException {
		afficheCRUD();
		int choixOperation;
		do {
			choixOperation = Integer.parseInt(sc.nextLine());
		} while (choixOperation < 1 || choixOperation > 4);
		switch (choixOperation) {
		case 1:
			createClient(sc);
			;
			break;
		case 2:
			requestClient(sc);
			break;
		case 3:
			updateClient(sc);
			;
			break;
		case 4:
			deleteClient(sc);
			;
			break;
		default:
			break;
		}
	}

	private static void deleteClient(Scanner sc) throws SQLException {
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
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().delete(cl);
	}

	private static void afficheCRUD() {
		System.out
				.println("Choisissez votre operation:\n" + "1-Create\n" + "2-Request\n" + "3-Update\n" + "4-Delete\n");

	}

	private static void updateClient(Scanner sc) throws SQLException {
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
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().update(cl);

	}

	private static void requestClient(Scanner sc) throws SQLException {
		System.out.println("Affichage par:\n" + "1-ID\n" + "2-Nom & Prenom\n");
		int choix;
		do {
			choix = Integer.parseInt(sc.nextLine());
		} while (choix < 1 || choix > 4);
		switch (choix) {
		case 1:
			reqIdClient(sc);
			break;
		case 2:
			reqNPClient(sc);
			break;
		default:
			break;
		}

	}

	private static void reqNPClient(Scanner sc) throws SQLException {
		String nom, prenom;
		System.out.print("Nom :");
		nom = sc.nextLine();
		System.out.print("Prenom :");
		prenom = sc.nextLine();
		ArrayList<Client> listeCL = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO()
				.getByNomPrenom(nom, prenom);
		for (Client cl : listeCL)
			System.out.println(cl.toString());

	}

	private static void reqIdClient(Scanner sc) throws SQLException {
		int id = Integer.parseInt(sc.nextLine());
		Client cl = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().getById(id);
		System.out.println(cl.toString());
	}

	private static void createClient(Scanner sc) throws SQLException {
		String nom, prenom, no_rue, voie, code_postal, ville, pays;
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
		Client cl = new Client(nom, prenom, no_rue, voie, code_postal, ville, pays);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().create(cl);

	}

}
