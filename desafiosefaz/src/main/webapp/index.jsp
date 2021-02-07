<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <title>Desafio Sefaz</title>
    </head>
    <body>
        <h1>Desafio Sefaz - Login</h1>
        <form id="form_index" name="form_index" method="post" action="login">
            <table>
                <tr>
                    <th>E-mail: </th>
                    <td><input id="input_email" name="input_email" type="email" value="" required="required"/></td>
                </tr>
                <tr>
                    <th>Senha: </th>
                    <td><input id="input_senha" name="input_senha" type="password" value="" required="required"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input id="input_entrar" name="input_entrar" type="submit" value="Entrar"/>
                    </td>
                </tr>
                <tr> 
                	<td colspan="2"><a id="a_cadastro" href="cadastrar?acao=cadastrar">Não tem cadastro? Registre-se</a></td>
                </tr>
            </table>
            <input type="hidden" id="input_login" name="input_login" value="login">
        </form>
		<script src="http://code.jquery.com/jquery-1.11.1.js"></script>
		<script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
       <script src="js/validacao-index.js"></script>
    </body>
</html>