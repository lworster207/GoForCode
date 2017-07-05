<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create New Sighting</title>
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
                  <li role="presentation" >
                      <a href="${pageContext.request.contextPath}/displayHerosPage">
                          Heros
                      </a>
                  </li>

                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayOrganizations">
                          Organizations
                      </a>
                  </li>
                  <li role="presentation" class="active">
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
    <!-- 
        Add col to hold the new contact form - have it take up the other 
        half of the row
    -->
    <div class="col-md-6">
        <h2>Edit Sighting</h2>
        <sf:form class="form-horizontal" 
              role="form" method="POST" 
               modelAttribute="sighting"
              action="updateSighting">
             <sf:hidden path="sightingId" id="sightingId" value="${sighting.sightingId}" />
            <div class="form-group">
                <label for="heroId" class="col-md-4 control-label">Hero:</label>
                <div class="col-md-8">
                    
                    <sf:select id="heroId" name="heroId" path="heroId">
                        <c:forEach var="currentHero" items="${herosList}"> 
                            <option value="${currentHero.heroId}" ${currentHero.selected}>${currentHero.heroName}</option>
                        </c:forEach>  
                    </sf:select>
                    
                </div>              
            </div>
            <div class="form-group">
                <label for="locationId" class="col-md-4 control-label">Location:</label>
                <div class="col-md-8">
                    <sf:select id="locationId" name="locationId" path="locationId">
                        <c:forEach var="currentLocation" items="${locationsList}"> 
                            <option value="${currentLocation.locationId}" ${currentLocation.selected}>${currentLocation.locationName}</option>
                        </c:forEach>  
                                                 
                    </sf:select>
                   
                </div>
            </div>
            
            <div class="form-group">
                <label for="date" class="col-md-4 control-label">Date:</label>
                <div class="col-md-8">
                    <sf:input type="text" name="date" path="date" placeholder="Date"  />
                </div>
            </div>            
 
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Update Sighting"/>
                </div>
            </div>
        </sf:form>

    </div> <!-- End col div -->

</div> <!-- End row div -->   
        </div>
            <!-- Main Page Content Stop -->    
               <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
