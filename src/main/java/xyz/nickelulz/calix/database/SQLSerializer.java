import java.sql.SQLException;

public abstract class SQLSerializer<T, SQL_ID>
{
    private String tableName;

    public SQLSerializer(String tableName)
    {
        this.tableName = tableName;
    }
    
    public abstract String createTableSQL();
    public abstract String getInsertSQL();
    
    public abstract void bindInsertParams(PreparedStatement ps, T obj) throws SQLException;
    public abstract T fromResultSet(ResultSet rs) throws SQLException;
    public abstract SQL_ID getDataID(T obj);
}
