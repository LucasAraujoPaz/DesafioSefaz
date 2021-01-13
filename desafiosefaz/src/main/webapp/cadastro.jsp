<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		<title>Desafio Sefaz</title>
	</head>
<body>
	<h1>Desafio Sefaz - Cadastro</h1>
	    <form id="form_cadastro" name="form_cadastro" method="post" action="index.jsp">
	    	<table>
			    <tr>
			        <th>Nome*: </th>
			        <td><input id="input_nome" name="input_nome" type="text" value="" maxlength="32" required="required" /></td>
			    </tr>
			    <tr>
			        <th>E-mail*: </th>
			        <td><input id="input_email" name="input_email" type="email" value="" required="required"/></td>
			    </tr>
			    <tr>
			        <th>Senha*: </th>
			        <td><input id="input_senha" name="input_senha" type="password" value="" required="required"/></td>
			    </tr>
			    <tr>
			        <th>Telefone 1*:</th>
			        <td><input id="input_telefone1" name="input_telefone1" class="telefone" type="tel" placeholder="Ex.: (00) 00000-0000" maxlength="15" required="required">
						Celular:<input id="input_celular1" type="radio" name="tipo1" checked="checked" />
						Fixo:<input id="input_fixo1" type="radio" name="tipo1" />
						</td>
			    </tr>
			    <tr>
			        <th>Telefone 2: </th>
			        <td><input id="input_telefone2" name="input_telefone2" class="telefone" type="tel" placeholder="Ex.: (00) 00000-0000" maxlength="15">
			        	Celular:<input id="input_celular2" type="radio" name="tipo2" checked="checked" />
						Fixo:<input id="input_fixo2" type="radio" name="tipo2" />
			        </td>
			    </tr>
				<tr><td><small>*Obrigatório</small></td></tr>
			    <tr>
			        <td colspan="2">
			            <input id="input_cadastrar" type="submit" name="input_cadastrar" value="Cadastrar"/>
			            <a href="index.jsp" ><button id="button_cancelar" type="button" name="button_cancelar">Cancelar</button></a>
			        </td>
			    </tr>
			</table>
		</form>
		<script src="http://code.jquery.com/jquery-1.11.1.js"></script>
		<script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
		<script src="https://github.com/igorescobar/jQuery-Mask-Plugin/blob/master/dist/jquery.mask.min.js"></script>
		<script src="js/validacao-cadastro.js"></script>
</body>
</html>