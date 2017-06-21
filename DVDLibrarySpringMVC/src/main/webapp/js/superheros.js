/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var superheroServiceURL = "http://localhost:8080";

$(document).ready(function () {
    loadItems();
    
    // Add Button onclick handler
    $('#add-dollar-button').click(function (event) {
    
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
        
        var balance = $("#money-total");
        var change = $("#change");
    
        var balanceValue = parseFloat(balance.text()) * 100;
        
        var changeString = "";
 
        var numQuarters = parseInt(balanceValue / 25);
        if ( numQuarters > 0 ) {
            changeString += numQuarters + " Quarter";
            if ( numQuarters > 1 ) {
                 changeString += "s";
            }
            balanceValue -= (numQuarters * 25);
        }
     
        var numDimes = parseInt(balanceValue / 10);
        if ( numDimes > 0 ) {
            changeString += " " + numDimes + " Dime";
            if ( numDimes > 1 ) {
                 changeString += "s";
            }
            balanceValue -= (numDimes * 10);
        }
      
        var numNickels = parseInt(balanceValue / 5);
        if ( numNickels > 0 ) {
            changeString += " " + numNickels + " Nickel";
            if ( numNickels > 1 ) {
                 changeString += "s";
            }            
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
        var itemId = itemRef.val();
            
        var itemQtyId = "#item-" + itemRef.val() + "-qty";
        var qty = parseInt($(itemQtyId).text());
        
        var change = $("#change");
        change.val(""); 
        
        var balance = $("#money-total");
        
        vendItem(balance.text(),itemId);
    });

    
    $(document).on('click','.vendingItem', function (event) {
        
        var message = $("#messages");
        // display the selected item id in item field
        var itemRef = $("#item");
        itemRef.val($(this).attr("id")); 
        var itemQtyId = "#item-" + $(this).attr("id") + "-qty";
        var qty = parseInt($(itemQtyId).text());
        
        var change = $("#change");
        change.val("");  
       
       if ( qty > 0 ) {
           // reference the total amount deposited
           var balance = $("#money-total");
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
          message.val("Please deposit $" + shortage.toFixed(2));        
       }
       else {
            message.val("");
       }
}

function vendItem(balance, id) {

        var change = $("#change");
        var message = $("#messages");
        
         $('#errorMessages').empty();
        
        $.ajax ({
        type: 'GET',
        url:  vendingServiceURL + "/money/" + balance + "/item/" + id,
        success: function (data, status) {
            var quarters = data.quarters;
            var dimes = data.dimes;
            var nickels = data.nickels;
            var pennies = data.pennies;
            
            var changeString = "";
            
            if ( quarters > 0 ) {
               changeString = quarters + " Quarter";
               if ( quarters > 1) {
                   changeString += "s";
               }
            }
            if ( dimes > 0 ) {
               changeString +=  " " + dimes + " Dime";
               if ( dimes > 1) {
                   changeString += "s";
               }
            }                              
            if ( nickels > 0 ) {
               changeString +=  " " + nickels + " Nickel";
               if ( nickels > 1) {
                   changeString += "s";
               }
            } 
            if ( pennies > 0 ) {
               changeString +=  " " +  pennies + " ";
               if ( pennies > 1) {
                   changeString += "Pennies";
               }
               else {
                  changeString += "Penny"; 
               }
            } 
            change.val(changeString);
            message.val("Thank You!!");
            
           var itemQtyId = "#item-" + id + "-qty";
           var qty = parseInt($(itemQtyId).text());
           $(itemQtyId).text(qty-1);
           var balance = $("#money-total");
           balance.text("0.00");
            
        },
        error: function(jqXHR) {
            var errMsg = jqXHR.responseText.split('"');
            message.val(errMsg[3]);
        }
    });
    
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
            
            var row = "";
            $.each(data, function (index, item) {
                var name = item.name;
                var price = item.price;
                var id = item.id;
                var qty = item.quantity;

                row += '                 <div class="form-control-static col-md-4">';
                row += '                    <div class="vendingItem" id="' + id + '">';
                row += '                        <div>' + id + '</div>';
                row += '                        <div id="item-' + id + '-name">' + name + '</div>';
                row += '                        <div id="item-' + id + '-price">$' + price + '</div>'
                row += '                        <div>Quantity Left: <span id="item-' + id + '-qty">' + qty + '</span></div>';
                row +='                    </div>';
                row += '                </div>';
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
    var contentRows = $('#itemTableDiv');
    contentRows.empty();
}
