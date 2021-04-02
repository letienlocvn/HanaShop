/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import loclt.cart.CartObj;
import loclt.order.OrderDAO;
import loclt.product.ProductDTO;
import loclt.users.UserDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author WIN
 */
public class OrderProductServlet extends HttpServlet {

    private final String historyPage = "FirstServlet";
    private final String outStockPage = "outStockPage.html";
    static final Logger LOGGER = Logger.getLogger(OrderProductServlet.class.getName());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = historyPage;
        try {
            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("CUSTCART");
            UserDTO user = (UserDTO) session.getAttribute("USER");
            String nameUser = user.getUsername();
            float orderTotal = cart.getTotal();
            OrderDAO orderDAO = new OrderDAO();
            //?
            String lastOrderID = orderDAO.getLastOrderIDByUser(nameUser);
            String uniqueID = UUID.randomUUID().toString();
            String orderID;
            if (lastOrderID == null) {
                orderID = "OD-" + nameUser + "-" + uniqueID;
            } else {
                orderID = "OD-" + nameUser + "-" + uniqueID;
            }

            boolean result = orderDAO.createOrder(orderID, nameUser, orderTotal, new Date(), true);

            //Check Quantity Of Product In Database
            if (result) {
                int count = 0;
                for (ProductDTO product : cart.getCart().values()) {
                    String productID = product.getProductID();
                    int quantity = product.getQuantity();
                    int checkQuantityInProduct = orderDAO.checkQuantityProduct(productID);
                    float priceOfProduct = product.getPrice();
                    String orderDetailID = orderID + "-" + count++;
                   
                    if (quantity > checkQuantityInProduct) {                      
                        boolean updateResult = orderDAO.updateOrder(orderID, false);
                        if (updateResult) {
                            url = outStockPage;
                        }
                        return;
                    } else {
                        boolean result2 = orderDAO.createOrderDetail(orderDetailID, orderID, productID, quantity, priceOfProduct);
                        if (result2) {
                            orderDAO.updateQuantity(productID, checkQuantityInProduct, quantity);
                            url = historyPage;
                            session.removeAttribute("CUSTCART");
                        }

                    }
                }
            }
        } catch (NumberFormatException | SQLException | NamingException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at OrderProductServlet: "+e.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
