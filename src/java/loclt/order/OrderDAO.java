/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import loclt.product.ProductDAO;
import loclt.product.ProductDTO;
import loclt.utils.DBUtils;

/**
 *
 * @author WIN
 */
public class OrderDAO implements Serializable {

    public List<OrderDTO> searchHistory(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> list = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select OrderID, Username, Total, DateOfCreate, Status from tblOrders  "
                        + "where (DATEPART(dd, DateOfCreate) like ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String orderID = rs.getString("OrderID");
                    String username = rs.getString("username");
                    float total = rs.getFloat("Total");
                    Date date = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    OrderDTO dto = new OrderDTO(orderID, username, total, date, status);
                    list.add(dto);
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
        return list;
    }

    public int checkQuantityProduct(String productID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int number = -1;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select Quantity from tblProducts where productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    number = rs.getInt("Quantity");
                    return number;
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
        return number;
    }

    public String getLastOrderIDByUser(String username) throws NamingException, SQLException {
        String orderID = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select OrderID from tblOrders "
                        + "Where DateOfCreate =(Select MAX(DateOfCreate) "
                        + "From tblOrders where username = ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    orderID = rs.getString("OrderID");
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
        return orderID;
    }

    public boolean createOrder(String orderID, String nameUser, float total, Date dateOfCreate, boolean status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert into tblOrders (OrderID, Username, Total, DateOfCreate, Status) "
                        + "values (?,?,?,?,?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, nameUser);
                stm.setFloat(3, total);
                stm.setTimestamp(4, new Timestamp(dateOfCreate.getTime()));
                stm.setBoolean(5, status);
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
    
    public boolean updateOrder(String orderID, boolean status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update tblOrders set OrderID = ?, Status = ? where OrderID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setBoolean(2, status);
                stm.setString(3, orderID);
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

    public boolean createOrderDetail(String orderDetailID, String orderID, String productID, int quantity, float price) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert into tblOrderDetails (OrderDetailsID, OrderID, ProductID, Quantity, Price) "
                        + "values (?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderDetailID);
                stm.setString(2, orderID);
                stm.setString(3, productID);
                stm.setInt(4, quantity);
                stm.setFloat(5, price);
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

 
    
    public void updateQuantity(String productID, int DBquantity, int orderQuantity) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update tblProducts set quantity = ? where productID = ?";
                stm = con.prepareStatement(sql);
                int quantityThenUpdate = DBquantity - orderQuantity;
                stm.setInt(1, quantityThenUpdate);
                stm.setString(2, productID);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<OrderDTO> getListOrder(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        List<OrderDTO> listOrder = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select OrderID, Username, Total, DateOfCreate, Status from tblOrders "
                        + " where username = ? "
                        + " ORDER BY DateOfCreate DESC";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("OrderID");
                    String usernameOder = rs.getString("Username");
                    float total = rs.getFloat("Total");
                    Date dateOfCreate = rs.getDate("DateOfCreate");
                    boolean status = rs.getBoolean("Status");
                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }
                    OrderDTO oder = new OrderDTO(orderID, usernameOder, total, dateOfCreate, status);
                    listOrder.add(oder);
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
        return listOrder;
    }

    public List<OrderDetailsDTO> getListOrderDetails(String orderID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        List<OrderDetailsDTO> listOrderDetails = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                ProductDAO productDAO = new ProductDAO();
                String sql = "Select OrderDetailsID, OrderID, ProductID, ProductID, Quantity, Price from tblOrderDetails"
                        + " where orderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderDetailsID = rs.getString("OrderDetailsID");
                    orderID = rs.getString("OrderID");
                    String productID = rs.getString("ProductID");
                    ProductDTO product = productDAO.findPrimaryKey(productID);
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    if (listOrderDetails == null) {
                        listOrderDetails = new ArrayList<>();
                    }
                    OrderDetailsDTO oderDetails = new OrderDetailsDTO(orderDetailsID, orderID, product, quantity, price);
                    listOrderDetails.add(oderDetails);
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
        return listOrderDetails;

    }

    public float getTotalByOrderID(String orderID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select Total from tblOrders where OrderID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    float total = rs.getFloat("Total");
                    return total;
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
