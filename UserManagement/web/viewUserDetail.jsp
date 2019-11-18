<%-- 
    Document   : viewUserDetail
    Created on : Nov 18, 2019, 2:37:32 PM
    Author     : SE130008
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USERLOGIN}"/>
        Welcome,${user}
        <h1>Profile Form</h1>
        <c:set var="resultUpdate" value="${requestScope.RESULT_UPDATE_ACCOUNT}"/>
        <c:if test="${resultUpdate}">
            <font color="green">Successful!!!</font><br/>
        </c:if>
        <c:set var="userDto" value="${requestScope.USERDTO}"/>
        <c:if test="${not empty userDto}">
            <form action="DispathController" method="POST" enctype="multipart/form-data">
                UserID: ${userDto.userId}<br/>
                <input type="hidden" name="txtUserId" value="${userDto.userId}"/>
                Password: <input type="password" name="txtPassword" value=""/><br/>
                Confirm: <input type="password" name="txtPasswordConfirm" value=""/><br/>
                Username: <input type="text" name="txtUsername" value="${userDto.username}"/><br/>
                Email: <input type="email" name="txtEmail" value="${userDto.email}"/><br/>
                Phone: <input type="number" name="txtPhone" value="${userDto.phone}"/><br/>
                Role: 
                <select name="txtRole">
                    <c:forEach var="roleDTO" items="${GROUPOFROLE}">
                        <option value="${roleDTO.type}"
                                <c:if test="${rdto.roleID eq param.dropOp}">
                                    selected
                                </c:if>
                                >
                            ${roleDTO.type}
                        </option>
                    </c:forEach>
                </select><br/>
                Avatar: <img src="images/${userDto.image}" width="150" height="150">
                <input type="hidden" name="imageName" value="${userDto.image}"/>
                <input type="file" name="image" value=""/><br/>
                <input type="submit" name="btAction" value="Update"/>               
            </form>
        </c:if>

        <a href="search.jsp">Back to search page...</a>

    </body>
</html>
