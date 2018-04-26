package myswing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/4/26 16:55
 * Modified By:
 * Description:
 */
public class DatabaseConnect {

    public DatabaseConnect() {
    }

    private Connection connection = null;

    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getDatabase() {
        List<String> databases = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "show databases;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String databaseName = resultSet.getString("DataBase");
                databases.add(databaseName);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databases;
    }

    public List<String> getTable(String database) {
        List<String> tables = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "select table_name from information_schema.tables"
                    + " where table_schema='" + database
                    + "' AND table_type='base table';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String tableName = resultSet.getString("table_name");
                tables.add(tableName);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }
}
