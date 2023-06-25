/*
 * PRJ301_Assignment
 * Long Vu  * 
 */
package longvu.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class DBHelpers implements Serializable {
    
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "12345";
    
    public static Connection makeConnection() 
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433"
                + ";databaseName=ShoppingStore";
        con = DriverManager.getConnection(url, USERNAME, PASSWORD);
        return con;
    }
}
