package br.com.lucaspaz.desafiosefaz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.lucaspaz.desafiosefaz.services.ServletService;

@WebServlet("/cadastrar")
public class CadastrarController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ServletService servletService = new ServletService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("CadastrarServlet get is called.");
	    
	    servletService.renderizarPaginaCadastrar(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("CadastrarServlet post is called.");

		request.setAttribute("tipo_de_adicao", "cadastrar");
	    servletService.adicionarUsuario(request, response);
	}
	
}