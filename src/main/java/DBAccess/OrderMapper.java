package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of UserMapper is to...
 *
 * @author kasper
 */
public class OrderMapper {

    public static void createOrder(Order order) throws LoginSampleException {
        try {
            int legoHusId = checkLegoHus(order);
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Orders (Users_id, LegoHus_id) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(order.getId()));
            ps.setString(2, String.valueOf(legoHusId));
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            order.setId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static int insertLegoHus(Order order) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO LegoHus (length, width, height) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(order.getLength()));
            ps.setString(2, String.valueOf(order.getWidth()));
            ps.setString(3, String.valueOf(order.getHeight()));
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            return id;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static int checkLegoHus(Order order) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `id` FROM LegoHus WHERE length = ? AND width = ? AND height = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, String.valueOf(order.getLength()));
            ps.setString(2, String.valueOf(order.getWidth()));
            ps.setString(3, String.valueOf(order.getHeight()));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return insertLegoHus(order);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new LoginSampleException(e.getMessage() + " TEST");
        }

    }

    public static List<Order> getOrderList(String id) throws LoginSampleException {
        List<Order> listOfOrders = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM Orders join LegoHus WHERE Users_id= ? AND LegoHus_id = id order by order_id";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int length = Integer.valueOf(rs.getString("length"));
                int width = Integer.valueOf(rs.getString("width"));
                int height = Integer.valueOf(rs.getString("height"));
                int orderId = Integer.valueOf(rs.getString("order_id"));
                String status = rs.getString("order_status");
                listOfOrders.add(new Order(length, width, height, orderId, status));
            }
            return listOfOrders;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage() + listOfOrders.size());
        }
    }

    public static Order getOrder(String id) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM Orders join LegoHus WHERE order_id= ? AND LegoHus_id = id";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int length = Integer.valueOf(rs.getString("length"));
                int width = Integer.valueOf(rs.getString("width"));
                int height = Integer.valueOf(rs.getString("height"));
                int orderId = Integer.valueOf(id);
                Order order = new Order(length, width, height, orderId);
                return order;
            } else {
                throw new LoginSampleException("Ingen ordre valgt");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static List<Order> getAllOrders() throws LoginSampleException {
        List<Order> listOfOrders = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM Orders join LegoHus WHERE LegoHus_id = id order by order_id";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int length = Integer.valueOf(rs.getString("length"));
                int width = Integer.valueOf(rs.getString("width"));
                int height = Integer.valueOf(rs.getString("height"));
                int orderId = Integer.valueOf(rs.getString("order_id"));
                String status = rs.getString("order_status");
                listOfOrders.add(new Order(length, width, height, orderId, status));
            }
            return listOfOrders;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static void changeOrders(String[] orders) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            PreparedStatement ps;
            for (String order : orders) {
                String SQL = "UPDATE Orders SET order_status='Sent' WHERE order_id = ?";
                ps = con.prepareStatement(SQL);
                ps.setString(1, String.valueOf(order));
                if (ps == null) {
                    throw new LoginSampleException("no orders to update");
                }
                ps.execute();
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

    }

}
