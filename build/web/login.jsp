<%-- 
    Document   : login
    Created on : Jan 23, 2021, 1:23:50 AM
    Author     : WIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <title>Login Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USERSHOPPING}" />
        <c:if test="${not empty user}">
            <h3>You had login</h3>
            <a href="FirstServlet" class="btn btn-primary">Back</a>
        </c:if>
        <c:set var="admin" value="${sessionScope.ADMIN}" />
        <c:if test="${not empty admin}">
            <h3>You had login</h3>
            <a href="ManagementServlet" class="btn btn-primary">Back</a>
        </c:if>  
        <c:if test="${ empty user and empty admin}">
            <h1 class="text-center">Login Page</h1>
            <div style=" width: 360px; margin-left: auto;margin-right: auto; position: relative">
                <form action="LoginServlet" method="POST" >
                    <div class="mb-3">
                        <label class="form-label">Username</label>
                        <input type="text" name="txtUsername" value="" class="form-control"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="password" name="txtPassword" class="form-control" placeholder="Password">
                    </div>
                    <input type="submit" value="Login" class="btn btn-primary" name="btAction" />
                    <a href="mainPage.jsp" class="btn btn-primary">Back</a>

                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/HanaShop4_LAB231_Check_2/GoogleServlet&response_type=code
    &client_id=69421826827-e5hiac3om932morgsj30be5i2u6srdbi.apps.googleusercontent.com">Login With Google</a> 


                </form>
            </div>
        </c:if>
    </body>
</html>
