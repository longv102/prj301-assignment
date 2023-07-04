/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvu.orderdetail;

import java.io.Serializable;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class OrderDetailDTO implements Serializable {
    
    private int orderDetailId;
    private int orderId;
    private String productId;
    private double price;
    private int quantity;

    public OrderDetailDTO() {
        this.orderDetailId = 1;
        this.orderId = 1;
        this.productId = "";
        this.price = 0;
        this.quantity = 0;
    }

    public OrderDetailDTO(int orderDetailId, 
            int orderId, String productId, double price, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
