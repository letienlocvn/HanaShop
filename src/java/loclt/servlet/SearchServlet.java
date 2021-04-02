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
import loclt.product.ProductDAO;
import loclt.product.ProductDTO;
import static loclt.servlet.SearchNameCategoryServlet.LOGGER;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author WIN
 */
public class SearchServlet extends HttpServlet {

    private final String searchProductPage = "searchProduct.jsp";
    static final Logger LOGGER = Logger.getLogger(SearchServlet.class.getName());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            String searchProduct = request.getParameter("txtSearch");
            String PriceFrom = request.getParameter("txtPriceFrom");
            String PriceTo = request.getParameter("txtPriceTo");
            if (!searchProduct.trim().isEmpty()) {
                String pageIndex = request.getParameter("pageIndex");
                if (pageIndex == null) {
                    pageIndex = "1";
                }
                if (PriceFrom.isEmpty()) {
                    PriceFrom = "0";
                }
                if (PriceTo.isEmpty()) {
                    PriceTo = "1000000000";
                }
                int index = Integer.parseInt(pageIndex);
                int productInPage = 6;

                ProductDAO dao = new ProductDAO();
//                dao.searchProduct(searchProduct, PriceFrom, PriceTo);
//                List<ProductDTO> result = dao.getListProduct();
                int getProductInDAO = dao.getAllProductBySearch(searchProduct, PriceFrom, PriceTo);
                int sizePage = getProductInDAO / productInPage;
                if (getProductInDAO % productInPage != 0) {
                    sizePage++;
                }

                List<ProductDTO> listProductBySearch
                        = dao.getPageSizeOfProductBySearch(searchProduct, PriceFrom, PriceTo, index, productInPage);
//                request.setAttribute("SEARCHPRODUCT", result);
                request.setAttribute("LISTPRODUCTBYSEARCH", listProductBySearch);
                request.setAttribute("SIZEPAGEBYSEARCH", sizePage);
            }

        } catch (SQLException | NamingException e) {
            BasicConfigurator.configure();
            LOGGER.error("ERROR at SearchServlet: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(searchProductPage);
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
