<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
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
                      <a href="${pageContext.request.contextPath}/displayDvdsPage">
                          DVDs
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
        <h2>DVDs</h2>
<table id="dvdTable" class="table table-hover">
    <tr>
            public String title;
    public LocalDate releaseDate;
    public String mpaaRating;
    public String director;
    public String studio;
    public String userNote;
        <th width="40%">Title</th>
        <th width="15%">Release Date</th>
        <th width="15%">Rating</th>
        <th width="5%">Director</th>
        <th width="5%">Studio</th>
        <th width="15%">Notes</th>
        <th width="5%"></th>
    </tr>
    
<c:forEach var="currentDvd" items="${addressList}">
    <tr>
        <td>
            <a href="displayDvdDetails?dvdId=${currentDvd.dvdId}">
            <c:out value="${currentDvd.title}"/> 
            </a>
        </td>
        <td>
            <c:out value="${currentDvd.releaseDate}"/>
        </td>
        <td>
            <c:out value="${currentDvd.mpaaRating}"/>
        </td>
        <td>
            <c:out value="${currentDvd.director}"/>
        </td>
        <td>
            <c:out value="${currentDvd.userNote}"/>
        </td>       
        <td>
            <a href="deleteDvd?dvdId=${currentDvd.dvdId}">
            Delete
            </a>
        </td>
    </tr>
    
</c:forEach>
    
</table> 
        <c:out value="Dvd Count: ${count}"/>
    </div> <!-- End col div -->
    <!-- 
        Add col to hold the new contact form - have it take up the other 
        half of the row
    -->
    <div class="col-md-6">
        <h2>Add New Dvd</h2>
        <form class="form-horizontal" 
              role="form" method="POST" 
              action="createDvd">
            <div class="form-group">
                <label for="add-title" class="col-md-4 control-label">Title:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="add-title" placeholder="Title"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-release-date" class="col-md-4 control-label">Release Date:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="add-release-date" placeholder="Release Date"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-mpaaRating" class="col-md-4 control-label">MPAA Rating:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="add-mpaaRating" placeholder="Rating"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-director" class="col-md-4 control-label">Director:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="add-director" placeholder="Director"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-note" class="col-md-4 control-label">Note:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="add-note" placeholder="Notes"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create Dvd"/>
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
