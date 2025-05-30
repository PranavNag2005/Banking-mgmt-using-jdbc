import java.sql.*;
public class Dbconn{
private static final String url="jdbc:mysql://localhost:3306/Bankingsystem";
private static final String user="root";
private static final String pass="root";

public static Connection getConnection(){
    Connection conn=null;
        try{
            conn=DriverManager.getConnection(url,user,pass);
        return conn;
    }
    catch(SQLException e){
    System.out.println("Database connection failed" );
    return null;
    }
}
public static void main(String[] args){
    Connection db=getConnection();
    System.out.println(db);
    if(db!=null){
        System.out.println("connection successfull");
    }
}

}