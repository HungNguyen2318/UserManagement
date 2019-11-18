<%-- 
    Document   : viewPromotionHistory
    Created on : Nov 18, 2019, 11:44:11 PM
    Author     : SE130008
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Promotion History</h1>
        <c:set var="historyList" value="${sessionScope.HISTORY}"/>
        <c:if test="${not empty historyList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>UserID</th>
                        <th>Rank</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${historyList}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.userId}</td>
                            <td>${dto.rank}</td>
                            <td>${dto.time}</td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>            
        </c:if>
        <c:if test="${empty historyList}">
            <h2>No history of promotion list is existed </h2>
        </c:if>
        <a href="search.jsp">Click here to back the search page</a>
    </body>
</html>
