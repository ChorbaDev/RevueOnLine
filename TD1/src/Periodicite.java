import java.sql.*;
public class Periodicite {
	private final String libelle;
	private final Connexion maConnexion;
	private final Connection Connect;
	
	public Periodicite(String ch) {
		this.libelle=ch;
		 maConnexion= new Connexion();
	     Connect=maConnexion.creeConnexion();
	}
	
	public void addPeriodicite() throws SQLException {
		String sql="insert into Periodicite(libelle) values (?)";
		PreparedStatement requete=Connect.prepareStatement(sql);
		requete.setString(1,this.libelle);
		int Nblignes=requete.executeUpdate();
	}
	public void deletePeriodicite() throws SQLException {
		String sql="delete from Periodicite where libelle=?";
		PreparedStatement requete=Connect.prepareStatement(sql);
		requete.setString(1,this.libelle);
		int Nblignes=requete.executeUpdate();
	}
	public void updatePeriodicite(String ch) throws SQLException {
		String sql="update Periodicite set libelle=? where libelle=?";
		PreparedStatement requete=Connect.prepareStatement(sql);
		requete.setString(1,ch);
		requete.setString(2,this.libelle);
		int Nblignes=requete.executeUpdate();
	}
	
}
