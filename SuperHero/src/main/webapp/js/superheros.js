/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var superheroServiceURL = "http://localhost:8080/SuperHero";

$(document).ready(function () {

    $('#cancel').click(function ()  {  

        //window.location = "http://localhost:8080/SuperHero/heros.jsp";
        history.back();
    });
});

function goBack() {
       $.ajax ({
        type: 'GET',
        url:  superheroServiceURL + "/displaySuperHeros",
        success: function (data, status) {
    
            
        },
        error: function(jqXHR) {
            var errMsg = jqXHR.responseText.split('"');
            message.val(errMsg[3]);
        }
    });
}