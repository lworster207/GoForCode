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
<div class="row">
    <!-- 
        Add a col to hold the summary table - have it take up half the row 
    -->
    <div class="col-md-10">
        <h2>Locations</h2>
<table id="contactTable" class="table table-hover">
    <tr>
        <th width="20%">Name</th>
        <th width="20%">Description</th>
        <th width="40%">Address</th>
        <th width="10%">Latitude</th>
        <th width="10%">Longitude</th>
        <th width="5%"></th>
        <th width="5%"></th>
    </tr>
<c:forEach var="currentContact" items="${contactList}">
    <tr>
        <td>
            <a href="displayContactDetails?contactId=${currentContact.contactId}">
            <c:out value="${currentContact.firstName}"/> <c:out value="${currentContact.lastName}"/>
            </a>
        </td>
        <td>
            <c:out value="${currentContact.description}"/>
        </td>
        <td>
            <c:out value="${currentContact.address}"/>
        </td>
        <td>
            <c:out value="${currentContact.latitude}"/>
        </td>
        <td>
            <c:out value="${currentContact.longitude}"/>
        </td>
        <td>
            <a href="displayEditContactForm?contactId=${currentContact.contactId}">
            Edit
            </a>
        </td>
        <td>
            <a href="deleteContact?contactId=${currentContact.contactId}">
            Delete
            </a>
        </td>
    </tr>
</c:forEach>
</table>                   
    </div> <!-- End col div -->
    <!-- 
        Add col to hold the new contact form - have it take up the other 
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
