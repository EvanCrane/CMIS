$(document).ready(function(){


    document.getElementById('addUser').on('click', function(event){
        event.preventDefault();
        document.getElementById('contactsModal').modal();

    });

    $('.editForm #addUser').on('click', function (event) {
        event.preventDefault();
       $('.contactFormModal #contactsModal').modal();

    });
    $('.editForm .relationships').on('click', function (event) {
        event.preventDefault();


        $('.relationshipForm #exampleModal').modal();
    });

    $('.editForm .editors').on('click', function (event) {
        event.preventDefault();


        $('.editorModal #theEditorModal').modal();
    });
});