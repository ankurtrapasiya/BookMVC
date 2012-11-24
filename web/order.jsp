<%-- 
    Document   : order
    Created on : 24 Nov, 2012, 4:23:17 PM
    Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>

        <c:if test="${requestScope.status  eq 'success'}">            

            Order Placed Successfully

        </c:if>
        <c:if test="${requestScope.status eq 'failed'}" >            

            There were problem placing your order. Sorry

        </c:if>        
    </body>
</html>
