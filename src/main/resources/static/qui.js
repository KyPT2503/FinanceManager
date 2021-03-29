// $(document).ready(function () {
//     jQuery("#btn").click(function(){
//         $.ajax({
//             url: 'controller.php',
//             type: 'POST',
//             data: 'string=' + data_test,
//             success: function (data) {
//                 setTimeout(function(){
//                     $('#demo-ajax').html(data);
//                 }, 3000);
//             },
//             error: function (e) {
//                 console.log(e.message);
//             }
//         });
//     });

//     function createLimit() {
//         const limit = {
//             startDay: document.getElementById("startDate").value,
//             endDay: document.getElementById("endDate").value,
//             money: document.getElementById("money").value,
//         }
//         $.ajax('http://localhost:8080/user/balance/create',
//             {
//                 dataType: 'json',
//                 timeout: 500,
//                 type: 'POST',
//                 contentType: 'application/json',
//                 data:JSON.stringify(limit),
//                 // success: loadTables,
//                 // error:function (jqXhr, textStatus, errorMessage) { // error callback
//                 //     console.log(jqXhr)
//                 // }
//             });
//     }
// });

$(document).ready(function() {
    $('#myModal').submit(function(event) {
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();
        var money = $('#money').val();
        var json = { "startTime" : startTime, "endTime" : endTime, "money": money};
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type : "POST",
            data: JSON.stringify(json),
            url: "/user/balance/create",
            success: function(smartphone) {
                var respContent = "";
                respContent += "<span class='success'>Smartphone was created: [";
                respContent += smartphone.producer + " : ";
                respContent += smartphone.model + " : " ;
                respContent += smartphone.price + "]</span>"
                $("#sPhoneFromResponse").html(respContent);
            }
        });
        event.preventDefault();
    });
});
