<%-- 
    Document   : viewHistory
    Created on : Jan 21, 2021, 12:57:55 PM
    Author     : WIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="asset/css/main.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <title>View History Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.USER.role == 2 and not empty sessionScope.USER}">
            
        </c:if>
        <section class="content content_content" style="width: 70%; margin: auto;">
            <section class="invoice">
                <!-- title row -->
                <!--                <div class="row">
                                    <div class="col-xs-12">
                                        <h2 class="page-header">
                                            <i class="fa fa-globe"></i> Category
                                            <small class="pull-right">Date: 2017/01/09</small>
                                        </h2>
                                    </div> /.col 
                                </div>-->
                <!-- info row -->
                <!--                <div class="row invoice-info">
                                    <div class="col-sm-4 invoice-col">
                                        From
                                        <address>
                                            <strong>
                                            </strong>
                                        </address>
                                    </div> /.col 
                                    <div class="col-sm-4 invoice-col">
                                        To
                                        <address>
                
                                        </address>
                                    </div> /.col 
                                    <div class="col-sm-4 invoice-col">
                                        <b></b><br>
                                        <br>
                                        <b>Order ID:</b> 4F3S8J<br>
                                        <b>Date Check out:</b> 2/22/2014<br>
                                        <b>Account:</b> cho ten username
                                    </div> /.col 
                                </div> /.row -->

                <!-- Table row -->
                <div class="row">
                    <div class="col-xs-12 table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Order Details ID</th>
                                    <th>Quantity</th>
                                    <th>Product Name</th>
                                    <th>Category</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="information" items="${sessionScope.LISTORDERDETAILS}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${information.orderDetailsID}</td>
                                        <td>${information.quantity}</td>
                                        <td>${information.product.productName}</td>
                                        <td>${information.product.cateDTO.cateName}</td>
                                        <td>${information.price*information.quantity}</td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div><!-- /.col -->
                </div><!-- /.row -->

                <!-- this row will not appear when printing -->
                <div class="row no-print">
                    <div class="col-xs-12">
                        <a href="ViewHistoryServlet" class="btn btn-default"><i class="fas fa-arrow-circle-left"></i>Back</a>
                        
                    </div>
                </div>
            </section>
        </section>
    </body>
</html>
