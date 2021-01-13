/**
 * 
 */
$(function(){
	$("#form_index").validate({
       rules: {
             input_email:{
                    required:true,
					email: true
             },
             input_senha:{
                    required:true
             }
       },
       messages: {
             input_email:{
                    required: "Digite o e-mail.",
					email: "E-mail inválido."
             },
             input_senha:{
                    required: "Digite a senha."
             }     
       }
	})
});

/*$(document).ready(function(){

	$('#input_entrar').click(function() {   

		var email = $('#input_email').val();
      	var senha = $('#input_senha').val();

      	$.ajax({
			type: "POST",
			url: "login",
			data: {"email": email, "senha": senha},
			success: function (data) {
				if (data == 'true'){
					let url = 'logado.jsp';
                	$(location).attr('href', url);
              	}else{
                	  alert('Par e-mail/senha inválido.');
            	}
        	}
    	});       
	});
});*/