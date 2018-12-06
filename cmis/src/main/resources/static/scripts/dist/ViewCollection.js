var deleteHref;
$('.delBtn').on('click', function (event)
{
    event.preventDefault();
    deleteHref = $(this).attr('href');
    $('.deleteSubmit #deleteConfirm').modal();
    $('.deleteSubmit #deleteConfirm #confirmText').text('Are you sure you want to delete this collection?');
});
function loadPage(href)
{
    console.log("now here");
    window.location = href;
    return false;
}

$('.deleteSubmit #deleteConfirm .deleteConBtn').on('click', function (event){
    event.preventDefault();
    console.log( $(this).attr('href'));
    var homeHref = $(this).attr('href');
    console.log("here");
    console.log(deleteHref);
    $.ajax({
        type : "GET",
        url : deleteHref,
        success : function(){
            $('.deleteSubmit #deleteConfirm').modal('hide');
            console.log( homeHref);
            loadPage(homeHref);
        },
        error : function ()
        {
            alert("Delete didn't work");
        }
    });
});
