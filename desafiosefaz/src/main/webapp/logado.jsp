<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.lucaspaz.desafiosefaz.entity.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		<title>Desafio Sefaz</title>
	</head>
	<body>
 		<h1 align="center" >Desafio Sefaz</h1>
<!-- 		<button id="button_consultar" name="button_consultar">Consultar usuários</button> -->
<!-- 		<a id="a_incluir" href="incluir.jsp"><button id="button_incluir">Incluir</button></a><p></p> -->
<!--         <form id="form_alterar" name="form_alterar" method="post" action="alterar.jsp"> -->
<!-- 			<input id="input_alterar" name="input_alterar" type="submit" value="Alterar"> -->
<!-- 			<p><label for="select_usuarios">Usuários</label><br> -->
<!-- 			<select id="select_usuarios" name="select_usuarios" size="10" required="required"> -->
				
<!-- 			</select></p> -->
<!-- 		</form> -->
<!-- 		<button id="button_remover">Remover</button> -->
<!-- 		<a id="a_sair" href="index.jsp">Sair</a><p></p>		 -->
		
		<div class="container">
		
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nome</th>
						<th>E-mail</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Usuario> listUsuario = (List<Usuario>) request.getAttribute("listUsuarios"); 
						
						for (Usuario usuario : listUsuario) {
							%> 
							<tr>
								<td><%= usuario.getNome() %></td>
								<td><%= usuario.getEmail() %></td>					
								<td>
									<a style="background: blue; color: white; padding: 5px; display:inline-block;" href="usuarios?edit=<%= usuario.getId() %>">Editar</a>
									<a style="background: red; color: white; padding: 5px; display:inline-block;" href="usuarios?delete=<%= usuario.getId() %>">Excluir</a>
								</td>	
							</tr>		 		
							<%						
						}
					%> 
				</tbody>
			</table>
		<a id="a_incluir" href="usuarios?acao=incluir"><button id="button_incluir">Incluir</button></a>
		</div>

		<script src="http://code.jquery.com/jquery-1.11.1.js"></script>		
		<script src="js/validacao-logado.js"></script>
	</body>
</html>