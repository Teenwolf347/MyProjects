package DatabaseTask;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.*;
import javax.sql.rowset.*;
import java.sql.*;
import java.util.regex.*;


abstract class DatabaseMySql implements DataBase {
    private String Url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private CachedRowSet cachedRowSet;
    private ResultSet resultSet;

    // Для получения данных
    void setUrl(String Url) {
        String Format = "(jdbc):(\\w{1,})://(((\\d|[1-9]{2}|1[0-9]{2}|2[0-5]{2})?.){4}|(\\S{1,})):(\\d{4})/(\\S{1,})";
        if (Url.matches(Format)) {
            this.Url = Url;
        } else {
            System.out.println("Данный URL не соответствует формату");
        }
    }
    void setUser(String user) {
        this.user = user;
    }
    void setPassword(String password) {
        this.password = password;
    }
    protected Connection getConnection() { return connection;}

    // Поключение к БД
    public void getDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DataSource dataSource = getMysqlDataSource();
            connection = dataSource.getConnection();
            System.out.println("Подключение прошло успешно");
        } catch (SQLException ex) {
            System.err.println("Возникла ошибка при подключении: " + ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void getDataBase(String Url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DataSource dataSource = getMysqlDataSource(Url, user, password);
            connection = dataSource.getConnection();
            System.out.println("Подключение прошло успешно");
        } catch (SQLException ex) {
            System.err.println("Возникла ошибка при подключении: " + ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
    private DataSource getMysqlDataSource() {
        MysqlDataSource mysqlDataSource = null;
        try {
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(Url);
            mysqlDataSource.setUser(user);
            mysqlDataSource.setPassword(password);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return mysqlDataSource;
    }
    private DataSource getMysqlDataSource(String Url, String user, String password) {
        MysqlDataSource mysqlDataSource = null;
        try {
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(Url);
            mysqlDataSource.setUser(user);
            mysqlDataSource.setPassword(password);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return mysqlDataSource;
    }
    public void closeDataBase()throws SQLException{connection.close();}

    //Работа с таблицой
    public void createTable(String sql) {
        Pattern pattern = Pattern.compile("CREATE TABLE", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        boolean format = matcher.find();
        if (format) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("Веденная команда используется не для создания новой таблицы");
        }
    }
    public void dropTable(String sql) {
        Pattern pattern = Pattern.compile("DROPE TABLE", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        boolean format = matcher.find();
        if (format) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("Веденная команда используется не для удаления таблицы");
        }
    }

    //Работа с данными таблицы
    public void addData(String sql) {
        Pattern pattern = Pattern.compile("INSERT INTO", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        boolean format = matcher.find();
        if (format) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("Веденная команда используется не для добавления данных в таблицу");
        }
    }
    public void deleteData(String sql) {
        Pattern pattern = Pattern.compile("DELETE", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        boolean format = matcher.find();
        if (format) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("Веденная команда используется не для удаления данных в таблице");
        }
    }
    public void updateData(String sql) {
        Pattern pattern = Pattern.compile("UPDATE", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        boolean format = matcher.find();
        if (format) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("Веденная команда используется не для обновления данных в таблице");
        }
    }

    //Получение резульатов
    public void getResult(String sql) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public ResultSet getCachedRowSet(String sql){
        try {
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            cachedRowSet = rowSetFactory.createCachedRowSet();
            connection = getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sql);
            cachedRowSet.populate(resultSet);
            return cachedRowSet;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cachedRowSet;
    }
    public void getTableDatabase(Integer columnIndex) {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            statement = connection.createStatement();
            resultSet = databaseMetaData.getTables(null,null,null, new String[] {"Table"});
            while (resultSet.next()){
                System.out.println(resultSet.getString(columnIndex));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void getColumnLabelDatabase(String sql){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData setMetaData = resultSet.getMetaData();
            for (int i = 1; i <= setMetaData.getColumnCount(); i++) {
                System.out.print(setMetaData.getColumnLabel(i) + " - " + setMetaData.getColumnTypeName(i) + " | ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

