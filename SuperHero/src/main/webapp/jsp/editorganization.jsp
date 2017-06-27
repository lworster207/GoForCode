<%-- 
    Document   : editorganization
    Created on : Jun 25, 2017, 9:37:35 PM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Super Hero</title>
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

                  <li role="presentation"  class="active">
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
    <!-- 
        Add col to hold the new contact form - have it take up the other 
        half of the row
    -->
    <div class="col-md-6">
        <h2>Edit Organization</h2>
        <sf:form class="form-horizontal" 
              role="form" method="POST" 
              modelAttribute="organization"
              action="updateOrganization"> 
             <sf:hidden id="organizationId" name="organizationId"  path="organizationId" value="${organization.organizationId}" />
             <sf:hidden id="addressId" name="addressId"  path="addressId" value="${organization.addressId}" />
             <sf:hidden id="contactId" name="contactId"  path="contactId" value="${organization.addressId}" />
            <div class="form-group">
                <label for="orgName" class="col-md-4 control-label">Organization Name:</label>
                <div class="col-md-8">
                   
                    <sf:input type="text" class="form-control"  id="orgName" name="orgName"  path="orgName" placeholder="Organization Name"   value="${organization.orgName}" />
                    <sf:errors path="orgName" cssclass="error"></sf:errors>
                    <input type="hidden" id="add-org-addressid" value="<c:out value='${address.addressId}' />" /> 
                </div>
            </div>
            <div class="form-group">
                <label for="orgDescription" class="col-md-4 control-label">Description:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="orgDescription" name="orgDescription" path="orgDescription" placeholder="Description" value="${organization.orgDescription}" />
                    <sf:errors path="orgDescription" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-address" class="col-md-4 control-label">Address:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-address" name="add-address" placeholder="Address" value="${address.streetAddress}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-city" class="col-md-4 control-label">City:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-city" name="add-city" placeholder="City" value="${address.city}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-state" class="col-md-4 control-label">State:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-state" name="add-state" placeholder="State/Province" value="${address.stateProvince}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-postcode" class="col-md-4 control-label">Post Code:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-postcode" name="add-postcode" placeholder="PostCode" value="${address.postCode}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Update Organization"/>
                    <input type="button" class="btn btn-default" id="cancel" name="cancel" value="Cancel" />
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
        <script src="${pageContext.request.contextPath}/js/superheros.js"></script>


    </body>
</html>

