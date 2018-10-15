package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
        String email = request.getParameter( "email" );
        String password1 = request.getParameter( "password1" );
        String password2 = request.getParameter( "password2" );
        if (email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            throw new LoginSampleException("Fields missing input");
        }
        if (!email.contains("@")) {
            throw new LoginSampleException("Invalid email");
        }
        if (password1.length() < 4) {
            throw new LoginSampleException("Mimimum password lengh is 4. Yours was: " + password1.length());
        }
        if ( password1.equals( password2 ) ) {
            User user = LogicFacade.createUser( email, password1 );
            HttpSession session = request.getSession();
            session.setAttribute( "email", email );
            session.setAttribute( "user", user );
            session.setAttribute( "role", user.getRole() );
            session.setAttribute("id", user.getId());
            session.setAttribute("OrderList", LogicFacade.listOfUsersOrders(String.valueOf(user.getId())));
            return user.getRole() + "page";
        } else {
            throw new LoginSampleException( "the two passwords did not match" );
        }
    }

}
