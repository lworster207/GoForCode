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
                        <a href="${pageContext.request.contextPath}/index.jsp">
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
            <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
            <c:out value="${currentSighting.heroId}"/>
            </a>
        </td>
        <td>
            <c:out value="${currentSighting.locationId}"/>
        </td>
        <td>
            <c:out value="${currentSighting.date}"/>
        </td>
        <td>
            <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
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

    </div> <!-- End col div -->

</div> <!-- End row div -->   
        </div>
            <!-- Main Page Content Stop -->    
               <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
