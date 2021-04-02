/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import loclt.product.ProductDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author WIN
 */
public class UpdateServlet extends HttpServlet {

    private final String updateErr = "updateErr.html";
    private final String updateSuccess = "ManagementServlet";
    static final Logger LOGGER = Logger.getLogger(UpdateServlet.class.getName());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = updateErr;
        try {
            String productID = request.getParameter("txtProductID");
            String productName = request.getParameter("txtProductName");
            String txtQuantity = request.getParameter("txtQuantity");
            int quantity = Integer.parseInt(txtQuantity);
            String txtPrice = request.getParameter("txtPrice");
            float price = Float.parseFloat(txtPrice);
//            String date = request.getParameter("txtDate");
            String status = request.getParameter("cbStatus");
            boolean changeStatus = true;
            if (status.equals("true")) {
                changeStatus = true;
            } else if (status.equals("false")) {
                changeStatus = false;
            }
            String getCateID = request.getParameter("cbbox");
            String cateID = getCateID.substring(0, 3);
            String description = request.getParameter("txtDescription");
            String adminUpdate = request.getParameter("txtUsername");

            ProductDAO productDAO = new ProductDAO();
            boolean notNameProduct = productName.trim().isEmpty();

            if (!notNameProduct) {
                boolean result = productDAO.updateProduct(productID, productName, cateID, quantity, price, changeStatus, description);
                if (result) {
                    url = updateSuccess;
                    productDAO.lastUserAndDate(productID, adminUpdate);
                }
            }
        } catch (SQLException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at UpdateServlet: " + e.getMessage());
        } catch (NamingException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at UpdateServlet_JNDI:: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
