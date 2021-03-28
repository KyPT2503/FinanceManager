$(document).ready(function () {
    jQuery("#btn").click(function(){
        $.ajax({
            url: 'controller.php',
            type: 'POST',
            data: 'string=' + data_test,
            success: function (data) {
                setTimeout(function(){
                    $('#demo-ajax').html(data);
                }, 3000);
            },
            error: function (e) {
                console.log(e.message);
            }
        });
    });
});