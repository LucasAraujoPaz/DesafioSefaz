/**
 * 
 */
var table_telefones = document.getElementById("table_telefones");

var button_adicionar_telefone = document.getElementById("button_adicionar_telefone");
var button_remover_telefone = document.getElementById("button_remover_telefone");

var contadorTelefone = Number(document.getElementById("input_numero_de_telefones").value);

button_adicionar_telefone.addEventListener("click", function() {
	if (contadorTelefone < 7) {
		let telefone = document.createElement("tr");

		telefone.id = `tr_telefone${contadorTelefone+1}`;
		telefone.innerHTML = `<th>Telefone ${contadorTelefone+1}: </th>`
					 + `<td><input id="input_telefone${contadorTelefone+1}" name="input_telefone${contadorTelefone+1}" class="telefone" type="tel" placeholder="Ex.: (00) 00000-0000" required="required" minlength="14" maxlength="15">
				        	Celular:<input id="input_celular${contadorTelefone+1}" name="tipo${contadorTelefone+1}" type="radio" checked="checked" value="celular" />
							Fixo:<input id="input_fixo${contadorTelefone+1}" name="tipo${contadorTelefone+1}" type="radio" value="fixo" />
			 	        </td>`;
		table_telefones.appendChild(telefone);
		
		contadorTelefone++;
		document.getElementById("input_numero_de_telefones").value = contadorTelefone.toString();
	}
});

button_remover_telefone.addEventListener("click", function() {
	
	if (contadorTelefone > 1) {	
		
		let telefone = document.getElementById(`tr_telefone${contadorTelefone}`);
		telefone.parentNode.removeChild( telefone );
		
		contadorTelefone--;
		document.getElementById("input_numero_de_telefones").value = contadorTelefone.toString();
	}
});