
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.ResultSetMetaData;
import java.util.Random;

public class DataCleaningClass {

    Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private int binSize = 8;
    private java.util.List sublist;
    ArrayList resultList = new ArrayList();
    ArrayList meanResult = new ArrayList();
    private int sum = 0;
    private double binAvg;
    private double totalSample;
    private double sampleSum;
    private double mean;
    ArrayList result = new ArrayList();
    ArrayList tempList = new ArrayList();
    private java.util.List intermediateList;
    private double finalSum = 0.0;

    /*midrange method is used to fill up missing values
     For example:
     result=(max(Column value)+min(Column value))/2
     */
    public ResultSet fillMissingValue(String host, String userName, String password, String databaseName, String tableName, String columnName) throws SQLException {
        double avgValue = 0.0;
        int minValue = 0;
        int min = 0;
        String newHost = host + databaseName;
        String str;

        StateManagerClass stm = new StateManagerClass();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(newHost, userName, password);
            statement = connect.createStatement();
            //Random Table
            int random;
            Random genRandom = new Random();
            random = genRandom.nextInt(10000);
            String newTalbleName = stm.getTableName() + random;
            stm.setNewTableName(newTalbleName);

            //Create Table
            connect.createStatement();
            String createCopy = "Create table " + stm.getNewTableName() + " as Select " + columnName + " from " + stm.getTableName();
            statement.executeUpdate(createCopy);
            System.out.println("New Talbe Created");

            resultSet = statement.executeQuery("select *from " + stm.getNewTableName());
            resultSet.next();
            ResultSetMetaData m = resultSet.getMetaData();
            int cc = m.getColumnCount();

            int i = 1;

//  while (i <= cc) {
            columnName = m.getColumnName(i);
            String type = m.getColumnTypeName(cc);
            if (type.equals("INT") || type.equals("DOUBLE")) {
                str = "select min(" + columnName + ") as MinValue, max(" + columnName + ") as MaxValue from " + stm.getNewTableName();
                statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                resultSet = statement.executeQuery(str);

                while (resultSet.next()) {

                    if (resultSet.getInt("MinValue") < min) {

                        min = resultSet.getInt("MinValue");
                    }

                    minValue = resultSet.getInt("MinValue");
                    int maxCollection = resultSet.getInt("MaxValue");
                    avgValue = (double) ((minValue + maxCollection) / 2);

                }

                statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                str = "select " + columnName + " from " + stm.getNewTableName();
                ResultSet resultSet = statement.executeQuery(str);

                System.out.println(m.getColumnName(i));
                System.out.println("Avg Values:" + avgValue);

                // resultSet.next();
                while (resultSet.next()) {
                    int si = (int) resultSet.getInt(columnName);
                    if (si == 0) {
                        Connection con = DriverManager.getConnection(newHost, userName, password);
                        Statement st = con.createStatement();
                        System.out.println(avgValue + "(Missing)");
                        String updateStr = "update " + stm.getNewTableName() + " set " + m.getColumnName(i) + "=" + avgValue + " where " + m.getColumnName(i) + "=0";
                        st.executeUpdate(updateStr);

                    } else {
                        System.out.println(resultSet.getString(columnName));

                    }

                }
            } else {
                System.out.println("Operations can only be performed on Numerical values");
            }

            i++;
        //}

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connect.close();

            statement.close();
        }
        return resultSet;
    }
    /*maximum likelihood method
     Mean: u=(add(elements))/total elements
     Deviation: d=SQRT((add(all element bins(a[0]-a[1]))) /total elements)
     diff:u-currentValue
     ans=diff/d
     if(ans>3 or ans<-3)
     print "Outlier Exists";        
     */

    public void identifyOutliers(String host, String userName, String password, String databaseName, String tableName, String columnName) {
        String newHost = host + databaseName;

        StateManagerClass stm = new StateManagerClass();
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
            String createCopy = "Create table " + stm.getNewTableName() + " as Select " + columnName + " from " + stm.getTableName();
            statement.executeUpdate(createCopy);
            System.out.println("New Talbe Created");

            resultSet = statement.executeQuery("select " + columnName + " from " + stm.getTableName());
            resultSet.next();
            ResultSetMetaData m = resultSet.getMetaData();
            int cc = m.getColumnCount();

            int i = 1;
            //      while (i <= cc) {
            columnName = m.getColumnName(i);
            String type = m.getColumnTypeName(cc);
            if (type.equals("INT") || type.equals("DOUBLE")) {
                String str = "select  count(" + columnName + ") as TotalSamples, sum(" + columnName + ") as TotalSum from " + stm.getTableName();
                statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                resultSet = statement.executeQuery(str);
                resultSet.next();

                //Calculating mean values
                this.sampleSum = resultSet.getInt("TotalSum");
                this.totalSample = resultSet.getInt("TotalSamples");
                this.mean = sampleSum / totalSample;    //Mean  

                statement = connect.createStatement();
                resultSet = statement.executeQuery("select " + columnName + " from " + stm.getTableName());

                while (resultSet.next()) {
                    result.add(resultSet.getInt(columnName));

                }
                //sort
                Collections.sort(result);

                for (int start = 0; start < result.size(); start += 2) {
                    int end = Math.min(start + 2, result.size());
                    this.intermediateList = result.subList(start, end);
                    int j;
                    double tempResult = 0;
                    for (j = 0; j < intermediateList.size(); j += 2) {
                        tempResult = ((int) intermediateList.get(0)) - ((int) intermediateList.get(1));
                        tempResult = Math.pow(tempResult, 2);
                        this.finalSum = this.finalSum + tempResult;
                    }

                }

                //Calculating deviation
                this.finalSum = this.finalSum / this.totalSample;
                this.finalSum = Math.sqrt(this.finalSum);  //Deviation

                preparedStatement = connect.prepareStatement("select  " + columnName + " as CurrentValue from " + stm.getTableName());
                resultSet = preparedStatement.executeQuery();

                double diff;
                double divResult;

                while (resultSet.next()) {
                    diff = this.mean - resultSet.getInt("CurrentValue");
                    divResult = (diff / this.finalSum);
                    String inStr;
                    if (divResult > (3.0) || divResult < (-3.0)) {
                        inStr = "update " + stm.getNewTableName() + " set " + m.getColumnName(i) + "=1 where " + columnName + "=" + resultSet.getInt("CurrentValue");
                        statement.executeUpdate(inStr);
                      
                    } else {
                        inStr = "update " + stm.getNewTableName() + " set " + m.getColumnName(i) + "=0 where " + columnName + "=" + resultSet.getInt("CurrentValue");
                        statement.executeUpdate(inStr);
                      
                    }

                }

            }
            i++;
            //   }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     Smoothing Data using mean value
     Finding Mean Value by:
     Mean=(Add(Bin Values))/(No of Bean elements)
     */
    public void smoothNoisyData(String host, String userName, String password, String databaseName, String tableName, String columnName) {
        String newHost = host + databaseName;
        StateManagerClass stm = new StateManagerClass();
        try {
            //     String columnName;
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
            String createCopy = "Create table " + stm.getNewTableName() + " as Select " + columnName + " from " + stm.getTableName();
            statement.executeUpdate(createCopy);
            System.out.println("New Talbe Created");

            resultSet = statement.executeQuery("select " + columnName + " from " + stm.getTableName());
            resultSet.next();
            ResultSetMetaData m = resultSet.getMetaData();
            int cc = m.getColumnCount();

            int j = 1;
            //    while (j <= cc) {
            columnName = m.getColumnName(j);
            String type = m.getColumnTypeName(cc);
            if (type.equals("INT") || type.equals("DOUBLE")) {
                connect = DriverManager.getConnection(newHost, userName, password);
                String str = "select  " + columnName + " from " + stm.getTableName();
                statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                resultSet = statement.executeQuery(str);

                while (resultSet.next()) {
                    resultList.add(resultSet.getInt(columnName));
                }
                //Sorting
                Collections.sort(resultList);

                /*Finding Mean Value by:
                 Mean=(Add(Bin Values))/(No of Bean elements)
                 */
                for (int start = 0, k = 1; start < resultList.size(); start += binSize, k++) {
                    int end = Math.min(start + binSize, resultList.size());
                    this.sublist = resultList.subList(start, end);
                    System.out.println(this.sublist);

                    int count = 0;
                    int tempSum = 0;
                    int i;
                    for (i = 0; i < sublist.size(); i++) {
                        tempSum = (int) tempSum + (int) sublist.get(i);
                        count++;
                    }

                    this.binAvg = ((double) tempSum / count);
                    this.meanResult.add(this.binAvg);
                    // i += binSize;
                    

                    String inStr = "update " + stm.getNewTableName() + " set " + m.getColumnName(j) + "=" + this.binAvg;
                    statement.executeUpdate(inStr);
                  
                    sublist.clear();

                }
            }

            j++;
            meanResult.clear();
            resultList.clear();

       //     }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
