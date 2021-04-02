/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import loclt.category.CategoryDAO;
import loclt.category.CategoryDTO;
import loclt.utils.DBUtils;

/**
 *
 * @author WIN
 */
public class ProductDAO implements Serializable {

    List<ProductDTO> listProduct;

    public List<ProductDTO> getListProduct() {
        return listProduct;
    }

    public ProductDTO findPrimaryKey(String productID) throws NamingException, SQLException{
        Connection con = null; 
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO dto = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null){
                String sql = "Select ProductName, Quantity, Price, DateOfCreate, Status, CategoryID, Description, Image "
                        + "from tblProducts where productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                CategoryDAO dao = new CategoryDAO();
                while (rs.next()) {                    
                    String productName = rs.getString("ProductName");
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    String cateID = rs.getString("CategoryID");
                    CategoryDTO cateDTO = dao.findCategory(cateID);
                    String description = rs.getString("Description");
                    String images = rs.getString("Image");
                    dto = new ProductDTO(productID, productName, cateDTO, description, quantity, price, date, status, images);                    
                }
            }
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return dto;
    }
    
    public boolean createProduct(String id, String productName, String cateID,
            int quantity, float price, boolean status, String description) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert into tblProducts (ProductID, ProductName, CategoryID, Quantity, Price, Status, Description, DateOfCreate) "
                        + " values(?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, productName);
                stm.setString(3, cateID);
                stm.setInt(4, quantity);
                stm.setFloat(5, price);
                stm.setBoolean(6, status);
                stm.setString(7, description);
                stm.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }

    public boolean updateProduct(String id, String productName, String txtCateID, int quantity, float price, boolean status, String description) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                System.out.println("Description: " + description);
                String sql = "Update tblProducts set "
                        + "productName = ?, CategoryID = ?, quantity = ?, price = ?, status = ? , description = ? "
                        + "where productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, productName);
                stm.setString(2, txtCateID);
                stm.setInt(3, quantity);
                stm.setFloat(4, price);
                stm.setBoolean(5, status);
                stm.setString(6, description);
                stm.setString(7, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }
        
    public boolean updateImageProduct(String id, String image) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update tblProducts set "
                        + "image = ? "
                        + "where productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, image);
                stm.setString(2, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    } 
    
    public boolean createImageProduct(String id, String image) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert into tblProducts (ProductID,Image) "
                        + "values (?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, image);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }
    
    public boolean setActiveProduct(String ID, boolean isChange) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update tblProducts set status = ? where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(2, ID);
                stm.setBoolean(1, isChange);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }

    public boolean lastUserAndDate(String productID, String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update tblProducts set LastUpdate = ? , UserUpdate = ? where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                stm.setString(2, username);
                stm.setString(3, productID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }

    public List<ProductDTO> getProductByID(String id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CategoryDAO cateDAO = new CategoryDAO();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select ProductID, ProductName, Quantity, Price, DateOfCreate, Status, CategoryID, Description, Image "
                        + "from tblProducts where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productName = rs.getString("ProductName");
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    String categoryID = rs.getString("CategoryID");
                    CategoryDTO cateDTO = cateDAO.findCategory(categoryID);
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    ProductDTO dto = new ProductDTO(productID, productName, cateDTO, description, quantity, price, date, status, image);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<>();
                    }
                    this.listProduct.add(dto);
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
        return listProduct;
    }

    
    
    public int getAllProductADMIN() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select COUNT(ProductID) from tblProducts";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int product = rs.getInt(1);
                    return product;
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
        return -1;
    }

    public List<ProductDTO> getAllProductByDate(CategoryDAO cateDAO, int pageIndex, int productInPage) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select ProductID, ProductName, Quantity, Price, DateOfCreate, Status, CategoryID, Description, Image "
                        + "from "
                        + "(Select ROW_NUMBER() over (order by DateOfCreate DESC) as r,\n"
                        + "* from tblProducts) as x \n"
                        + "where r between (?-1)*?+1 and ?*?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, pageIndex);
                stm.setInt(2, productInPage);
                stm.setInt(3, pageIndex);
                stm.setInt(4, productInPage);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productName = rs.getString("ProductName");
                    String cateID = rs.getString("CategoryID");
                    CategoryDTO cateDTO = cateDAO.findCategory(cateID);
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    ProductDTO dto = new ProductDTO(productID, productName, cateDTO, description, quantity, price, date, status, image);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<>();
                    }
                    this.listProduct.add(dto);
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
        return listProduct;
    }

    public void searchProduct(String searchValue, String priceFrom, String priceTo) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CategoryDAO cateDAO = new CategoryDAO();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select ProductID, ProductName, Quantity, Price, DateOfCreate, Status, CategoryID, Description, Image "
                        + "from tblProducts where ProductName like ? and price >= ? and price <= ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, priceFrom);
                stm.setString(3, priceTo);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productName = rs.getString("ProductName");
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    String categoryID = rs.getString("CategoryID");
                    CategoryDTO cateDTO = cateDAO.findCategory(categoryID);
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    ProductDTO dto = new ProductDTO(productID, productName, cateDTO, description, quantity, price, date, status, image);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<>();
                    }
                    this.listProduct.add(dto);
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
    }

    public int getAllTotalProduct() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select count(productID) as ID\n"
                        + "from tblProducts\n"
                        + "where Quantity > 0 and Status = 1";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int tolalOfProduct = rs.getInt(1);
                    return tolalOfProduct;
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
        return -1;
    }

    public List<ProductDTO> getPageSizeOfProduct(int pageIndex, int maxPageSize)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProductInPageSize = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select ProductID, ProductName, Quantity, Price, DateOfCreate, "
                        + "Status, CategoryID, Description, Image "
                        + "from "
                        + "(Select ROW_NUMBER() over (order by DateOfCreate DESC) as r,\n"
                        + "* from tblProducts where Quantity > 0 and Status =1) as x \n"
                        + "where r between (?-1)*?+1 and ?*?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, pageIndex);
                stm.setInt(2, maxPageSize);
                stm.setInt(3, pageIndex);
                stm.setInt(4, maxPageSize);
                rs = stm.executeQuery();
                CategoryDAO cateDAO = new CategoryDAO();
                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productName = rs.getString("ProductName");
                    String cateID = rs.getString("CategoryID");
                    CategoryDTO cateDTO = cateDAO.findCategory(cateID);
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    ProductDTO dto = new ProductDTO(productID, productName, cateDTO, description, quantity, price, date, status, image);
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

    public List<ProductDTO> getPageSizeOfProductBySearch(String searchValue, String priceFrom,
            String priceTo, int pageIndex, int maxPageSize)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProductInPageSize = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select ProductID, ProductName, Quantity, Price, DateOfCreate, "
                        + "Status, CategoryID, Description, Image "
                        + "from "
                        + "(Select ROW_NUMBER() over (order by DateOfCreate DESC) as r,\n"
                        + "* from tblProducts where ProductName like ? and price >= ? and price <= ? and "
                        + "Quantity > 0 and Status =1) as x \n"
                        + "where r between (?-1)*?+1 and ?*?";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, priceFrom);
                stm.setString(3, priceTo);
                stm.setInt(4, pageIndex);
                stm.setInt(5, maxPageSize);
                stm.setInt(6, pageIndex);
                stm.setInt(7, maxPageSize);
                rs = stm.executeQuery();
                CategoryDAO cateDAO = new CategoryDAO();
                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productName = rs.getString("ProductName");
                    String cateID = rs.getString("CategoryID");
                    CategoryDTO cateDTO = cateDAO.findCategory(cateID);
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    ProductDTO dto = new ProductDTO(productID, productName, cateDTO, description, quantity, price, date, status, image);
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

    public int getAllProductBySearch(String search, String priceFrom, String priceTo)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select count(productID) as ID\n"
                        + "from tblProducts\n"
                        + "where ProductName like ? and price >= ? and price <= ? and "
                        + "Quantity > 0 and Status = 1";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, priceFrom);
                stm.setString(3, priceTo);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int tolalOfProduct = rs.getInt(1);
                    return tolalOfProduct;
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
        return -1;
    }


    /*Lien Quan den Category*/

 /* public List<ProductDTO> getAllProductByCateID(CategoryDAO cateDAO, String cateID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select ProductID, ProductName, Quantity, Price, DateOfCreate, Status, CategoryID, Description, Image "
                        + "from tblProducts where CategoryID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, cateID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productName = rs.getString("ProductName");
                    CategoryDTO cateDTO = cateDAO.findCategory(cateID);
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    ProductDTO dto = new ProductDTO(productID, productName, cateDTO, description, quantity, price, date, status, image);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<>();
                    }
                    this.listProduct.add(dto);
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
        return listProduct;
    }*/
    public List<ProductDTO> getPageSizeInCategory(int pageIndex, int maxPageSize, String cate)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProductInPageSize = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select ProductID, ProductName, Quantity, Price, DateOfCreate, "
                        + "Status, CategoryID, Description, Image "
                        + "from "
                        + "(Select ROW_NUMBER() over (order by DateOfCreate DESC) as r,\n"
                        + "* from tblProducts "
                        + "where categoryID = ? and Quantity > 0 and Status =1) as x \n"
                        + "where r between (?-1)*?+1 and ?*?";

                stm = con.prepareStatement(sql);
                stm.setString(1, cate);
                stm.setInt(2, pageIndex);
                stm.setInt(3, maxPageSize);
                stm.setInt(4, pageIndex);
                stm.setInt(5, maxPageSize);
                rs = stm.executeQuery();
                CategoryDAO cateDAO = new CategoryDAO();
                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productName = rs.getString("ProductName");
                    String cateID = rs.getString("CategoryID");
                    CategoryDTO cateDTO = cateDAO.findCategory(cateID);
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    ProductDTO dto = new ProductDTO(productID, productName, cateDTO, description, quantity, price, date, status, image);
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

    public int getTotalProductInCategory(String cateID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select COUNT(CategoryID) "
                        + "from tblProducts "
                        + "where CategoryID = ? and Quantity > 0 and Status = 1";
                stm = con.prepareStatement(sql);
                stm.setString(1, cateID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int productInCategory = rs.getInt(1);
                    System.out.println("Gia tri cua Product In Category: " + productInCategory);
                    return productInCategory;
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
        return -1;
    }

}
