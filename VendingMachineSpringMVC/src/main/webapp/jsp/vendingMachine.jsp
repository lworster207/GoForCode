<%-- 
    Document   : vendingMachine
    Created on : Jun 2, 2017, 11:22:11 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Vending Machine</h1>
            <hr/>
            <ul class="list-group" id="errorMessages">
            </ul>
            <!--
            Add a row to our container - this will hold the summary table and the new contact form.
            -->
          <!--  <div class="row"> -->
                <!-- #2: Add a col to hold the summary table - have it take up half the row -->
                <div class="col-md-6">
                    <h2>Available Items</h2>
                    <form class="form-horizontal" role="form" id="item-form" name="item-form" action="selectItem" method="POST">
                    <input type="hidden" name="item-form-itemId" id="item-form-itemId" value="">
                    <div class="form-group" id="itemTableDiv">
                    <c:forEach var="currentItem" items="${itemList}">    
                        <div class="form-control-static col-md-4">
                        <div class="vendingItem" id="${currentItem.itemId}">
                            <div>${currentItem.itemId}</div>
                            <div id="item-${currentItem.itemId}-name">${currentItem.name}</div>
                            <div id="item-${currentItem.itemId}-price">$${currentItem.price}</div>
                            <div>Quantity Left: <span id="item-${currentItem.itemId}-qty">${currentItem.quantity}</span></div>
                        </div>
                        </div>
                    </c:forEach>                             
                    </div>
                    </form>    

                </div> <!-- End col div -->
                <!--
                #4: Add col to hold the new contact form - have it take up the other half of the row
                -->
               <div class="col-md-6">
                    <div class="container" id="view">
                        <form class="form-horizontal" role="form" id="money-form" action="addMoney" method="POST">
                            <input type="hidden" name="amountToAdd" id="amountToAdd" value="0.00">
                            <input type="hidden" name="balance-message" id="balance-message" value="">
                            <input type="hidden" name="itemId" id="itemId" value="<c:out value='${itemId}' />">
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
                                        <div align='center' id='money-total'><c:out value="${balance}" /></div>
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
                            <form class="form-horizontal" role="form" id="make-purchase-form" name="make-purchase-form" action="purchaseItem" method="POST">
                                <input type="hidden" name="make-purchase-message" id="make-purchase-message" value="<c:out value='${message}' />">
                                <div class="row">
                                     <div class="form-control-static">  
                                         <div class="col-md-6">
                                           <h2 align='center'>Messages</h2> 
                                         </div>
                                     </div>
                                </div>  
                                <div class="row">
                                   <div class="form-control-static col-md-6">
                                      
                                           <input type="text"  size="50" name='messages' id='messages' value="<c:out value='${message}' />">
                                            
                                      
                                   </div>
                                </div>  
                                <div class="row">   
                                   <div class="form-control-static">
                                    <div class="col-md-6">
                                        <label for="item">Item</label>
                                        <input type="text" id="item" name="item" value="<c:out value='${itemId}' />">
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
                            <form class="form-horizontal" role="form" name="change-return-form" id="change-return-form" action="changeReturn" method="POST">
                                  <input type="hidden" name="changeMsg" id="changeMsg" value="">
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
                                      
                                           <input type="text"  id='change' value="<c:out value='${changeMessage}' />">
                                      
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
