<%-- 
    Document   : result
    Created on : May 30, 2017, 5:31:51 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flooring Calculator</title>
    </head>
    <body>
        <p>Width: ${width}
        <p>Length: ${length}
        <p>Cost per Square Foot: ${cost}
        <p>Total Cost: ${totalCost}    


                    <c:out value="The number is${totalCost}"/>


       
        <p>
            <a href="index.jsp">Calculate another floor</a>
        </p>
        
    </body>
</html>
