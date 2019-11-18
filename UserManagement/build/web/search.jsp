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
        <c:set var="user" value="${sessionScope.USERLOGIN}"/>    
    <h5>Welcome, ${user}</h5>
    <form action="DispathController">
        <input type="submit" name="btAction" value="Log Out"/>
    </form>
    <form action="DispathController">
        Search: <input type="text" name="txtValueSearch" value="${param.txtValueSearch}">       
        <input type="submit" name="btAction" value="Search"/>
    </form>



    <c:set var="searchValue" value="${param.txtValueSearch}"/>
    <!-- create tab -->
    <c:set var="listRole" value="${LIST_ROLE}"/>
    <c:if test="${not empty listRole}">
        <c:url var="urlRewriting" value="DispathController">
            <c:param name="btAction" value="Search"/>                   
            <c:param name="txtValueSearch" value="${searchValue}"/>
        </c:url>
        <a href="${urlRewriting}">All</a>
        <c:forEach var="item" items="${listRole}">
            <c:url var="urlRewriting" value="DispathController">
                <c:param name="btAction" value="Search"/>
                <c:param name="roleSearchValue" value="${item.type}"/>
                <c:param name="txtValueSearch" value="${searchValue}"/>
            </c:url>
            <a href="${urlRewriting}">${item.type}</a>
        </c:forEach>
    </c:if>
    <br/>        
    <a href="createNewUser.jsp">Create new account</a>        
    <!-- create new table -->        
    <c:set var="result" value="${requestScope.RESULT_SEARCH}"/>      
    <c:if test="${not empty result}">           
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>User ID</th>
                    <th>User Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Role</th>
                    <th>Avatar</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${result}" varStatus="counter">
                <form action="DispathController">
                    <tr>
                        <td>${counter.count}</td>
                        <td>
                            ${item.userId}
                            <input type="hidden" name="txtUserId" value="${item.userId}"
                        </td>
                        <td>
                            ${item.username}
                            <input type="hidden" name="txtUsername" value="${item.username}"/>
                        </td>
                        <td>
                            ${item.email}
                            <input type="hidden" name="txtEmail" value="${item.email}"/>
                        </td>
                        <td>
                            ${item.phone}
                            <input type="hidden" name="txtPhone" value="${item.phone}"/>
                        </td>
                        <td>
                            ${item.role}
                            <input type="hidden" name="txtRole" value="${item.role}"/>
                        </td>
                        <td>
                            <img src="images/${item.image}" width="150" height="150">
                            <input type="hidden" name="image" value="${item.image}"/>
                        </td>
                        <td>
                            <c:url var="urlRewritingDelete" value="DispathController">
                                <c:param name="btAction" value="Delete"/>
                                <c:param name="txtUserId" value="${item.userId}"/>
                                <c:param name="lastSearchValue" value="${searchValue}"/>
                            </c:url>
                            <a href="${urlRewritingDelete}">Delete</a>
                        </td>
                        <td>
                            <input type="hidden" name="txtPassword" value="${item.password}"/>
                            <input type="submit" name="btAction" value="Edit"/>
                        </td>
                    </tr>
                </form>
            </c:forEach>                    
        </tbody>
    </table>
</c:if>    
</body>
</html>
