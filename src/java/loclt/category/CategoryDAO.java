/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import loclt.utils.DBUtils;

/**
 *
 * @author WIN
 */
public class CategoryDAO implements Serializable {

    List<CategoryDTO> listCategory;

    public List<CategoryDTO> getListCategory() {
        return listCategory;
    }

    public List<CategoryDTO> getCategory() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select CategoryID, CategoryName, Description from tblCategories";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String cateID = rs.getString("CategoryID");
                    String cateName = rs.getString("CategoryName");
                    String description = rs.getString("Description");
                    CategoryDTO dto = new CategoryDTO(cateID, cateName, description);
                    if (this.listCategory == null) {
                        this.listCategory = new ArrayList<>();
                    }
                    this.listCategory.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listCategory;
    }

    public int findCategoryID(String cateID) throws NamingException, SQLException {
        getCategory();
        for (int i = 0; i < this.listCategory.size(); i++) {
            if (cateID.equals(this.listCategory.get(i).getCateID())) {
                return i;
            }
        }
        return -1;
    }

    public CategoryDTO findCategory(String cateID) throws NamingException, SQLException {
        int i = findCategoryID(cateID);
        return i < 0 ? null : this.listCategory.get(i);
    }

    public List<CategoryDTO> getNameOfCategory() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select CategoryID,categoryName from tblCategories";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String cateID = rs.getString("CategoryID");
                    String name = rs.getString("categoryName");
                    CategoryDTO dto = new CategoryDTO(cateID, name);
                    if (this.listCategory == null) {
                        this.listCategory = new ArrayList<>();
                    }
                    this.listCategory.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listCategory;
    }

    public List<CategoryDTO> categoryPageSizeOfProduct(int pageIndex, int maxPageSize)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<CategoryDTO> listProductInPageSize = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select *"
                        + "from "
                        + "(Select ROW_NUMBER() over (order by categoryID DESC) as r,\n"
                        + "* from tblCategory) as x \n"
                        + "where r between (?-1)*?+1 and ?*?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, pageIndex);
                stm.setInt(2, maxPageSize);
                stm.setInt(3, pageIndex);
                stm.setInt(4, maxPageSize);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("Description");
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName, description);
                    listProductInPageSize.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listProductInPageSize;
    }

}
