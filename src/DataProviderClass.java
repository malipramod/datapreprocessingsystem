
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProviderClass {

    Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    StateManagerClass stm = new StateManagerClass();

    public String[] selectDatabaseFromMySQL(String dbUserName, String dbPassword) throws SQLException {
        int i = 0;
        String[] rArray = new String[100];
        String host = "jdbc:mysql://localhost:3306";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);
            resultSet = connect.getMetaData().getCatalogs();
            while (resultSet.next()) {

                rArray[i] = resultSet.getString("TABLE_CAT");
                i++;
            }
        } catch (ClassNotFoundException | SQLException e) {

        }
        return rArray;
    }

    public String[] selectDatabaseFromSQLServer(String dbUserName, String dbPassword) {
        int i = 0;
        String[] rArray = new String[100];
        String host = "jdbc:sqlserver://localhost:1433";

        try {
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);
            resultSet = connect.getMetaData().getCatalogs();
            while (resultSet.next()) {

                rArray[i] = resultSet.getString("TABLE_CAT");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rArray;
    }

    public String[] selectTableFromMySQL(String databaseName, String dbUserName, String dbPassword) throws ClassNotFoundException {
        int i = 0;
        String[] rArray = new String[100];
        String host = "jdbc:mysql://localhost:3306";
        String newHost = host + "/" + databaseName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(newHost, dbUserName, dbPassword);
            DatabaseMetaData md = connect.getMetaData();
            resultSet = md.getTables(null, null, "%", null);

            while (resultSet.next()) {

                rArray[i] = resultSet.getString(3);
                i++;
            }
        } catch (SQLException e) {

        }
        return rArray;

    }

    public String[] selectTableFromSQLServer(String databaseName) {
        int i = 0;

        String[] rArray = new String[1000];
        StateManagerClass stm = new StateManagerClass();
        String host = stm.getSQLServerHost();
        host = host + databaseName;

        String dbUserName = stm.getSQLServerUserName();
        String dbPassword = stm.getSQLServerPassword();
        String type[] = {"TABLE"};
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);
            DatabaseMetaData md = connect.getMetaData();
            resultSet = md.getTables(databaseName, null, "%", type);
            while (resultSet.next()) {

                rArray[i] = resultSet.getString(3);
                i++;

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rArray;
    }

    public ResultSet bindTable(String databaseName, String tableName, String host) {
        StateManagerClass stm = new StateManagerClass();
        if (stm.getDatabaseType() == 1) {
            String newhost = "jdbc:mysql://localhost:3306/" + databaseName + "?zeroDateTimeBehavior=convertToNull";
            String dbUserName = stm.getMySQLUserName();
            String dbPassword = stm.getMySQLPassword();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection(newhost, dbUserName, dbPassword);
                String str = "select *from " + tableName;
                preparedStatement = connect.prepareStatement(str);
                resultSet = preparedStatement.executeQuery();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (stm.getDatabaseType() == 2) {
            String newhost = host + databaseName;
            String dbUserName = stm.getSQLServerHost();
            String dbPassword = stm.getSQLServerPassword();
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connect = DriverManager.getConnection(newhost, dbUserName, dbPassword);
                String str = "select *from " + tableName;
                preparedStatement = connect.prepareStatement(str);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Error While selecting");
        }

        return resultSet;

    }

    public ResultSet bindTable2(String databaseName, String tableName, String host, String columName) {
        StateManagerClass stm = new StateManagerClass();
        if (stm.getDatabaseType() == 1) {
            String newhost = "jdbc:mysql://localhost:3306/" + databaseName + "?zeroDateTimeBehavior=convertToNull";
            String dbUserName = stm.getMySQLUserName();
            String dbPassword = stm.getMySQLPassword();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection(newhost, dbUserName, dbPassword);
                String str = "select " + columName + " from " + stm.getNewTableName();
                preparedStatement = connect.prepareStatement(str);
                resultSet = preparedStatement.executeQuery();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (stm.getDatabaseType() == 2) {
            String newhost = host + databaseName;
            String dbUserName = stm.getSQLServerHost();
            String dbPassword = stm.getSQLServerPassword();
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connect = DriverManager.getConnection(newhost, dbUserName, dbPassword);
                String str = "select *from " + tableName;
                preparedStatement = connect.prepareStatement(str);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Error While selecting");
        }

        return resultSet;

    }

    public String[] bindColumnCombo(String host, String databaseName, String tableName) {
        String[] rArray = new String[100];
        StateManagerClass stm = new StateManagerClass();
        host = host + stm.getDatabaseName();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(host, stm.getMySQLUserName(), stm.getMySQLPassword());
            statement = connect.createStatement();
            String str = "Select * from " + tableName;
            resultSet = statement.executeQuery(str);
            ResultSetMetaData m = resultSet.getMetaData();

            int numberOfColumns = m.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {

                rArray[i] = m.getColumnName(i);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rArray;
    }

}
