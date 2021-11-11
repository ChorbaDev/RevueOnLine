package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface Dao<T> {
    /**
     * @param id id de l'objet recherché
     * @return l'objet recherché
     */
    public abstract T getById(int id) throws SQLException, IOException;

    /**
     * @param object à ajouter à insérer dans la solution de persistance
     * @return vrai si l'objet à bien été inséré
     */
    public abstract boolean create(T object) throws SQLException, IOException;

    /**
     * @param object à ajouter à mettre à jour / modifier dans la solution de persistance
     * @return vrai si l'objet à bien été mis à jour
     */
    public abstract boolean update(T object) throws SQLException, IOException;

    /**
     * @param object à ajouter à suppriemer dans la solution de persistance
     * @return vrai si l'objet à bien été supprimé
     */
    public abstract boolean delete(T object) throws SQLException;
}
