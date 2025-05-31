import java.sql.*;
import java.util.Scanner;

public class mains {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = Dbconn.getConnection();
       
        // BankService bankService = new BankService(conn);

        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            

            switch (choice) {
                case 1: // Register User
                     // Consume newline
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    System.out.println("Enter 14 digit accoutnnumber");
                    String acccoutnnumber=sc.nextLine();
                    user.createuser(name, password,acccoutnnumber);
                    break;

                case 2: // Login User
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String loginName = sc.nextLine();
                    System.out.print("Enter password: ");
                    String loginPass = sc.nextLine();

                    int userId = user.login(loginName, loginPass);
                    if (userId != -1) {
                        System.out.println("Login successful! Welcome, " + loginName);
                        while (true) {
                            
                        
                        System.out.println("1. Withdrawal");
                        System.out.println("2. Deposit");
                        System.out.println("3. Transfer");
                        System.out.println("4. CheckBalance");
                        System.out.println("5. Logout");
                        System.out.print("Choose an option: ");
                        int choices = sc.nextInt();
                        sc.nextLine();
                        switch(choices){
                            case 1:
                            System.out.print("Enter account number ");
                            String accountnumber=sc.nextLine();
                            sc.nextLine();
                            System.out.print("enter the amount to be withdrawn : ");
                            double amount=sc.nextDouble();
                            // sc.nextLine();
                            operations.withdrawal(accountnumber,amount);
                            break;
                            case 2:
                            System.out.print("Enter the account number ");
                            String accountno=sc.nextLine();
                            
                            System.out.println("Enter the amount to  be deposited ");
                            double amountd=sc.nextDouble();
                            sc.nextLine();
                            operations.Deposit(accountno,amountd);
                            break;
                            case 4:
                            System.out.println("enter the account number ");
                            String acc=sc.nextLine();
                            operations.checkbalance(acc);
                            break;
                            case 5:
                            System.out.println("Thankyou please visit again");
                            break;
                            case 3:
                            System.out.println("Enter the sender account number");
                            String sender_no=sc.nextLine();
                            System.out.print("enter the receiver accout number ");
                            String rec_no=sc.nextLine();
                            System.out.println("Enter the amount to send ");

                            double amounts=sc.nextDouble();
                            operations.transferMoney(sender_no, rec_no, amounts);
                        }
                

                    } }
                    else {
                        System.out.println("Invalid credentials. Try again.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using our banking system!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }}

    