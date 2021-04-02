/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.product;

import java.io.Serializable;
import java.util.Date;
import loclt.category.CategoryDTO;

/**
 *
 * @author WIN
 */
public class ProductDTO implements Serializable {

    private String productID, productName, description;
    private int quantity;
    private float price;
    private Date datime;
    private boolean status;
    private String images;
    private CategoryDTO cateDTO;
    private String userUpdate;
    private Date lastDatetime;
    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, CategoryDTO cateDTO, String description, int quantity, float price, Date datime, boolean status, String images) {
        this.productID = productID;
        this.productName = productName;
        this.cateDTO = cateDTO;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.datime = datime;
        this.status = status;
        this.images = images;
    }

    public ProductDTO(String productID, String username, Date lastUpdate) {
        this.productID = productID;
        this.userUpdate = username;
        this.lastDatetime = lastUpdate;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getDatime() {
        return datime;
    }

    public void setDatime(Date datime) {
        this.datime = datime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public CategoryDTO getCateDTO() {
        return cateDTO;
    }

    public void setCateDTO(CategoryDTO cateDTO) {
        this.cateDTO = cateDTO;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", productName=" + productName + ", description=" + description + ", quantity=" + quantity + ", price=" + price + ", datime=" + datime + ", status=" + status + ", images=" + images + ", cateDTO=" + cateDTO + '}';
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getLastDatetime() {
        return lastDatetime;
    }

    public void setLastDatetime(Date lastDatetime) {
        this.lastDatetime = lastDatetime;
    }

    

    
}
