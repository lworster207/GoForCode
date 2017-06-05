<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        
        
        <div class="container">
            <h1>Spring MVC Application from Archetype</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayVendingMachine">VendingMachine</a></li>
                </ul>    
            </div>
                
                        <%
                            response.setStatus(response.SC_MOVED_TEMPORARILY);
                            response.setHeader("Location", "http://localhost:8080/displayVendingMachine/displayVendingMachine");
                        %>
        </div>                
 
    </body>
</html>

