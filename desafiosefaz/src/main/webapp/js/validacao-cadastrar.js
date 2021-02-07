/**
 * 
 */
$(function(){

	var maskBehavior = function (val) {
	  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
	},
	options = {onKeyPress: function(val, e, field, options) {
	        field.mask(maskBehavior.apply({}, arguments), options);
	    }
	};
	
	$('.telefone').mask(maskBehavior, options);

	$("#form_cadastrar").validate({
       rules: {
			input_nome: {
				required: true,
			},
            input_email: {
            	required:true,
				email: true
            },
            input_senha: {
            	required:true
            },
			input_telefone1: {
				required: true,
				minlength: 14
			},
			input_telefone2: {
				required: false,
				minlength: 14
			}
       },
       messages: {
			input_nome: {
				required: "Digite o nome."
			},			
            input_email: {
            	required: "Digite o e-mail.",
				email: "E-mail inválido."
            },
            input_senha: {
            	required: "Digite a senha."
            },
			input_telefone1: {
				required: "Digite ao menos o telefone 1.",
				minlength: "Telefone inválido."
			},
			input_telefone2: {
				minlength: "Telefone inválido."
			}
		}
	})
});