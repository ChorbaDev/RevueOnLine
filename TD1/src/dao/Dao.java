package dao;

import java.sql.SQLException;

public interface Dao<T> {
    public abstract T getById(int id) throws SQLException;

    public abstract boolean create(T object) throws SQLException;

    public abstract boolean update(T object) throws SQLException;

    public abstract boolean delete(T object) throws SQLException;
}
