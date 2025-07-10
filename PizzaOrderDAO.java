import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaOrderDAO {
    private final String DB_URL = "jdbc:mysql://localhost:3306/pizza_order_db";
    private final String USER = "root";
    private final String PASSWORD = "password";

    public void insert(PizzaOrder order) {
        String sql = "INSERT INTO orders (customer_name, mobile_number, pizza_size, num_toppings, total_bill) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, order.getCustomerName());
            stmt.setString(2, order.getMobileNumber());
            stmt.setString(3, order.getPizzaSize());
            stmt.setInt(4, order.getNumToppings());
            stmt.setDouble(5, order.getTotalBill());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PizzaOrder> getAllOrders() {
        List<PizzaOrder> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new PizzaOrder(
                    rs.getInt("id"),
                    rs.getString("customer_name"),
                    rs.getString("mobile_number"),
                    rs.getString("pizza_size"),
                    rs.getInt("num_toppings"),
                    rs.getDouble("total_bill")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PizzaOrder order) {
        String sql = "UPDATE orders SET customer_name=?, mobile_number=?, pizza_size=?, num_toppings=?, total_bill=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, order.getCustomerName());
            stmt.setString(2, order.getMobileNumber());
            stmt.setString(3, order.getPizzaSize());
            stmt.setInt(4, order.getNumToppings());
            stmt.setDouble(5, order.getTotalBill());
            stmt.setInt(6, order.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
