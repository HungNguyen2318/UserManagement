<%-- 
    Document   : user
    Created on : Nov 15, 2019, 10:47:13 PM
    Author     : SE130008
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER Page</title>
    </head>
    <body>
        <h1>Hello , ${requestScope.USER_PROFILE.username}</h1>
        <form action="DispathController">
            <input type="submit" name="btAction" value="Log Out"/>
        </form>
        <c:set var="item" value="${requestScope.USER_PROFILE}"/>
        <c:if test="${not empty item}">
            <table border="1">
                <thead>
                    <tr>                       
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <th>Avatar</th>
                    </tr>
                </thead>
                <tbody>                   
                    <tr>                            
                        <td>
                            ${item.userId}
                        </td>
                        <td>
                            ${item.username}
                        </td>
                        <td>
                            ${item.email}
                        </td>
                        <td>
                            ${item.phone}
                        </td>
                        <td>
                            ${item.role}
                        </td>
                        <td>
                            <img src="images/${item.image}" width="150" height="150">
                        </td>
                    </tr>                   
                </tbody>
            </table>
        </c:if>
    </body>
</html>
