import java.sql.*;
public class user {
    public static void createuser( String name,String password , String acccoutnumber){
        try{
            Connection conn=Dbconn.getConnection();
String userquery="insert into users(name,password,accountnumber) values (?,?,?)";
PreparedStatement pstmt=conn.prepareStatement(userquery);
pstmt.setString(1, name);
pstmt.setString(2, password);
pstmt.setString(3,acccoutnumber);
pstmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static int login(String name,String password){
         Connection conn=Dbconn.getConnection();
String checkquery="select id from users where name=? and password=?";
try {
    PreparedStatement pt=conn.prepareStatement(checkquery);
    pt.setString(1, name);
    pt.setString(2, password);
    ResultSet rs=pt.executeQuery();
    if(rs.next()){
        return rs.getInt("id");
    }
} catch (SQLException e) {
   
    e.printStackTrace();
}return -1;

    }
}
