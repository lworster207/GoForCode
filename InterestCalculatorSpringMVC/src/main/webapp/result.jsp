<%-- 
    Document   : result
    Created on : May 31, 2017, 4:06:06 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         
        Interest Rate: <c:out value="${inRate}"></c:out>
            Years: <c:out value="${rate}"></c:out>    
            Principal: <c:out value="${principal}"></c:out>
            Annually: <c:out value="${annualList}"></c:out>

    </body>
</html>
