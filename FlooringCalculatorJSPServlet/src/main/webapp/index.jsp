<%-- 
    Document   : index
    Created on : May 30, 2017, 5:31:12 PM
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
        <form method="post" action="FlooringCalculatorJSPServlet">
            Width:
            <input type="text" name="width"/>
            <p>Length: 
            <input type="text" name="length"/>
            <p>Cost per Square foot:
            <input type="text" name="cost"/>
            <p><input type="submit" value="Calculate"/>
        </form>
    </body>
</html>
