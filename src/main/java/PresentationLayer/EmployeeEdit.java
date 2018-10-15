/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mark
 */
public class EmployeeEdit extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        try {
            String[] getCheckedOrders = request.getParameterValues("checkedorder");
            if (getCheckedOrders == null) {
                throw new LoginSampleException("No orders selected.");
            }
            LogicFacade.changeOrderStatus(getCheckedOrders);

            List<Order> list = LogicFacade.listOfAllOrders();
            request.setAttribute("emporderlist", list);
            return request.getSession().getAttribute("role") + "page";
            
        } catch (LoginSampleException e) {
            throw new LoginSampleException(e.getMessage());
        }

    }

}
