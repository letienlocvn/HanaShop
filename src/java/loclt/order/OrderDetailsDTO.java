/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.order;

import java.io.Serializable;
import loclt.product.ProductDTO;

/**
 *
 * @author WIN
 */
public class OrderDetailsDTO implements Serializable {
    private String orderDetailsID;
    private String orderID;
    private ProductDTO product;
    private int quantity;
    private float price; 

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderDetailsID, String orderID, ProductDTO product, int quantity, float price) {
        this.orderDetailsID = orderDetailsID;
        this.orderID = orderID;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderDetailsID() {
        return orderDetailsID;
    }

    public void setOrderDetailsID(String orderDetailsID) {
        this.orderDetailsID = orderDetailsID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
    
}
