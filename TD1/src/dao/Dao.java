package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface Dao<T> {
    public abstract T getById(int id) throws SQLException, IOException;

    public abstract boolean create(T object) throws SQLException, IOException;

    public abstract boolean update(T object) throws SQLException, IOException;

    public abstract boolean delete(T object) throws SQLException;
}
