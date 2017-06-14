<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Address Book</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Company Contacts</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"> 
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                  </li>
                  <li role="presentation"
                      class="active">
                      <a href="${pageContext.request.contextPath}/displayAddressesPage">
                          Contacts
                      </a>
                  </li>
                </ul>    
            </div>
            <!-- Main Page Content Start -->
<div class="row">
    <!-- 
        Add a col to hold the summary table - have it take up half the row 
    -->
    <div class="col-md-6">
        <h2>Addresses</h2>
<table id="addressTable" class="table table-hover">
    <tr>
        <th width="40%">Name</th>
        <th width="30%">Street</th>
        <th width="15%">City</th>
        <th width="5%">State</th>
        <th width="5%">PostCode</th>
        <th width="5%"></th>
    </tr>
<c:forEach var="currentAddress" items="${addressList}">
    <tr>
        <td>
            <a href="displayAddressDetails?addressId=${currentAddress.addressId}">
            <c:out value="${currentAddress.firstName}"/> <c:out value="${currentAddress.lastName}"/>
            </a>
        </td>
        <td>
            <c:out value="${currentAddress.street}"/>
        </td>
        <td>
            <c:out value="${currentAddress.city}"/>
        </td>
        <td>
            <c:out value="${currentAddress.state}"/>
        </td>
        <td>
            <c:out value="${currentAddress.postCode}"/>
        </td>       
        <td>
            <a href="deleteAddress?addressId=${currentAddress.addressId}">
            Delete
            </a>
        </td>
    </tr>
    
</c:forEach>
    
</table> 
        <c:out value="Address Count: ${count}"/>
    </div> <!-- End col div -->
    <!-- 
        Add col to hold the new contact form - have it take up the other 
        half of the row
    -->
    <div class="col-md-6">
        <h2>Add New Address</h2>
        <form class="form-horizontal" 
              role="form" method="POST" 
              action="createAddress">
            <div class="form-group">
                <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="firstName" placeholder="First Name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="lastName" placeholder="Last Name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-street" class="col-md-4 control-label">Street:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="street" placeholder="Street"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-city" class="col-md-4 control-label">City:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="city" placeholder="City"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-state" class="col-md-4 control-label">State:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="state" placeholder="State"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-postcode" class="col-md-4 control-label">PostCode:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="postcode" placeholder="PostCode"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create Address"/>
                </div>
            </div>
        </form>

    </div> <!-- End col div -->

</div> <!-- End row div -->            
            <!-- Main Page Content Stop -->    
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
