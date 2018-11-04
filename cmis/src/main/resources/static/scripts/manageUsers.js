$(document).ready(function(){


    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function(user, status, jqObj){
           $('.myForm #username').val(user.userId);
           $(' .myForm #organization').val(user.organization);
           $('.myForm #password').val('');

        });

        // function populateModal(userId){
        //     $.ajax({
        //         type : "GET",
        //         url : href
        //         data : {
        //             userId : userId
        //         },
        //         success : function () {
        //             $('.myForm #username').val(user)
        //
        //         }
        //     })
        // }

        $('.myForm #exampleModal').modal();
    });
});