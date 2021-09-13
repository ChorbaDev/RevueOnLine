import Metier.Abonnement;
import Metier.Client;
import Metier.Periodicite;
import Metier.Revue;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        Client c = new Client("gho", "you", "5", "fekoe", "5700", "yutz", "fr");
        Periodicite p = new Periodicite("bonjour");
        Revue r = new Revue("Sa majesté le roi des mouches", " le roi de mouches", 10.95, "beau", 5);
        Abonnement a = new Abonnement("10/09/21", "11/10/21", 12, 1);
        System.out.println("que souhaitez vous faire ? : 1- ajouter, 2-modifier, 3-supprimer");
        int choixtravail = scanner.nextInt();
        System.out.println("sur quelle table souhaitez vous travailler ? : 1- Periodicité, 2-Metier.Client, 3-Metier.Revue, 4-Metier.Abonnement");
        int choixtable = scanner.nextInt();
        switch (choixtable) {
            case 1:
                switch (choixtravail) {
                    case 1:
                        p.addPeriodicite();
                        break;
                    case 2:
                        p.updatePeriodicite("aurevoir");
                        break;
                    case 3:
                        p.deletePeriodicite();
                        break;
                }
            case 2:
                switch (choixtravail) {
                    case 1:
                        c.addClient();
                        break;
                    case 2:
                        c.updateClient("gholem", "youss", "12", "the voice", "57500", "metz", "marsa");
                        break;
                    case 3:
                        c.deleteClient();
                        break;
                }
            case 3:
                switch (choixtravail) {
                    case 1:
                        r.addRevue();
                        break;
                    case 2:
                        r.updateRevue("Sa majesté le roi des mouches", " le roi de mouches", 10.95, "beau", 5);
                        break;
                    case 3:
                        r.deleteRevue();
                        break;
                }
            case 4:
                switch (choixtravail) {
                    case 1:
                        a.addAbonnement();
                        break;
                    case 2:
                        a.updateAbonnement("10/09/21", "11/10/22", 12, 1);
                        break;
                    case 3:
                        a.deleteAbonnement();
                        break;
                }

        }


    }


}