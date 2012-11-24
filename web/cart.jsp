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
        <script type="text/javascript">
            function setValue(dt)
            {
                alert(dt);
                var x=document.getElementById("hdnBookId");
                x.value=dt;
                alert(x.value);
            }
        </script>
    </head>
    <body>        
        <div style="float: left;"><a href="BookController">Home</a></div>
        <div style="float: right;"><a href="LoginController">Log out</a></div>
        <div style="clear:both">
            <table border="5" align="center">
                <tr>
                    <td>
                        Item Image
                    </td>
                    <td>
                        Book Title
                    </td>
                    <td>
                        Price
                    </td>
                    <td>
                        Quantity
                    </td>
                    <td>
                        Remove from cart
                    </td>
                </tr>
                <c:forEach items="${requestScope.books}" var="book">

                    <tr>                    
                        <td align="center">
                            <img src="img/${book.key.image}" href="no image" width="80" height="90"/>
                        </td>     
                        <td align="center">
                            <c:out value="${book.key.name}"/>
                        </td>
                        <td align="center">
                            <c:out value="${book.key.price}"/>    
                        </td>                                      
                        <td align="center">
                            <c:out value="${book.value}"/>    
                        </td>     
                    <form action="CartController" method="post">
                        <td align="center">                                
                            <input type="submit" onclick="setValue('${book.key.id}');" value="Remove From Cart" />
                        </td>
                        <input type="hidden" value="" id="hdnBookId" name="hdnBookId"/>
                    </form>
                    </tr>
                </c:forEach>
                    
                <form action="OrderController" method="get">
                    <div style="float: right"><input type="submit" value="place order"/></div>
                </form>
                    
            </table>
        </div>            
</body>
</html>
