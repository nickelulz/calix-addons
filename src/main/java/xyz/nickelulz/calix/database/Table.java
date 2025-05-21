package xyz.nickelulz.calix.database;

import xyz.nickelulz.calix.database.SQLDatabaseManager;

public class Table<T> {
    private final String tableName;
    
    /**
     * Writes objects to the SQL database.
     *
     * @param sql The SQL write query to call on the SQL database.
     * @see SQLSerializable#serialize
     */
    public <T> void write(String sql, SQLSerializable<T> obj) throws SQLException
    {}

    /**
     * Queries the SQL database based on the string <code>sqlQuery</code> query string.
     *
     * @param sql      The SQL read query to call on the SQL database.
     * @param template The type of object to cast the result set to (the type of the object being fetched).
     * @return A list of the queried objects, cast to the <code>template</cod> type.
     */
    public <T> List<T> query(String sql, SQLSerializable<T> template) throws SQLException
    {}

    /**
     * Queries the SQL database based on the string <code>sqlQuery</code> query string with the additional option for prepared statement parameters.
     *
     * @param sql      The SQL read query to call on the SQL database.
     * @param template The type of object to cast the result set to (the type of the object being fetched).
     * @param params   Additional prepared statement parameters.
     * @return A list of the queried objects, cast to the <code>template</cod> type.
     */
    public <T> List<T> query(String sql, SQLSerializable<T> template, Object... params) throws SQLException
    {}
}
