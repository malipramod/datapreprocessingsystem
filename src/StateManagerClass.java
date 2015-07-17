
public class StateManagerClass {

    public static String userName;
    public static  String password;
    public static int uid;
    public static String databaseName;
    public static String tableName;
    public static String mySQLHost="jdbc:mysql://localhost:3306/"; //"jdbc:mysql://localhost:3306/"
    public static String sqlServerHost="jdbc:sqlserver://localhost:1433;databaseName=";  
    public static String mySQLUser; //"root"
    public static String mySQLPass="" ;//""
    public static String sqlServerUser; //= "sa"
    public static String sqlServerPass; //"tiger"
    public static int databaseType=1; //mysql=1, oracle=2
    public static String newDatabaseName;
    public static String newTableName;


    public void setDatabaseType(int newDatabaseType)
    {
        StateManagerClass.databaseType=newDatabaseType;        
    }
    public int getDatabaseType()
    {
        return StateManagerClass.databaseType;
    }
            
    public void setUserName(String newUserName) {
        StateManagerClass.userName = newUserName;
    }

    public String getUserName() {
        return StateManagerClass.userName;
    }

    public void setPassword(String newPassword) {
        StateManagerClass.password = newPassword;
    }

    public String getPassword() {
        return StateManagerClass.password;
    }

    public void setUID(int uid)
    {
        StateManagerClass.uid=uid;
    }
    public int getUID()
    {
        return StateManagerClass.uid;
    }
    public void setDatabase(String newDatabaseName) {
        StateManagerClass.databaseName = newDatabaseName;
    }

    public String getDatabaseName() {
        return StateManagerClass.databaseName;
    }

    public void setTableName(String newTableName) {
        StateManagerClass.tableName = newTableName;
    }

    public String getTableName() {
        return StateManagerClass.tableName;
    }
//mysql
  

    public String getMySQLHost() {
        return StateManagerClass.mySQLHost;
    }

    public void setMySQLUserName(String newMySQLUserName) {
        StateManagerClass.mySQLUser = newMySQLUserName;
    }

    public String getMySQLUserName() {
        return StateManagerClass.mySQLUser;
    }

    public void setMySQLPassword(String newMySQLPass) {
        StateManagerClass.mySQLPass = newMySQLPass;
    }

    public String getMySQLPassword() {
        return StateManagerClass.mySQLPass;
    }

    //SQL Server
   

    public String getSQLServerHost() {
        return StateManagerClass.sqlServerHost;
    }

    public void setSQLServerUserName(String newSQLServerUserName) {
        StateManagerClass.sqlServerUser = newSQLServerUserName;
    }

    public String getSQLServerUserName() {
        return StateManagerClass.sqlServerUser;
    }

    public void setSQLServerPassword(String newSQLServerPassword) {
        StateManagerClass.sqlServerPass = newSQLServerPassword;
    }

    public String getSQLServerPassword() {
        return StateManagerClass.sqlServerPass;
    }
    
    public void setNewDatabaseName(String dbName)
    {
        StateManagerClass.newDatabaseName=dbName;
    }
    
    public String getNewDatabaseName()
    {
        return StateManagerClass.newDatabaseName;
    }
    public void setNewTableName(String tableName)
    {
        StateManagerClass.newTableName=tableName;
    }
    
    public String getNewTableName()
    {
        return StateManagerClass.newTableName;
    }
    
   
}
