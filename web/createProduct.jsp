<%-- 
    Document   : createProduct
    Created on : Jan 15, 2021, 2:58:56 PM
    Author     : WIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="asset/css/main.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link href="../assets/css/material-dashboard.min.css?v=2.1.2" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Product</title>
    </head>
    <body>
        <c:if test="${sessionScope.USERSHOPPING != null}">
            Deny Access <br/>
            <a href="mainPage.jsp">Click here to try again</a>
        </c:if>
        <c:if test="${sessionScope.USER != null and sessionScope.ADMIN != null}" >
            <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute">
                <div class="container-fluid">
                    <div class="navbar-wrapper">
                        <a class="navbar-brand">${sessionScope.USER.fullName}<div class="ripple-container"></div></a>
                    </div>
                    <div class="navbar-wrapper">
                        <a class="navbar-brand btn btn-primary" href="adminPage.jsp">Back Home<div class="ripple-container"></div></a>
                    </div>
                </div>
            </nav>
            <div class="container-fluid">
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="card">
                                    <div class="card-header card-header-primary">
                                        <h4 class="card-title">Create Product</h4>
                                        <p class="card-category">Complete your product</p>
                                    </div>
                                    <div class="card-body">
                                        <form action="DispatcherServlet">
                                            <c:set var="errors" value="${requestScope.CREATER}"/>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group bmd-form-group">
                                                        <label class="bmd-label-floating">Product Name</label>
                                                        <input type="text" class="form-control" name="txtProductName" value="" maxlength="50" minlength="3">
                                                    </div>
                                                    <c:if test="${not empty errors.productNameErr}">
                                                        <font color="red">
                                                        ${errors.productNameErr}
                                                        </font>
                                                    </c:if>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group bmd-form-group">
                                                        <label class="bmd-label-floating">Category Name</label>
                                                        <div class="dropdown">
                                                            <select name="cbbox" class="btn btn-primary">
                                                                <c:forEach var="category" items="${sessionScope.LISTCATENAME}">
                                                                    <option value="${category.cateID}">
                                                                        ${category.cateName}
                                                                    </option>                                                                    
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="form-group bmd-form-group">
                                                        <label class="bmd-label-floating">Price</label>
                                                        <input type="number" class="form-control" name="txtPrice" min="0" value="${param.txtPrice}">
                                                    </div>
                                                    <c:if test="${not empty errors.productPriceErr}">
                                                        <font color="red">
                                                        ${errors.productPriceErr}
                                                        </font>
                                                    </c:if>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="form-group bmd-form-group">
                                                        <label class="bmd-label-floating">Quantity</label>
                                                        <input type="number" class="form-control" name="txtQuantity" min="0" value="${param.txtQuantity}">
                                                    </div>
                                                    <c:if test="${not empty errors.ProductQuantityErr}">
                                                        <font color="red">
                                                        ${errors.ProductQuantityErr}
                                                        </font>
                                                    </c:if>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="form-group bmd-form-group">
                                                        <label class="bmd-label-floating">Product ID</label>
                                                        <input type="text" class="form-control" name="txtProductID" 
                                                               maxlength="10" minlength="0" value="${param.txtProductID}"/>
                                                    </div>

                                                    <c:if test="${not empty errors.productIdErr}">
                                                        <font color="red">
                                                        ${errors.productIdErr}
                                                        </font>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label>Information about Product</label>
                                                        <div class="form-group bmd-form-group">
                                                            <label class="bmd-label-floating">Description</label>
                                                            <textarea class="form-control" rows="5" 
                                                                      name="txtDescription" minlength="0" maxlength="200">${param.txtDescription}</textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="submit" value="Create" name="btAction" class="btn btn-primary pull-right"/>
                                            <input type="reset" class="btn btn-primary" value="Reset"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <form action="DispatcherServlet">
                                    <div class="card card-profile text-center">
                                        <div class="card-avatar">
                                            <img class="img rounded-circle" src="asset/image/${product.images}" width="250" height="254">
                                            <input type="hidden" name="txtImage" value="${param.txtImage}"/>
                                        </div>
                                        <div class="card-body">
                                            <h6 class="card-category text-gray">${product.cateDTO.cateName}</h6>
                                            <h4 class="card-title">${product.productName}</h4>
                                            <p class="card-description">
                                                ${product.description}
                                            </p>
                                            <div class="form-group">
                                                <input type="file" value="${param.txtImage}" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp">
                                                <input type="submit" value="Create Image" name="btAction" />
                                            </div>

                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

    </body>
</html>
