<%-- 
    Document   : searchProduct
    Created on : Jan 9, 2021, 8:30:16 AM
    Author     : WIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Hana Shop</title>
        <link rel="stylesheet" href="asset/css/main.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>

    <body>


        <hr/><br/>
        <div class="container">
            <div class="row">
                <!-- Search Product -->
                <div class="col-sm-4 col-md-3">
                    <form action="DispatcherServlet">
                        <div class="well">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Search products..." name="txtSearch" value="${param.txtSearch}">

                                        <div class="form-group shop-filter__price">
                                            <div class="row">
                                                <h3 class="col-xs-4">Price</h3>
                                                <div class="col-xs-4">
                                                    <label for="shop-filter-price_from" class="sr-only"></label>
                                                    <input id="shop-filter-price_from" type="number" min="0" 
                                                           class="form-control" placeholder="From" name="txtPriceFrom" value="${param.txtPriceFrom}">
                                                </div>
                                                <div class="col-xs-4">
                                                    <label for="shop-filter-price_to" class="sr-only"></label>
                                                    <input id="shop-filter-price_to" type="number" min="0" 
                                                           class="form-control" placeholder="To" name="txtPriceTo" value="${param.txtPriceTo}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-primary col-sm-11" type="submit" 
                                        value="Search" name="btAction" style="margin-left: 15px">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Filter -->
                    <form class="shop__filter" action="DispatcherServlet">
                        <!-- Price -->

                        <!-- Category -->
                        <div class="list-group list-group-flush">
                            <h3>
                                <span>Category</span>
                            </h3>
                            <li class="list-group-item list-group-item-action">
                                <a href="FirstServlet?btAction=All">All</a>
                            </li>
                            <c:forEach var="category" items="${sessionScope.LISTCATENAME}">
                                <c:url var="urlRewriting" value="SearchNameCategoryServlet">
                                    <c:param name="txtCateID" value="${category.cateID}"/>
                                </c:url>
                                <li class="list-group-item list-group-item-action">
                                    <a href="${urlRewriting}">${category.cateName}</a>
                                </li>
                            </c:forEach>
                        </div>
                    </form>
                </div>

                <div class="col-sm-8 col-md-9">
                    <!-- Login -->
                    <ul class="shop__sorting">
                        <div class="row">
                            <c:set var="user" value="${sessionScope.USER}"/>
                            <c:if test="${not empty user}">
                                <div class="col-md-12">
                                    Welcome, ${user.fullName}
                                </div>

                                <!-- View History -->
                                <div class="col-sm-4" style="padding-right: 15px;">
                                    <form action="DispatcherServlet">
                                        <input class="btn btn-primary" type="submit" value="View History" name="btAction" />
                                    </form>
                                </div>

                                <!-- View Cart -->
                                <div class="col-md-4" style="padding-left: 25px;">
                                    <a class="btn btn-primary" href="viewCart.jsp">Your cart
                                        <c:if test="${not empty sessionScope.CUSTCART.cart.values()}">
                                            ${sessionScope.CUSTCART.cart.values().size()}
                                        </c:if>
                                    </a>
                                </div>
                                <div class="col-sm-4">
                                    <form action="DispatcherServlet">
                                        <input class="btn btn-primary"type="submit" value="Logout" name="btAction" />
                                    </form>
                                </div>


                            </c:if>
                            <div class="col-sm-12">
                                <c:if test="${empty user}">
                                    <a href="login.jsp">Login</a>
                                </c:if>
                            </div>
                        </div>
                    </ul>

                    <!-- Search Product  -->
                    <div class="row">
                        <c:forEach var="product" items="${requestScope.LISTPRODUCTBYSEARCH}">
                            <c:if test="${not empty product}">
                                <div class="col-sm-6 col-md-4">
                                    <form action="DispatcherServlet">
                                        <div class="shop__thumb">
                                            <div class="shop-thumb__img">
                                                <img src="asset/image/${product.images}" class="img-responsive" alt="Shop"
                                                     width="150" height="154">
                                            </div>
                                            <h5 class="shop-thumb__title">
                                                ${product.productName}
                                                <input type="hidden" name="txtProductID" value="${product.productID}" />
                                            </h5>
                                            <div class="shop-thumb__price">
                                                <span class="shop-thumb-price_new">${product.price}đ</span>
                                                <input type="hidden" name="txtPrice" value="${product.price}" />
                                            </div>
                                            <div class="shop-thumb__categoryName">
                                                <span class="">${product.cateDTO.cateName}</span>
                                            </div>
                                            <div class="shop-thumb__description">
                                                <span class="">${product.description}</span>
                                            </div>
                                            <div class="shop-thumb__day">
                                                <span class="">${product.datime}</span>
                                            </div>
                                            <div class="shop-thum__status">
                                                <input class="btn btn-info" type="submit" value="Add to cart" name="btAction"/>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                        </c:forEach>

                        <!-- Category Search-->
                        <c:forEach var="category" items="${requestScope.LISTPRODUCTINCATEGORY}">                            
                            <c:if test="${not empty category}">
                                <div class="col-sm-6 col-md-4">
                                    <form action="DispatcherServlet">
                                        <div class="shop__thumb">
                                            <div class="shop-thumb__img">
                                                <img src="asset/image/${category.images}" class="img-responsive" alt="Shop"
                                                     width="150" height="154">
                                            </div>
                                            <h5 class="shop-thumb__title">
                                                ${category.productName}
                                                <input type="hidden" name="txtProductID" value="${category.productID}" />
                                            </h5>
                                            <div class="shop-thumb__price">
                                                <span class="shop-thumb-price_new">${category.price}đ</span>
                                                <input type="hidden" name="txtPrice" value="${category.price}" />
                                            </div>
                                            <div class="shop-thumb__categoryName">
                                                <span class="">${category.cateDTO.cateName}</span>
                                            </div>
                                            <div class="shop-thumb__description">
                                                <span class="">${category.description}</span>
                                            </div>
                                            <div class="shop-thumb__day">
                                                <span class="">${category.datime}</span>
                                            </div>
                                            <div class="shop-thum__status">
                                                <input class="btn btn-info" type="submit" value="Add to cart" name="btAction"/>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </c:if>
                        </c:forEach>

                    </div> <!-- / .row -->

                    <!-- Pagination for Load All Product -->
                    <div class="row" style="float: right">
                        <div class="col-sm-12">
                            <ul class="pagination">
                                <c:forEach var="sizeOfPage" begin="1" end="${requestScope.SIZEOFPAGE}">
                                    <li class="page-item">
                                        <a class="page-link" href="FirstServlet?pageIndex=${sizeOfPage}">${sizeOfPage}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div> <!-- / .row -->

                    <!-- Paging base on category -->
                    <div class="row" style="float: right">
                        <div class="col-sm-12">
                            <ul class="pagination">
                                <c:forEach var="sizeOfPageCategory" begin="1" end="${requestScope.SIZEPAGECATEGORY}">
                                    <li class="page-item">
                                        <c:url var="ProductInPage" 
                                               value="SearchNameCategoryServlet?txtCateID=${param.txtCateID}&pageIndex=${sizeOfPageCategory}"/>
                                        <a class="page-link"
                                           href="${ProductInPage}">
                                            ${sizeOfPageCategory}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <!-- Paging for search Product -->

                    <div class="row" style="float: right">
                        <div class="col-sm-12">
                            <ul class="pagination">
                                <c:forEach var="sizeOfPageBySearch" begin="1" end="${requestScope.SIZEPAGEBYSEARCH}">
                                    <li class="page-item">
                                        <c:url var="SearchProduct" 
                                               value="DispatcherServlet?txtSearch=${param.txtSearch}&txtPriceFrom=${param.txtPriceFrom}&txtPriceTo=${param.txtPriceTo}&btAction=Search&pageIndex=${sizeOfPageBySearch}"/>
                                        <a class="page-link" 
                                           href="${SearchProduct}">${sizeOfPageBySearch}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div> <!-- / .row -->
                </div> <!-- / .col-sm-8 -->
            </div> <!-- / .row -->
        </div>
        <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script type="text/javascript">
        </script>
    </body>
</html>