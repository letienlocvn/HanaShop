/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.category;

import java.io.Serializable;

/**
 *
 * @author WIN
 */
public class CategoryDTO implements Serializable {

    private String cateID, cateName, description;

    public CategoryDTO() {
    }

    public CategoryDTO(String cateID, String cateName, String description) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.description = description;
    }

    public CategoryDTO(String cateID,String cateName) {
        this.cateID = cateID;
        this.cateName = cateName;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
