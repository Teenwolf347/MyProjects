import java.sql.*;
public interface Database {
    void connectionDatabase();
    void disconnectDatabase() throws SQLException;
    Statement statementDatabase();
    void createTable(String sql);
    void dropTable(String sql);
    void addData(String sql);
    void deleteData(String sql);
    void updateData(String sql);
    ResultSet getCachedRowSet(String sql);
    void getTableDatabase(Integer columnIndex);
    void getColumnLabelDatabase(String table);

}
