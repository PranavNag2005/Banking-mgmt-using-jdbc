import java.sql.*;

import com.mysql.cj.protocol.Resultset;
public class operations {
 private final static Connection conn=Dbconn.getConnection();
    public static void withdrawal(String accountnumber,double amount){
        String checkbalance="select balance from accounts where accountnumber=?";
        String updatebalance="update accounts set balance=balance-? where accountnumber=?";
        try{
            conn.setAutoCommit(false);
            try(PreparedStatement pt=conn.prepareStatement(checkbalance)){
                pt.setString(1, accountnumber);
                ResultSet rs=pt.executeQuery();
                if(rs.next() && rs.getDouble("balance")>=amount){
                    try(PreparedStatement ptm=conn.prepareStatement(updatebalance)){
                        ptm.setDouble(1, amount);
                        ptm.setString(2, accountnumber);
                        ptm.executeUpdate();
                        System.out.println("with draw successfull");
                        
                    }
                    conn.commit();
                }
                else{
                    System.out.println("Insufficient Balance");
                    conn.rollback();
                }

            }}
            catch(SQLException e){
                e.printStackTrace();
            }

            
        
    }
    public static void Deposit(String accountnumber,double amount){
        String updatedeposit="update accounts set balance=balance+? where accountnumber=?";
        try{
            
            try(PreparedStatement pt=conn.prepareStatement(updatedeposit)){
                
                if(amount>=1){
                    pt.setDouble(1, amount);
                pt.setString(2, accountnumber);
                System.out.println("Executing update: Deposit " + amount + " into account " + accountnumber);
                    pt.executeUpdate();
                  
                    
                    System.out.println("Update successful.");
                      
                }
              
                else{
                    System.out.println("Invalid amount");
                   
                }
            }
        }
        catch(SQLException e){
        e.printStackTrace();
        }
    }
    public static void checkbalance(String accountnumbers){
        String checkbalance="select balance from accounts where accountnumber=?";
        try(PreparedStatement pt=conn.prepareStatement(checkbalance)){
            pt.setString(1, accountnumbers);
            ResultSet rs=pt.executeQuery();
            while (rs.next()) {

                double balance=rs.getDouble("balance");
                System.out.println("Balance was "+balance);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
       
    }
   public static void transferMoney(String senderAccount, String receiverAccount, double amount) {
    String checkBalance = "SELECT balance FROM accounts WHERE accountnumber = ?";
    String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE accountnumber = ?";
    String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE accountnumber = ?";

    try {
        conn.setAutoCommit(false); // Start transaction
        
        // Check sender balance
        try (PreparedStatement checkStmt = conn.prepareStatement(checkBalance)) {
            checkStmt.setString(1, senderAccount);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getDouble("balance") >= amount) {
                
                // Withdraw from sender
                try (PreparedStatement withdrawStmt = conn.prepareStatement(withdrawQuery)) {
                    withdrawStmt.setDouble(1, amount);
                    withdrawStmt.setString(2, senderAccount);
                    withdrawStmt.executeUpdate();
                }
                
                // Deposit into receiver
                try (PreparedStatement depositStmt = conn.prepareStatement(depositQuery)) {
                    depositStmt.setDouble(1, amount);
                    depositStmt.setString(2, receiverAccount);
                    depositStmt.executeUpdate();
                }

                conn.commit(); // Commit if both steps succeed
                System.out.println("Transfer successful!");
                
            } else {
                System.out.println("Insufficient balance.");
                conn.rollback(); // Rollback if balance is insufficient
            }
        }
    } catch (SQLException e) {
        try {
            conn.rollback(); // Rollback on error
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        e.printStackTrace();
    }
}
    

}


