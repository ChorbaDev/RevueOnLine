import java.sql.SQLException;

public class Application {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Revue r=new Revue("titre","descp",1.2,"visuel",4);
		r.addRevue();
		r.updateRevue("tift", "des", 3.2, "vis",4);
	}

}
