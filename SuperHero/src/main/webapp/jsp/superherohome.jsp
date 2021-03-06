<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings </h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                  <li role="presentation" 
                      class="active">
                        <a href="${pageContext.request.contextPath}/home">
                            Home
                        </a>
                  </li>                
                  <li role="presentation">
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
                          <div class=""row>
                              Welcome to the handy-dandy SuperHero Sightings site.<br>
                              This site is currently tracking <c:out value="${totalSightings}"/> sightings of <c:out value="${totalHeros}"/> heros seen at <c:out value="${totalLocations}"/> locations.
                          </div>
                          <hr>
<div class="row">
    <!-- 
        Add a col to hold the summary table - have it take up half the row 
    -->
    <div class="col-md-10">
        <h2>Sightings</h2>
<table id="sightingTable" class="table table-hover">
    <tr>
        <th width="40%">Super Hero</th>
        <th width="40%">Location</th>
        <th width="20%">Date</th>

    </tr>
    
    

<c:forEach var="currentSighting" items="${sightingsList}">
    <tr>
        <td>
            <a href="editHero?heroId=${currentSighting.heroId}">
            <c:out value="${currentSighting.heroName}"/>
            </a>
        </td>
        <td>
             <a href="editLocation?locationId=${currentSighting.locationId}">          
             <c:out value="${currentSighting.locationName}"/><c:out value="${currentSighting.locationDescription}"/>
             </a>
        </td>
        <td>
            <a href="editSighting?sightingId=${currentSighting.sightingId}">
            <c:out value="${currentSighting.sightingDate}"/>
            </a>
        </td>
    </tr>
</c:forEach>
</table>                   
    </div> <!-- End col div -->


</div> <!-- End row div -->           </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

