import java.util.Scanner;

public class BankingMenu {

    public static void menu(String user) throws Exception {
        Scanner sc = new Scanner(System.in);
        BankDAO dao = new BankDAO();

        while (true) {
            System.out.println("\n1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.Balance");
            System.out.println("4.Logout");
            System.out.print("Choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Amount: ");
                    dao.deposit(user, sc.nextDouble());
                    System.out.println("Deposit Successful");
                    break;
                case 2:
                    System.out.print("Amount: ");
                    dao.withdraw(user, sc.nextDouble());
                    System.out.println("Withdraw Successful");
                    break;
                case 3:
                    System.out.println("Balance: " + dao.getBalance(user));
                    break;
                case 4:
                    return;
            }
        }
    }
}
