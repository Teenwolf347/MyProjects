package DatabaseTask;

import java.sql.*;
public interface DataBase {
    public void setDataBase();
    public void closeDataBase() throws SQLException;
    public void createTable(String sql);
    public void dropTable(String sql);
    public void addData(String sql);
    public void deleteData(String sql);
    public void updateData(String sql);
    public void getResult(String sql);
    public ResultSet getCachedRowSet(String sql);
    public void getTableDatabase(Integer columnIndex);
    public void getColumnLabelDatabase(String sql);

}
