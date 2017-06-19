<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create New Location</title>
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
                  <li role="presentation" >
                      <a href="${pageContext.request.contextPath}/displaySightings">
                          Sightings
                      </a>
                  </li>
                  <li role="presentation" class="active">
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
        <h2>New Location</h2>
        <form class="form-horizontal" 
              role="form" method="POST" 
              action="createLocation">
             <div class="form-group">
                <label for="add-loc-name" class="col-md-4 control-label">Location Name:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-loc-name"  name="add-loc-name" placeholder="Location Name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-loc-description" class="col-md-4 control-label">Description:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-loc-description" name="add-loc-description" placeholder="Description"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-address" class="col-md-4 control-label">Address:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-address" name="add-address" placeholder="Address"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-city" class="col-md-4 control-label">City:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-city" name="add-city" placeholder="Address"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-state" class="col-md-4 control-label">State:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-state" name="add-state" placeholder="State/Province"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-postcode" class="col-md-4 control-label">Post Code:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-postcode" name="add-postcode" placeholder="PostCode"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Add Location"/>
                </div>
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