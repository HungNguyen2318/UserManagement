<%-- 
    Document   : search
    Created on : Nov 13, 2019, 4:50:31 PM
    Author     : SE130008
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
        <td><img src="images/user01.png" width="120" height="150"></td>
        <td><img src="images/admin01.jpg" width="120" height="150"></td>
        <c:set var="user" value="${sessionScope.USERLOGIN}"/>
        <form action="DispathController">
            <input type="submit" name="btAction" value="Log Out"/>
        </form>
        <h5>Welcome, ${user}</h5>
        <form action="DispathController">
            Search: <input type="text" name="txtValueSearch" value="${param.txtValueSearch}">
            <input type="submit" name="btAction" value="Search"/>
        </form>
            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>

                </c:if>
            </c:if>    
    </body>
</html>
