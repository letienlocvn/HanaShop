/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import loclt.cart.CartObj;
import loclt.product.ProductDAO;
import loclt.product.ProductDTO;
import loclt.users.UserDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author WIN
 */
public class AddProductServlet extends HttpServlet {

    private final String shoppingPage = "mainPage.jsp";
    private final String loginPage = "login.jsp";

    static final Logger LOGGER = Logger.getLogger(AddProductServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = loginPage;
        try {

            //1. Customer goes to place cart
            HttpSession session = request.getSession();
            UserDTO usetDTO = (UserDTO) session.getAttribute("USER");
            if (usetDTO != null) {
                //2. Customer take a cart
                CartObj cart = (CartObj) session.getAttribute("CUSTCART");
                if (cart == null) {
                    cart = new CartObj();
                }
                //3. Customer take a product
                ProductDAO productDAO = new ProductDAO();
                String productID = request.getParameter("txtProductID");
                ProductDTO product = productDAO.findPrimaryKey(productID);
                product.setQuantity(1);
                cart.addProductToCart(product);
                session.setAttribute("CUSTCART", cart);
                url = shoppingPage;
            }

        } catch (SQLException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at AddProductServlet: " + e.getMessage());
        } catch (NamingException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at AddProductServlet_ JNDI " + e.getMessage());
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
