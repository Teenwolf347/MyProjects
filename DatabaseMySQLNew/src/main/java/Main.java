import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseExchangeRate database = new DatabaseExchangeRate();
        database.connectionDatabase();
        Scanner inString = new Scanner(System.in);
        Scanner inSQL = new Scanner(System.in);
        Scanner inCol = new Scanner(System.in);
        String instruction = "-create - команда позволяет создать новую таблицу \n" +
                "-drop - команда позволяет удалить существующую таблицу \n" +
                "-add - команда позволяет добавлять в таблицу новые данные \n" +
                "-delete - команда позволяет удалять уже добавленые данные в таблице \n" +
                "-update - команда позволяет обновлять существующие данные в таблице \n" +
                "-result - команда выводит в консоль результат SQL запроса \n" +
                "-saveres - команда сохраняет в файле результат SQL запроса \n" +
                "-tabledb - команда предоставляет список таблиц существующие в БД \n" +
                "-columndb - команда предоставляет данные о колонках таблицы \n" +
                "-exit - выход из программы \n";
        System.out.print(instruction + "\n");

        while (true) {
            System.out.print("> ");
            String c = inString.nextLine();
            switch (c) {
                case "-create":
                    System.out.print("Введите команду sql : ");
                    String sqlCreate = inSQL.nextLine();
                    database.createTable(sqlCreate);
                    break;
                case "-drop":
                    System.out.print("Введите команду sql :");
                    String sqlDrop = inSQL.nextLine();
                    database.dropTable(sqlDrop);
                    break;
                case "-add":
                    System.out.print("Введите команду sql : ");
                    String sqlAdd = inSQL.nextLine();
                    database.addData(sqlAdd);
                    break;
                case "-delete":
                    System.out.print("Введите команду sql : ");
                    String sqlDelete = inSQL.nextLine();
                    database.deleteData(sqlDelete);
                    break;
                case "-update":
                    System.out.print("Введите команду sql : ");
                    String sqlUpdate = inSQL.nextLine();
                    database.updateData(sqlUpdate);
                    break;
                case "-result":
                    System.out.print("Введите команду sql :");
                    String sqlResult = inSQL.nextLine();
                    database.getResult(sqlResult);
                    break;
                case "-saveres":
                    System.out.print("Введите команду sql : ");
                    String sqlSave = inSQL.nextLine();
                    database.saveResult(sqlSave);
                    break;
                case "-tabledb":
                    System.out.print("Введите команду номер колонки : ");
                    Integer idColumn = inCol.nextInt();
                    database.getTableDatabase(idColumn);
                    break;
                case "-columndb":
                    System.out.print("Введите команду sql : ");
                    String sqlColumn = inSQL.nextLine();
                    database.getColumnLabelDatabase(sqlColumn);
                    break;
                case "-exit":
                    database.disconnectDatabase();
                    System.exit(0);
                    break;
                case "-help":
                    System.out.println(instruction);
                    break;
                default:
                    System.out.println("Команда -help выведит список команд");
            }
        }
    }
}
