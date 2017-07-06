<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Heros - Locations</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <script src="${pageContext.request.contextPath}/js/superheros.js"></script>
    </head>
    <body>
        
        
		<c:if test="${not empty error}">
                    <div class="row">
                      <div class="col-md-6">  
                          <div class="alert alert-danger alert-dismissable">
                             <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                             <strong>${error}</strong>
                          </div>  
                      </div>
                    </div>
                </c:if>
        
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
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displaySightings">
                          Sightings
                      </a>
                  </li>
                  <li role="presentation"  class="active">
                      <a href="${pageContext.request.contextPath}/displayLocations">
                          Locations
                      </a>
                  </li>
                </ul>    
            </div>
     <!--   </div> -->
            <!-- Main Page Content Start -->
            <c:if test="${error}">
                
            </c:if>
<div class="row">
    <!-- 
        Add a col to hold the summary table - have it take up half the row 
    -->
    <div class="col-md-10">
        <h2>Locations</h2>
<table id="locationTable" class="table table-hover">
    <tr>
        <th width="30%">Name</th>
        <th width="30%">Description</th>
        <th width="20%">Latitude</th>
        <th width="20%">Longitude</th>
        <th width="5%"></th>
        <th width="5%"></th>
    </tr>
<c:forEach var="currentLocation" items="${locationList}">
    <tr>
        <td>
            <a href="editLocation?locationId=${currentLocation.locationId}">
            <c:out value="${currentLocation.locationName}"/>
            </a>
        </td>
        <td>
            <c:out value="${currentLocation.locationDescription}"/>
        </td>
        <td>
            <c:out value="${currentLocation.latitude}"/>
        </td>
        <td>
            <c:out value="${currentLocation.longitude}"/>
        </td>
        <td>
            <a href="editLocation?locationId=${currentLocation.locationId}">
            Edit
            </a>
        </td>
        <td>
            <a href="deleteLocation?locationId=${currentLocation.locationId}">
            Delete
            </a>
        </td>
    </tr>
</c:forEach>
</table>                   
    </div> <!-- End col div -->
    <!-- 
        Add col to hold the new location form - have it take up the other 
        half of the row
    -->
    <div class="col-md-2">
        <a href="createNewLocation">Create New Location</a>

    </div> <!-- End col div -->

</div> <!-- End row div -->   
        </div>
            <!-- Main Page Content Stop -->    
               <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
 
    </body>
</html>
