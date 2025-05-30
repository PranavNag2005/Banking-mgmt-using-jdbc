import java.sql.*;
public class Dbconn{
private static final String url="jdbc:mysql://localhost:3306/Bankingsystem";
private static final String user="root";
private static final String pass="root";

public static Connection getConnection(){
    try{
        return DriverManager.getConnection(url,user,pass);
    }
    catch(SQLException e){
    System.out.println("Database connection failed");
    return null;
    }
}

}