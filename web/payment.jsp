<%-- 
    Document   : payment
    Created on : 24 Nov, 2012, 5:18:23 PM
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
        <form action="OrderController" method="post">

            <table>
                <tr>
                    <td>
                        Select Payment Type
                    </td>
                    <td>
                        <select name="comboPayType">
                            <option value="1">Net Banking</option>
                            <option value="2">Credit Card</option>
                            <option value="3">Debit Card</option>
                            <option value="4">Cash On Delivery</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Enter Shipping Address
                    </td>
                    <td>
                        <textarea name="shippingAddress" rows="4" cols="50">
                        </textarea>
                    </td></tr>
                <tr>
                    <td>
                        <input type="submit" value="proceed" name="btnProceed"/>
                    </td>
                    <td>
                        <input type="reset" value="reset" name="btnReset"/>
                    </td>
                </tr>

            </table>                        

        </form>
    </body>
</html>
