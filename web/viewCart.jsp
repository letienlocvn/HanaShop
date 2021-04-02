<%-- 
    Document   : viewCart
    Created on : Jan 19, 2021, 12:17:20 AM
    Author     : WIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <title>View Cart Page</title>
    </head>
    <body>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <c:if test="${sessionScope.USER.role == 2 or sessionScope.USER != null}">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12 col-md-10 col-md-offset-1">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th class="text-center">Price</th>
                                    <th class="text-center">Total</th>
                                    <th> </th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="shopping" items="${sessionScope.CUSTCART.cart.values()}">
                                <form action="DispatcherServlet">
                                    <tr>
                                        <td class="col-sm-8 col-md-6">
                                            <div class="media">
                                                <a class="thumbnail pull-left" href="#"> 
                                                    <img class="media-object" src="asset/image/${shopping.images}" style="width: 72px; height: 72px;"> </a>
                                                <div class="media-body">
                                                    <h4 class="media-heading">${shopping.productName}</h4>
                                                    <h5 class="media-heading"> From <a class="btn-link">${shopping.cateDTO.cateName}</a></h5>
                                                    <span>Status: </span>
                                                    <c:if test="${shopping.status == true}">
                                                        <span class="text-success"><strong>In Stock</strong></span>
                                                    </c:if>
                                                    <c:if test="${shopping.status == false}">
                                                        <span class="text-fail"><strong>Out Of Stock</strong></span>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="col-sm-1 col-md-1" style="text-align: center">
                                            <input type="number" name="txtQuantity" min="0" class="form-control" value="${shopping.quantity}">
                                            <input type="hidden" name="txtProductID" value="${shopping.productID}" />
                                        </td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>$${shopping.price}</strong></td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>${shopping.price*shopping.quantity}</strong></td>
                                        <td class="col-sm-1 col-md-1">
                                            <button type="submit" class="btn btn-success" value="Update Product" name="btAction">
                                                <span class="glyphicon glyphicon-ok"></span> Update
                                            </button>
                                        </td>
                                        <td>
                                            <button type="submit" class="btn btn-danger" value="Remove Product" name="btAction">
                                                <span class="glyphicon glyphicon-remove"></span> Remove
                                            </button>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>


                            <!-- Total -->
                            <tr>
                                <td>  </td>
                                <td> 

                                </td>
                                <td>   </td>
                                <td><h3>Total</h3></td>
                                <td class="text-right"><h3><strong>$${sessionScope.CUSTCART.total}</strong></h3></td>
                            </tr>

                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td>   </td>
                                <td>
                                    <button type="button" class="btn btn-default" >
                                        <span class="glyphicon glyphicon-shopping-cart">
                                            <a href="FirstServlet">Continue Shopping</a>
                                        </span> 
                                    </button>
                                </td>
                                <td>
                                    <form action="DispatcherServlet">

                                        <button type="submit" class="btn btn-success" value="Check Out" name="btAction">
                                            Checkout <span class="glyphicon glyphicon-play"></span>
                                        </button>
                                    </form>

                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>
        
    </body>
</html>
