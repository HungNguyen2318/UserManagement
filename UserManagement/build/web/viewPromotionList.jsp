<%-- 
    Document   : viewPromotionList
    Created on : Nov 18, 2019, 10:14:11 PM
    Author     : SE130008
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USERLOGIN}"/>    
        <h5>Welcome, ${user}</h5>
        <a href="search.jsp">Back to search page...</a>
        <form action="DispathController">
            <input type="submit" name="btAction" value="Log Out"/>
        </form>
        <c:set var="proList" value="${sessionScope.PROMOTION}"/>
        <c:if test="${not empty proList}">
            <c:set var="items" value="${proList.items}"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>UserID</th>
                        <th>Rank</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                <form action="DispathController">
                    <c:forEach var="item" items="${items}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${item.key}
                                <input type="hidden" name="txtUserId${counter.count}" value="${item.key}" />
                            </td>
                            <td>
                                <select name="cbxRank${counter.count}">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option selected>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                </select>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">
                            <a href="DispatchServlet?btAction=SearchPage">Click here to add more user to promotion list</a>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <input type="submit" name="btAction" value="Confirm"/>
                        </td>
                </form>
            </tr>           
        </tbody>
    </table>
</c:if>

</body>
</html>
