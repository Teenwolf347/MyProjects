package DatabaseTask;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String create ="CREATE TABLE IF NOT EXISTS BookStore" +
                " (id mediumint not null auto_increment," +
                " namebook varchar(30), author varchar(30)," +
                " price mediumint not null," +
                " count mediumint not null," +
                " primary key (id))";
        String Url = "jdbc:mysql://localhost:3306/Java?useSSL=false";
        String user = "root";
        String password ="1234";
        DataBaseBookStore book = new DataBaseBookStore(Url,user,password);
        book.setDataBase();
        book.getResult("Select * from bookstore");
        book.closeDataBase();
    }
}

class DataBaseBookStore extends DatabaseMySql{
    DataBaseBookStore(String Url, String user, String password) {
        setUrl(Url);
        setUser(user);
        setPassword(password);

    }
    public void getResult(String sql){
        try {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData setMetaData =resultSet.getMetaData();
        System.out.printf("%-2s | %-20s | %-15s | %-5s | %s\n",setMetaData.getColumnLabel(1),
                setMetaData.getColumnLabel(2),
                setMetaData.getColumnLabel(3),
                setMetaData.getColumnLabel(4),
                setMetaData.getColumnLabel(5));
        System.out.println("==============================================================");
        while (resultSet.next()) {
            System.out.printf("%-1d. | %-20s | %-15s | %-5d | %d\n", resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5));
        }
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
}