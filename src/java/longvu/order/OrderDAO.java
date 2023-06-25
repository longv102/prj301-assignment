/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvu.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import longvu.utils.DBHelpers;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class OrderDAO implements Serializable {

    private final String SQL_INSERT = "INSERT INTO Orders"
            + "(OrderDate, Total, Username, Status) "
            + "VALUES(?, ?, ?, ?)";

    /*public boolean insert(OrderDTO order) 
            throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                stm.setTimestamp(1, order.getOrderDate());
                stm.setDouble(2, order.getTotal());
                stm.setString(3, order.getUsername());
                stm.setBoolean(4, true);
                int value = stm.executeUpdate();
                if (value > 0) {
                    check = true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return check;
    }*/
    
    public int insert(OrderDTO order)
            throws SQLException, ClassNotFoundException {
        int orderId = -1;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                stm.setTimestamp(1, order.getOrderDate());
                stm.setDouble(2, order.getTotal());
                stm.setString(3, order.getUsername());
                stm.setBoolean(4, true);
                int affectedRows = stm.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Failed to insert order, no rows affected.");
                }

                try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Failed to retrieve ID of inserted order, no ID obtained.");
                    }
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderId;
    }

}
