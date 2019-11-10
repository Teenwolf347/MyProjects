import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import javax.sql.*;
import java.util.Objects;
import java.util.regex.*;
import javax.sql.rowset.*;
import org.apache.log4j.*;

abstract class DatabaseMySQL implements Database {
    private String Url;
    private String user;
    private Connection connection;
    private static Logger logger = Logger.getLogger(DatabaseMySQL.class);

//  Получение информации
    protected Connection getConnection() {
        return connection;
    }
    protected String getUrl(){
        return Url;
    }
    protected String getUser(){
        return user;
    }

//  Подключение к БД
    public void connectionDatabase(){
            String Format =
                    "(jdbc):(\\w{1,30})://(((\\d|[1-9]{2}|1[0-9]{2}|2[0-5]{2})?.){4}|(\\S{1,30})):(\\d{4,})/(\\S+)";
            if (Objects.requireNonNull(DatabaseConfig.getUrl()).matches(Format)) {
                Url = DatabaseConfig.getUrl();
                Format = "^[a-zA-Z][a-zA-Z0-9_.]{3,20}";
                if (Objects.requireNonNull(DatabaseConfig.getUser()).matches(Format)) {
                    user = DatabaseConfig.getUser();
                    Format = "(^[a-zA-Z0-9][a-zA-Z0-9@_#]{2,14}[a-zA-Z0-9]$)|(\\d{4,16})|([a-zA-Z]{4,16})";
                    if (Objects.requireNonNull(DatabaseConfig.getPassword()).matches(Format)) {
                        String password = DatabaseConfig.getPassword();
                        try {
                            Class.forName(DatabaseConfig.getDriver());
                            DataSource dataSource = getMysqlDataSource(Url, user, password);
                            connection = dataSource.getConnection();
                            logger.info("Подключение к Базе данных прошло успешно\n");
                        } catch (SQLException | ClassNotFoundException ex) {
                            logger.error(new Exception(ex));
                            System.exit(0);
                        }
                    } else {logger.error("Введенный пароль не соотвествует требованиям");
                        System.exit(0);}
                } else {logger.error("Введенный username не соотвествует требованиям");
                    System.exit(0);}
            } else {logger.error("Введенный URL адрес не соотвествует требованиям");
                System.exit(0);}
    }
    private DataSource getMysqlDataSource(String Url, String user, String password) {
        MysqlDataSource mysqlDataSource = null;
        try {
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(Url);
            mysqlDataSource.setUser(user);
            mysqlDataSource.setPassword(password);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return mysqlDataSource;
    }
    public void disconnectDatabase() throws SQLException {
        connection.close();
    }
    public Statement statementDatabase(){
        try {
            return connection.createStatement();
        }catch (SQLException | NullPointerException ex){
            logger.error(new Exception(ex));
        }
        return null;
    }

    //Работа с таблицой
    public void createTable(String sql) {
        Pattern pattern = Pattern.compile("CREATE TABLE", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            try(Statement statement = statementDatabase()){
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
        if (matcher.find()) {
            try( Statement statement = statementDatabase()) {
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
        if (matcher.find()) {
            try (Statement statement = statementDatabase()) {
                statement.executeUpdate(sql);
                logger.info("Данные успешно добавлены");
            } catch (SQLException ex) {
                logger.error(new Exception(ex));
            }
        } else {
            System.err.println("Веденная команда используется не для добавления данных в таблицу");
        }
    }
    public void deleteData(String sql) {
        Pattern pattern = Pattern.compile("DELETE", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            try (Statement statement = statementDatabase()) {
                statement.executeUpdate(sql);
                logger.error("Данные успешно удалены");
            } catch (SQLException ex) {
                logger.error(new Exception(ex));
            }
        } else {
            System.err.println("Веденная команда используется не для удаления данных в таблице");
        }
    }
    public void updateData(String sql) {
        Pattern pattern = Pattern.compile("UPDATE", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
                logger.error("Данные успешно обновлены");
            } catch (SQLException ex) {
                logger.error(new Exception(ex));
            }
        } else {
            System.err.println("Веденная команда используется не для обновления данных в таблице");
        }
    }

    //Получение резульатов
    public void getResultOld(String sql) {
        try (Statement statement = statementDatabase()) {
            ResultSet resultSet = statement.executeQuery(sql);
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            logger.error(new Exception(ex));
        }
    }
    public ResultSet getCachedRowSet(String sql){
        CachedRowSet cachedRowSet = null;
        try {
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            cachedRowSet = rowSetFactory.createCachedRowSet();
            try (Statement statement = statementDatabase();
            ResultSet resultSet = statement.executeQuery(sql)) {
                cachedRowSet.populate(resultSet);
                return cachedRowSet;
            } catch (SQLException ex) {
                logger.error(new Exception(ex));
            }
            cachedRowSet.close();
        } catch (SQLException ex){
            logger.error(new Exception(ex));
        }
        return cachedRowSet;
    }
    public void getTableDatabase(Integer columnIndex) {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            try (ResultSet resultSet =
                         databaseMetaData.getTables
                                 (null, null, null, new String[]{"Table"})) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(columnIndex));
                }
            } catch (SQLException ex) {
                logger.error(new Exception(ex));
            }
        }catch (SQLException ex){
            logger.error(new Exception(ex));
        }
    }
    public void getColumnLabelDatabase(String table){
        try (Statement statement = statementDatabase();
             ResultSet resultSet = statement.executeQuery("Select * from " + table)){
            ResultSetMetaData setMetaData = resultSet.getMetaData();
            for (int i = 1; i <= setMetaData.getColumnCount(); i++) {
                System.out.print(setMetaData.getColumnLabel(i) + " - " + setMetaData.getColumnTypeName(i) + " (" + setMetaData.getPrecision(i)+ ") | ");
            }
            System.out.println();
        } catch (SQLException ex) {
            logger.error(new Exception(ex));
        }
    }
    abstract void saveResult(String sql);
    abstract void getResult(String sql);
}
