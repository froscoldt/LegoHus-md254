package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of Login is to...
 *
 * @author kasper
 */
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = LogicFacade.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("email", email);
        session.setAttribute("role", user.getRole());
        session.setAttribute("id", user.getId());
        if (user.getRole().equalsIgnoreCase("customer")) {
            session.setAttribute("OrderList", LogicFacade.listOfUsersOrders(String.valueOf(user.getId())));
        }
        if ("employee".equals(user.getRole())) {
            List<Order> list = LogicFacade.listOfAllOrders();
            request.setAttribute("emporderlist", list);
            
        }
        return user.getRole() + "page";
    }

}
