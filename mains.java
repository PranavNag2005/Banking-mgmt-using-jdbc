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
                    sc.nextLine();  // Consume newline
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
                        System.out.println("1. Withdrawal");
                        System.out.println("2. Deposit");
                        System.out.println("3. Transfer");
                        System.out.print("Choose an option: ");
                        int choices = sc.nextInt();
                        
                    } else {
                        System.out.println("Invalid credentials. Try again.");
                    }
                    break;

                // case 3:
                //     System.out.println("Thank you for using our banking system!");
                //     sc.close();
                //     System.exit(0);
                //     break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }}

    