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
            <c:set var="errors" value="${requestScope.ERROROBJ}"/>
            UserID: <input type="text" name="txtUserId" value=""/><br/>
            <c:if test="${not empty errors.userIdLengthError}">
                <font color="red">
                    ${errors.userIdLengthError}
                </font><br/>
            </c:if>
            Password: <input type="password" name="txtPassword" value=""/><br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            Confirm: <input type="password" name="txtPasswordConfirm" value=""/><br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                    ${errors.confirmNotMatch}
                </font><br/>
            </c:if>
            Username: <input type="text" name="txtUsername" value=""/><br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                    ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            Email: <input type="email" name="txtEmail" value=""/><br/>
            <c:if test="${not empty errors.emailLengthError}">
                <font color="red">
                    ${errors.emailLengthError}
                </font><br/>
            </c:if>
            Phone: <input type="number" name="txtPhone" value=""/><br/>
            <c:if test="${not empty errors.phoneLengthError}">
                <font color="red">
                    ${errors.phoneLengthError}
                </font><br/>
            </c:if>
            Role: <select name="txtRole">
                <c:forEach var="roleDTO" items="${sessionScope.GROUPOFROLE}">
                    <option value="${roleDTO.type}">
                        ${roleDTO.type}
                    </option>
                </c:forEach>
            </select><br/>
            Avatar: <input type="file" name="image" value=""/><br/>
            <c:if test="${not empty errors.imageEmpty}">
                <font color="red">
                    ${errors.imageEmpty}
                </font><br/>
            </c:if>
            
            <input type="submit" name="btAction" value="Register"/>               
        </form>
        <a href="search.jsp">Back to search page...</a>
    </body>
</html>
