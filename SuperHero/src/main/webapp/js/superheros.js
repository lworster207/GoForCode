/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var superheroServiceURL = "http://localhost:8080/SuperHero";

$(document).ready(function () {

    getSuperPowers();

    

    
    
}

    
function getSuperPowers() {
       $.ajax ({
        type: 'GET',
        url:  superheroServiceURL + "/superpowers/1",
        success: function (data, status) {

            alert(data.superPowerId + " " + data.description);
           $('#add-city').val(data.superPowerId);
            
        },
        error: function(jqXHR) {
            var errMsg = jqXHR.responseText.split('"');
            alert("error");
            //message.val(errMsg[3]);
        }
    });
}