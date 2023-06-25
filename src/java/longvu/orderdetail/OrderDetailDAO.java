/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvu.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import longvu.utils.DBHelpers;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class OrderDetailDAO implements Serializable {

    private final String SQL_INSERT = "INSERT INTO OrderDetail"
            + "(Price, Quantity, OrderID, ProductID, Status) "
            + "VALUES (?, ?, ?, ?, ?)";
    
    public boolean insert(OrderDetailDTO orderDetail) 
            throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_INSERT);
                stm.setDouble(1, orderDetail.getPrice());
                stm.setInt(2, orderDetail.getQuantity());
                stm.setInt(3, orderDetail.getOrderId());
                stm.setString(4, orderDetail.getProductId());
                stm.setBoolean(5, true);
                int value = stm.executeUpdate();
                if (value > 0)
                    check = true;
            }
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return check;
    }
}
