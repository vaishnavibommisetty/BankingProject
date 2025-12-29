import java.util.Scanner;

public class Register {

    public static void registerUser() {
        Scanner sc = new Scanner(System.in);
        BankDAO dao = new BankDAO();

        System.out.print("Username: ");
        String u = sc.nextLine();

        System.out.print("Password: ");
        String p = sc.nextLine();

        System.out.print("Full Name: ");
        String n = sc.nextLine();

        System.out.print("Email: ");
        String e = sc.nextLine();

        if (dao.register(u, p, n, e))
            System.out.println("Account Created Successfully");
        else
            System.out.println("Account Creation Failed");
    }
}
