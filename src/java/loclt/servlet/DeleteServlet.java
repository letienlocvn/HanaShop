/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import loclt.category.CategoryDAO;
import loclt.product.ProductDAO;
import loclt.product.ProductDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class DeleteServlet extends HttpServlet {

    private final String deleteError = "errors.jsp";
    private final String adminPage = "adminPage.jsp";
    static final Logger LOGGER = Logger.getLogger(DeleteServlet.class.getName());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = deleteError;
        try {
            String id = request.getParameter("txtProductID");
            String status = request.getParameter("status");
            boolean changeStatus = true;
            if(status.equals("true")){
                changeStatus = false;
            } else if (status.equals("false")){
                changeStatus = false;
            }              
            ProductDAO productDAO = new ProductDAO();
            boolean result = productDAO.setActiveProduct(id, changeStatus);        
            if (result){
                url = adminPage;
//                productDAO.updateUserAndDate(id, username, dtf.format(now));
            }
            
            // Paging
            CategoryDAO category = new CategoryDAO();

            String pageIndex = request.getParameter("pageIndex");
            if (pageIndex == null) {
                pageIndex = "1";
            }
            int index = Integer.parseInt(pageIndex);
            int productInPage = 20;
            int totalProduct = productDAO.getAllProductADMIN();
            int sizeOfPage = totalProduct / productInPage;
            if (totalProduct % productInPage != 0) {
                sizeOfPage++;
            }
            List<ProductDTO> listOfProduct= productDAO.getAllProductByDate(category, index, productInPage);
            HttpSession session = request.getSession();
            session.setAttribute("LISTPRODUCTBYDATE", listOfProduct);
            session.setAttribute("SIZEPAGEADMIN", sizeOfPage);
        } catch (SQLException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at DeleteServlet: " + e.getMessage());
        } catch (NamingException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at DeleteServlet: " + e.getMessage());
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
