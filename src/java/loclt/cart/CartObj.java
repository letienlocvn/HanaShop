/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.cart;

import java.io.Serializable;
import java.util.HashMap;
import loclt.product.ProductDTO;

/**
 *
 * @author WIN
 */
public class CartObj implements Serializable {

    private String customerName;
    private HashMap<String, ProductDTO> cart;

    public CartObj() {
        this.cart = new HashMap<>();
    }
    public CartObj(String customer) {
        this.cart = new HashMap<>();
        this.customerName = customer;
    }

    public CartObj(String customerName, HashMap<String, ProductDTO> cart) {
        this.customerName = customerName;
        this.cart = cart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public void addProductToCart(ProductDTO product) {
        String productID = product.getProductID();
        if (this.cart.containsKey(productID)) {
            int newQuantity = this.cart.get(productID).getQuantity() + 1;
            this.cart.get(productID).setQuantity(newQuantity);
        } else {
            this.cart.put(productID, product);
        }
    }

    public float getTotal() {
        float result = 0;
        result = this.cart.values().stream().map((dto) -> dto.getQuantity() * dto.getPrice()).reduce(result, (accumulator, _item) -> accumulator + _item);
        return result;
    }

    public void removeCart(String title) {
        if (this.cart.containsKey(title)) {
            this.cart.remove(title);
        }
    }

    public void updateCart(String title, int quantity) {
        if (this.cart.containsKey(title)) {
            this.cart.get(title).setQuantity(quantity);
        }
    }
}
