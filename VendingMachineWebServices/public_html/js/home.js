/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var vendingServiceURL = "http://localhost:8080";

$(document).ready(function () {
    


    //loadItems();
    
    

    // Add Button onclick handler
    $('#add-dollar-button').click(function (event) {
       // alert("add dollar");
    
        var balance = $("#money-total");
        
        var numToAdd = parseFloat("1.00");
        var balanceValue = parseFloat(balance.text());
        
        var newBalance = ( numToAdd  + balanceValue).toFixed(2);
        
        
        balance.text(newBalance);
        reSelectItem();
        
    });

    $('#add-quarter-button').click(function (event) {
      
    
        var balance = $("#money-total");
    
        var numToAdd = parseFloat("0.25");
        var balanceValue = parseFloat(balance.text());
        
        var newBalance = ( numToAdd  + balanceValue).toFixed(2);
        
        
        balance.text(newBalance);
        reSelectItem();
    });

    $('#add-dime-button').click(function (event) {
       
    
        var balance = $("#money-total");
    
        var numToAdd = parseFloat("0.10");
        var balanceValue = parseFloat(balance.text());
        
        var newBalance = ( numToAdd  + balanceValue).toFixed(2);
        
        
        balance.text(newBalance);
        reSelectItem();
    });

    $('#add-nickel-button').click(function (event) {
     
    
        var balance = $("#money-total");
    
        var numToAdd = parseFloat("0.05");
        var balanceValue = parseFloat(balance.text());
        
        var newBalance = ( numToAdd  + balanceValue).toFixed(2);
        
        
        balance.text(newBalance);
        reSelectItem();
    });
    
    $('#change-return-button').click(function (event) {
       // return change
    
        var balance = $("#money-total");
        var change = $("#change");
    
        var balanceValue = parseFloat(balance.text()) * 100;
        
        var changeString = "";
       // alert(balanceValue);
        var numQuarters = parseInt(balanceValue / 25);
        if ( numQuarters > 0 ) {
            changeString += numQuarters + " Quarters";
            balanceValue -= numQuarters * 25;
        }
        // alert(balanceValue);      
        var numDimes = parseInt(balanceValue / 10);
        if ( numDimes > 0 ) {
            changeString += " " + numDimes + " Dimes";
            balanceValue -= (numDimes * 10);
        }
        //alert(balanceValue);       
        var numNickels = parseInt(balanceValue / 5);
        if ( numNickels > 0 ) {
            changeString += " " + numNickels + " Nickels";
            balanceValue -= (numNickels * 5);
        }
             
        change.val(changeString); 
        balance.text("0.00");
        
        var itemRef = $("#item");
        itemRef.val("");
        
        var message = $("#messages");
        message.val("");
   
    });
    
    $("#make-purchase-button").click(function (event) {
       // reference the total amount deposited
    
        var message = $("#messages");
        // get the selected item id in item
        var itemRef = $("#item");
        var itemQtyId = "#item-" + itemRef.val() + "-qty";
        var qty = parseInt($(itemQtyId).text());
        
        var change = $("#change");
        change.val("");   
       
       if ( qty > 0 ) {    
        
           var balance = $("#money-total");
           // reference the change field


            var itemRef = $("#item");
            var itemId = itemRef.val();



           // create the id for the selected item
           var itemCostId = "#item-" + itemId + "-price";
           var itemQtyId = "#item-" + itemId + "-qty";

           var itemPrice = $(itemCostId);
           // turn the amount deposited into pennies
           var balanceValue = parseFloat(balance.text()) * 100;  


           // turn the item cost into pennies
           var cost= parseFloat(itemPrice.text().substring(1)) * 100;
           //alert(balanceValue + " " + cost);

           var qty = parseInt($(itemQtyId).text());

           if ( balanceValue >= cost ) {

              $(itemQtyId).text(qty-1);

              var surplus = (balanceValue - cost)/100; 
              balance.text(surplus.toFixed(2));
              $('#change-return-button').click();
              message.val("Thank You!");      
           }
        }
        else {
          message.val("Sold Out!");   
        }
       
    });

    
    $(".vendingItem").click(function (event) {


        
        var message = $("#messages");
                  // display the selected item id in item
        var itemRef = $("#item");
        itemRef.val($(this).attr("id")); 
        var itemQtyId = "#item-" + $(this).attr("id") + "-qty";
        var qty = parseInt($(itemQtyId).text());
        
        var change = $("#change");
        change.val("");  
       
       if ( qty > 0 ) {

           // reference the total amount deposited
           var balance = $("#money-total");
           // reference the change field


           // create the id for the selected item
           var itemCostId = "#item-" + $(this).attr("id") + "-price";
           var itemPrice = $(itemCostId);
           // turn the amount deposited into pennies
           var balanceValue = parseFloat(balance.text()) * 100;  


           // turn the item cost into pennies
           var cost= parseFloat(itemPrice.text().substring(1)) * 100;







           updateDepositMessage(balanceValue,cost);
       }
       else {
           message.val("Sold Out!");
       }

   
      // alert($(this).attr("id") + " " + cost);
         
    });

});

function reSelectItem() {
        // called after entering more funds, to update messages
        var itemRef = $("#item");
        var itemRefId = "#" + itemRef.val();
        $(itemRefId).click();
}
    
function updateDepositMessage(balanceValue,cost) {
       var message = $("#messages");
       
       
      
       if ( balanceValue < cost ) {
        
          var shortage = (cost - balanceValue)/100;
             //alert($(this).attr("id") + " " + cost + " " + shortage);
              message.val("Please deposit $" + shortage.toFixed(2));        
       }
       else {
            message.val("");
       }
}


function loadItems() {
    // we need to clear the previous content so we don't append to it
    clearItemsTable();

    // grab the the tbody element that will hold the rows of contact information
    var contentRows = $('#itemTableDiv');

    $.ajax ({
        type: 'GET',
        url:  vendingServiceURL + "/items",
        success: function (data, status) {
                //alert(data.length);
            
            var row = "";
            
            $.each(data, function (index, item) {

                var name = item.name;
                var price = item.price;
                var id = item.id;
                var qty = item.quantity;

                if ( index === 0 || (index%3 === 0) ) {
                    // starting a new row
                    //row += '<tr>';
                    row += '<div class="row" >';                
                }
               // row += '<td>';
 
                row += ' <div class="form-control-static vendingItem" id="item-' + id + '">';                
                row += '   <div class="col-md-4">';
                //row += '     <div class="container">';
                //row += '     <div class=" vendingItem" id="item-' + id + '">';
                row += '     <p>' + index+1;
                row += '     <p>' + name;
                row += '     <p>' + price;
                row += '     <p>Quantity Left: ' + qty;
                row += '   </div>';
                row += ' </div>'
               // row += '</td>';  // end of column div
                    
                if ( index === data.length || (index+1)%3 === 0)  {
                    // end of the current row
                    row += '</div>';  // end or row div
                }                    
                    
                
            });
           
            contentRows.append(row);
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });
}

function clearItemsTable() {
    $('#contentRows').empty();
}
