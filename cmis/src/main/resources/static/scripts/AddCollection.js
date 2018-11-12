$(document).ready(function(){



    $('#addCollecSave').on('click', function(event){


        var collecType = $('#collecType').val();
        var fullName = $('#fullName').val();
        var accronym = $('#accronym').val();
        var backUp = $('#backUP').val();
        var serviceType = $('#serviceType').val();
        var status = $('#status').val();
        var organizations = $('#organization').val();
        var desOrgs = $('#desOrganizations').val();

        if(collecType==='' || fullName==='' || accronym==='' || backUp==='' || serviceType==='' || status==='' || organizations==='' || desOrgs===''){
            event.preventDefault();
            document.getElementById('errorMsg').style.display = 'block';
        }
        console.log(desOrgs);

    });

});
