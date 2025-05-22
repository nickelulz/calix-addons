import java.sql.SQLException;

public interface SQLSerializable<T>
{
    String createTableSQL();
    String getInsertSQL();
    void bindInsertParams(PreparedStatement ps, T obj) throws SQLException;
    T fromResultSet(ResultSet rs) throws SQLException;
}
