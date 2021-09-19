package Application;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.Persistance;
import DAOFactory.DAOFactory;

public class Application {

	public static void main(String[] args) throws SQLException {
		String reponse;
		System.out.println("---------Bienvenue dans notre application---------");
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("Choissisez entre:\n1-My SQL\n2-Liste Memoire");
			int idxPer = Integer.parseInt(sc.nextLine());
			Persistance persistance;
			if (idxPer == 1)
				persistance = Persistance.MYSQL;
			else
				persistance = Persistance.ListeMemoire;
			System.out.println("Quelle table vous voulez manipuler? \n" + "1-Abonnement\n" + "2-Client\n"
					+ "3-Periodicite\n" + "4-Revue");
			DAOFactory daof = DAOFactory.getDAOFactory(persistance);
			int choixTable;
			do {
				choixTable = Integer.parseInt(sc.nextLine());
			} while (choixTable < 1 || choixTable > 4);

			switch (choixTable) {
			case 1:
				AppAbonnement.manipAbonnement(sc, daof);
				break;
			case 2:
				AppClient.manipClient(sc, daof);
				break;
			case 3:
				AppPeriodicite.manipPeriodicite(sc, daof);
				break;
			case 4:
				AppRevue.manipRevue(sc, daof);
				break;
			default:
				break;
			}
			System.out.println("Voulez-vous continuer? O pour oui");
			reponse = sc.nextLine();
		} while (reponse.toUpperCase().equals("O"));
		System.out.println("Au revoir!");
	}

}