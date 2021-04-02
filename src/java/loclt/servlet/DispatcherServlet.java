/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author WIN
 */
public class DispatcherServlet extends HttpServlet {

    private final String mainPage = "mainPage.jsp";
    private final String loginServlet = "LoginServlet";
    private final String logoutServlet = "LogoutServlet";
    private final String firstServlet = "FirstServlet";
    private final String searchServlet = "SearchServlet";
    private final String updateServlet = "UpdateServlet";
    private final String deleteServlet = "DeleteServlet";
    private final String createServlet = "CreateServlet";
    private final String addProductToCart = "AddProductServlet";
    private final String yourCart = "viewCart.jsp";
    private final String removeShoppingServlet = "RemoveShoppingServlet";
    private final String updateShoppingServlet = "UpdateShoppingServlet";
    private final String orderProduct = "OrderProductServlet";
    private final String viewHistory = "ViewHistoryServlet";
    private final String viewDetailsHistoryServlet = "DetailsHistoryServlet";
    private final String searchHistoryServlet = "SearchHistoryServlet";
    private final String updateImageServlet = "UpdateImageServlet";
    private final String createImageServlet = "CreateImageServlet";
    static final Logger LOGGER = Logger.getLogger(DispatcherServlet.class.getName());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = mainPage;
        String button = request.getParameter("btAction");
        try {
            if (button == null) {
                url = firstServlet;
            } else if (button.equals("Login")) {
                url = loginServlet;
            } else if (button.equals("Logout")) {
                url = logoutServlet;
            } else if (button.equals("Search")) {
                url = searchServlet;
            } else if (button.equals("All")){
                url = firstServlet;
            } else if (button.equals("Update")){
                url = updateServlet;
            } else if (button.equals("Delete")){
                url = deleteServlet;
            } else if ("Update Image".equals(button)){
                url = updateServlet;
            } else if (button.equals("Create")){
                url = createServlet;
            } else if ("Add to cart".equals(button)){
                url = addProductToCart;
            } else if ("Your cart".equals(button)){
                url = yourCart;
            } else if ("Update Product".equals(button)){
                url = updateShoppingServlet;
            } else if("Remove Product".equals(button)){
                url = removeShoppingServlet;
            } else if ("Check Out".equals(button)){
                url = orderProduct;
            } else if ("View History".equals(button)){
                url = viewHistory;
            } else if ("View Orders Details".equals(button)){
                url = viewDetailsHistoryServlet;
            } else if ("Search History".equals(button)){
                url = searchHistoryServlet;
            } else if ("Image".equals(button)){
                url = updateImageServlet;
            } else if ("Create Image".equals(button)){
                url = createImageServlet;
            }
        } catch (Exception e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at DispatcherServlet: " + e.getMessage());
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
