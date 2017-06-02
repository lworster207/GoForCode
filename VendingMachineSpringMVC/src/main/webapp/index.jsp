<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Spring MVC Application from Archetype</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                </ul>    
            </div>
            <h2>Vending Machine Spring MVC</h2>
                  <div class="container">
            <h1>Vending Machine - index.jsp</h1>
            <hr/>
            <ul class="list-group" id="errorMessages"></ul>
            <!--
            Add a row to our container - this will hold the summary table and the new contact form.
            -->
          <!--  <div class="row"> -->
                <!-- #2: Add a col to hold the summary table - have it take up half the row -->
                <div class="col-md-6">
                     <h2>Available Items</h2>
                     <form class="form-horizontal" role="form" id="item-form">
                    <div class="form-group" id="itemTableDiv">
<table id="contactTable" class="table table-hover">
    <tr>
        <th width="40%">Item Name</th>
        <th width="30%">Price</th>
        <th width="30%">Quantity</th>
    </tr>
<c:forEach var="currentItem" items="${itemList}">
    <tr>
        <td>
            <a href="displayContactDetails?contactId=${currentItem.name}">
            <c:out value="${currentItem.name}"/>
            </a>
        </td>
        <td>
            <c:out value="$${currentItem.price}"/>
        </td>
        <td>
            <c:out value="$${currentItem.qty}"/>
        </td>
    </tr>
</c:forEach>
</table>                   
                       
                    </div>
                     </form>    

                </div> <!-- End col div -->
                <!--
                #4: Add col to hold the new contact form - have it take up the other half of the row
                -->
                
                <div class="col-md-6">
                    <div class="container" id="view">
                        <form class="form-horizontal" role="form" id="money-form">
                            <div class="row">
                                 <div class="form-control-static">  
                                     <div class="col-md-6">
                                       <h2 align='center'>Total $ In</h2> 
                                     </div>
                                 </div>
                            </div>    
                            <div class="row">
                                <div class="form-control-static">
                                    <div class="col-md-6">
                                        <div align='center' id='money-total'>0.00</div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div align='center' class="col-md-3">
                                    <div class="form-control-static">
                                    
                                        <button type="button"
                                                id="add-dollar-button"
                                                class="btn btn-default">
                                            Add Dollar
                                        </button>
                                    </div>
                                </div>
                                <div class="form-control-static">
                                    <div align='center' class="col-md-3">
                                        <button type="button"
                                                id="add-quarter-button"
                                                class="btn btn-default">
                                            Add Quarter
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                 <div align='center' class="col-md-3">
                                    <div class="form-control-static">
                                        <button type="button"
                                                id="add-dime-button"
                                                class="btn btn-default">
                                            Add Dime
                                        </button>
                                    </div>
                                </div>
                                    <div align='center' class="col-md-3">
                                       <div class="form-control-static">

                                        <button type="button"
                                                id="add-nickel-button"
                                                class="btn btn-default">
                                            Add Nickel
                                        </button>
                                    </div>
                                </div>
                            </div>

                        </form>
                        <hr>
                       
                        <div class="row">
                            <form class="form-horizontal" role="form" id="messages-form">
                                <div class="row">
                                     <div class="form-control-static">  
                                         <div class="col-md-6">
                                           <h2 align='center'>Messages</h2> 
                                         </div>
                                     </div>
                                </div>  
                                <div class="row">
                                   <div class="form-control-static col-md-6">
                                      
                                           <input type="text"  id='messages'>
                                      
                                   </div>
                                </div>  
                                <div class="row">   
                                   <div class="form-control-static">
                                    <div class="col-md-6">
                                        <label for="item">Item</label>
                                        <input type="text" id="item">
                                    </div>
                                   </div>
                                </div>
                                <div class='row'>
                                    <div class="form-control-static">
                                    <div class="col-md-6">
                                           <button type="button"
                                                   id="make-purchase-button"
                                                   class="btn btn-default">
                                               Make Purchase
                                           </button>
                                    </div>
                                    </div>
                                </div>    
               
                            </form>
                        </div>
                       
                            
                        <hr>
                        <div class="row">
                            <form class="form-horizontal" role="form" id="messages-form">
                                <div class="row">
                                     <div class="form-control-static">  
                                         <div class="col-md-6">
                                           <h2 align='center'>Change</h2> 
                                         </div>
                                     </div>
                                </div>  
                                <div class="row">
                                   <div class='col-md-6'> 
                                   <div class="form-control-static">
                                      
                                           <input type="text"  id='change'>
                                      
                                   </div>
                                   </div>
                                </div>  
                                <div class='row'>
                                    <div class="form-control-static">
                                    <div class="col-md-6">
                                           <button type="button"
                                                   id="change-return-button"
                                                   class="btn btn-default">
                                               Change Return
                                           </button>
                                    </div>
                                    </div>
                                </div>    
               
                            </form>
                        </div>                        
                    </div>    
                </div> <!-- End col div -->
               
           <!--  </div> --> <!-- End row div -->
        </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>

    </body>
</html>

