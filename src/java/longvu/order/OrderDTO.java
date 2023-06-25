/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvu.order;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class OrderDTO implements Serializable {
    
    private int orderId;
    private Timestamp orderDate;
    private double total;
    private String username;

    public OrderDTO() {
        this.orderId = 1;
        this.orderDate = Timestamp.from(Instant.now());
        this.total = 0;
        this.username = "";
    }

    public OrderDTO(Timestamp orderDate, double total, String username) {
//        this.orderId = orderId;
        this.orderDate = orderDate;
        this.total = total;
        this.username = username;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
