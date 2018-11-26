$(document).ready(function(){



    // Load Contacts Modal
    $('#addUser, #editContactBtn').on('click', function (event) {
        event.preventDefault();
        var text = $(this).text();

        if(text === 'Edit')
        {
            $('#contactsModalLabel').text('Edit Contact');
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
        if(text === 'Edit')
        {
            $('#vendorLoadLabel').text('Edit Vendor Product');
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
        if(text === 'Edit')
        {
            $('#subObjLabel').text('Edit SubObject');
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
        if(text == 'Edit')
        {
            $('#relationshipModalLabel').text('Edit Relationship');
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
        if(text == 'Edit')
        {
            $('#editorModalLabel').text('Edit Editor');
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


});