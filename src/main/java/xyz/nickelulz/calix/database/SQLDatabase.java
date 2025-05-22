package xyz.nickelulz.calix.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class SQLDatabase
{
    private final String url;
    private final String user;
    private final String password;

    private Connection connection;
    
    public SQLDatabaseManager(String url, String user, String password) throws SQLException
    {
	this.url = String.format("jdbc:sqlite:%s", url);
	this.user = user;
	this.password = password;

	connect();
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
    
    public Connection getConnection() {
	return connection;
    }
}
