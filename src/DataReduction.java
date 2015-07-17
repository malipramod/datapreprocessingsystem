
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramod
 */
public class DataReduction {

    Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private double minVal;
    private double maxVal;
    private double val;
    List sourceList = new ArrayList();

    public void reduceData(String host, String userName, String password, String databaseName, String tableName) {
        StateManagerClass stm = new StateManagerClass();
        double lowRange = 0.0, highRange = 1.0;
        double result = 0.0;
        String columnName = "";
        String newHost = host + databaseName;
        String str;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(newHost, userName, password);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select *from " + stm.getTableName());
            resultSet.next();
            ResultSetMetaData m = resultSet.getMetaData();
            int cc = m.getColumnCount();

            int i = 1;

            while (i <= cc) {
                columnName = m.getColumnName(i);
              System.out.println(columnName);
                String type = m.getColumnTypeName(cc);
                if (type.equals("INT") || type.equals("DOUBLE")) {
                    str = "select " + columnName + " from " + stm.getTableName();
                    statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    resultSet = statement.executeQuery(str);
              
                    while (resultSet.next()) {
                        sourceList.add(resultSet.getDouble(columnName));
                       
                    }
                   
                    List newList = new ArrayList(new LinkedHashSet(sourceList));
                    Iterator it = newList.iterator();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }

                  
                }sourceList.clear();
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   

}
