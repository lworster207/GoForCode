<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>View Super Hero</title>
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
    <!-- 
        Add col to hold the new contact form - have it take up the other 
        half of the row
    -->
    <div class="col-md-6">
        <h2>Super Hero Details</h2>
        
        <sf:form class="form-horizontal" 
              role="form" method="POST"
               modelAttribute="hero"
              action="editHero">
            <div class="form-group">
                <label for="add-hero-name" class="col-md-4 control-label">Hero Name:</label>
                <div class="col-md-8">
                    <input type="hidden" id="add-hero-id" value="<c:out value='${hero.heroId}' />" />
                    <input type="hidden" id="add-hero-contactid" value="<c:out value='${hero.contactId}' />" />    
                    <input type="hidden" id="add-hero-addressid" value="<c:out value='${address.addressId}' />" /> 
                    <sf:hidden path="contactId" id="contactId" name="contactId" value="${hero.contactId}" />
                    <sf:hidden path="heroId" id="heroId" name="heroId" value="${hero.heroId}" />
                    <sf:input type="text" class="form-control" path="heroName" id="heroName"  name="heroName" placeholder="Hero Name"/>
                    <sf:errors path="heroName" cssclass="error"></sf:errors>
                </div>
                
            </div>
            <div class="form-group">
                <label for="add-super-power" class="col-md-4 control-label">Super Power:</label>
                <div class="col-md-8">
                <label class="col-md-4 control-label">
                        <c:forEach var="currentPower" items="${powersList}">
                            <c:out value=" ${currentPower.description}"/>
                        </c:forEach>                    
                </label>                     
                </div>
            </div>           
            <div class="form-group">
                <label for="add-organizations" class="col-md-4 control-label">Organizations:</label>
                 <div class="col-md-8">
                <label class="col-md-4 control-label">
                        <c:forEach var="currentOrg" items="${organizationsList}"> 
                            <c:out value=" ${currentOrg.orgName}"/>
                        </c:forEach>                    
                </label>
                 </div>
            </div> 
            <div class="form-group">
                <label for="add-sightings" class="col-md-4 control-label">Sightings:</label>
                <div class="col-md-8">
                    <select multiple class="form-control" id="add-sightings" name="add-sightings">
                        <c:forEach var="currentSighting" items="${sightingsList}"> 
                            <option value="${currentSighting.sightingId}" >${currentSighting.sightingId} ${currentSighting.locationId}</option>
                        </c:forEach>                          
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="add-description" class="col-md-4 control-label">Description:</label>
                <div class="col-md-8">
                    
                    <sf:input type="text" class="form-control" path="description" id="description" name="description" placeholder="Description" value="${hero.description}"/>
                </div>
            </div>                        
            <div class="form-group">
                <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-first-name" name="add-first-name" placeholder="First Name" value="<c:out value='${contact.firstName}' />"/>
                </div>
            </div>            
            <div class="form-group">
                <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-last-name" name="add-last-name" placeholder="Last Name" value="<c:out value='${contact.lastName}' />"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-address" class="col-md-4 control-label">Address:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-address" name="add-address" placeholder="Address" value="<c:out value='${address.streetAddress}' />"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-city" class="col-md-4 control-label">City:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-city" name="add-city" placeholder="City" value="<c:out value='${address.city}' />"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-state" class="col-md-4 control-label">State:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-state" name="add-state" placeholder="State/Province" value="<c:out value='${address.stateProvince}' />"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-postcode" class="col-md-4 control-label">Post Code:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="add-postcode" name="add-postcode" placeholder="PostCode" value="<c:out value='${address.postCode}' />"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-email" class="col-md-4 control-label">Email:</label>
                <div class="col-md-8">
                    <input type="email" class="form-control" id="add-email" name="add-email" placeholder="Email" value="<c:out value='${contact.email}' />"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                <div class="col-md-8">
                    <input type="tel" class="form-control" id="add-phone" name="add-phone" placeholder="Phone" value="<c:out value='${contact.phone}' />"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Edit Super Hero"/>
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
