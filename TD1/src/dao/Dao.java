package dao;

import java.io.IOException;
import java.sql.SQLException;

public interface Dao<T> {
    T getById(int id) throws SQLException, IOException, ClassNotFoundException;

    boolean create(T object) throws SQLException, IOException, ClassNotFoundException;

    boolean update(T object) throws SQLException, IOException, ClassNotFoundException;

    boolean delete(T object) throws SQLException, ClassNotFoundException;
}
