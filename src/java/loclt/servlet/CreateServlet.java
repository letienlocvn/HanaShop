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
import javax.servlet.http.HttpSession;
import loclt.product.ProductCreateErr;
import loclt.product.ProductDAO;
import loclt.users.UserDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author WIN
 */
public class CreateServlet extends HttpServlet {

    private final String createProductPage = "createProduct.jsp";
    private final String mainPage = "FirstServlet";
    static final Logger LOGGER = Logger.getLogger(CreateServlet.class.getName());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = createProductPage;
        ProductCreateErr errors = new ProductCreateErr();
        String productID = request.getParameter("txtProductID");
        String productName = request.getParameter("txtProductName");
        String cateID = request.getParameter("cbbox");
        String txtPrice = request.getParameter("txtPrice");
        if (txtPrice.trim().isEmpty()) {
            txtPrice = "0";
        }
        float price = Float.parseFloat(txtPrice);
        String txtQuantity = request.getParameter("txtQuantity");
        if (txtQuantity.trim().isEmpty()) {
            txtQuantity = "0";
        }
        int quantity = Integer.parseInt(txtQuantity);
        String description = request.getParameter("txtDescription");
        try {

            //1. Check validate of 04 users
            boolean foundErr = false;
            if (productID.trim().length() < 3 || productID.trim().length() > 10) {
                foundErr = true;
                errors.setProductIdErr("Product ID requires typing from 3 - 10");
            }
            if (productName.trim().length() < 6 || productName.trim().length() > 30) {
                foundErr = true;
                errors.setProductNameErr("Product name requires typing from 6 - 30");
            }
            if (price <= 0) {
                foundErr = true;
                errors.setProductPriceErr("Price requires bigger than 0");
            }
            if (quantity <= 0) {
                foundErr = true;
                errors.setProductQuantityErr("Quantity requires bigger than 0");
            }
            if (foundErr) {
                // 2.1 Store Erros into Scope 
                request.setAttribute("CREATER", errors);

            } else {
                //2.2 call DAO to insert to DB
                ProductDAO productDAO = new ProductDAO();

                boolean result = productDAO.createProduct(productID, productName, cateID, quantity, price, true, description);
                System.out.println("Result: " + result);
                if (result) {
                    HttpSession session = request.getSession();
                    UserDTO user = (UserDTO) session.getAttribute("USER");
                    if (user == null) {
                        url = mainPage;
                    } else if (user.getRole() == 1) {
                        url = createProductPage;
                    } else if (user.getRole() == 2) {
                        url = mainPage;
                    }
                }
            }//end if Errors are occured
        } catch (NamingException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at CreateImageServlet: " + e.getMessage());
        } catch (SQLException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at CreateServlet _ SQL " + e.getMessage());
            errors.setProductIdErr(productID + " is exits in system");

            request.setAttribute("CREATER", errors);
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
