$(document).ready(function(){



    // Load Contacts Modal
    $('#addUser, #editContactBtn').on('click', function (event) {
        event.preventDefault();
        var text = $(this).text();
        var href = $(this).attr('href');

        if(text === 'Edit')
        {
            $('#contactsModalLabel').text('Edit Contact');
            populateContact(href);
            $('#contactsModal').modal();
        }
        else
        {
            $('#contactsModalLabel').text('Add Contact');
            $('#contactsModal').modal();
        }

        //Add logic of when to put information in the modal



    });
    //Delete Contact modal
    $('#deleteContactBtn').on('click', function (event){
        $('#deleteConfirmText').text('Are you sure you want to delete contact ?');
        $('#confirmDelCont').modal();

    });

    //Load Vendor Products Modal
    $('#editVendorBtn, #addVendorBtn').on('click', function(){
        var text = $(this).text();
        var href = $(this).attr('href');
        if(text === 'Edit')
        {
            $('#vendorLoadLabel').text('Edit Vendor Product');
            populateVendor(href);
            $('#vendorLoadModal').modal();
        }
        else
        {
            $('#vendorLoadLabel').text('Add Vendor Product');
            $('#vendorLoadModal').modal();
        }
    });

    //Delete Vendor Product modal
    $('#deleteVendorBtn').on('click', function(){
        $('#deleteConfirmVen').text('Are you sure you want to delete Vendor Product ?')
        $('#confirmDelVen').modal();
    });


    //SubObjects Load Modal
    $('#editSubObjBtn, #addSubObject').on('click', function () {
        var text = $(this).text();
        var href = $(this).attr('href');

        if(text === 'Edit')
        {
            $('#subObjLabel').text('Edit SubObject');
            populateSubObjects(href);
            $('#subObjectModal').modal();
        }
        else
        {
            $('#subObjLabel').text('Add SubObject');
            $('#subObjectModal').modal();
        }

    });

    //Delete SubOBject modal
    $('#deleteSubObjBtn').on('click', function(){
        $('#deleteConfirmSub').text('Are you sure you want to delete SubObject ?')
        $('#confirmDelSub').modal();
    });

    //Relationhip Load Modal
    $('#editRelationBtn, #addRelationship').on('click', function(){
        var text = $(this).text();
        var href = $(this).attr('href');

        if(text == 'Edit')
        {
            $('#relationshipModalLabel').text('Edit Relationship');
            populateRelationship(href);
            $('#relationshipModal').modal();
        }
        else
        {
            $('#relationshipModalLabel').text('Add Relationship');
            $('#relationshipModal').modal();
        }
    });

    //Delete Relationship modal
    $('#deleteRelationBtn').on('click', function(){
        $('#deleteConfirmRel').text('Are you sure you want to delete Relationship ?')
        $('#confirmDelRel').modal();
    });

    // Editor Load Modal
    $('#editEditorBtn, #addEditor').on('click', function(){
        var text = $(this).text();
        var href = $(this).attr('href');

        if(text == 'Edit')
        {
            $('#editorModalLabel').text('Edit Editor');
            populateEditors(href);
            $('#theEditorModal').modal();
        }
        else
        {
            $('#editorModalLabel').text('Add Editor');
            $('#theEditorModal').modal();
        }
    });

    //Delete Relationship modal
    $('#deleteEditorBtn').on('click', function(){
        $('#deleteConfirmEditor').text('Are you sure you want to delete editor ?')
        $('#confirmDelEditor').modal();
    });

    // Populate the Edit Contact modal
    function populateContact(aHref)
    {
        console.log('Gets to populate contact');
        $.ajax({
           type: "GET",
           url: aHref,
           dataType : "json",
           success : function (aContact) {
               console.log(aContact);
               $('#contactName').val(aContact.contName);
               $('#contactType').val(aContact.contType);
               $('#contactPhone').val(aContact.phoneNum);
               $('#contactLocation').val(aContact.location);

           },
           error : function(e) {
               console.log('Ajax fails');
               console.log(aHref);
               console.log(e);
           }
        });
    }

    //Save Contact Modal
    $('#contactSaveModal').on('click', function (event){
        event.preventDefault();

        //th:href="@{/findOne(id=${user.getUserId()})}"



        var conName = $('.contactFormModal #contactName').val();
        if(conName===''){conName = 'none'};

        //if (userName===''){console.log("empty user name");}else{console.log("valid user Name");}

        var type = $('.contactFormModal #contactType').val();
        if(type===''){type = 'none'};

        var phone = $('.contactFormModal #contactPhone').val();
        if(phone===''){phone = 'none'};

        var location = $('.contactFormModal #contactLocation').val();
        if(location===''){location = 'none'};
        console.log(userID);


        var modalTitle = $(' #contactsModalLabel').text();
        console.log(modalTitle);
        console.log('log works');

        if(conName==="none" || type==="none" || phone==="none" || (location === "none")) {

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

    //Save User Modal
    $('#submitVendor').on('click', function (event){
        event.preventDefault();

        //th:href="@{/findOne(id=${user.getUserId()})}"



        var vendorName = $('#vendorName').val();
        if(vendorName===''){vendorName = 'none'};

        //if (userName===''){console.log("empty user name");}else{console.log("valid user Name");}

        var version = $('#vendorVersion').val();
        if(version===''){version = 'none'};

        var classified = $('#vendorClassified').val();
        if(classified===''){classified = 'none'};

        var description = $('#vendorDescription').val();
        if(description===''){description= 'none'};
        console.log(userID);


        var modalTitle = $('#vendorLoadLabel').text();
        console.log(modalTitle);
        console.log('log works');

        if(version==="none" || vendorName==="none" || classified==="none" || (description === "none")) {

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

    //Save SubObjs Object Modal
    $('#saveSubObj').on('click', function (event){
        event.preventDefault();

        //th:href="@{/findOne(id=${user.getUserId()})}"



        var subclass = $('#subObjClassified').val();
        if(subclass===''){subclass = 'none'};

        //if (userName===''){console.log("empty user name");}else{console.log("valid user Name");}

        var subvers = $('#subObjVers').val();
        if(subvers===''){subvers = 'none'};

        var subtype = $('#subObjType').val();
        if(subtype===''){subtype = 'none'};

        var subname = $('#subObjName').val();
        if(subname===''){subname = 'none'};
        console.log(userID);


        var modalTitle = $(' #subObjLabel').text();
        console.log(modalTitle);
        console.log('log works');

        if(subclass==="none" || subvers==="none" || subtype==="none" || (subname === "none")) {

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

    //Save Contact Modal
    $('#contactSaveModal').on('click', function (event){
        event.preventDefault();

        //th:href="@{/findOne(id=${user.getUserId()})}"



        var conName = $('.contactFormModal #contactName').val();
        if(conName===''){conName = 'none'};

        //if (userName===''){console.log("empty user name");}else{console.log("valid user Name");}

        var type = $('.contactFormModal #contactType').val();
        if(type===''){type = 'none'};

        var phone = $('.contactFormModal #contactPhone').val();
        if(phone===''){phone = 'none'};

        var location = $('.contactFormModal #contactLocation').val();
        if(location===''){location = 'none'};
        console.log(userID);


        var modalTitle = $(' #contactsModalLabel').text();
        console.log(modalTitle);
        console.log('log works');

        if(conName==="none" || type==="none" || phone==="none" || (location === "none")) {

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

    //Save Relationships Modal
    $('#saverelationship').on('click', function (event){
        event.preventDefault();

        //th:href="@{/findOne(id=${user.getUserId()})}"



        var relval = $('#relationshipValue').val();
        if(relval===''){relval = 'none'};

        //if (userName===''){console.log("empty user name");}else{console.log("valid user Name");}

        var reltype = $('#relationshipTypes').val();
        if(reltype===''){reltype = 'none'};

        var modalTitle = $(' #relationshipModalLabel').text();
        console.log(modalTitle);
        console.log('log works');

        if(reltype==="none" || relval==="none") {

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

    //Save Editor Modal
    $('#editSave').on('click', function (event){
        event.preventDefault();

        //th:href="@{/findOne(id=${user.getUserId()})}"



        var editaccesslvl = $('#editorAccessLvl').val();
        if(editaccesslvl===''){editaccesslvl = 'none'};

        //if (userName===''){console.log("empty user name");}else{console.log("valid user Name");}

        var editname = $('#editorName').val();
        if(editname===''){editname = 'none'};

        var editphone = $('#editorPhoneNum').val();
        if(editphone===''){editphone = 'none'};

        var editloc= $('#editorLocation').val();
        if(editloc===''){editloc = 'none'};


        var modalTitle = $(' #editorModalLabel').text();
        console.log(modalTitle);
        console.log('log works');

        if(editaccesslvl==="none" || editname==="none" || editphone==="none" || (editloc === "none")) {

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

    function populateVendor(aHref){
        $.ajax({
            type : "GET",
            url : aHref,
            dataType: "json",
            success : function (aVendor) {
                $('#vendorName').val(aVendor.prodName);
                $('#vendorVersion').val(aVendor.version);
                $('#vendorDescription').val(aVendor.description);
                $('#vendorClassified').val(aVendor.classified);
            },
            error : function(e){
                console.log('Ajax fails');
                console.log(aHref);
                console.log(e);
            }
        });
    };

    function populateSubObjects(aHref){
        $.ajax({
            type : "GET",
            url : aHref,
            dataType: "json",
            success : function (aSubObject) {
                $('#subObjType').val(aSubObject.subType);
                $('#subObjName').val(aSubObject.subName);
                $('#subObjVers').val(aSubObject.version);
                $('#subObjClassified').val(aSubObject.classified);
            },
            error : function(e){
                console.log('Ajax fails');
                console.log(aHref);
                console.log(e);
            }
        });
    };

    function populateRelationship(aHref){
        $.ajax({
            type : "GET",
            url : aHref,
            dataType: "json",
            success : function (aRelationship) {
                $('#relationshipTypes').val(aRelationship.type);
                $('#relationshipValue').val(aRelationship.value);
            },
            error : function(e){
                console.log('Ajax fails');
                console.log(aHref);
                console.log(e);
            }
        });
    };

    function populateEditors(aHref){
        $.ajax({
            type : "GET",
            url : aHref,
            dataType: "json",
            success : function (aEditor) {
                $('#editorName').val(aEditor.userName);
                $('#editorLocation').val(aEditor.location);
                $('#editorPhoneNum').val(aEditor.phoneNum);
                $('#editorAccessLvl').val(aEditior.accessLvl);
            },
            error : function(e){
                console.log('Ajax fails');
                console.log(aHref);
                console.log(e);
            }
        });
    };


});
