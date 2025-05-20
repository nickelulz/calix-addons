package xyz.nickelulz.calix.datatypes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import xyz.nickelulz.calix.datatypes.Database;

public class SQLDatabaseManager
{
    private final String url;
    private final String user;
    private final String password;

    private Connection connection;
    
    public SQLDatabaseManager(String url, String user, String password)
    {
	this.url = url;
	this.user = user;
	this.password = password;
    }

    /**
     * Connect to the SQL Database specified at <code>url</code> with credentials <code>user</code> and <code>password</code>.
     */
    public void connect() throws SQLException
    {
	if (connection == null || connection.isClosed()) {
	    connection = DriverManager.getConnection(url, user, password);
	}
    }

    /**
     * Disconnect from the SQL Database specified at <code>url</code>.
     */
    public void disconnect() throws SQLException
    {
	if (connection != null && !connection.isClosed()) {
	    connection.close();
	}
    }

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
