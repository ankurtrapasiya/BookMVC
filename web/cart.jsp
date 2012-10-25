<%-- 
    Document   : cart
    Created on : 29 Aug, 2012, 12:32:55 PM
    Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <c:forEach items="${sessionScope.books}" var="book">

                <tr>                    
                    <td align="center">
                        <img src="img/${book.value.image}" href="no image"/>
                    </td>     
                    <td align="center">
                        <c:out value="${book.value.name}"/>
                    </td>
                    <td align="center">
                        <c:out value="${book.value.ISBN}"/>
                    </td>
                    <td align="center">
                        <c:out value="${book.value.title}"/>    
                    </td>
                    <td align="center">
                        <c:out value="${book.value.publisher}"/>    
                    </td>
                    <td align="center">
                        <c:out value="${book.value.price}"/>    
                    </td>                   
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
