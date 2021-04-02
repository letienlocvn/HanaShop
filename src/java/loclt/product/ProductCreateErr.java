/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.product;

import java.io.Serializable;

/**
 *
 * @author WIN
 */
public class ProductCreateErr implements Serializable {
    private String productIdErr;
    private String productNameErr;
    private String productPriceErr;
    private String ProductQuantityErr;
    

    public ProductCreateErr() {
    }

    public ProductCreateErr(String productIdErr, String productNameErr, String productPriceErr, String ProductQuantityErr) {
        this.productIdErr = productIdErr;
        this.productNameErr = productNameErr;
        this.productPriceErr = productPriceErr;
        this.ProductQuantityErr = ProductQuantityErr;
    }


    public String getProductIdErr() {
        return productIdErr;
    }

    public void setProductIdErr(String productIdErr) {
        this.productIdErr = productIdErr;
    }

    public String getProductNameErr() {
        return productNameErr;
    }

    public void setProductNameErr(String productNameErr) {
        this.productNameErr = productNameErr;
    }

    public String getProductPriceErr() {
        return productPriceErr;
    }

    public void setProductPriceErr(String productPriceErr) {
        this.productPriceErr = productPriceErr;
    }

    public String getProductQuantityErr() {
        return ProductQuantityErr;
    }

    public void setProductQuantityErr(String ProductQuantityErr) {
        this.ProductQuantityErr = ProductQuantityErr;
    }
    
    
}
