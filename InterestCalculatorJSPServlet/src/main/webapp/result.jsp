<%-- 
    Document   : result
    Created on : May 30, 2017, 5:24:21 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Interest Calculator</title>
    </head>
    <body>
            Yearly Interest...
        <table>

            <c:forEach var="currentYear" items="${annualList}">
                <tr>
                   
                       <c:out value="${currentYear}"/>
                    
                 
                </tr>
            </c:forEach>
        </table>             
    </body>
</html>
