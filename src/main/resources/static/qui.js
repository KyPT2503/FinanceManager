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
// });


function createLimit() {
    const limit = {
        startDay: document.getElementById("startDate").value,
        endDay: document.getElementById("endDate").value,
        money: document.getElementById("money").value,
    }
    $.ajax('http://localhost:8080/user/balance/create',
        {
            dataType: 'json',
            timeout: 500,
            type: 'POST',
            contentType: 'application/json',
            data:JSON.stringify(limit),
            // success: loadTables,
            // error:function (jqXhr, textStatus, errorMessage) { // error callback
            //     console.log(jqXhr)
            // }
        });
}