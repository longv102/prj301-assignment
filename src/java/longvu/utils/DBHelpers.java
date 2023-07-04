/*
 * PRJ301_Assignment
 * Long Vu  * 
 */
package longvu.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class DBHelpers implements Serializable {
    
    public static Connection makeConnection() 
            throws NamingException, SQLException {
        Connection con = null;
        Context context = new InitialContext();
        Context end = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) end.lookup("DBConnection");
        con = ds.getConnection();
        return con;
    }
}
