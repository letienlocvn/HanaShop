<%-- 
    Document   : historyPage
    Created on : Jan 20, 2021, 2:03:32 PM
    Author     : WIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="asset/css/main.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <title>Details History Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.ADMIN != null}">
            Deny Access 
            <a href="mainPage.jsp"></a>
        </c:if>
        <c:if test="${sessionScope.USERSHOPPING != null}">
            <div class="container">
                <div class="row">

                    <div class="col-sm-12 col-md-10 col-md-offset-1">
                        <div class="col-sm-8">
                            <div class="input-group">
                                <form action="DispatcherServlet">
                                    <div class="form-outline">
                                        <label class="form-label" for="form1">Search Only DAY</label>
                                        <input type="text" id="form1" class="form-control" name="txtSearch" value="${param.txtSearch}" />
                                    </div>
                                    <button type="submit" name="btAction" value="Search History" class="btn btn-primary">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </form>
                            </div>
                        </div>

                        <table class="table table-hover">
                            <thead>
                                <tr>

                                    <th>Order ID</th>
                                    <th>Date Of Create</th>
                                    <th class="text-center">Total</th>
                                    <th class="text-center">Status</th>
                                    <th>View Details</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="order" items="${sessionScope.LISTORDER}" varStatus="counter">
                                    <c:set var="status" value="${order.status}"/>
                                <form action="DispatcherServlet">
                                    <c:if test="${status == true}">
                                        <tr>
                                            <td class="col-sm-8 col-md-6">
                                                <div class="media">
                                                    <div class="media-body">
                                                        <h3 class="media-heading">${sessionScope.USER.fullName}</h3>

                                                        <h5 class="media-heading">Bill: ${order.orderID}</h5>
                                                        <input type="hidden" name="txtOrderID" value="${order.orderID}" />
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="col-sm-1 col-md-1" style="text-align: center">
                                                <span class="text-danger">${order.dateOfCreate}</span>
                                                <input type="hidden" name="txtDate" value="${order.dateOfCreate}" />
                                            </td>
                                            <td class="col-sm-1 col-md-1 text-center"><strong>${order.total}</strong></td>
                                            <td class="col-sm-1 col-md-1 text-center"><strong>${order.status}</strong></td>
                                            <td class="col-sm-1 col-md-1">
                                                <input type="hidden" name="txtOrderDetails" value="${order.orderID}" />
                                                <button type="submit" class="btn btn-info" value="View Orders Details" name="btAction">
                                                    <span class="glyphicon glyphicon-ok"></span> View Details
                                                </button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </form>
                            </c:forEach>


                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td>   </td>
                                <td>   </td>
                                <td>

                                    <a class="btn btn-success glyphicon glyphicon-shopping-cart" 
                                       href="FirstServlet">Continue shopping</a>
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
