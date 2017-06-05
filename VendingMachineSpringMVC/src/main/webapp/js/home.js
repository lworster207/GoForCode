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
    
        var balance = $("#money-total");
        var item = $("#item");
        var itemId = $("#itemId");
        
        var numToAdd = parseFloat("1.00");
        var balanceValue = parseFloat(balance.text());
        
        var newBalance = ( numToAdd  + balanceValue).toFixed(2);
        balance.text(newBalance);
        reSelectItem();
        itemId.val(item.val());
        $("#amountToAdd").val("1.00");
        $("#money-form").submit();

    });

    $('#add-quarter-button').click(function (event) {
        var balance = $("#money-total");
        var item = $("#item");
        var itemId = $("#itemId");
        
        var numToAdd = parseFloat("0.25");
        var balanceValue = parseFloat(balance.text());
        
        var newBalance = ( numToAdd  + balanceValue).toFixed(2);
        balance.text(newBalance);
        reSelectItem();
        itemId.val(item.val());
        $("#amountToAdd").val("0.25");
        $("#money-form").submit();
    });

    $('#add-dime-button').click(function (event) {
        var balance = $("#money-total");
        var item = $("#item");
        var itemId = $("#itemId");    
        var numToAdd = parseFloat("0.10");
        var balanceValue = parseFloat(balance.text());
        
        var newBalance = ( numToAdd  + balanceValue).toFixed(2);
        balance.text(newBalance);
       reSelectItem();
       itemId.val(item.val());
       $("#amountToAdd").val("0.10");
       $("#money-form").submit();
    });

    $('#add-nickel-button').click(function (event) {
        var balance = $("#money-total");
        var item = $("#item");
        var itemId = $("#itemId");
        
        var numToAdd = parseFloat("0.05");
        var balanceValue = parseFloat(balance.text());
        
        var newBalance = ( numToAdd  + balanceValue).toFixed(2);
        
        balance.text(newBalance);
        reSelectItem();
        itemId.val(item.val());
        $("#amountToAdd").val("0.05");
        $("#money-form").submit();        

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
   
        $("#changeMsg").val(changeString);
        $("#change-return-form").submit();
        
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
        
        //vendItem(balance.text(),itemId);
        $("#make-purchase-form").submit();
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
       var balanceMsg = $("#balance-message");
       if ( balanceValue < cost ) {
          var shortage = (cost - balanceValue)/100;
          message.val("Please deposit $" + shortage.toFixed(2));
          balanceMsg.val(message.val());
          
       }
       else {
            message.val("");
            balanceMsg.val(message.val());
       }
}

function vendItem(balance, id) {

        var change = $("#change");
        var message = $("#messages");
        
        $('#errorMessages').empty();
        

    
}


function loadItems() {
    // we need to clear the previous content so we don't append to it
    clearItemsTable();

    // grab the the tbody element that will hold the rows of contact information
    var contentRows = $('#itemTableDiv');


}

function clearItemsTable() {
    var contentRows = $('#itemTableDiv');
    contentRows.empty();
}
