import java.sql.*;

public class BankDAO {

    public boolean register(String user, String pass, String name, String email) {
        try (Connection con = DBUtil.getConnection()) {

            String custSQL = "INSERT INTO customers(username,password,full_name,email) VALUES(?,?,?,?)";
            PreparedStatement ps1 = con.prepareStatement(custSQL);
            ps1.setString(1, user);
            ps1.setString(2, pass);
            ps1.setString(3, name);
            ps1.setString(4, email);
            ps1.executeUpdate();

            String accSQL = "INSERT INTO accounts(customer_id,account_number,balance) " +
                            "VALUES((SELECT customer_id FROM customers WHERE username=?),?,0)";
            PreparedStatement ps2 = con.prepareStatement(accSQL);
            ps2.setString(1, user);
            ps2.setString(2, "ACC" + System.currentTimeMillis());
            ps2.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Registration Failed");
            return false;
        }
    }

    public boolean login(String user, String pass) {
        String sql = "SELECT * FROM customers WHERE username=? AND password=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);
            return ps.executeQuery().next();
        } catch (Exception e) {
            return false;
        }
    }

    public double getBalance(String user) throws Exception {
        String sql = "SELECT balance FROM accounts WHERE customer_id=" +
                     "(SELECT customer_id FROM customers WHERE username=?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        return rs.getDouble(1);
    }

    public void deposit(String user, double amt) throws Exception {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE customer_id=" +
                     "(SELECT customer_id FROM customers WHERE username=?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, amt);
        ps.setString(2, user);
        ps.executeUpdate();
    }

    public void withdraw(String user, double amt) throws Exception {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE customer_id=" +
                     "(SELECT customer_id FROM customers WHERE username=?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, amt);
        ps.setString(2, user);
        ps.executeUpdate();
    }
}
