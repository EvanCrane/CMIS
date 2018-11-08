$(document).ready(function(){


    $('.editForm .relationships').on('click', function (event) {
        event.preventDefault();


        $('.relationshipForm #exampleModal').modal();
    });

    $('.editForm .editors').on('click', function (event) {
        event.preventDefault();


        $('.editorModal #theEditorModal').modal();
    });
});