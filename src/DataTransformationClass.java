
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Random;

public class DataTransformationClass {

    Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private double minVal;
    private double maxVal;
    private double val;

    /*
     Min-Max Algorithm
     Formula:
     Ans=((A-min(A))/(max(A)-min(A)))*((highRange-lowRange)+lowRange)
     */
    public void normalization(String host, String userName, String password, String databaseName, String tableName, String columnName) {
        StateManagerClass stm = new StateManagerClass();
        double lowRange = 0.0, highRange = 1.0;
        double result = 0.0;
    
        String newHost = host + databaseName;
        String str;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(newHost, userName, password);
            statement = connect.createStatement();

            int random;
            Random genRandom = new Random();
            random = genRandom.nextInt(10000);
            String newTalbleName = stm.getTableName() + random;
            stm.setNewTableName(newTalbleName);

            //Create Table
            connect.createStatement();
            String createCopy = "Create table " + stm.getNewTableName() + " as Select "+columnName+" from " + stm.getTableName();
            statement.executeUpdate(createCopy);
            System.out.println("New Talbe Created");

            resultSet = statement.executeQuery("select "+columnName+" from " + stm.getTableName());
            resultSet.next();
            ResultSetMetaData m = resultSet.getMetaData();
            int cc = m.getColumnCount();

            int i = 1;

         //   while (i <= cc) {
                columnName = m.getColumnName(i);
                String type = m.getColumnTypeName(cc);
                if (type.equals("INT") || type.equals("DOUBLE")) {
                    str = "select  min(" + columnName + ") as MinValue, max(" + columnName + ") as MaxValue from " + tableName;
                    statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    resultSet = statement.executeQuery(str);
                    while (resultSet.next()) {
                        this.minVal = resultSet.getDouble("minValue");
                        this.maxVal = resultSet.getDouble("maxValue");

                    }
                    statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    resultSet = statement.executeQuery("select " + columnName + " from " + tableName);
                    while (resultSet.next()) {
                        this.val = resultSet.getInt(columnName);
                        Connection con = DriverManager.getConnection(newHost, userName, password);
                        Statement st = con.createStatement();
                        result = (double) ((this.val - this.minVal) / (this.maxVal - this.minVal)) * ((highRange - lowRange) + lowRange);
                        String updateStr = "update " + stm.getNewTableName() + " set " + m.getColumnName(i) + "=" + result+" where "+columnName+"="+this.val;
                        st.executeUpdate(updateStr);
                        System.out.println(result);
                    }

                }
                
       
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   

}
