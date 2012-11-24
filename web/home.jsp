<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*;"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BookList</title>
        <script type="text/javascript">
            function setValue(dt)
            {
                var x=document.getElementById("hdnBookId");
                x.value=dt;
            }
        </script>
    </head>
    <body>
        <div style="float: left;"><a href="CartController">view cart</a></div>
        <div style="float: right;"><a href="LoginController">Log out</a></div>
        <div style="clear: both">
            <form action="BookList" method="post" >
                <table width="100%" >

                    <c:forEach items="${requestScope.bookList}" var="book">
                        <tr>
                            <td align="center">
                                <img src="img/${book.image}" href="no image"/>                                                        
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <b>Name:</b> <c:out value="${book.name}"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <b>ISBN:</b><c:out value="${book.ISBN}"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <b>Title:</b> <c:out value="${book.title}"/>    
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <b>Publisher:</b> <c:out value="${book.publisher}"/>    
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <b>Price:</b> <c:out value="${book.price}"/>    
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <b>Authors:</b> <c:forEach items="${book.authors}" var="item">
                                    <c:out value="${item.name}"/>,
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <input type="submit" onclick="setValue('${book.id}');" value="Add To Cart" />
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>   
                    </c:forEach>        

                </table>
                <input type="hidden" id="hdnBookId" value="" name="hdnBookId"/>
            </form>
        </div>
    </body>
</html>