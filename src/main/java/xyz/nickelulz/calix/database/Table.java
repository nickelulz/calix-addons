package xyz.nickelulz.calix.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import xyz.nickelulz.calix.database.SQLDatabaseManager;

public class Table<T, ID> {
    private final SQLDatabase db;
    private final String tableName;
    private final SQLSerializer<T, ID> serializer;

    public Table(SQLDatabase db, String tableName, SQLMapper<T> mapper) throws SQLException
    {
        this.db = db;
        this.tableName = tableName;
        this.mapper = mapper;
        
        try (Statement statement = db.getConnection()
             .createStatement())
            {
                statement.executeUpdate(mapper.getCreateTableSQL());
            }
    }

    /**
     * Helper for SELECT queries returning one result
     *
     * @param sql    The SQL query string to execute.
     * @param binder The consumer function to call to bind parameters to the SQL statement
     */
    public Optional<T> queryOne(String sql, SQLConsumer<PreparedStatement> binder) throws SQLException
    {
        try (PreparedStatement ps = db.getConnection()
             .prepareStatement(sql))
            {
                binder.accept(ps);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapper.fromResultSet(rs));
                    }
                }
                return Optional.empty();
            }
    }

    /**
     * Helper for SELECT queries returning multiple results.
     *
     * @param sql The SQL query string to execute
     * @return The result set of all objects matching the query.
     */
    public Set<T> queryAll(String sql)
        throws SQLException
    {
        Set<T> result = new HashSet<>();
        try (PreparedStatement ps = db.getConnection()
             .prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(mapper.fromResultSet(rs));
            }
        }
        return result;
    }

    /**
     * Helper for INSERT, UPDATE, DELETE
     *
     * @param sql The SQL query string to execute
     */
    public void executeUpdate(String sql,
                              SQLConsumer<PreparedStatement> binder)
        throws SQLException
    {
        try (PreparedStatement ps = db.getConnection()
             .prepareStatement(sql))
            {
                binder.accept(ps);
                ps.executeUpdate();
            }
    }

    /**
     * Inserts an object into the SQL table (appended).
     *
     * @param obj The object to insert into the table
     */
    public void insert(T obj)
        throws SQLException
    {
        executeUpdate(mapper.getInsertSQL(),
                      ps -> mapper.bindInsertParams(ps, obj));
    }

    /**
     * Retrieves all objects from the SQL table.
     *
     * @return A set of all objects within the table
     */
    public Set<T> getAll()
        throws SQLException
    {
        return queryAll("SELECT * FROM " + tableName);
    }

    /**
     * Locates an object based on some ID field (with
     * template type <code>ID</code> referring to what-
     * ever type is utilized by the mapper class.
     *
     * @return The located object (or none if not found)
     */
    public Optional<T> findById(ID id)
        throws SQLException
    {
        return queryOne(mapper.getSelectByIdSQL(),
                        ps -> ps.setObject(1, id));
    }

    /**
     * Removes an object from the SQL database (if it is
     * currently present).
     *
     * @param obj The object to remove.
     */
    public void delete(T obj)
        throws SQLException
    {
        executeUpdate(mapper.getDeleteByIdSQL(),
                      ps -> ps.setObject(1, mapper.getId(obj)));
    }

    public void update(T obj)
        throws SQLException
    {
        executeUpdate(mapper.getUpdateSQL(),
                      ps -> mapper.bindUpdateParams(ps, obj));
    }
}
