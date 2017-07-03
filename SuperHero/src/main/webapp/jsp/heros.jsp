<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Heros</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings </h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                  <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                  </li>
                  <li role="presentation" class="active">
                      <a href="${pageContext.request.contextPath}/displayHerosPage">
                          Heros
                      </a>
                  </li>

                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayOrganizations">
                          Organizations
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displaySightings">
                          Sightings
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayLocations">
                          Locations
                      </a>
                  </li>
                </ul>    
            </div>
     <!--   </div> -->
            <!-- Main Page Content Start -->
<div class="row">
    <!-- 
        Add a col to hold the summary table - have it take up half the row 
    -->
    <div class="col-md-10">
        <h2>Super Heros</h2>
        
        
<table id="heroTable" class="table table-hover">
    <tr>
        <th width="20%">Super Hero Name</th>
        <th width="20%">Super Power</th>
        <th width="30%">Description</th>       
        <th width="15%"></th>
        <th width="15%"></th>
    </tr>
<c:forEach var="currentHero" items="${heroList}">
    <tr>
        <td>
            <a href="viewHero?heroId=${currentHero.heroId}">
            <c:out value="${currentHero.heroName}"/>
            </a>
        </td>
        <td>
            <c:out value="${currentHero.heroId}"/>
        </td>
        <td>
            <c:out value="${currentHero.description}"/>
        </td>
        <td>
            <a href="editHero?heroId=${currentHero.heroId}">
            Edit
            </a>
        </td>
        <td>
            <a href="deleteHero?heroId=${currentHero.heroId}&contactId=${currentHero.contactId}">
            Delete
            </a>
        </td>
    </tr>
</c:forEach>
</table>                   
    </div> <!-- End col div -->
    <!-- 
        Add col to hold the new hero form - have it take up the other 
        half of the row
    -->
    <div class="col-md-2">
        <a href="createNewHero">Create New Super Hero</a>

    </div> <!-- End col div -->

</div> <!-- End row div -->   
        </div>
            <!-- Main Page Content Stop -->    
               <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superheros.js"></script>
        

    </body>
</html>
