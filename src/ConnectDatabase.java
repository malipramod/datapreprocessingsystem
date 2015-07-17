import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {

    Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String host = "jdbc:mysql://localhost:3306/datapreprocessingsystem";
    String dbUserName = "root";
    String dbPassword = "";

    public boolean login(String userName, String password) {
        boolean flag = false;
        LogClass lg = new LogClass();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);

            preparedStatement = connect.prepareStatement("Select *from usermaster where UserName=? and Password=?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            flag = resultSet.next();
            int id = resultSet.getInt("UID");            
            String description = "User with ID:" + id + " Logged In";
            lg.recordLog(id, description);
            StateManagerClass stm=new StateManagerClass();
            stm.setUID(id);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public int register(String userName, String firstName, String lastName, String mobileNo, String password, String securityQuestiton, String securityAnswer) {
        boolean flag = false;
        int rValue = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);
            preparedStatement = connect.prepareStatement("select *from UserMaster where username=?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                rValue = 1;
            } else {
                preparedStatement = connect.prepareStatement("Insert into UserMaster(UserName,FirstName,LastName,MobileNo,Password,SecurityQuestion,SecurityAnswer) values(?,?,?,?,?,?,?)");
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setString(4, mobileNo);
                preparedStatement.setString(5, password);
                preparedStatement.setString(6, securityQuestiton);
                preparedStatement.setString(7, securityAnswer);
                preparedStatement.executeUpdate();
                rValue = 2;
            }

        } catch (ClassNotFoundException | SQLException e) {
        }
        return rValue;
    }

    public String forgetPasswor(String userName) {
        //  boolean flag=false;
        String securityQuestion = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);
            preparedStatement = connect.prepareStatement("select securityQuestion from UserMaster where username=?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                securityQuestion = resultSet.getString("SecurityQuestion");
            }

        } catch (ClassNotFoundException | SQLException e) {

        }
        return securityQuestion;
    }

    public String varifySecurityAnswer(String userName) {
        String securityAnswer = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);
            preparedStatement = connect.prepareStatement("select securityAnswer from UserMaster where username=? ");
            preparedStatement.setString(1, userName);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                securityAnswer = resultSet.getString("SecurityAnswer");
            }
        } catch (ClassNotFoundException | SQLException e) {

        }
        return securityAnswer;
    }

    public boolean changePassword(String newPassword, String userName) {
        boolean flag = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(host, dbUserName, dbPassword);
            preparedStatement = connect.prepareStatement("Update UserMaster set Password=? where UserName=?");
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (ClassNotFoundException | SQLException e) {
        }
        return flag;
    }

}
