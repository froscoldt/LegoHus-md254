package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 The purpose of Connector is to...

 @author kasper
 */
public class Connector {

    // eventelt indtast din egen DB hvis det ikke dur og brug dbinit til at sætte den op. Bagefter kør dbdummydata
    private static final String URL = "jdbc:mysql://104.248.42.142:3306/mydb";
    private static final String USERNAME = "editor";
    private static final String PASSWORD = "monsterdaase%642";

    private static Connection singleton;

    public static void setConnection( Connection con ) {
        singleton = con;
    }

    public static Connection connection() throws ClassNotFoundException, SQLException {
        if ( singleton == null ) {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            singleton = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return singleton;
    }

}
