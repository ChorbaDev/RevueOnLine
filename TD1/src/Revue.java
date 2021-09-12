import java.sql.*;
public class Revue {
	private final String titre;
	private final String description;
	private final double tarif_numero;
	private final String visuel;
	private final int id_p;
	private int id=0;
	
	private final Connexion maConnexion;
	private final Connection connect;
	
	public Revue(String titre, String description, double tarif_numero, String visuel,int id_p) {
		this.titre = titre;
		this.description = description;
		this.tarif_numero = tarif_numero;
		this.visuel = visuel;
		this.id_p=id_p;
		
		maConnexion= new Connexion();
	    connect=maConnexion.creeConnexion();
	}
	public void addRevue() throws SQLException {
		String sql="insert into Revue(titre,description,tarif_numero,visuel,id_periodicite) values (?,?,?,?,?)";
		PreparedStatement requete=connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		requete.setString(1,this.titre );
		requete.setString(2,this.description );
		requete.setDouble(3, this.tarif_numero);
		requete.setString(4,this.visuel );
		requete.setInt(5,this.id_p );
		int nbLignes=requete.executeUpdate();
		ResultSet res=requete.getGeneratedKeys();
		if(res.next()) {
			id=res.getInt(1);
		}
	}
	public void deleteRevue() throws SQLException {
		if(id!=0) {
			String sql="delete from Revue where id_revue=?";
			PreparedStatement requete=connect.prepareStatement(sql);
			requete.setInt(1,id);
			int nbLignes=requete.executeUpdate();
		}else {
			System.out.println("Cette Revue n'exite pas dans la base");
		}
		
	}
	public void updateRevue(String titre, String description, double tarif_numero, String visuel,int id_p) throws SQLException {
		if(id!=0) {
			String sql="update Revue set titre=? , description=? , tarif_numero=? , visuel=? , id_periodicite=? where id_revue=?";
			PreparedStatement requete=connect.prepareStatement(sql);
			requete.setString(1,titre );
			requete.setString(2,description );
			requete.setDouble(3, tarif_numero);
			requete.setString(4,visuel );
			requete.setInt(5,id_p );
			requete.setInt(6,this.id );
			int nbLignes=requete.executeUpdate();
		}
		else {
			System.out.println("Cette Revue n'exite pas dans la base");
		}
	}
}
