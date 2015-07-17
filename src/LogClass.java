
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.zip.DataFormatException;

public class LogClass {

    Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String host = "jdbc:mysql://localhost:3306/datapreprocessingsystem";
    String dbUserName = "root";
    String dbPassword = "";

    public void recordLog(int UID, String description) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);
            preparedStatement = connect.prepareStatement("Insert into LogMaster(UID,LogDate,LogTime,Description) values(?,?,?,?)");
            preparedStatement.setInt(1, UID);
            preparedStatement.setString(2, dateFormat.format(cal.getTime()));
            preparedStatement.setString(3, timeFormat.format(cal.getTime()));
            preparedStatement.setString(4, description);
            preparedStatement.executeUpdate();
        } catch (Exception e) {

        }
    }
    
    public ResultSet viewRecord()
    {
        StateManagerClass stm=new StateManagerClass();
        try
        {
            int uid=stm.getUID();
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);
            preparedStatement=connect.prepareStatement("select *from logmaster where UID="+uid);
            resultSet=preparedStatement.executeQuery();            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    return resultSet;
    }
}
