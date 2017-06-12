/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    
    //////////    Add Money 
    $('#add-dollar-button').click(function () {  
        $("#amountToAdd").val("1.00");
        $("#money-form").submit();

    });

    $('#add-quarter-button').click(function () {
        $("#amountToAdd").val("0.25");
        $("#money-form").submit();
    });

    $('#add-dime-button').click(function () {
       $("#amountToAdd").val("0.10");
       $("#money-form").submit();
    });

    $('#add-nickel-button').click(function () {
        $("#amountToAdd").val("0.05");
        $("#money-form").submit();        
    });

    /////////   Return Change
    $('#change-return-button').click(function () {
        $("#change-return-form").submit();       
    });
    
   
    /////////    Make Purchase
    $("#make-purchase-button").click(function () {
        $("#make-purchase-form").submit();
    });

    
    /////////    Select an item
    $(document).on('click','.vendingItem', function () {       
        $("#item-form-itemId").val($(this).attr("id"));
        $("#item-form").submit();
    });

});