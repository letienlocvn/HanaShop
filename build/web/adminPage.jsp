<%-- 
    Document   : adminPage
    Created on : Jan 14, 2021, 3:10:08 PM
    Author     : WIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="asset/css/main.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <c:if test="${not empty sessionScope.ADMIN}">
            <nav class="navbar navbar-dark sticky-top bg-primary flex-md-nowrap p-0">
                <a class="navbar-brand col-sm-3 col-md-2 mr-0">${sessionScope.USER.fullName}</a>
                <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
                <form action="DispatcherServlet">
                    <ul class="navbar-nav px-3">
                        <li class="nav-item text-nowrap">
                            <input class="btn btn-primary"type="submit" value="Logout" name="btAction" />
                        </li>
                    </ul>
                </form>
            </nav>
            <div class="container-fluid">
                <div class="row">
                    <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                        <div class="sidebar-sticky">
                            <ul class="nav flex-column">
                                <li class="nav-item">
                                    <a class="nav-link active" href="#">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>
                                        Dashboard <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="createProduct.jsp">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                                        Create Product
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                        <h2>Dashboard</h2>
                        <hr/>
                        <div class="table-responsive">
                            <table class="table table-striped table-sm table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Image</th>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Quantity </th>
                                        <th>Price</th>
                                        <th>Date</th>
                                        <th>Status</th>
                                        <th>Category</th>
                                        <th>Description</th>
                                        <th>Action</th>
                                        <th>Action</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" items="${sessionScope.LISTPRODUCTBYDATE}" varStatus="counter">
                                    <form action="DispatcherServlet">
                                        <c:if test="${not empty product}">
                                            <tr class="text-center">
                                                <td>${counter.count}</td>
                                                <td>
                                                    <img class="img rounded-circle img-thumbnail" src="asset/image/${product.images}"alt="Shop"
                                                         width="150" height="150">
                                                </td>
                                                <td>
                                                    ${product.productID}
                                                    <input type="hidden" name="txtProductID" value="${product.productID}" />
                                                </td>
                                                <td>
                                                    ${product.productName}
                                                    <input type="hidden" name="txtProductName" value="${product.productName}"/>
                                                </td>
                                                <td>${product.quantity}</td>
                                                <td>${product.price}</td>
                                                <td>${product.datime}</td>
                                                <td>                                                   
                                                    <c:if test="${product.status == true}">
                                                        <select class="btn btn-success">
                                                            <option>Active</option>
                                                            <option>Inactive</option>
                                                        </select>
                                                    </c:if>
                                                    <c:if test="${product.status == false}">
                                                        <select class="btn btn-secondary">
                                                            <option>Inactive</option>
                                                            <option>Active</option>
                                                        </select>
                                                    </c:if>
                                                    <input type="hidden" name="status" value="${product.status}"/>
                                                </td>
                                                <td>${product.cateDTO.cateName}</td>
                                                <td>${product.description}</td>
                                                <td>                                                                                                   
                                                    <input Onclick="return ConfirmDelete();" class="btn btn-danger" type="submit" value="Delete" name="btAction" />                                                
                                                </td>
                                                <td>
                                                    <c:url var="nextUpdate" value="StoreProductServlet">
                                                        <c:param name="txtProductID" value="${product.productID}"/>
                                                    </c:url>
                                                    <a class="btn btn-primary" href="${nextUpdate}">Update</a>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </form>

                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <form action="DispatcherServlet">
                            <div class="row" >
                                <div class="col-sm-1" style="float: right">
                                    <ul class="pagination">
                                        <c:forEach var="sizeADMIN" begin="1" end="${sessionScope.SIZEPAGEADMIN}">
                                            <li class="page-item">
                                                <c:url var="ManagementProduct" 
                                                       value="ManagementServlet?pageIndex=${sizeADMIN}"/>
                                                <a class="page-link" 
                                                   href="${ManagementProduct}">${sizeADMIN}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </form>

                    </main>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.USERSHOPPING != null}">
            Deny Access <br/>
            <a href="mainPage.jsp">Click here to try again</a>
        </c:if>
        <script>
            function ConfirmDelete() {
                var deleteItem = confirm("Are you sure you want to delete?");
                if (deleteItem)
                    return true;
                else
                    return false;
            }
        </script>

</body>
</html>
