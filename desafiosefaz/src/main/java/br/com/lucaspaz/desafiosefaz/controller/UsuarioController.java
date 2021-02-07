package br.com.lucaspaz.desafiosefaz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.lucaspaz.desafiosefaz.services.ServletService;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;    
	
	private ServletService servletService = new ServletService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------ UsuarioServlet get is called.");

		String editarUsuarioId = request.getParameter("edit");
		String excluirUsuarioId = request.getParameter("delete");
		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("incluir")) {
			servletService.renderizarPaginaIncluir(request, response);
			return;
		}
		
		if (editarUsuarioId != null) {
			servletService.renderizarPaginaAlterar(request, response, editarUsuarioId);
			return;
		}
		
		if (excluirUsuarioId != null) {
			servletService.deletarUsuario(request, response);
			return;
		}

		servletService.renderizarTodosOsUsuarios(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------ UsuarioServlet post is called.");
		
		String idUsuario = request.getParameter("input_id");		
		
		if (idUsuario != null) {
			servletService.alterarUsuario(request, response);
			return;
		} 
		
		request.setAttribute("tipo_de_adicao", "incluir");
		servletService.adicionarUsuario(request, response);

	}

}