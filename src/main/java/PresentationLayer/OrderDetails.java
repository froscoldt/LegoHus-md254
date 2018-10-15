/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.PieceList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mark
 */
public class OrderDetails extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        try {
            String orderid =  String.valueOf(request.getParameter("chooseorder"));
            if (orderid == null) {
                throw new LoginSampleException("No order selected");
            }
            PieceList plist = LogicFacade.givePieceList(orderid);
            request.getSession().setAttribute("pl", plist);
           return request.getSession().getAttribute("role") + "page";

        } catch (Exception e) {
            throw new LoginSampleException(e.getMessage());
        }

    }

}
