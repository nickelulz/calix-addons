import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLSerializable<T> {
    void serialize(PreparedStatement ps) throws SQLException;
    T deserialize(ResultSet rs) throws SQLException;
}
