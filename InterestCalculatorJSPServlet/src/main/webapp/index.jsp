<%-- 
    Document   : index
    Created on : May 30, 2017, 5:23:59 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="InterestCalculatorJSPServlet">
            Interest Rate:
            <input type="text" name="rate"/>
            <p>Number of Years: 
            <input type="text" name="years"/>
            <p>Initial Principal:
            <input type="text" name="principal"/>
            <p><input type="submit" value="Calculate Interest"/>
        </form>
    </body>
</html>
