package MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.ClientDAO;
import Metier.Client;
import Metier.Connexion;

public class MySQLClientDAO implements ClientDAO {
	private static MySQLClientDAO instance;

	public MySQLClientDAO() {
	}

	public static MySQLClientDAO getInstance() {
		if (instance == null)
			instance = new MySQLClientDAO();
		return instance;
	}

	@Override
	public ArrayList<Client> getByNomPrenom(String nom, String prenom) throws SQLException {
		ArrayList<Client> lClient = new ArrayList<>();
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		PreparedStatement req = connect.prepareStatement("select * from Client where nom=? and prenom=?");
		req.setString(1, nom);
		req.setString(2, prenom);
		ResultSet res = req.executeQuery();

		while (res.next()) {
			lClient.add(new Client(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
					res.getString(5), res.getString(6), res.getString(7), res.getString(8)));
		}
		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();
		return lClient;
	}

	@Override
	public Client getById(int id) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		PreparedStatement req = connect.prepareStatement("select * from Client where id_client=?");
		req.setInt(1, id);
		ResultSet res = req.executeQuery();
		Client client = null;
		if (res.next())
			client = new Client(id, res.getString(2), res.getString(3), res.getString(4), res.getString(5),
					res.getString(6), res.getString(7), res.getString(8));
		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();
		return client;
	}

	@Override
	public boolean create(Client object) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		PreparedStatement req = connect.prepareStatement(
				"insert into Client(nom,prenom,no_rue,voie,code_postal,ville,pays) values (?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		req.setString(1, object.getNom());
		req.setString(2, object.getPrenom());
		req.setString(3, object.getNo_rue());
		req.setString(4, object.getVoie());
		req.setString(5, object.getCode_postal());
		req.setString(6, object.getVille());
		req.setString(7, object.getPays());
		int nbLignes = req.executeUpdate();
		ResultSet res = req.getGeneratedKeys();
		if (res.next()) {
			object.setCle(res.getInt(1));
		}

		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return nbLignes == 1;
	}

	@Override
	public boolean update(Client object) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		PreparedStatement req = connect.prepareStatement(
				"update Client set nom=?, prenom=?,no_rue=?,voie=?,code_postal=?,ville=?,pays=? where id_client=?");
		req.setString(1, object.getNom());
		req.setString(2, object.getPrenom());
		req.setString(3, object.getNo_rue());
		req.setString(4, object.getVoie());
		req.setString(5, object.getCode_postal());
		req.setString(6, object.getVille());
		req.setString(7, object.getPays());
		req.setInt(8, object.getCle());
		int nbLignes = req.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public boolean delete(Client object) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		PreparedStatement req = connect.prepareStatement("delete from Client where id_client=?");
		req.setInt(1, object.getCle());
		int nbLignes = req.executeUpdate();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return nbLignes == 1;
	}

	@Override
	public ArrayList<Client> findAll() throws SQLException {
		ArrayList<Client> lClient = new ArrayList<>();
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		PreparedStatement req = connect.prepareStatement("select * from Client");

		ResultSet res = req.executeQuery();

		while (res.next()) {
			lClient.add(new Client(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
					res.getString(5), res.getString(6), res.getString(7), res.getString(8)));
		}
		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();
		return lClient;
	}
}
