package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.PieceList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mark
 */
public class Basket extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        try {
            try {
                Integer.valueOf(request.getParameter("length"));
                Integer.valueOf(request.getParameter("width"));
                Integer.valueOf(request.getParameter("height"));
            } catch (NumberFormatException e) {
                throw new LoginSampleException("Values are not numbers");
            }
            
            int length = Integer.valueOf(request.getParameter("length"));
            int width = Integer.valueOf(request.getParameter("width"));
            int height = Integer.valueOf(request.getParameter("height"));
            
            if (height < 1) {throw new LoginSampleException("Height has to be a minimum of 1 height");}
            if (length < 4) {throw new LoginSampleException("Value too low. Length has to be minimum 4 dots");}
            if (width < 4) {throw new LoginSampleException("Value too low. Width has to be minimum 4 dots");}
            if (length > 1000) { throw new LoginSampleException("Length exceeded 1000");}
            if (width > 1000) {throw new LoginSampleException("Width exceeded 1000");}
            if (height > 1000) {throw new LoginSampleException("Height exceeded 1000");}

            int id = (int) request.getSession().getAttribute("id");
            Order order = LogicFacade.createOrder(length, width, height, id);
            request.getSession().setAttribute("OrderList", LogicFacade.listOfUsersOrders(String.valueOf(id)));
            PieceList plist = LogicFacade.givePieceList(String.valueOf(order.getId()));
            request.getSession().setAttribute("pl", plist);
            return request.getSession().getAttribute("role") + "page";
        } catch (LoginSampleException | NumberFormatException e) {
            throw new LoginSampleException(e.getMessage());
        }
    }

}
