import java.util.Scanner;

public class Login {

    public static void loginUser() throws Exception {
        Scanner sc = new Scanner(System.in);
        BankDAO dao = new BankDAO();

        System.out.print("Username: ");
        String u = sc.nextLine();

        System.out.print("Password: ");
        String p = sc.nextLine();

        if (dao.login(u, p)) {
            System.out.println("Login Successful");
            BankingMenu.menu(u);
        } else {
            System.out.println("Invalid Credentials");
        }
    }
}
