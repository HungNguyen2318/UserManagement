<%-- 
    Document   : createNewUser
    Created on : Nov 17, 2019, 1:14:37 AM
    Author     : SE130008
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create New Account Page</h1>
        <c:set var="user" value="${sessionScope.USERLOGIN}"/>
        <h1>Sign Up Form</h1>
        <c:set var="resultCreate" value="${requestScope.RESULT_CREATR_ACCOUNT}"/>
        <c:if test="${resultCreate}">
            <font color="green">Successful!!!</font>
        </c:if>
        <form action="DispathController" method="POST" enctype="multipart/form-data">
            UserID: <input type="text" name="txtUserId" value=""/><br/>
            Password: <input type="password" name="txtPassword" value=""/><br/>
            Confirm: <input type="password" name="txtPasswordConfirm" value=""/><br/>
            Username: <input type="text" name="txtUsername" value=""/><br/>
            Email: <input type="email" name="txtEmail" value=""/><br/>
            Phone: <input type="number" name="txtPhone" value=""/><br/>
            Role: <input type="text" name="txtRole" value=""/><br/>
            Avatar: <input type="file" name="image" value=""/><br/>
            <input type="submit" name="btAction" value="Register"/>               
        </form>
            <a href="search.jsp">Back to search page...</a>
    </body>
</html>
