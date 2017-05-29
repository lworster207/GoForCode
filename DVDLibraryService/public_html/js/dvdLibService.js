/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    



var dvdServiceURL = "http://localhost:8080";

    $.ajax({
    type: "GET",
    url: dvdServiceURL + "/dvd/0",
    success: function (dvd) {
        var dvdContent = $("#dvdContent");
        
        
        alert("Data: + " + dvd.title);
    },
    error:function () {
        alert("FAILURE!");
    }});


});

