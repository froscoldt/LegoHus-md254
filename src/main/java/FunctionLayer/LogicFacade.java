package FunctionLayer;

import DBAccess.OrderMapper;
import DBAccess.UserMapper;
import java.util.List;

/**
 * The purpose of LogicFacade is to...
 *
 * @author kasper
 */
public class LogicFacade {

    public static User login(String email, String password) throws LoginSampleException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String password) throws LoginSampleException {
        User user = new User(email, password, "customer");
        UserMapper.createUser(user);
        return user;
    }

    public static Order createOrder(int length, int width, int height, int id) throws LoginSampleException{
        Order order = new Order(length, width, height, id);
        OrderMapper.createOrder(order);
        return order;
    }
    
    public static List<Order> listOfUsersOrders(String id) throws LoginSampleException {
        List<Order> Orders = OrderMapper.getOrderList(id);
        return Orders;
    }

    public static PieceList givePieceList(String id) throws LoginSampleException {
        OrderToPieceList otpl = new OrderToPieceList();
        return otpl.makePieceList(OrderMapper.getOrder(id));
        
    }
    
    public static List<Order> listOfAllOrders() throws LoginSampleException {
        return OrderMapper.getAllOrders();
    
    }
    
    public static void changeOrderStatus(String[] chosenOrders) throws LoginSampleException {
        OrderMapper.changeOrders(chosenOrders);
    } 

}
