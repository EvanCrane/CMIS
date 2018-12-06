$(document).ready(function(){

    //Open User Modal
    $('.addUserBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        document.getElementById('errorMsg').style.display = 'none';
        var href = $(this).attr('href');
        var text = $(this).text();



        if(text=='Edit'){
            $('.myForm #exampleModalLabel').text('Edit User');
            populateModal(href);
            $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #exampleModalLabel').text('Add User');
            $('.myForm #userIDinModal').val('');
            $('.myForm #username').val('');
            $('.myForm #organization').val('');
            $('.myForm #accessLvl').val('');

            var modalTitle = $('.myForm #exampleModal #exampleModalLabel').text();
            $('.myForm #exampleModal').modal();
            console.log(modalTitle);
            console.log('log works');
        }
    });

    var deleteHref;
    var deleteUser;

    $('.table .delBtn').on('click', function (event)
    {
        event.preventDefault();
        deleteHref = $(this).attr('href');
        deleteUser = $(this).attr('id');
        $('.deleteSubmit #deleteConfirm').modal();
        $('.deleteSubmit #deleteConfirm #confirmText').text('Are you sure you want to delete user ' + deleteUser+ "? ");
    });

    $('.deleteSubmit #deleteConfirm .deleteConBtn').on('click', function (event){
        event.preventDefault();
        console.log(deleteHref);
        $.ajax({
            type : "GET",
            url : deleteHref,
            success : function(){
                $('.deleteSubmit #deleteConfirm').modal('hide');
                location.reload();
            },
            error : function ()
            {
                alert("Delete didn't work");
            }
        });
    });


    // populates the user modal
    function populateModal(href){
        $.ajax({
            type : "GET",
            url : href,
            dataType: "json",
            success : function (aUser) {
                $('.myForm #username').val(aUser.userName);
                $('.myForm #organization').val(aUser.organization);
                $('.myForm #userIDinModal').val(aUser.userId);
                $('.myForm #accessLvl').val(aUser.accessLvl);
            },
            error : function(e){
                $('.myForm #username').val('No user found');
            }
        });
    };



    //Save User Modal
    $('.myForm #exampleModal #userSaveModal').on('click', function (event){
        event.preventDefault();

        //th:href="@{/findOne(id=${user.getUserId()})}"



        var userName = $('.myForm #username').val();
        if(userName===''){userName = 'none'};

        //if (userName===''){console.log("empty user name");}else{console.log("valid user Name");}

        var organization = $('.myForm #organization').val();
        if(organization===''){organization = 'none'};

        var password = $('.myForm #password').val();
        if(password===''){password = 'none'};

        var userID = $('.myForm #userIDinModal').val();
        if(userID===''){userID = -1;}else{userID = parseInt(userID, 10);}
        console.log(userID);

        var level = $('.myForm #accessLvl').val();
        console.log(level);

        var modalTitle = $('.myForm #exampleModal #exampleModalLabel').text();
        console.log(modalTitle);
        console.log('log works');

        if(organization==="none" || userName==="none" || level==="none" || (password === "none" && modalTitle==="Add User"))
        {

            document.getElementById('errorMsg').style.display = 'block';

            return;
        }

        console.log("/updateUser?id=" + userID + "?name=" + userName + "?org=" + organization + "?pass=" + password);

        //(@RequestParam("id") int userId, @RequestParam("name") String userName, @RequestParam("org") String organization, @RequestParam("pass") String unEncPass)
        $.ajax({
            type : "POST",
            url : "/updateUser?id=" + userID + "&name=" + userName + "&org=" + organization + "&pass=" + password + "&lvl=" + level,
            success : function(data){
                if(data===true){
                    $('.myForm #exampleModal').modal('hide');
                    location.reload();
                }
            },
            error : function (){

            }
        });
    });
});