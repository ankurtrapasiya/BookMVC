<%-- 
    Document   : index
    Created on : 28 Aug, 2012, 2:19:19 PM
    Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table align="center">
            <form method="post" action="LoginController">
                <tr>
                    <td>Enter username</td>
                    <td><input type="text" name="txtUsername"></td>
                </tr>
                <tr>
                    <td>
                        Enter password</td>
                    <td><input type="password" name="txtPassword"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right"><input type="submit" value="login" name="btnLogin"/></td>
                    <td align="left"><input type="reset" value="reset" name="btnReset"/></td>
                </tr>
            </form>
        </table>
    </body>
</html>
