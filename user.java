import java.sql.*;
public class user {
    public static void createuser( String name,String password , String acccoutnumber){
        try{
            Connection conn=Dbconn.getConnection();
String userquery="insert into users(name,password,accountnumber) values (?,?,?)";
PreparedStatement pstmt=conn.prepareStatement(userquery,Statement.RETURN_GENERATED_KEYS);
pstmt.setString(1, name);
pstmt.setString(2, password);
pstmt.setString(3,acccoutnumber);
pstmt.executeUpdate();
ResultSet rs=pstmt.getGeneratedKeys();
if(rs.next()){
    int userid=rs.getInt(1);
    String account="insert into accounts(accountnumber,id,balance) values (?,?,?)";
    PreparedStatement accst=conn.prepareStatement(account);
    accst.setString(1,acccoutnumber);
    accst.setInt(2, userid);
    accst.setDouble(3,0.0);
    accst.executeUpdate();
    System.out.println("User registered successfully! Account Number: " + acccoutnumber+" "+name);
}
         else {
            System.out.println("Error: User ID not generated.");
        }

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
