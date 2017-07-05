<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Heros - Sightings</title>
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
                  <li role="presentation"  class="active">
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
        <h2>Sightings</h2>
<table id="sightingTable" class="table table-hover">
    <tr>
        <th width="30%">Super Hero</th>
        <th width="30%">Location</th>
        <th width="20%">Date</th>
        <th width="10%"></th>
        <th width="10%"></th>
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
        <td>
            <a href="editSighting?sightingId=${currentSighting.sightingId}">
            Edit
            </a>
        </td>
        <td>
            <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
            Delete
            </a>
        </td>
    </tr>
</c:forEach>
</table>                   
    </div> <!-- End col div -->
    <!-- 
        Add col to hold the new sighting form - have it take up the other 
        half of the row
    -->
    <div class="col-md-2">
        <a href="createNewSighting">New Sighting</a>
        <c:if test="${herosList.size() > 0}"  >
            <form action="displaySightingsByHero" method="POST">
            <div class="form-group">
                <label for="heroFilter" class="col-md-4 control-label">Heros:</label>
                <div class="col-md-8">
                    <select class="form-control" id="heroId" name="heroId">
                        <c:forEach var="currentHero" items="${herosList}"> 
                            <option value="${currentHero.heroId}">${currentHero.heroName}</option>
                        </c:forEach>                          
                    </select>
                </div>
                <input type="submit" class="btn btn-default" value="Filter By Hero"/>
            </div> 
            </form>
        </c:if>

        <c:if test="${locationsList.size() > 0}"  >
            <form action="displaySightingsByLocation" method="POST">
            <div class="form-group">
                <label for="heroFilter" class="col-md-4 control-label">Locations:</label>
                <div class="col-md-8">
                    <select class="form-control" id="locationId" name="locationId">
                        <c:forEach var="currentLocation" items="${locationsList}"> 
                            <option value="${currentLocation.locationId}">${currentLocation.locationName}(${currentLocation.locationDescription})</option>
                        </c:forEach>                          
                    </select>
                </div>
                <input type="submit" class="btn btn-default" value="Filter By Location"/>
            </div> 
            </form>
        </c:if>
        <c:if test="${dateList.size() > 0}"  >
            <form action="displaySightingsByDate" method="POST">
            <div class="form-group">
                <label for="heroFilter" class="col-md-4 control-label">Dates:</label>
                <div class="col-md-8">
                    <select class="form-control" id="date" name="date">
                        <c:forEach var="currentDate" items="${dateList}"> 
                            <option value="${currentDate}">${currentDate}</option>
                        </c:forEach>                          
                    </select>
                </div>
                <input type="submit" class="btn btn-default" value="Filter By Date"/>
            </div> 
            </form>
        </c:if> 
            <form action="displaySightings" method="GET">
            <div class="form-group">
                <input type="submit" class="btn btn-default" value="Show All Sightings"/>
            </div> 
            </form>
    </div> <!-- End col div -->

</div> <!-- End row div -->   
        </div>
            <!-- Main Page Content Stop -->    
               <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
