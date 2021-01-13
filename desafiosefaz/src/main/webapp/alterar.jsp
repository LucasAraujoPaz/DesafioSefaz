<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.lucaspaz.desafiosefaz.entity.Usuario" %>
<%@ page import="br.com.lucaspaz.desafiosefaz.entity.Telefone" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		<title>Desafio Sefaz</title>
	</head>
<body>
	<h1>Desafio Sefaz - Alterar</h1>
	
	    <form id="form_cadastro" name="form_cadastro" method="post" action="usuarios">

	    	<% Usuario usuario = (Usuario) request.getAttribute("usuario");
	    	
	    	List<Telefone> telefones = usuario.getTelefones();
	    	
	    	String primeiroTelefone = telefones.isEmpty()? "" 
	    			: ("(" + telefones.get(0).getDdd() + ") " + telefones.get(0).getNumero());
	    	
	    	int numeroDeTelefones = telefones.size(); %>
    	
	    	<input type="hidden" name="input_id" value="<%= usuario.getId() %>">    	
	    	
	    	<table>
			    <tr>
			        <th>Nome*: </th>
			        <td><input id="input_nome" name="input_nome" type="text" value="<%= usuario.getNome() %>" maxlength="32" required="required"/></td>
			    </tr>
			    <tr>
			        <th>E-mail*: </th>
			        <td><input id="input_email" name="input_email" type="email" value="<%= usuario.getEmail() %>" required="required" disabled="disabled"/></td>
			    </tr>
			    <tr>
			        <th>Senha*: </th>
			        <td><input id="input_senha" name="input_senha" type="password" value="<%= usuario.getSenha() %>" required="required"/></td>
			    </tr>
		 	</table>

		 	<table id="table_telefones">
				<tr id="tr_telefone1">
					<th>Telefone 1*:</th>
			        <td><input id="input_telefone1" name="input_telefone1" class="telefone" type="tel" placeholder="Ex.: (00) 00000-0000" minlength="14" maxlength="15" required="required" 
			        value="<%= primeiroTelefone %>" >
						Celular:<input id="input_celular1" type="radio" name="tipo1" checked="checked" value="celular" />
						Fixo:<input id="input_fixo1" type="radio" name="tipo1" value="fixo" />
						</td>
			    </tr>
			    <% for (int i = 2; i <= numeroDeTelefones; i++) { 
			    	
			    	Telefone telefoneDaVez = telefones.get(i-1); 
			    	String celularChecked = (telefoneDaVez.getTipo().equalsIgnoreCase("celular"))? "checked='checked'" : "";
			    	String fixoChecked = (telefoneDaVez.getTipo().equalsIgnoreCase("fixo"))? "checked='checked'" : "";%>
			    	
				    <tr id="tr_telefone<%= i %>">
					   <th>Telefone <%= i %>: </th>
				        <td><input id="input_telefone<%= i %>" name="input_telefone<%= i %>" class="telefone" type="tel" placeholder="Ex.: (00) 00000-0000" required="required" minlength="14" maxlength="15"
				         value="<%= "(" + telefoneDaVez.getDdd() + ") " + telefoneDaVez.getNumero() %>" >
				        	Celular:<input id="input_celular<%= i %>" type="radio" name="tipo<%= i %>" <%= celularChecked %> value="celular" />
							Fixo:<input id="input_fixo<%= i %>" type="radio" name="tipo<%= i %>" <%= fixoChecked %> value="fixo" />
			 	        </td>
			 	    </tr>
			  <%  } %>
<!--		    <tr id="tr_telefone2">
				   <th>Telefone 2: </th>
			        <td><input id="input_telefone2" name="input_telefone2" class="telefone" type="tel" placeholder="Ex.: (00) 00000-0000" required="required" minlength="14" maxlength="15" >
			        	Celular:<input id="input_celular2" type="radio" name="tipo2" checked="checked" value="celular" />
						Fixo:<input id="input_fixo2" type="radio" name="tipo2" value="fixo" />
		 	        </td>
		 	    </tr> ... -->
			</table>
			
			<input type="hidden" id="input_numero_de_telefones" name="input_numero_de_telefones" value="<%= numeroDeTelefones %>">
			
			<small>*Obrigatório</small><br>
			
			<button id="button_adicionar_telefone" name="button_adicionar_telefone" type="button">Adicionar telefone</button>
			<button id="button_remover_telefone" name="button_remover_telefone" type="button">Remover telefone</button><br>

			<input id="input_alterar" type="submit" name="input_alterar" value="Alterar"/>
			<a href="usuarios" ><button id="button_cancelar" type="button" name="button_cancelar">Cancelar</button></a>
		</form>


		<script src="js/alterar.js"></script>		
		<script src="http://code.jquery.com/jquery-1.11.1.js"></script>
		<script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
		<script src="https://github.com/igorescobar/jQuery-Mask-Plugin/blob/master/dist/jquery.mask.min.js"></script>
		<script src="js/validacao-alterar.js"></script>
</body>
</html>