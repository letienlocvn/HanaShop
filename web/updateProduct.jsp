<%-- 
    Document   : updateProduct
    Created on : Jan 15, 2021, 8:10:27 AM
    Author     : WIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
        <link rel="stylesheet" href="asset/css/main.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link href="../assets/css/material-dashboard.min.css?v=2.1.2" rel="stylesheet">
    </head>
    <body>
        <c:if test="${sessionScope.USER != null and sessionScope.USERSHOPPING == null}">
            <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute">
                <div class="container-fluid">
                    <div class="navbar-wrapper">
                        <a class="navbar-brand">${sessionScope.USER.fullName}<div class="ripple-container"></div></a>
                    </div>
                    <div class="navbar-wrapper">
                        <a href="adminPage.jsp" class="navbar-brand btn btn-primary">Back Home<div class="ripple-container"></div></a>
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
                                        <h4 class="card-title">Update Product</h4>
                                        <p class="card-category">Complete your product</p>
                                    </div>

                                    <!-- Information Product -->
                                    <div class="card-body">
                                        <c:forEach var="product" items="${requestScope.DETAILPRODUCT}">
                                            <form action="DispatcherServlet" method="GET">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group bmd-form-group">                                               
                                                            <label class="bmd-label-floating">Product Name</label>                                                    
                                                            <input name="txtProductName" type="text" class="form-control" value="${product.productName}"/>

                                                            <input type="hidden" name="txtProductID" value="${product.productID}"/>
                                                            <input type="hidden" name="txtUsername" value="${sessionScope.ADMIN}"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group bmd-form-group">
                                                            <label class="bmd-label-floating">Category Name</label>
                                                            <div class="dropdown">
                                                                <select name="cbbox" class="btn btn-primary">
                                                                    <c:forEach var="category" items="${sessionScope.LISTNAMECATEGORY}">
                                                                        <option>
                                                                            ${category.cateID} - ${category.cateName}
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
                                                            <input name="txtPrice" type="number" class="form-control" value="${product.price}">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group bmd-form-group">
                                                            <label class="bmd-label-floating">Quantity</label>
                                                            <input name="txtQuantity" type="number" class="form-control" value="${product.quantity}">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="bmd-label-floating">Status</label>
                                                        <div class="dropdown">
                                                            <select name="cbStatus" class="btn btn-primary">                                                            
                                                                <option value="${product.status = true}">Active</option>                    
                                                                <option value="${product.status = false}">Inactive</option>                    
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--                                            <div class="form-group row">
                                                                                                <label for="dateinput" class="col-12 col-form-label">Date</label>
                                                                                                <div class="col-10">
                                                                                                    <input class="form-control" type="date" id="dateinput" value="${product.datime}">
                                                                                                    <input type="hidden" name="txtDate" value="${product.datime}"/>
                                                                                                </div>
                                                                                            </div>-->
                                                <!-- Description-->
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label>Description</label>
                                                            <div class="form-group bmd-form-group">
                                                                <label class="bmd-label-floating">Information product</label>
                                                                <textarea name="txtDescription" class="form-control" rows="5">${product.description}</textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <input type="submit"  class="btn btn-primary pull-right" value="Update" name="btAction" />
                                                <input type="reset" class="btn btn-primary " value="Reset"/>
                                            </form>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <!-- Update Product Imgae-->
                            <div class="col-md-4">
                                <form action="DispatcherServlet">
                                    <c:forEach var="product" items="${requestScope.DETAILPRODUCT}">
                                        <div class="card card-profile text-center">
                                            <div class="card-avatar">
                                                <img class="img rounded-circle" src="asset/image/${product.images}" width="250" height="254">
                                                <input type="hidden" name="txtProductID" value="${product.productID}" />
                                            </div>
                                            <div class="card-body">
                                                <h6 class="card-category text-gray">${product.cateDTO.cateName}</h6>
                                                <h4 class="card-title">${product.productName}</h4>
                                                <p class="card-description">
                                                    ${product.description}
                                                </p>

                                                <div class="form-group">
                                                    <input type="file" name="txtImage"  value="${product.images}" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp" />
                                                    <button type="submit" value="Image" name="btAction" class="btn btn-info">
                                                        Update Image
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>

                                </form>
                            </div>
                        </div>

                        <!--  Button Update -->
<!--                        <form action="DispatcherServlet">
                            <div class="row">
                                <div class="col-sm-12 center">
                                    <div class="col-sm-6">

                                    </div>
                                    <div class="col-sm-6" >

                                    </div>
                                </div>                        
                            </div>
                        </form>-->
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.USERSHOPPING != null}">
            Deny Access <br/>
            <a href="mainPage.jsp">Click here to try again</a>
        </c:if>
    </body>
</html>
