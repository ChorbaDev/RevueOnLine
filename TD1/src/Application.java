import java.sql.SQLException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		Client c=new Client("gho","you","5","fekoe","5700","yutz","fr");
		System.out.println("que souhaitez vous faire ? : 1- ajouter, 2-modifier, 3-supprimer");
		int choixtravail=scanner.nextInt();
		switch (choixtravail){
			case 1:
				c.ajoutClient();
				break;
			case 2:
				c.updateClient("gholem","youss","12","the voice","57500","metz","marsa");
				break;
			case 3:
				c.deleteClient();
				break;
		}





	}


}
